<template>
    <el-card shadow="hover">
        <span>柱状图2</span>
        <div id="histogramTwo" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import {histogramOptionTwo } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('histogramTwo')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramOptionTwo(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
