<template>
    <el-card shadow="hover">
        <span>柱状图3</span>
        <div id="histogramThree" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import {histogramOptionThree } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('histogramThree')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramOptionThree(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
