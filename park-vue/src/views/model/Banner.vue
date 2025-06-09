<template>
    <div class="home-banner">
        <el-carousel height="500px">
            <el-carousel-item v-for="(item,index) in bannerList" :key="index">
                <img :src="item.imageUrl" style="cursor: pointer" @click="toDetail(item.id)">
            </el-carousel-item>
        </el-carousel>
    </div>
</template>


<script>






    import { getStallList } from '@/api/stall';

    export default {
        name:'Banner',

        data () {
            return {
                bannerList:[],
              baseUrl:null
            }
        },
        created() {
            this.getData();
        },

        methods:{

            getData(){
                //是否推荐,isBanner=2
              getStallList({isBanner:2}).then(res =>{
                this.bannerList = res.data.list;
              })
            },

            toDetail(id){
              this.$router.push({
                path:'/detail',
                query:{id:id}
              });
            },

        },
    }
</script>

<style scoped >
    .home-banner {
        //width: 1240px;
        height: 7.8125rem;
        /*position: absolute;*/
        /*left: 0;*/
        /*background-color: red;*/
        /*top: 0;*/
        z-index: 98;
        margin-top: 0.15625rem;

        img {
            width: 100%;
            height: 7.8125rem;
        }
    }
</style>
