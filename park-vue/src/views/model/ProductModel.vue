<template>
  <el-card class="item" >
    <el-image :src="model.imageUrl" class="image"></el-image>
    <div class="title">
      <div class="name">
        {{model.stallNumber}}
        <span style="font-size: 18px;color: red;margin-left: 10px">{{model.price}}元</span>
      </div>
      <div class="introduction" :title="model.desc">
        {{model.desc}}
      </div>
      <div class="time">
        <div class="left">
          {{model.createTime}}
        </div>
        <div class="right" @click="toDetail(model)">
          点击详情>
        </div>
      </div>
    </div>
  </el-card>
</template>
<script>

import { updateStallCountInfo } from '@/api/stall';

export default {
  name: 'BookModel',
  props:{
    model:{},
  },
  methods:{
    toDetail(row){
      this.$router.push({
        path:'/detail',
        query:{id:row.id}
      })

      updateStallCountInfo({id:row.id}).then(res =>{

      })

    }
  }
};
</script>


<style scoped>
.item{
  width: 6.3rem;
  background: white;
  //background: red;
  height: 7rem;
  border: solid 1px #f2f2f2;
  display: inline-flex;
  flex-direction:column;
  cursor: pointer;
  margin-left: 0.3125rem;
  margin-top: 0.078125rem;
  padding: 0.15625rem;
  .image{
    flex: 3;
  //background: green;
    width: 100%;
    height: 4.125rem;
  }
  .title{
    flex: 1;
  //background: #3e9eff;


    .name{
      font-family: Microsoft YaHei,serif;
      font-size: 0.21875rem;
      font-weight: bold!important;
      //display: block;
      //text-align: center;
      line-height: 0.46875rem
    }
    .introduction{
      font-family: Microsoft YaHei,serif;
      font-size: 0.1875rem;
      line-height: 0.3125rem;
      margin-top: 0.05rem;
      color: #696969;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 3;
      overflow: hidden;
      height:0.703125rem;
      //background: red;
    }
    .time{
      width: 100%;
      height: 0.78125rem;
      //background: green;
      display: inline-flex;
      flex-direction: row;
      line-height: 0.78125rem;
      justify-content: space-between;
      .left{
        color: #525252;
        font-size: 0.15625rem;
        font-family: Microsoft YaHei,"\5FAE\8F6F\96C5\9ED1",sans-serif;
      }
      .right{
        font-size: 0.15625rem;
        color: #33724e;
        font-weight: 700;
      }
    }
  }
}


</style>
