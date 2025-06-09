<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="mod__collect">
      <el-form :inline="true" :model="query" @keyup.enter.native="getData()">
    <el-form-item >
        <el-input v-model="query.realName" placeholder="用户" clearable></el-input>
    </el-form-item>
            <el-form-item >
        <el-input v-model="query.title" placeholder="广告" clearable></el-input>
    </el-form-item>
           <el-form-item>
          <el-button @click="getData()" type="success">查询</el-button>
          <el-button @click="reset()">重置</el-button>
   </el-form-item>

                <el-form-item>
            <el-button :disabled="this.multipleSelection.length === 0" type="danger"  @click="delBatchCollectInfo">批量删除</el-button>
        </el-form-item>

  </el-form>
      <el-table v-loading="tableDataLoading" :data="tableData" border @selection-change="selectionChange" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="realName" label="用户" header-align="center" align="center">
          <template slot-scope="scope">
              <span v-if="scope.row.realName !=null">{{scope.row.realName}}</span>
              <span v-else>暂无</span>
          </template>
      </el-table-column>
      <el-table-column prop="title" label="广告" header-align="center" align="center">
          <template slot-scope="scope">
              <span v-if="scope.row.title !=null">{{scope.row.title}}</span>
              <span v-else>暂无</span>
          </template>
      </el-table-column>
       <el-table-column prop="createTime" label="创建时间" width="160" header-align="center" align="center"></el-table-column>
       <el-table-column prop="updateTime" label="更新时间" width="160" header-align="center" align="center"></el-table-column>
            <el-table-column label="操作" fixed="right" header-align="center" align="center" width="150" >
            <template slot-scope="scope">
                    <el-button type="success"  size="small"  @click="toDetailInfo(scope.row)">详情</el-button>
                    <el-button type="danger"  size="small"  @click="delCollectInfo(scope.row.id)">删除</el-button>
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
 <el-dialog  :visible.sync="addOrUpdateVisible" :title="!form.id ? '新增' : '编辑'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" :rules="rule" ref="form" label-width="100px">
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

    import {getCollectList,saveCollectInfo,updateCollectInfo,delCollectInfo,delBatchCollectInfo} from '@/api/collect'
                                import {getUserList,} from '@/api/user'
                                            import {getAdvertList,} from '@/api/advert'
                    import SingleUpload from '@/components/common/singleUpload.vue';


  export default {
    components: {
    },

    data () {
      return {
        tableData: [],
        query: {
          pageNum: 1,
          pageSize: 10,

          userId:null,
          advertId:null,
          realName:null,
          title:null,
        },
        form: {},
        multipleSelection: [],
        tableDataLoading: false,
        total: 0,
        addOrUpdateVisible: false,
        rule: {
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
       * 获取所有收藏
       */
      getData(){
        this.query.userId = sessionStorage.getItem('userId')
        getCollectList(this.query).then(res =>{
          this.tableData = res.data.list;
          this.total = res.data.total;
        });


      },

      /**
       * 打开添加框
       */
      addCollectInfo(){
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
      updateCollectInfo(row){
        this.form = JSON.parse(JSON.stringify(row));
        this.addOrUpdateVisible = true;
        },

      /**
       * 按住enter键或者确定按钮提交数据,对后台发请求
       * 如果id为null,就是走添加收藏的接口
       * 如果id不为null,就是走更新收藏的接口
       */
      formSubmitHandle(){
        this.$refs['form'].validate(valid => {
                if(valid){
          if (this.form.id == null){
        saveCollectInfo(this.form).then(res =>{
            if (res.code === 1){
                this.$message.success('添加成功');
                this.getData();
                this.addOrUpdateVisible = false;
            }
            })
          }else {
        updateCollectInfo(this.form).then(res =>{
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
      delCollectInfo(id){
        this.$confirm('确定要删除所选择的吗？', '提示', {
          type: 'warning'
        }).then(action => {
          if (action === 'confirm') {
            delCollectInfo(id).then(res=>{
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
         * 批量删除收藏的接口
         */
      delBatchCollectInfo(){
        this.$confirm('确定要删除所选择的吗？', '提示', {
            type: 'warning'
        }).then(action => {
            if (action === 'confirm') {
                delBatchCollectInfo(this.multipleSelection.join(',')).then(res =>{
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
        this.query.userId = null;
        this.query.advertId = null;
             this.query.realName=null
                  this.query.title=null
             this.$set(this.query, 'pageNum', 1);
        this.getData();
      },


    /**
     *查看详情按钮,因为模板不支持这种写法,所以用了注释,到时候解开即可
     */
    toDetailInfo(row){
         this.$router.push({path:'/advert-detail',query:row.advertId})
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
