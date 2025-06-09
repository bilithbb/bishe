<template>
    <el-card shadow="hover">
        <span>柱状图4</span>
        <div id="histogramFour" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import {histogramOptionFour } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('histogramFour')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramOptionFour(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
