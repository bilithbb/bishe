<template>
  <div class="index-Activity">
    <div class="Activity-content">
      <NoticeModel  v-for="(item,index) in noticeList" :activity="item" :key="index"></NoticeModel>
      <el-pagination
          :current-page="query.pageNum"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="query.pageSize"
          :total="total"
          style="margin-top: 10px"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="pageSizeChange"
          @current-change="pageCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import NoticeModel from "./model/NoticeModel.vue";
import { getNoticeList } from '@/api/notice';
import { getAdvertList } from '@/api/advert';
export default {
  data(){
    return{
      noticeList:[],
      query:{
        pageNum:1,
        pageSize:10
      },
      total:0,
    }
  },
  components:{NoticeModel},
  created(){
    this.getData();
  },
  methods:{
    getData(){
      getAdvertList(this.query).then(res =>{
        this.noticeList = res.data.list;
        this.total =res.data.total;
      })
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
  }
}

</script>


<style scoped >
.index-Activity{
  width: 85%;
  //height: 1500px;
  margin: 0 auto;
  .banner{
    width: 100%;
    height: 350px;
    .image{
      height: 100%;
      width: 100%;
    }
  }
  .breadcrumb{
    height: 50px;
    width: 100%;
    //background: green;
    line-height: 50px;
    margin-top: 20px;
    cursor: pointer;
    .title{
      font-family: 微软雅黑,serif;
      line-height: 50px;
      .one-title{
        color: #5a5a5a;
        font-size: 14px;

      }
      .sub-title{
        color: #333333;
        font-size: 12px;
      }
    }
  }
  .Activity-content{
    width: 100%;
  }
}
</style>
