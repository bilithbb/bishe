<template>
    <el-card shadow="hover">
        <span>饼图1</span>
        <div id="pieOne" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import { pieOptionOne } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{},
        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('pieOne')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(pieOptionOne(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
