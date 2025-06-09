<template>
    <div class="index">
        <div class="index-top">
<!--            欢迎登录XXX系统-->
            <div class="item">
                <img :src="sellUrl">
                营业额:{{staInfo.totalPrice}}
            </div>
            <div class="item">
                <img :src="personUrl">
                注册人数:{{staInfo.userCount}}
            </div>
            <div class="item">
                <img :src="articleUrl">
                停车位数:{{staInfo.stallCount}}
            </div>
        </div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="近七天" name="first"></el-tab-pane>
        <el-tab-pane label="近十二月" name="second"></el-tab-pane>
        <el-tab-pane label="近三年" name="third"></el-tab-pane>


      </el-tabs>
      <HistogramLineOne :type="query.type" style="margin-top: 10px" :data="histogram"></HistogramLineOne>
<!--      <HistogramOne ref="childRef" :data="histogram" ></HistogramOne>-->
<!--        <LineThree ref="childRef2" :data="histogram2" ></LineThree>-->
      <PieFour :data="pieData" style="margin-top: 10px"></PieFour>
    </div>
</template>

<script>
    import HistogramOne from '@/sta/one/HistogramOne.vue';
    import PieFour from '@/sta/one/PieFour.vue';
    import {
        getOneHistogramOrLineData,
        getPieData,
        getStaInfo,
        getTwoHistogramOrLineData
    } from '@/api/echart';
    import LineOne from '@/sta/one/LineOne.vue';
    import LineTwo from '@/sta/one/LineTwo.vue';
    import LineThree from '@/sta/one/LineThree.vue';
    import HistogramLineOne from '@/sta/two/HistogramLineOne.vue';
    export default {
        name: 'Index',
        components: { HistogramLineOne, LineThree, LineTwo, LineOne, PieFour, HistogramOne},
        data(){
            return{
                personUrl:require('@/assets/image/person.png'),
                sellUrl:require('@/assets/image/sell.png'),
                articleUrl:require('@/assets/image/write.png'),
                staInfo:{},
                histogram:{},
                histogram2:{},
                histogramTwo:{},
                pieData:[],
                query:{
                    type:1
                },
              activeName:'first'
            }
        },
        created() {
            this.getData();
        },
        watch:{
          'query.type':function(newVa,oldVal) {
              getTwoHistogramOrLineData(this.query).then(res =>{
                  this.histogramTwo = res.data;
              })
          },
            // 'query.startTime':function(newVa,oldVal) {
            //     getTwoHistogramOrLineData(this.query).then(res =>{
            //         this.histogramTwo = res.data;
            //     })
            // },
            // 'query.endTime':function(newVa,oldVal) {
            //     getTwoHistogramOrLineData(this.query).then(res =>{
            //         this.histogramTwo = res.data;
            //     })
            // }
        },
        methods:{
            getData(){
                getStaInfo().then(res =>{
                    this.staInfo = res.data;
                });
              getTwoHistogramOrLineData(this.query).then(res =>{
                    this.histogram = res.data;
                });

                getPieData().then(res =>{
                    this.pieData = res.data;
                });
                // getTwoHistogramOrLineData(this.query).then(res =>{
                //     this.histogramTwo = res.data;
                // })
            },

          handleClick(e){
            console.log();
            this.query.type = Number(e.index) +1;
            getTwoHistogramOrLineData(this.query).then(res =>{
              this.histogram = res.data;
            });

          },

            search(){
                getOneHistogramOrLineData(this.query).then(res =>{
                    this.histogram = res.data;
                    this.$refs.childRef.drawLine2('histogramOne',this.histogram);
                });
            },
            reset(){
                this.query.startTime = null;
                this.query.endTime = null;
                getOneHistogramOrLineData(this.query).then(res =>{
                    this.histogram = res.data;
                    this.$refs.childRef.drawLine2('histogramOne',this.histogram);
                });
            },
        }
    };
</script>

<style scoped >
    .index{
        //height: 1100px;
        width: 100%;
        /*background-color: red;*/
        display: inline-flex;
        flex-direction: column;
        background-color: white;
    }
    .index-top{
        /*background-color: #00a854;*/
        background-color: white;
        //flex: 1;
        display:flex;
        justify-content: space-between;
        /*align-items: center;*/
        font-size: 30px;
        /*flex-direction: row;*/
        color: white;
        padding: 10px;
        box-sizing: border-box;
        .item{
            width: 340px;
            height: 100%;
            margin-right: 10px;
            margin-bottom: 10px;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: 700;
            font-size: 20px;
            &:nth-child(1){
                margin-left: 10px;
                background-color: #5edab4;
            }
            &:nth-child(2){
                background-color: #9694ff;
            }
            &:nth-child(3){
                margin-right: 10px;
                background-color: #57caeb;
            }
        }
    }
    .index-middle{
        background-color: white;
        margin-top: 10px;
        flex: 3;
        display: inline-flex;
        .left{
            flex:3;
            padding: 10px;
            /*background-color: #00D1B2;*/
        }
        .right{
            flex: 2;
            padding: 10px;
            /*background-color: #009cf9;*/
        }
    }
    .index-bottom{
        background-color:white;
        margin-top: 10px;
        flex: 3;
    }

    img{
        width: 60px;
        height: 60px;
        display: inline-block;
        margin-right: 10px;
    }
</style>
