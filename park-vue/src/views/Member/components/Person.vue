<template>
    <el-card >
        <div class="person">
        <div class="left">
            <single-upload v-model="userInfo.imageUrl"></single-upload>
        <div style="position: absolute;top: 300px;left: 100px">

        </div>
        </div>
        <div class="right">
            <el-form :model="userInfo" :rules="rule" ref="form" @keyup.enter.native="formSubmitHandle()" label-width="100px">
        <el-form-item label="用户名" prop="userName">
            <el-input v-model="userInfo.userName" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="userInfo.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="realName">
            <el-input v-model="userInfo.realName" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
            <el-input v-model="userInfo.phone" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱号" prop="email">
            <el-input v-model="userInfo.email" placeholder="邮箱号"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="userInfo.sex">
                    <el-radio :label="1">男</el-radio>
                    <el-radio :label="2">女</el-radio>
            </el-radio-group>
        </el-form-item>
              <el-form-item label="金额" prop="money">
                <el-input :disabled="true" v-model="userInfo.money" placeholder="金额"></el-input>
              </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="formSubmitHandle()">确定修改</el-button>
            <el-button type="success" style="margin-left: 150px" @click="openPassword">修改密码</el-button>
          <el-button type="primary" @click="formSubmitHandle2()">充值</el-button>
        </el-form-item>

    </el-form>
      </div>
</div>
        <el-dialog :visible.sync="addOrUpdateVisible" title="修改密码" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :model="form" :rules="passwordRule" ref="passwordForm"  label-width="100px">
                <el-form-item label="新密码" prop="password">
                    <el-input type="password" v-model="form.password"
                              placeholder="新密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                    <el-input type="password" v-model="form.confirmPassword" placeholder="确认密码"></el-input>
                </el-form-item>
            </el-form>
            <template slot="footer">
                <el-button @click="addOrUpdateVisible = false">取消</el-button>
                <el-button type="primary" @click="updatePassword()">确认</el-button>
            </template>
        </el-dialog>
    <el-dialog :visible.sync="addOrUpdateVisible2" title="充值" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" :rules="rechargeRule" ref="rechargeForm"  label-width="100px">
            <el-form-item label="充值金额" prop="money">
                <el-input v-model="form.money"
                          placeholder="充值金额"></el-input>
            </el-form-item>
        </el-form>
        <template slot="footer">
            <el-button @click="addOrUpdateVisible2 = false">取消</el-button>
            <el-button type="primary" @click="userRecharge()">确认</el-button>
        </template>
    </el-dialog>

    </el-card>
</template>

<script>

    import SingleUpload from '@/components/common/singleUpload2.vue';
    import { getUserInfo, updatePassword, updateUserInfo, userRecharge } from '@/api/user';
    import {validatePhone,validateEmail, } from '@/utils/validator'
    export default {
        name: "Person",
        components: { SingleUpload },
        data(){
            const validateNewPassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.form.confirmPassword !== '') {
                        this.$refs.passwordForm.validateField('confirmPassword');
                    }
                    callback();
                }
            };
            const validateConfirmPassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.form.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return{
                form:{},
                addOrUpdateVisible:false,
                userInfo:{},
                imageUrl:'',
                rule: {
                    userName: [{required: true, message: '用户名不得为空', trigger: "blur"}],
                    password: [{required: true, message: '密码不得为空', trigger: "blur"}],
                    realName: [{required: true, message: '姓名不得为空', trigger: "blur"}],
                    phone: [{required: true, message: '手机号不得为空', trigger: 'blur'},{validator:validatePhone,trigger: 'blur'}],
                    email: [{required: true, message: '邮箱号不得为空', trigger: 'blur'},{validator:validateEmail,trigger: 'blur'}],
                    address: [{required: true, message: '地址不得为空', trigger: "blur"}],
                    sex: [{required: true, message: '性别不得为空', trigger: "change"}],
                    imageUrl: [{required: true, message: '头像不得为空', trigger: "blur"}],
                    userType: [{required: true, message: '用户类型不得为空', trigger: "change"}],
                    money: [{required: true, message: '余额不得为空', trigger: "blur"}],
                },
                passwordRule: {
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 5, max: 16, message: '长度在 5 到 16 个字符', trigger: 'blur' },
                        { validator: validateNewPassword, trigger: 'blur' }
                    ],
                    confirmPassword:[
                        { required: true, message: '请确认密码', trigger: 'blur' },
                        { min: 5, max: 16, message: '长度在 5 到 16 个字符', trigger: 'blur' },
                        { validator: validateConfirmPassword, trigger: 'blur', required: true }
                    ],
                },
                addOrUpdateVisible2:false,
                rechargeRule: {
                    money: [{required: true, message: '充值金额不得为空', trigger: "blur"}],
                },
            }
        },

        created() {
            this.getData()
        },
        methods: {

            getData(){
                getUserInfo({id:this.$store.state.userInfo.id}).then(res =>{
                    this.userInfo = res.data;
                })
            },

          formSubmitHandle2(){
              this.form = {};
              this.addOrUpdateVisible2 = true;
          },

            formSubmitHandle(){
                this.$refs['form'].validate(valid => {
                        if(valid){
                            updateUserInfo(this.userInfo).then(res =>{
                                this.$message.success('修改成功');
                                this.getData();
                            })
                        }
              },)},

            openPassword(){
                this.form = {};
                this.addOrUpdateVisible = true;
            },
            openUserRecharge(){
                this.form = {};
                this.addOrUpdateVisible2 = true;
            },

            updatePassword(){
                    this.$refs['passwordForm'].validate(valid => {
                            if(valid){
                          this.form.id = this.$store.state.userInfo.id;
                        updatePassword(this.form).then(res =>{
                            this.addOrUpdateVisible = false;
                            this.$message.success('密码更新成功');
                        })
                    }
                    })
            },

            userRecharge(){
                this.$refs['rechargeForm'].validate(valid => {
                        if(valid){
                    this.form.id = sessionStorage.getItem('userId')
                    userRecharge(this.form).then(res =>{
                        this.addOrUpdateVisible2 = false;
                        this.getData();
                        this.$message.success('充值成功');
                    })
                }
                })
            },

        }
    }
</script>

<style scoped>
    .person{
        display: inline-flex;
        width: 1000px;
        /*height: 400px;*/
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
    }
    .left{
        flex: 1;
        /*height: 400px;*/
        padding: 5px;
        /*background-color: red;*/
        position: relative;
    }
    .right{
        flex: 3;
        /*height: 400px;*/
        padding: 20px;
        /*background-color: #00a854;*/
    }

</style>
