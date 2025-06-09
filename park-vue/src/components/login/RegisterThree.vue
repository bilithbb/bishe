<template>
    <div class='Loginbody'>
        <div class="login-header">
<!--            <div class="img">-->
<!--&lt;!&ndash;                <svg-icon icon-class="bgLogo"></svg-icon>&ndash;&gt;-->
<!--&lt;!&ndash;                <img  class="logoImage" :src="logUrl" />&ndash;&gt;-->
<!--            </div>-->
            <div class="login-right">
                <el-form ref="form" :model="form" :rules="rules" class="login-form" auto-complete="on"
                         label-position="left">
<!--                    <i class="top"></i><i class="bottom"></i>-->
<!--                    <div class="logo">-->
<!--                        <el-avatar :src="url"></el-avatar>-->
<!--                    </div>-->

                    <div class="title-container">
                        <h3 class="title">注册</h3>
                    </div>

                    <el-form-item prop="userName">
                        <el-input prefix-icon="el-icon-user-solid" v-model="form.userName" placeholder="用户名"
                                  name="userName" type="text" tabindex="1" auto-complete="on" />
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input prefix-icon="el-icon-lock"  v-model="form.password"
                                  show-password placeholder="密码" name="password" tabindex="2" auto-complete="on"
                                  ref="password" @keyup.enter.native="toLogin" />
                    </el-form-item>

                    <el-form-item prop="realName">
                        <el-input prefix-icon="el-icon-s-custom" v-model="form.realName" placeholder="请输入姓名"/>
                    </el-form-item>
                    <el-form-item prop="phone">
                        <el-input prefix-icon="el-icon-phone" v-model="form.phone" placeholder="请输入手机号"/>
                    </el-form-item>
                    <el-form-item prop="email">
                        <el-input  prefix-icon="el-icon-s-promotion" v-model="form.email" placeholder="请输入邮箱" />
                    </el-form-item>
                    <el-form-item prop="address">
                        <el-input  prefix-icon="el-icon-location-information" v-model="form.address" placeholder="请输入地址"></el-input>
                    </el-form-item>
                    <el-form-item prop="sex">
                        <el-radio-group v-model="form.sex">
                            <el-radio :label="1">男</el-radio>
                            <el-radio :label="2">女</el-radio>
                        </el-radio-group>                    </el-form-item>
                    <el-form-item  prop="imageUrl">
                        <single-upload v-model="form.imageUrl"></single-upload>
                    </el-form-item>

                    <el-button  type="primary" style="width:100%;margin-bottom:30px;"
                               @click.native.prevent="toRegister">
                        <span>注 册</span>
                    </el-button>
                </el-form>
            </div>
        </div>


    </div>
</template>

<script>
    import singleUpload from '../common/singleUpload';
    import { Login, saveUserInfo } from '../../api/user';
    import { validateEmail, validateIdCardNumber, validatePhone } from '../../utils/validator';
    import { resetPassword, sendEmail } from '../../api/email';
    import VCode from "vue-puzzle-vcode";
    export default {
        name: 'RegisterThree',
        components:{
            VCode,singleUpload
        },
        props:{
            loginTitle:{
                type:String,
                default:"无敌系统"
            }
        },
        data() {
            return {
                logUrl:require('./image/login-three-bg-log.png'),
                form:{},
                rules: {
                    userName: [{required: true, message: '用户名不得为空', trigger: "blur"}],
                    password: [{required: true, message: '密码不得为空', trigger: "blur"}],
                    realName: [{required: true, message: '姓名不得为空', trigger: "blur"}],
                    address: [{required: true, message: '地址不得为空', trigger: "blur"}],
                    phone: [{required: true, message: '联系方式不得为空', trigger: 'blur'},{validator:validatePhone,trigger: 'blur'}],
                    idCard: [{required: true, message: '身份证号不得为空', trigger: 'blur'},{validator:validateIdCardNumber,trigger: 'blur'}],
                    userType: [{required: true, message: '用户类型不得为空', trigger: "change"}],
                    sex: [{required: true, message: '性别不得为空', trigger: "change"}],
                    imageUrl: [{required: true, message: '头像不得为空', trigger: "blur"}],
                    email: [{required: true, message: '邮箱不得为空', trigger: "blur"},{validator:validateEmail,trigger: 'blur'}],
                },
            }
        },
        methods: {
            toRegister(){
                this.$refs['form'].validate(valid => {
                    if(valid){
                        this.form.userType = 2;
                        saveUserInfo(this.form).then(res =>{
                            if (res.code === 1){
                                this.$message.success('注册成功,现在可以去登录了');
                                sessionStorage.setItem('userStatus',"111");
                                setTimeout(() =>{
                                    this.$router.push('/login')
                                },2000)
                            }else {
                                this.$message.error('注册失败');
                            }
                        })
                    }
                });
            },
        },
    }
</script>

<style rel="stylesheet/scss"  scoped>
    $bg: #2d3a4b;
    $dark_gray: #889aa4;
    $light_gray: #eee;

    @media screen and (max-width: 1118px) {
        .Loginbody {
            height: 100%;
            width: 100%;
            background-image: url("./image/login-three-bg.png");



            .login-header {

                .img {
                    display: none;
                }

                .login-right {
                    width: 100%;
                    padding: 20px;

                    .login-form {
                        width: 500px;
                        /*height: 450px;*/
                        max-width: 100%;
                        padding: 35px 35px 15px 35px;
                        border-radius: 5px;
                        margin: auto;
                        position: relative;
                        overflow: hidden;
                        border: 1px solid #74efff;

                        &::before {
                            content: ' ';
                            position: absolute;
                            width: 4px;
                            height: 100%;
                            top: -100%;
                            left: 0;
                            background-image: linear-gradient(0deg,
                                    transparent,
                                    #ff74ba,
                                    transparent);
                            animation: two 4s linear infinite;
                        }

                        &::after {
                            content: ' ';
                            position: absolute;
                            width: 4px;
                            height: 100%;
                            bottom: -100%;
                            right: 0;
                            background-image: linear-gradient(360deg,
                                    transparent,
                                    #74efff,
                                    transparent);
                            animation: four 4s linear 2s infinite;
                        }

                        i {
                            position: absolute;
                            display: inline-block;
                            height: 4px;
                            width: 100%;
                        }

                        .logo {
                            text-align: center;
                        }

                        .bottom {
                            bottom: 0;
                            left: -100%;
                            background-image: linear-gradient(270deg,
                                    transparent,
                                    #b574ff,
                                    transparent);
                            animation: one 4s linear 1s infinite;
                        }

                        .top {
                            top: 0;
                            right: -100%;
                            background-image: linear-gradient(270deg,
                                    transparent,
                                    #74ff97,
                                    transparent);
                            animation: three 4s linear 3s infinite;
                        }
                    }

                    .title {
                        font-size: 26px;
                        font-weight: 400;
                        color: #303133;
                        margin: 0px auto 40px auto;
                        text-align: center;
                        font-weight: bold;
                        font-weight: 400;
                    }

                    .show-pwd {
                        position: absolute;
                        right: 10px;
                        top: 7px;
                        font-size: 16px;
                        color: $dark_gray;
                        cursor: pointer;
                        user-select: none;

                        svg {
                            vertical-align: 5px;
                        }
                    }
                }
            }

            .el-login-footer {
                position: absolute;
                bottom: 0;
                left: 0;
                right: 0;
                text-align: center;
            }
        }
    }


    @media screen and (min-width: 1119px) {
        .Loginbody {
          height: 100vh;
            width: 100%;
            background-image: url("./image/login-three-bg.png");



            .login-header {
                display: flex;
                height: 100%;

                .img {
                    height: 50px;
                    position: relative;
                    top: 30%;
                    left: 20%;

                    .logoImage {
                        width: 500px;
                        height: 300px;
                    }
                }

                .login-right {
                    width: 100%;
                    display: flex;
                    padding: 20px;
                    /*margin-left: 100px;*/

                    .login-form {
                        width: 500px;
                        /*height: 450px;*/
                        max-width: 100%;
                        padding: 35px 35px 15px 35px;
                        border-radius: 5px;
                        margin: auto;
                        position: relative;
                        overflow: hidden;
                        border: 1px solid #74efff;

                        &::before {
                            content: ' ';
                            position: absolute;
                            width: 4px;
                            height: 100%;
                            top: -100%;
                            left: 0;
                            background-image: linear-gradient(0deg,
                                    transparent,
                                    #ff74ba,
                                    transparent);
                            animation: two 4s linear infinite;
                        }

                        &::after {
                            content: ' ';
                            position: absolute;
                            width: 4px;
                            height: 100%;
                            bottom: -100%;
                            right: 0;
                            background-image: linear-gradient(360deg,
                                    transparent,
                                    #74efff,
                                    transparent);
                            animation: four 4s linear 2s infinite;
                        }

                        i {
                            position: absolute;
                            display: inline-block;
                            height: 4px;
                            width: 100%;
                        }

                        .logo {
                            text-align: center;
                        }

                        .bottom {
                            bottom: 0;
                            left: -100%;
                            background-image: linear-gradient(270deg,
                                    transparent,
                                    #b574ff,
                                    transparent);
                            animation: one 4s linear 1s infinite;
                        }

                        .top {
                            top: 0;
                            right: -100%;
                            background-image: linear-gradient(270deg,
                                    transparent,
                                    #74ff97,
                                    transparent);
                            animation: three 4s linear 3s infinite;
                        }
                    }

                    .title {
                        font-size: 26px;
                        font-weight: 400;
                        color: #303133;
                        margin: 0px auto 40px auto;
                        text-align: center;
                        font-weight: bold;
                        font-weight: 400;
                    }

                    .show-pwd {
                        position: absolute;
                        right: 10px;
                        top: 7px;
                        font-size: 16px;
                        color: $dark_gray;
                        cursor: pointer;
                        user-select: none;

                        svg {
                            vertical-align: 5px;
                        }
                    }
                }
            }

            .el-login-footer {
                position: absolute;
                bottom: 0;
                left: 0;
                right: 0;
                text-align: center;
            }
        }
    }

    @keyframes one {
        0% {
            left: -100%;
        }

        50%,
        100% {
            left: 100%;
        }
    }

    @keyframes two {
        0% {
            top: -100%;
        }

        50%,
        100% {
            top: 100%;
        }
    }

    @keyframes three {
        0% {
            right: -100%;
        }

        50%,
        100% {
            right: 100%;
        }
    }

    @keyframes four {
        0% {
            bottom: -100%;
        }

        50%,
        100% {
            bottom: 100%;

        }
    }

    .tip{
        width: 100%;
        height: 20px;
        /*background-color: red;*/
        display: flex;
        /*color: white;*/
        font-size: 13px;
        cursor: pointer;
        justify-content: space-between;
    }
</style>
