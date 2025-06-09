<template>
  <div id="histogramLineOne" style="width: 100%;height: 550px;"></div>
</template>

<script>

    import echarts from 'echarts'
    import { histogramLineOptionOne } from './twoEcharts';
    export default {
        props:{
            data:{},
            name:{},
            type:1
        },

      watch:{
          type:function (newVal,oldVal){
            setTimeout(() =>{
              this.$nextTick(function() {
                this.drawLine('histogramLineOne')
              })
            },1000)
          }
      },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('histogramLineOne')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramLineOptionOne(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
