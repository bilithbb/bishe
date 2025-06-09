<template>
  <div>
    <header class="login-header">
      <div class="container2 m-top-20">
        <h1 class="logo">
          <RouterLink to="/">{{loginTitle}}</RouterLink>
        </h1>
        <RouterLink class="entry" to="/register">
          注册
          <i class="el-icon-arrow-right"></i>
          <i class="el-icon-arrow-right"></i>
        </RouterLink>
      </div>
    </header>
    <section class="login-section">
      <div class="login-wrapper">
        <nav>
          <a href="javascript:;">账户登录</a>
        </nav>
        <div class="account-box">
          <div class="form">
            <el-form ref="form" :model="form" :rules="rules" label-position="right" label-width="60px" status-icon>
              <el-form-item prop="userName" label="账户">
                <el-input v-model="form.userName" />
              </el-form-item>
              <el-form-item prop="password" label="密码">
                <el-input v-model="form.password" />
              </el-form-item>
              <el-form-item prop="agree" label-width="22px">
                <div class="tip">
                  <span @click="toRegister">点击注册</span>
                  <el-checkbox v-model="checked">记住我</el-checkbox>
                </div>
              </el-form-item>
              <el-button size="large" class="subBtn" @click="toLogin">点击登录</el-button>
            </el-form>
          </div>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import {  Login } from '../../api/user';
import VCode from "vue-puzzle-vcode";
import { validateEmail } from '../../utils/validator';
export default {
  name: 'loginTwo',
  components:{
    VCode
  },
  props:{
    loginTitle:{
      type:String,
      default:'无敌管理系统'
    }
  },
  data(){
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.confirmPassword !== '') {
          this.$refs.resetForm.validateField('confirmPassword');
        }
        callback();
      }
    };
    const validatePassAgain = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      form:{
        userName:null,
        password:null,
      },
      checked:false,
      isShow:false,
      rules: {
        userName: [{required: true, message: '用户名不得为空', trigger: "blur"}],
        password: [{required: true, message: '密码不得为空', trigger: "blur"}],
      },
      addOrUpdateVisible:false,
      resetRule: {
        newPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        confirmPassword:[
          { required: true, message: '请确认密码', trigger: 'blur' },
          { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' },
          { validator: validatePassAgain, trigger: 'blur', required: true }
        ],
        code: [{required: true, message: '验证码不得为空', trigger: "blur"}],
        email: [{required: true, message: '邮箱不得为空', trigger: "blur"},{validator:validateEmail,trigger: 'blur'}],
      },
      isSend:false,
    };


  },
  methods: {

    success() {
      this.login()
    },

    close() {
      this.isShow = false;
    },

    toLogin() {
      // this.isShow = true;
      this.login()
    },

    login() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          Login(this.form).then(res => {
            if (res.code === 1) {
              // sessionStorage.setItem('userInfo',JSON.stringify(res.data));
              sessionStorage.setItem('userId',res.data.id);
              sessionStorage.setItem('imageUrl',res.data.imageUrl);
              sessionStorage.setItem('userType',res.data.userType)
              sessionStorage.setItem('userName',res.data.userName);
              this.$store.commit('saveUserInfo',res.data)
              if (res.data.userType === 2) {
                this.$router.push('/index');
              } else {
                this.$router.push('/person')
              }
              if (this.checked){
                this.setCookie(this.form.userName,this.form.password,7);
              }else {
                this.setCookie("", "", -1); // 修改2值都为空，天数为负1天就好了
              }
              sessionStorage.setItem("Authorization",res.data.token)
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    toRegister() {
      this.$router.push('/register')
    },

    // 设置cookie
    setCookie(userName, password, days) {
      let date = new Date(); // 获取时间
      date.setTime(date.getTime() + 24 * 60 * 60 * 1000 * days); // 保存的天数
      // 字符串拼接cookie
      window.document.cookie =
          "userName" + "=" + userName + ";path=/;expires=" + date.toGMTString();
      window.document.cookie =
          "password" + "=" + password + ";path=/;expires=" + date.toGMTString();
    },

    // 读取cookie 将用户名和密码回显到input框中
    getCookie() {
      if (document.cookie.length > 0) {
        let arr = document.cookie.split("; "); //分割成一个个独立的“key=value”的形式
        for (let i = 0; i < arr.length; i++) {
          let arr2 = arr[i].split("="); // 再次切割，arr2[0]为key值，arr2[1]为对应的value
          if (arr2[0] === "userName") {
            this.form.userName = arr2[1];
          } else if (arr2[0] === "password") {
            this.form.password = arr2[1];// base64解密
            this.checked = true;
          }
        }
      }
    },
  },

  created() {
    this.getCookie()
  },

};
</script>

<style scoped >
.login-header {
  background: #fff;
  border-bottom: 1px solid #e4e4e4;
  height: 120px;

  .container2 {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    width: 1240px;
    margin: 0 auto;
    position: relative;
  }

  .logo {
    width: 200px;
    /*height: 200px;*/
    line-height: 120px;

    a {
      /*display: block;*/
      height: 232px;
      width: 100%;
      text-indent: -9999px;
      /*color: black;*/
      color: #00a854;
    }
  }

  a{
    text-decoration: none;
    outline: none;
    color: #00a854;
  }

  .sub {
    flex: 1;
    font-size: 24px;
    font-weight: normal;
    margin-bottom: 38px;
    margin-left: 20px;
    color: #666;
  }

  .entry {
    width: 120px;
    margin-bottom: 38px;
    font-size: 16px;

    i {
      font-size: 14px;
      color: #27ba9b;
      letter-spacing: -5px;
    }
  }
}

.login-section {
  background: url('../../components/login/image/login-bg.png') no-repeat center / cover;
  height: 488px;
  position: relative;

  .login-wrapper {
    width: 380px;
    background: #fff;
    position: absolute;
    left: 50%;
    top: 54px;
    transform: translate3d(100px, 0, 0);
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);

    nav {
      font-size: 14px;
      height: 55px;
      margin-bottom: 20px;
      border-bottom: 1px solid #f5f5f5;
      display: flex;
      padding: 0 40px;
      text-align: right;
      align-items: center;

      a {
        flex: 1;
        line-height: 1;
        display: inline-block;
        font-size: 18px;
        position: relative;
        text-align: center;
        color: black;
        text-decoration: none;
        outline: none;
      }
    }
  }
}

.login-footer {
  padding: 30px 0 50px;
  background: #fff;

  p {
    text-align: center;
    color: #999;
    padding-top: 20px;

    a {
      line-height: 1;
      padding: 0 10px;
      color: #999;
      display: inline-block;

      ~a {
        border-left: 1px solid #ccc;
      }
    }
  }
}

.account-box {
  .toggle {
    padding: 15px 40px;
    text-align: right;

    a {
      color: #27ba9b;

      i {
        font-size: 14px;
      }
    }
  }

  .form {
    padding: 0 20px 20px 20px;

    &-item {
      margin-bottom: 28px;

      .input {
        position: relative;
        height: 36px;

        >i {
          width: 34px;
          height: 34px;
          background: #cfcdcd;
          color: #fff;
          position: absolute;
          left: 1px;
          top: 1px;
          text-align: center;
          line-height: 34px;
          font-size: 18px;
        }

        input {
          padding-left: 44px;
          border: 1px solid #cfcdcd;
          height: 36px;
          line-height: 36px;
          width: 100%;

          &.error {
            border-color: #cf4444;
          }

          &.active,
          &:focus {
            border-color: #27ba9b;
          }
        }

        .code {
          position: absolute;
          right: 1px;
          top: 1px;
          text-align: center;
          line-height: 34px;
          font-size: 14px;
          background: #f5f5f5;
          color: #666;
          width: 90px;
          height: 34px;
          cursor: pointer;
        }
      }

      >.error {
        position: absolute;
        font-size: 12px;
        line-height: 28px;
        color: #cf4444;

        i {
          font-size: 14px;
          margin-right: 2px;
        }
      }
    }

    .agree {
      a {
        color: #069;
      }
    }

    .btn {
      display: block;
      width: 100%;
      height: 40px;
      color: #fff;
      text-align: center;
      line-height: 40px;
      background: #27ba9b;

      &.disabled {
        background: #cfcdcd;
      }
    }
  }

  .action {
    padding: 20px 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .url {
      a {
        color: #999;
        margin-left: 10px;
      }
    }
  }
}

.subBtn {
  background: #27ba9b;
  width: 100%;
  color: #fff;
}
.tip{
  width: 100%;
  height: 20px;
  /*background-color: red;*/
  display: flex;
  color: black;
  font-size: 13px;
  cursor: pointer;
  justify-content: space-between;
}

/*/deep/.vue-puzzle-overflow{*/
/*     overflow:auto !important;*/
/*    background-color: green;*/
/* }*/
</style>
