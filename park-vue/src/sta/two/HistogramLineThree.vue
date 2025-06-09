<template>
    <el-card shadow="hover">
        <span>两个统计图4</span>
        <div id="histogramLineThree" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import { histogramLineOptionThree } from './twoEcharts';
    export default {
        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('histogramLineThree')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(histogramLineOptionThree(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
