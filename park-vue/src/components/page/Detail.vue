<template>
    <div>
    <el-card class="detail">
        <div class="place-detail">
            <div class="place-detail-bottom">
                <div class="left">
                    <img :src="detailInfo.imageUrl">
                </div>
                <div class="right">
                <div class="item">
                    <div class="item-left">
                        编号
                    </div>
                    <div class="item-right">
                       {{ detailInfo.stallNumber}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        名称
                    </div>
                    <div class="item-right">
                       {{ detailInfo.stallName}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        分类
                    </div>
                    <div class="item-right">
                       {{ detailInfo.sortId}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        停车位位置
                    </div>
                    <div class="item-right">
                       {{ detailInfo.location}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        简介
                    </div>
                    <div class="item-right">
                       {{ detailInfo.desc}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        内容
                    </div>
                    <div class="item-right">
                       {{ detailInfo.content}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        状态
                    </div>
                    <div class="item-right">
                       {{ detailInfo.status}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        是否推荐
                    </div>
                    <div class="item-right">
                       {{ detailInfo.isBanner}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        访问量
                    </div>
                    <div class="item-right">
                       {{ detailInfo.count}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        创建时间
                    </div>
                    <div class="item-right">
                       {{ detailInfo.createTime}}
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">

                    </div>
                    <div class="item-right">
                       {{ detailInfo.price}}
                    </div>
                </div>
                    <div class="item" style="margin-top: 20px">
                        <el-button class="appoint" @click="saveAppointOperate">预约</el-button>
                    </div>
                </div>
            </div>
        </div>
    </el-card>
    <el-card class="remark">
        <div class="remark-content">
            <v-md-preview v-highlight  class="content" :text="detailInfo.desc" ref="preview" />
        </div>
    </el-card>
    <el-card class="remark">
        <div class="remark-content">
            <v-md-preview v-highlight  class="content" :text="detailInfo.remark" ref="preview" />
        </div>
    </el-card>
        <el-dialog :visible.sync="addOrUpdateVisible" :rules="rules" title="操作" :close-on-click-modal="false" :close-on-press-escape="false">
               <el-form :model="form" ref="form"  label-width="100px">
                   <el-form-item label="开始时间">
                       <el-input v-model="form.start_time" placeholder="请输入开始时间"></el-input>
                   </el-form-item>
               </el-form>
            <template slot="footer">
                <el-button @click="addOrUpdateVisible = false">取消</el-button>
                <el-button type="primary" @click="formSubmitHandle()">确认</el-button>
            </template>
        </el-dialog>

    </div>
    </div>
</template>

<script>
    import {getStallInfo} from '../../api/stall.js'
    import {saveAppointInfo} from '../../api/appoint.js'

    export default {
        name: 'Detail',

        data(){
            return{
                detailInfo:{},
                list:[],
                form:{
                    userId:sessionStorage.getItem('userId'),
                    stall_id:this.$route.query.id,
                    start_time:null,
            },
                addOrUpdateVisible:false,
                rules: {
                    start_time:[{required: true, message: '开始时间不得为空', trigger: "blur"}],
                },



            }
        },
        created() {
            this.getData();
        },


        methods:{
            getData(){
                getStallInfo({id:this.$route.query.id}).then(res =>{
                    this.detailInfo = res.data;
                })
            },

            saveAppointOperate(item){
                if(sessionStorage.getItem('userId') == null){
                    this.$message.warning('请登录');
                    return false;
                }
                this.form.start_time = null;
                this.addOrUpdateVisible = true;
            },
        formSubmitHandle(){
            this.$refs['form'].validate(valid => {
                    if(valid){
                this.form.userId = this.$store.state.userInfo.id;
                saveAppointInfo(this.form).then(res =>{
                    this.getData();
                    this.addOrUpdateVisible = false;
                    this.$message.success(res.msg);
                })
            }
            })
        },
        }
    };
</script>

<style scoped>

    .remark{
        width: 1300px;
        margin: 10px auto;
    }
    /deep/.vuepress-markdown-body:not(.custom){
        padding: 0 0;
    }
    /deep/.vuepress-markdown-body  img{
        height: 400px;
    }

    .detail{
        width: 1300px;
        /*height: 600px;*/
        margin: 0 auto;
    }
    .detail .place-detail{
        width: 100%;
        height: 100%;
        background-color:white;
        display: inline-flex;
        border-radius: 10px;
        border: 1px solid #f1f3f4;
        flex-direction: column;
        margin-top: 20px;
    }
    .detail .place-detail .place-detail-top{
        flex: 1;
        /*background-color: blue;*/
        color: #323233;
        font-size: 22px;
        padding-left: 20px;
        line-height: 50px;
    }
    .detail .place-detail .place-detail-bottom{
        flex: 9;
        /*background-color: #2d8cf0;*/
        display: inline-flex;
    }
    .detail .place-detail .place-detail-bottom .left{
        flex: 1;
        /*background-color: blue;*/
        padding: 10px;
    }
    .detail .place-detail .place-detail-bottom .left img{
        width: 100%;
        height: 100%;
    }
    .detail .place-detail .place-detail-bottom .right{
        flex: 2;
        /*background-color: greenyellow;*/
        color: #323233;
        font-size: 14px;
    }
    .detail .place-detail .place-detail-bottom .right .item{
        width: 95%;
        height: 50px;
        /*background-color: red;*/
        margin-top: 10px;
        display: inline-flex;
        border: 2px solid #f1f3f4;
        border-radius: 10px;
    }
    .detail .place-detail .place-detail-bottom .right .item .item-left{
        flex: 1;
        /*background-color: #2d8cf0;*/
        text-align: center;
        line-height: 50px;
    }
    .detail .place-detail .place-detail-bottom .right .item .item-right{
        flex: 3;
        text-align: left;
        line-height: 50px;
    }
    .detail .place-detail .place-detail-bottom .right .item2{
        width: 95%;
        /*height: 150px;*/
        /*background-color: red;*/
        margin-top: 10px;
        display: inline-flex;
        border: 2px solid #f1f3f4;
        border-radius: 10px;
        padding: 10px;
        box-sizing: border-box;
        height: 250px;
    }
    .detail .place-detail .place-detail-bottom .right .item2 .item-left2{
        flex: 1;
        /*background-color: green;*/
        text-align: center;

    }
    .detail .place-detail .place-detail-bottom .right .item2 .item-right2{
        flex: 3;
        /*background-color: green;*/

        display: -webkit-box;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .detail .place-detail .place-detail-bottom .right .item .appoint{
        width: 100%;
        height: 100%;
        background-color: #00a854;
        color: white;
    }

    .detail .place-remark{
        width: 100%;
        background-color:white;
        display: inline-flex;
        border-radius: 10px;
        border: 1px solid #f1f3f4;
        flex-direction: column;
        margin-top: 20px;
        padding: 10px;
        box-sizing: border-box;
    }
    .collect-like{
        width:100%;
        margin: 10px auto;
        height: 50px;
        background-color: white;
    }
    .collect-like2{
        width:90%;
        margin: 10px auto;
        /*height: 50px;*/
        background-color: white;
        padding: 4px 10px;
        line-height: 30px;
    }
    .collect-like .item{
        width: 160px;
        height: 100%;
        margin-left: 10px;
        /*background-color: red;*/
        display: inline-flex;
        padding: 10px 25px;
    }
    .collect-like .item img{
        width: 30px;
        height: 30px;
        margin-left: 10px;
        line-height: 50px;
        cursor: pointer;
    }
    .collect-like .item span{
        color: #323233;
        font-size: 14px;
        margin-top:6px;
        margin-left: 10px;
    }
</style>
