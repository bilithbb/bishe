<template>
    <el-card shadow="hover">
        <span>两个统计图5</span>
        <div id="lineLineTwo" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import {lineLineOptionTwo } from './twoEcharts';
    export default {
        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('lineLineTwo')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(lineLineOptionTwo(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
