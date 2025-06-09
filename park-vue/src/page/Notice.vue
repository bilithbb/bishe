<template>
    <el-card shadow="never" class="aui-card--fill">
        <div class="mod__notice">
            <el-form :inline="true" :model="query" @keyup.enter.native="getData()">
                <el-form-item>
                    <el-input v-model="query.title" placeholder="公告标题" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getData()" type="success">查询</el-button>
                    <el-button @click="reset()">重置</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"  @click="addNoticeInfo">添加</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button :disabled="this.multipleSelection.length === 0" type="danger"  @click="delBatchNoticeInfo">批量删除</el-button>
                </el-form-item>
<!--                <el-form-item>-->
<!--                    <el-button type="primary" @click="createNoticeInfo">生成数据</el-button>-->
<!--                </el-form-item>-->

            </el-form>
            <el-table v-loading="tableDataLoading" :data="tableData" border @selection-change="selectionChange" style="width: 100%;">
                <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
                <el-table-column prop="title" label="公告标题" header-align="center" align="center"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="160" header-align="center" align="center"></el-table-column>
                <el-table-column prop="updateTime" label="更新时间" width="160" header-align="center" align="center"></el-table-column>
                <el-table-column label="操作" fixed="right" header-align="center" align="center" width="150" >
                    <template slot-scope="scope">
                        <el-button type="success"  size="small"  @click="updateNoticeInfo(scope.row)">更新</el-button>
                        <el-button type="danger"  size="small"  @click="delNoticeInfo(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    :current-page="query.pageNum"
                    :page-sizes="[10, 20, 50, 100]"
                    :page-size="query.pageSize"
                    :total="total"
                    layout="total, sizes, prev, pager, next, jumper"
                    @size-change="pageSizeChange"
                    @current-change="pageCurrentChange">
            </el-pagination>
            <!-- 弹窗, 新增 / 修改 -->
            <el-dialog  width="80%" :visible.sync="addOrUpdateVisible" :title="!form.id ? '新增' : '编辑'" :close-on-click-modal="false" :close-on-press-escape="false">
                <el-form :model="form" :rules="rule" ref="form"  label-width="100px">
                    <el-form-item label="公告标题" prop="title">
                        <el-input v-model="form.title" placeholder="公告标题"></el-input>
                    </el-form-item>
                    <el-form-item label="公告内容" prop="content">
                        <mavon-editor
                                v-model="form.content"
                                ref="md"
                                @change="contentChangeMarkdown"
                                style="min-height: 600px"
                                @imgAdd="contentImgAddMarkdown"
                        />
                    </el-form-item>
                </el-form>
                <template slot="footer">
                    <el-button @click="addOrUpdateVisible = false">取消</el-button>
                    <el-button type="primary" @click="formSubmitHandle()">确认</el-button>
                </template>
            </el-dialog>


        </div>
    </el-card>
</template>

<script>
    import { } from '../utils/validator'

    import {getNoticeList,saveNoticeInfo,updateNoticeInfo,delNoticeInfo,delBatchNoticeInfo,createNoticeData} from '../api/notice'
    import { mavonEditor } from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'
    import {getUUID, policy} from "../api/policy";
    import axios from 'axios'
    import SingleUpload from '../components/common/singleUpload.vue';


    export default {
        components: {
            mavonEditor,
        },

        data () {
            return {
                tableData: [],
                query: {
                    pageNum: 1,
                    pageSize: 10,

                    title:null,
                },
                form: {},
                multipleSelection: [],
                tableDataLoading: false,
                total: 0,
                addOrUpdateVisible: false,
                rule: {
                    title: [{required: true, message: '公告标题不得为空', trigger: "blur"}],
                    content: [{required: true, message: '公告内容不得为空', trigger: "blur"}],
                },
            }
        },
        created() {
            this.getData();
        },
        computed:{
            userType(){
                return Number(sessionStorage.getItem('userType'))
            }
        },

        methods:{


            /**
             * 获取所有公告
             */
            getData(){

                getNoticeList(this.query).then(res =>{
                    this.tableData = res.data.list;
                    this.total = res.data.total;
                })
            },
            contentChangeMarkdown(value, render) {
                // render 为 markdown 解析后的结果[html]
                this.form.content = render;
            },

            contentImgAddMarkdown(pos, $file){
                const ossInfo = {};
                policy().then(response => {
                    console.log(response)
                    ossInfo.policy = response.policy;
                    ossInfo.signature = response.signature;
                    ossInfo.ossaccessKeyId = response.accessid;
                    ossInfo.key = response.dir + '/'+getUUID()+'_${filename}';
                    ossInfo.dir = response.dir;
                    ossInfo.host = response.host;
                    const formData = new FormData();
                    formData.append('policy', response.policy);
                    formData.append('signature', response.signature);
                    formData.append('ossaccessKeyId', response.accessid);
                    formData.append('key', response.dir + '/'+getUUID());
                    formData.append('dir', response.dir);
                    formData.append('host', response.host);
                    formData.append('file', $file);
                    axios.post('http://gulimall-psw.oss-cn-hangzhou.aliyuncs.com',formData).then(res =>{
                        const imageUrl = response.host + '/' + formData.get('key')
                        console.log(imageUrl)
                        this.$refs.md.$img2Url(pos,imageUrl);
                    })

                    // resolve(true)
                });
            },


            /**
             * 打开添加框
             */
            addNoticeInfo(){
                this.form = {};
                this.form.id = null;
                if (this.$refs['form'] !==undefined){
                    this.$refs['form'].resetFields();
                }
                this.addOrUpdateVisible = true;
            },

            /**
             * 打开更新框
             */
            updateNoticeInfo(row){
                this.form = JSON.parse(JSON.stringify(row));
                this.addOrUpdateVisible = true;
            },

            /**
             * 按住enter键或者确定按钮提交数据,对后台发请求
             * 如果id为null,就是走添加公告的接口
             * 如果id不为null,就是走更新公告的接口
             */
            formSubmitHandle(){
                this.$refs['form'].validate(valid => {
                    if(valid){
                        if (this.form.id === null){
                            saveNoticeInfo(this.form).then(res =>{
                                if (res.code === 1){
                                    this.$message.success('添加成功');
                                    this.getData();
                                    this.addOrUpdateVisible = false;
                                }
                            })
                        }else {
                            updateNoticeInfo(this.form).then(res =>{
                                this.$message.success('更新成功');
                                this.getData();
                                this.addOrUpdateVisible = false;
                            })
                        }
                        // this.$refs['form'].resetFields()

                    }
                })
            },

            /**
             *单个删除数据
             */
            delNoticeInfo(id){
                this.$confirm('确定要删除所选择的吗？', '提示', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                        delNoticeInfo(id).then(res=>{
                            if (res.code === 1){
                                this.$message.success('删除成功');
                                this.getData();
                            }else {
                                return this.$message.error(res.msg)
                            }

                        })
                    }
                }).catch(() => {});

            },



            /**
             * 选中table表格事件
             */
            selectionChange(val){
                this.multipleSelection = [];
                val.forEach((item) => {
                    this.multipleSelection.push(item.id);
                });
            },

            /**
             * 批量删除公告的接口
             */
            delBatchNoticeInfo(){
                this.$confirm('确定要删除所选择的吗？', '提示', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                        delBatchNoticeInfo(this.multipleSelection.join(',')).then(res =>{
                            this.$message.success('批量删除成功');
                            this.getData();
                        })
                    }
                }).catch(() => {});

            },

            /**
             * 重置数据
             */
            reset(){
                this.query.title = null;
                this.$set(this.query, 'pageNum', 1);
                this.getData();
            },



            /**
             * 分页(改变页码)
             * @param val
             */
            pageCurrentChange(val){
                this.$set(this.query, 'pageNum', val);
                this.getData();
            },

            /**
             * 分页(改变每页条数)
             * @param val
             */
            pageSizeChange(val){
                this.$set(this.query, 'pageSize', val);
                this.getData();
            },

            /**
             * 关闭弹窗
             */
            clearAddForm(){
                this.addOrUpdateVisible = false;
            },


            createNoticeInfo(){
                createNoticeData().then(res =>{
                    this.getData();
                    this.$message.success('操作成功')
                })
            }

        },


    }
</script>


<style scoped>

</style>
