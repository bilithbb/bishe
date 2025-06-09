<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="mod__car">
      <el-form :inline="true" :model="query" @keyup.enter.native="getData()">
  <el-form-item>
    <el-input v-model="query.carNumber" placeholder="车牌号" clearable></el-input>
  </el-form-item>
  <el-form-item>
    <el-input v-model="query.brand" placeholder="品牌" clearable></el-input>
  </el-form-item>
  <el-form-item>
    <el-select v-model="query.type" placeholder="请选择车型">
                    <el-option key="1" label="A级车" value="1"></el-option>
                    <el-option key="2" label="B级车" value="2"></el-option>
                    <el-option key="3" label="C级车" value="3"></el-option>
                    <el-option key="4" label="D级车" value="4"></el-option>
    </el-select>
  </el-form-item>
    <el-form-item >
        <el-input v-model="query.realName" placeholder="用户" clearable></el-input>
    </el-form-item>
           <el-form-item>
          <el-button @click="getData()" type="success">查询</el-button>
          <el-button @click="reset()">重置</el-button>
   </el-form-item>
            <el-form-item v-if="userType ===1">
            <el-button type="primary" @click="addCarInfo">添加</el-button>
        </el-form-item>
                <el-form-item v-if="userType ===1">
            <el-button :disabled="this.multipleSelection.length === 0" type="danger" @click="delBatchCarInfo">批量删除</el-button>
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
     <el-table-column prop="carNumber" label="车牌号" header-align="center" align="center">
         <template slot-scope="scope">
         <span v-if="scope.row.carNumber !=null">{{scope.row.carNumber}}</span>
         <span v-else>暂无</span>
         </template>
     </el-table-column>
     <el-table-column prop="brand" label="品牌" header-align="center" align="center">
         <template slot-scope="scope">
         <span v-if="scope.row.brand !=null">{{scope.row.brand}}</span>
         <span v-else>暂无</span>
         </template>
     </el-table-column>
     <el-table-column prop="color" label="颜色" header-align="center" align="center">
         <template slot-scope="scope">
         <span v-if="scope.row.color !=null">{{scope.row.color}}</span>
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
       <el-table-column prop="type" label="车型"  header-align="center" align="center">
         <template slot-scope="scope">
             <el-tag type="success" v-if="scope.row.type === 1">A级车</el-tag>
             <el-tag type="success" v-if="scope.row.type === 2">B级车</el-tag>
             <el-tag type="success" v-if="scope.row.type === 3">C级车</el-tag>
             <el-tag type="success" v-if="scope.row.type === 4">D级车</el-tag>
         </template>
       </el-table-column>
       <el-table-column prop="createTime" label="创建时间" width="160" header-align="center" align="center"></el-table-column>
       <el-table-column prop="updateTime" label="更新时间" width="160" header-align="center" align="center"></el-table-column>
            <el-table-column label="操作"  v-if="true || true"  fixed="right" header-align="center" align="center" width="150">
            <template slot-scope="scope">
                <el-button type="success"  size="small" v-if="userType ===1 || true" @click="updateCarInfo(scope.row)">更新</el-button>
                <el-button type="danger"  size="small" v-if="userType ===1 || true" @click="delCarInfo(scope.row.id)">删除</el-button>
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
    <el-form-item label="车牌号" prop="carNumber">
        <el-input v-model="form.carNumber" placeholder="车牌号"></el-input>
    </el-form-item>
    <el-form-item label="品牌" prop="brand">
        <el-input v-model="form.brand" placeholder="品牌"></el-input>
    </el-form-item>
    <el-form-item label="颜色" prop="color">
        <el-input v-model="form.color" placeholder="颜色"></el-input>
    </el-form-item>
      <el-form-item label="图片" prop="imageUrl">
          <single-upload v-model="form.imageUrl"></single-upload>
      </el-form-item>
      <el-form-item label="车型" prop="type">
          <el-radio-group v-model="form.type">
    <el-radio :label="1">A级车</el-radio>
    <el-radio :label="2">B级车</el-radio>
    <el-radio :label="3">C级车</el-radio>
    <el-radio :label="4">D级车</el-radio>
          </el-radio-group>
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

    import {getCarList,saveCarInfo,updateCarInfo,delCarInfo,delBatchCarInfo} from '@/api/car'
                                import {getUserList,} from '@/api/user'
                    import SingleUpload from '@/components/common/singleUpload.vue';


  export default {
    components: {
    SingleUpload,
    },

    data () {
      return {
        tableData: [],
        query: {
          pageNum: 1,
          pageSize: 10,

          carNumber:null,
          brand:null,
          type:null,
          userId:null,
          realName:null,
        },
        form: {},
        multipleSelection: [],
        tableDataLoading: false,
        total: 0,
        addOrUpdateVisible: false,
        rule: {
         carNumber: [{required: true, message: '车牌号不得为空', trigger: "blur"}],
         brand: [{required: true, message: '品牌不得为空', trigger: "blur"}],
         color: [{required: true, message: '颜色不得为空', trigger: "blur"}],
         type: [{required: true, message: '车型不得为空', trigger: "change"}],
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
       * 获取所有汽车
       */
      getData(){
        getCarList(this.query).then(res =>{
          this.tableData = res.data.list;
          this.total = res.data.total;
        });

      },

      /**
       * 打开添加框
       */
      addCarInfo(){
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
      updateCarInfo(row){
        this.form = JSON.parse(JSON.stringify(row));
        this.addOrUpdateVisible = true;
        },

      /**
       * 按住enter键或者确定按钮提交数据,对后台发请求
       * 如果id为null,就是走添加汽车的接口
       * 如果id不为null,就是走更新汽车的接口
       */
      formSubmitHandle(){
        this.$refs['form'].validate(valid => {
                if(valid){
          if (this.form.id == null){
           this.form.userId = Number(sessionStorage.getItem('userId'))
        saveCarInfo(this.form).then(res =>{
            if (res.code === 1){
                this.$message.success('添加成功');
                this.getData();
                this.addOrUpdateVisible = false;
            }
            })
          }else {
        updateCarInfo(this.form).then(res =>{
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
      delCarInfo(id){
        this.$confirm('确定要删除所选择的吗？', '提示', {
          type: 'warning'
        }).then(action => {
          if (action === 'confirm') {
            delCarInfo(id).then(res=>{
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
         * 批量删除汽车的接口
         */
      delBatchCarInfo(){
        this.$confirm('确定要删除所选择的吗？', '提示', {
            type: 'warning'
        }).then(action => {
            if (action === 'confirm') {
                delBatchCarInfo(this.multipleSelection.join(',')).then(res =>{
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
        this.query.carNumber = null;
        this.query.brand = null;
        this.query.type = null;
        this.query.userId = null;
             this.query.realName=null
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
