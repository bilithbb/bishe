<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="mod__advert">
      <el-form :inline="true" :model="query" @keyup.enter.native="getData()">
  <el-form-item>
    <el-input v-model="query.title" placeholder="标题" clearable></el-input>
  </el-form-item>
   <el-form-item>
          <el-button @click="getData()" type="success">查询</el-button>
          <el-button @click="reset()">重置</el-button>
   </el-form-item>
            <el-form-item>
            <el-button type="primary"  @click="addAdvertInfo">添加</el-button>
        </el-form-item>
                <el-form-item>
            <el-button :disabled="this.multipleSelection.length === 0" type="danger"  @click="delBatchAdvertInfo">批量删除</el-button>
        </el-form-item>

  </el-form>
      <el-table v-loading="tableDataLoading" :data="tableData" border @selection-change="selectionChange" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
     <el-table-column prop="title" label="标题" header-align="center" align="center">
         <template slot-scope="scope">
         <span v-if="scope.row.title !=null">{{scope.row.title}}</span>
         <span v-else>暂无</span>
         </template>
     </el-table-column>
       <el-table-column prop="imageUrl" label="图片"  header-align="center" align="center">
          <template slot-scope="scope">
              <el-image
                      style="width: 60px; height: 60px"
                      v-if="scope.row.imageUrl != null"
                      :src="scope.row.imageUrl"
                      :preview-src-list="[scope.row.imageUrl]">
              </el-image>
              <span v-else>暂无图片</span>
          </template>
       </el-table-column>
       <el-table-column prop="createTime" label="创建时间" width="160" header-align="center" align="center"></el-table-column>
       <el-table-column prop="updateTime" label="更新时间" width="160" header-align="center" align="center"></el-table-column>
            <el-table-column label="操作" fixed="right" header-align="center" align="center" width="150" >
            <template slot-scope="scope">
                    <el-button type="success"  size="small"  @click="updateAdvertInfo(scope.row)">更新</el-button>
                    <el-button type="danger"  size="small"  @click="delAdvertInfo(scope.row.id)">删除</el-button>
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
    <el-form :model="form" :rules="rule" ref="form" label-width="100px">
    <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" placeholder="标题"></el-input>
    </el-form-item>
    <el-form-item label="简介" prop="desc">
        <textarea cols="80" rows="10" v-model="form.desc" placeholder="简介"></textarea>
    </el-form-item>
    <el-form-item label="内容" prop="content">
        <mavon-editor
                v-model="form.content"
                ref="md"
                @change="contentChangeMarkdown"
                style="min-height: 600px"
                @imgAdd="contentImgAddMarkdown"
        />
    </el-form-item>
      <el-form-item label="图片" prop="imageUrl">
          <single-upload v-model="form.imageUrl"></single-upload>
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
    import { } from '@/utils/validator'

    import {getAdvertList,saveAdvertInfo,updateAdvertInfo,delAdvertInfo,delBatchAdvertInfo} from '@/api/advert'
        import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {getUUID, policy} from "@/api/policy";
import axios from 'axios'
import SingleUpload from '@/components/common/singleUpload.vue';


  export default {
    components: {
    mavonEditor,
    SingleUpload,
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
         title: [{required: true, message: '标题不得为空', trigger: "blur"}],
         desc: [{required: true, message: '简介不得为空', trigger: "blur"}],
         content: [{required: true, message: '内容不得为空', trigger: "blur"}],
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
       * 获取所有广告
       */
      getData(){
        getAdvertList(this.query).then(res =>{
          this.tableData = res.data.list;
          this.total = res.data.total;
        });
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
      addAdvertInfo(){
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
      updateAdvertInfo(row){
        this.form = JSON.parse(JSON.stringify(row));
        this.addOrUpdateVisible = true;
        },

      /**
       * 按住enter键或者确定按钮提交数据,对后台发请求
       * 如果id为null,就是走添加广告的接口
       * 如果id不为null,就是走更新广告的接口
       */
      formSubmitHandle(){
        this.$refs['form'].validate(valid => {
                if(valid){
          if (this.form.id == null){
        saveAdvertInfo(this.form).then(res =>{
            if (res.code === 1){
                this.$message.success('添加成功');
                this.getData();
                this.addOrUpdateVisible = false;
            }
            })
          }else {
        updateAdvertInfo(this.form).then(res =>{
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
      delAdvertInfo(id){
        this.$confirm('确定要删除所选择的吗？', '提示', {
          type: 'warning'
        }).then(action => {
          if (action === 'confirm') {
            delAdvertInfo(id).then(res=>{
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
         * 批量删除广告的接口
         */
      delBatchAdvertInfo(){
        this.$confirm('确定要删除所选择的吗？', '提示', {
            type: 'warning'
        }).then(action => {
            if (action === 'confirm') {
                delBatchAdvertInfo(this.multipleSelection.join(',')).then(res =>{
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



    },


  }
</script>


<style scoped>

                            /* 去除默认样式 */
    textarea {
        border: none;
        outline: none;
        padding: 0;
        margin: 0;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        background-image: none;
        background-color: transparent;
        font-size: inherit;
        width: 100%;
    }

    textarea:focus {
        outline: none;
    }

    /* 自定义样式 */
    textarea {
        display: inline-block;
        resize: vertical;
        padding: 5px 15px;
        line-height: 1.5;
        box-sizing: border-box;
        color: #606266;
        background-color: #fff;
        border: 1px solid #dcdfe6;
        border-radius: 4px;
        transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
    }

    /* 提示文字 */
    textarea::placeholder {
        color: #c0c4cc;
    }

    /* 鼠标hover */
    textarea:hover {
        border-color: #c0c4cc;
    }

    /* 获得焦点 */
    textarea:focus {
        border-color: #3677f0;
    }

                                        </style>
