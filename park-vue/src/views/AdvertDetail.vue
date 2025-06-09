
<template>
  <div class="detail">
    <div class="top">
      {{detailInfo.title}}
    </div>
    <div class="content">
      <v-md-preview   v-highlight  :text="detailInfo.content" ref="preview" />
    </div>
    <div class="content">
      <el-button @click="addCollect" type="success">{{detailInfo.isCollect?'取消收藏':'点击收藏'}}</el-button>
    </div>

  </div>
</template>
<script>


import { getAdvertList } from '@/api/advert';
import { saveCollectInfo } from '@/api/collect';

export default {
  name: 'NoticeDetail',
  data(){
    return{
      detailInfo:{},
      query:{
        id:this.$route.query.id
      },
    }
  },
  created(){
    this.getData();
  },
  methods:{
    getData(){
      getAdvertList({id:this.$route.query.id,userId:sessionStorage.getItem('userId')}).then(res =>{
        this.detailInfo = res.data.list[0];
      });
    },
    addCollect(){
      if(sessionStorage.getItem('userId') == null){
        this.$message.warning('请登录');
        return false;
      }
      const collectInfo = {
        userId:sessionStorage.getItem('userId'),
        advertId: this.detailInfo.id
      }
      saveCollectInfo(collectInfo).then(res =>{
        this.$message.success(res.msg);
        this.getData();
      })
    }

  }
};
</script>


<style scoped >
.detail{
  width: 1170px;
  margin: 10px auto;
  //height: 400px;
  //background: red;
  .top{
    height: 50px;
    width: 100%;
    //background: #00a854;
    text-align: center;
    line-height: 50px;
    font-size: 24px;
    font-weight: 900;
    border-bottom: 1px solid #e8f0e9;
  }


  .place{
    width: 100%;
    //height: 300px;
    padding: 5px;
    //background: red;
  }
  .submit{
    width: 100%;
    height: 40px;
    margin-top: 20px;
    .cha{
      width: 100%;
      height: 100%;
    }
  }


  /deep/.vuepress-markdown-body:not(.custom){
    padding: 0 0;
  }
  /deep/ .vuepress-markdown-body h3 {
    font-size: 30px;
  }
  /deep/ .vuepress-markdown-body img{
    width: 100%;
    height: 500px;
    text-align: center;
    //margin-left: 200px;
  }
  /deep/ .vuepress-markdown-body p{
    line-height: 40px;
  }
}
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
