<template>
    <el-card shadow="hover">
        <span>饼图3</span>
        <div id="pieThree" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import { pieOptionThree } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{},
        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('pieThree')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(pieOptionThree(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
