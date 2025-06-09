<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入车位名" v-model="query.stallName" class="search-input"></el-input>
      <el-button type="success" round class="search-btn" @click="getData">点击搜索</el-button>
      <el-button type="primary" round class="search-btn" @click="reset">点击重置</el-button>
    </div>
    <div class="sort">
      <div class="item" @click="clickSort(item,index)" v-for="(item,index) in sortList" :class="{'isClick':index===currentKey}">
        {{item.sortName}}
      </div>
    </div>

        <ProductModel v-if="list.length > 0" :indexNumber="index" v-for="(item,index) in list" @reloadModel="getData"  :model="item" :key="index"></ProductModel>


    <el-empty style="margin: 0 auto" v-if="list.length === 0" description="暂无" />
    <el-pagination
        :current-page="query.pageNum"
        :page-sizes="[8, 20, 50, 100]"
        :page-size="query.pageSize"
        :total="total"
        style="margin-top: 10px"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="pageSizeChange"
        @current-change="pageCurrentChange">
    </el-pagination>
  </div>
</template>
<script>
import { getSortList } from '@/api/sort';
import ProductModel from '@/views/model/ProductModel.vue';
import { getStallList } from '@/api/stall';

export default {
  name: 'IndexServe',
  components: { ProductModel},
  created(){
    this.getData();
    this.getSortList();
  },

  data(){
    return{
      list:[],
      query:{
        pageNum:1,
        pageSize:8,
        stallName:null,
        sortName:null,
        authorName:null,
        createPlace:null,
        status:1
      },
      total:0,
      sortList:[],
      currentKey:0,
      baseUrl:null
    }
  },

  methods:{
    getData(){
      this.query.loginUserId = sessionStorage.getItem('userId')
      getStallList(this.query).then(res =>{
        this.list =res.data.list;
        this.total = res.data.total;
      })
    },
    getSortList(){
      getSortList({pageSize:100}).then(res =>{
        this.sortList = res.data.list;
        this.sortList.unshift({
          id:0,
          sortName:'全部'
        })
      })
    },

    clickSort(item,index){
      this.currentKey = index;
      if (item.id !== 0){
        this.query.sortId = item.id;
      }else {
        this.query.sortId = null;
      }
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
    reset(){
      this.query.pageNum = 1;
      this.query.stallName = null;
      this.query.createPlace = null;
      this.query.authorName = null;
      this.query.sortName = null;
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
  }
};
</script>



<style scoped>
.search{
  width: 100%;
  height:0.9375rem;
  margin-top: 0.15625rem;
//background: red;
  text-align: center;
  .search-input{
    line-height: 0.9375rem;
    width: 3.90625rem;
    margin-left: 0.3125rem;

  }
  .search-btn{
    width: 1.5625rem;
    margin-left: 0.3125rem;
    font-size: 0.1875rem;
  }
}
/deep/ .el-input--small .el-input__inner{
  border-radius: 1.09375rem;
  box-shadow: #5a5a5a;
}
.sort{
  width: 100%;
  //height: 60px;
  padding: 0.15625rem 3.125rem;
  display: inline-flex;

//padding: 0 ;
  .item{
    width: 1.875rem;
    cursor: pointer;
    height: 100%;
    text-align: center;
    line-height: 0.625rem;
  //background: green;
    margin-left: 0.15625rem;
    border-radius: 0.46875rem;
    font-size: 0.21875rem;
    color: #666666;

    &:hover{
      background: #41b982;
      color: #ffffff;
    }
  }
  .isClick{
    background: #41b982;
    color: #ffffff;
  }
}

/deep/.el-input--small .el-input__inner{
  height: 0.515625rem !important;
  font-size: 0.203125rem;
}
</style>
