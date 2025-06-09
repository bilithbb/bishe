<template>
  <div class="activity">
    <div class="item">
      <div class="item-left">
        <el-image :src="activity.imageUrl" class="image"></el-image>
      </div>
      <div class="item-right">
       <div class="title"  @click="toDetail(activity.id)">
         {{activity.title}}
       </div>
        <div class="content">
          {{activity.desc}}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props:{
    activity:{}
  },
  methods:{
    toDetail(id){
      this.$router.push({
        path:'/advert-detail',
        query:{id:id}
      })
    }
  }
}


</script>



<style scoped>
.activity{
  width: 100%;
  //height: 500px;
  //background: red;
  border: solid 1px #f2f2f2;
  padding: 10px;
  .item{
    width: 100%;
    height: 294px;
    display: inline-flex;
    margin-top: 10px;
    border-bottom: solid 1px #f2f2f2;
    .item-left{
      flex: 3;
      height: 100%;
      //background: green;
      .image{
        width: 100%;
        height: 100%;
      }
    }
    .item-right{
      flex: 4;
      height: 100%;
      //background: yellow;
      padding: 10px;
      .title{
        display: inline-block;
        font-family: Microsoft YaHei,serif;
        font-size: 15px;
        color: #5a5a5a;
        font-weight: bold;
        text-decoration: none;
        line-height: 140%;
        cursor: pointer;
      }
      .time{
        display: block;
        font-family: Microsoft YaHei,serif;
        font-size: 14px;
        padding-top: 10px;
        color: #929292;
        font-weight: normal;
        text-decoration: none;
        line-height: 140%;
      }
      .content{
        line-height: 30px;
        width: 100%;
        overflow: hidden;
        word-wrap: break-word;
        display: inline-block;
        font-family: Microsoft YaHei,serif;
        font-size: 14px;
        padding-top: 16px;
        color: #666666;
        font-weight: normal;
        text-decoration: none
      }
    }
  }
}
</style>
