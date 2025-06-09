<template>
<!--        <div>-->
<!--                <span>11111</span>-->
<!--                <div id="histogramOne" :style="[{width:width+'px'},{height: height+'px'}]"></div>-->
                <div id="histogramOne" style="width: 1250px;height: 400px;"></div>
<!--                <div id="histogramOne" style="width:100%;height: 100%"></div>-->
<!--        </div>-->

</template>

<script>

    import echarts from 'echarts'
    import { histogramOptionOne } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{},

        },
        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('histogramOne')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramOptionOne(this.data,this.name));
            },

            drawLine2(id,data) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramOptionOne(data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
