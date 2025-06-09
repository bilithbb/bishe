<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="mod__leave">
      <el-form :inline="true" :model="query" @keyup.enter.native="getData()">
  <el-form-item>
    <el-input v-model="query.leaveMessage" placeholder="留言内容" clearable></el-input>
  </el-form-item>
    <el-form-item >
        <el-input v-model="query.realName" placeholder="留言人" clearable></el-input>
    </el-form-item>
               <el-form-item>
          <el-button @click="getData()" type="success">查询</el-button>
          <el-button @click="reset()">重置</el-button>
   </el-form-item>
                <el-form-item>
            <el-button :disabled="this.multipleSelection.length === 0" type="danger"  @click="delBatchLeaveInfo">批量删除</el-button>
        </el-form-item>

  </el-form>
      <el-table v-loading="tableDataLoading" :data="tableData" border @selection-change="selectionChange" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
       <el-table-column prop="leaveMessage" label="留言内容" header-align="center" align="center"></el-table-column>
       <el-table-column prop="replyMessage" label="回复信息" header-align="center" align="center"></el-table-column>
          <el-table-column prop="realName" label="留言人" header-align="center" align="center">
              <template slot-scope="scope">
                  <span v-if="scope.row.realName !=null">{{scope.row.realName}}</span>
                  <span v-else>暂无</span>
              </template>
          </el-table-column>
          <el-table-column prop="imageUrl" label="头像"  header-align="center" align="center">
              <template slot-scope="scope">
                  <el-image
                          style="width: 60px; height: 60px"
                          v-if="scope.row.imageUrl !=null"
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
                    <el-button type="success"  size="small"  @click="updateLeaveInfo(scope.row)">回复</el-button>
                    <el-button type="danger"  size="small"  @click="delLeaveInfo(scope.row.id)">删除</el-button>
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
 <el-dialog :visible.sync="addOrUpdateVisible" :title="!form.id ? '新增' : '编辑'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" :rules="rule" ref="form" label-width="100px">
    <el-form-item label="回复信息" prop="replyMessage">
        <el-input v-model="form.replyMessage" placeholder="回复信息"></el-input>
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

    import {getLeaveList,saveLeaveInfo,updateLeaveInfo,delLeaveInfo,delBatchLeaveInfo} from '../api/leave'
                                import {getUserList,} from '../api/user'
                    import SingleUpload from '../components/common/singleUpload.vue';


  export default {
    components: {
    },

    data () {
      return {
        tableData: [],
        query: {
          pageNum: 1,
          pageSize: 10,

          leaveMessage:null,
          userId:null,
          realName:null,
          imageUrl:null,
        },
        form: {},
        multipleSelection: [],
        tableDataLoading: false,
        total: 0,
        addOrUpdateVisible: false,
        rule: {
         leaveMessage: [{required: true, message: '留言内容不得为空', trigger: "blur"}],
         replyMessage: [{required: true, message: '回复信息不得为空', trigger: "blur"}],
         time: [{required: true, message: '滚动速度不得为空', trigger: "blur"}],
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
       * 获取所有留言
       */
      getData(){

        getLeaveList(this.query).then(res =>{
          this.tableData = res.data.list;
          this.total = res.data.total;
        })

      },

      /**
       * 打开添加框
       */
      addLeaveInfo(){
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
      updateLeaveInfo(row){
        this.form = JSON.parse(JSON.stringify(row));
        this.addOrUpdateVisible = true;
        },

      /**
       * 按住enter键或者确定按钮提交数据,对后台发请求
       * 如果id为null,就是走添加留言的接口
       * 如果id不为null,就是走更新留言的接口
       */
      formSubmitHandle(){
        this.$refs['form'].validate(valid => {
                if(valid){
          if (this.form.id === null){
        saveLeaveInfo(this.form).then(res =>{
            if (res.code === 1){
                this.$message.success('添加成功');
                this.getData();
                this.addOrUpdateVisible = false;
            }
            })
          }else {
        updateLeaveInfo(this.form).then(res =>{
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
      delLeaveInfo(id){
        this.$confirm('确定要删除所选择的吗？', '提示', {
          type: 'warning'
        }).then(action => {
          if (action === 'confirm') {
            delLeaveInfo(id).then(res=>{
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
         * 批量删除留言的接口
         */
      delBatchLeaveInfo(){
        this.$confirm('确定要删除所选择的吗？', '提示', {
            type: 'warning'
        }).then(action => {
            if (action === 'confirm') {
                delBatchLeaveInfo(this.multipleSelection.join(',')).then(res =>{
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
        this.query.leaveMessage = null;
        this.query.userId = null;
             this.query.realName=null
             this.query.imageUrl=null
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

                                                            </style>
