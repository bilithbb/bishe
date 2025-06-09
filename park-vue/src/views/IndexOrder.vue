<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="mod__appoint">
      <el-form :inline="true" :model="query" @keyup.enter.native="getData()">
        <el-form-item>
          <el-select v-model="query.status" placeholder="请选择状态">
            <el-option key="1" label="待审核" value="1"></el-option>
            <el-option key="2" label="审核通过" value="2"></el-option>
            <el-option key="3" label="审核不通过" value="3"></el-option>
            <el-option key="4" label="待支付" value="4"></el-option>
            <el-option key="5" label="已支付" value="5"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item >
          <el-input v-model="query.realName" placeholder="用户" clearable></el-input>
        </el-form-item>
        <el-form-item >
          <el-input v-model="query.stallNumber" placeholder="车位号" clearable></el-input>
        </el-form-item>
        <el-form-item >
          <el-input v-model="query.carNumber" placeholder="车牌号" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="getData()" type="success">查询</el-button>
          <el-button @click="reset()">重置</el-button>
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
        <el-table-column prop="stallNumber" label="车位号" header-align="center" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.stallNumber !=null">{{scope.row.stallNumber}}</span>
            <span v-else>暂无</span>
          </template>
        </el-table-column>
        <el-table-column prop="carNumber" label="车牌号" header-align="center" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.carNumber !=null">{{scope.row.carNumber}}</span>
            <span v-else>暂无</span>
          </template>
        </el-table-column>
        <el-table-column prop="startTime" label="开始时间" width="160" header-align="center" align="center"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="160" header-align="center" align="center"></el-table-column>
        <el-table-column prop="status" label="状态"  header-align="center" align="center">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.status === 1">待审核</el-tag>
            <el-tag type="success" v-if="scope.row.status === 2">审核通过</el-tag>
            <el-tag type="success" v-if="scope.row.status === 3">审核不通过</el-tag>
            <el-tag type="success" v-if="scope.row.status === 4">待支付</el-tag>
            <el-tag type="success" v-if="scope.row.status === 5">已支付</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="totalPrice" label="总价" header-align="center" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.totalPrice !=null">{{scope.row.totalPrice}}</span>
            <span v-else>暂无</span>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="类型"  header-align="center" align="center">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.type === 1">系统车牌</el-tag>
            <el-tag type="success" v-if="scope.row.type === 2">外部车牌</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160" header-align="center" align="center"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间" width="160" header-align="center" align="center"></el-table-column>
        <el-table-column label="操作"   fixed="right" header-align="center" align="center" width="150">
          <template slot-scope="scope">
            <el-button type="success"  size="small" v-if="scope.row.status !==4" @click="updateAppointInfo(scope.row)">暂无</el-button>
            <el-button type="primary"  size="small" v-else @click="delAppointInfo(scope.row)">支付</el-button>
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
          <el-form-item label="开始时间" prop="startTime">
            <el-date-picker
                class="runPicker"
                v-model="form.startTime"
                type="datetime"
                placeholder="请选择开始时间"
                value-format="yyyy-MM-dd HH:mm:ss"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
                class="runPicker"
                v-model="form.endTime"
                type="datetime"
                placeholder="请选择结束时间"
                value-format="yyyy-MM-dd HH:mm:ss"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-radio-group v-model="form.status">
              <el-radio :label="1">待审核</el-radio>
              <el-radio :label="2">审核通过</el-radio>
              <el-radio :label="3">审核不通过</el-radio>
              <el-radio :label="4">待支付</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="总价" prop="totalPrice">
            <el-input v-model="form.totalPrice" placeholder="总价"></el-input>
          </el-form-item>
          <el-form-item label="车辆" prop="carId">
            <el-select v-model="form.carId" placeholder="请选择车辆">
              <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.carNumber"
                  :value="item.id">
              </el-option>
            </el-select>
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

import {
  getAppointList,
  saveAppointInfo,
  updateAppointInfo,
  delAppointInfo,
  delBatchAppointInfo,
  payAppointInfo
} from '@/api/appoint';
import {getUserList,} from '@/api/user'
import {getStallList,} from '@/api/stall'
import {getCarList,} from '@/api/car'
import SingleUpload from '@/components/common/singleUpload.vue';


export default {
  components: {
  },

  data () {
    return {
      options:[],
      tableData: [],
      query: {
        pageNum: 1,
        pageSize: 10,

        userId:null,
        stallId:null,
        status:null,
        realName:null,
        stallNumber:null,
        carNumber:null,
      },
      form: {},
      multipleSelection: [],
      tableDataLoading: false,
      total: 0,
      addOrUpdateVisible: false,
      rule: {
        startTime: [{required: true, message: '开始时间不得为空', trigger: "blur"}],
        endTime: [{required: true, message: '结束时间不得为空', trigger: "blur"}],
        totalPrice: [{required: true, message: '总价不得为空', trigger: "blur"}],
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
     * 获取所有预约
     */
    getData(){
      if (Number(sessionStorage.getItem('userType')) !==1){
        this.query.userId = Number(sessionStorage.getItem('userId'))
      }
      getAppointList(this.query).then(res =>{
        this.tableData = res.data.list;
        this.total = res.data.total;
      });


      getCarList({pageSize:100}).then(res =>{
        this.options = res.data.list;
      });

    },

    /**
     * 打开添加框
     */
    addAppointInfo(){
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
    updateAppointInfo(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.addOrUpdateVisible = true;
    },

    /**
     * 按住enter键或者确定按钮提交数据,对后台发请求
     * 如果id为null,就是走添加预约的接口
     * 如果id不为null,就是走更新预约的接口
     */
    formSubmitHandle(){
      this.$refs['form'].validate(valid => {
        if(valid){
          if (this.form.id == null){
            this.form.userId = Number(sessionStorage.getItem('userId'))
            saveAppointInfo(this.form).then(res =>{
              if (res.code === 1){
                this.$message.success('添加成功');
                this.getData();
                this.addOrUpdateVisible = false;
              }
            })
          }else {
            updateAppointInfo(this.form).then(res =>{
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
    delAppointInfo(row){
      this.$confirm('确定要支付吗？', '提示', {
        type: 'warning'
      }).then(action => {
        if (action === 'confirm') {
          payAppointInfo(row).then(res=>{
            if (res.code === 1){
              this.$message.success('支付成功');
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
     * 批量删除预约的接口
     */
    delBatchAppointInfo(){
      this.$confirm('确定要删除所选择的吗？', '提示', {
        type: 'warning'
      }).then(action => {
        if (action === 'confirm') {
          delBatchAppointInfo(this.multipleSelection.join(',')).then(res =>{
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
      this.query.stallId = null;
      this.query.status = null;
      this.query.realName=null
      this.query.stallNumber=null
      this.query.carNumber=null
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
