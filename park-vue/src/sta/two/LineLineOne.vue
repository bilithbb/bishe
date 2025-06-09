<template>
    <el-card shadow="hover">
        <span>两个统计图2</span>
        <div id="lineLineOne" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import { histogramLineOptionOne, lineLineOptionOne } from './twoEcharts';
    export default {
        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('lineLineOne')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(lineLineOptionOne(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
