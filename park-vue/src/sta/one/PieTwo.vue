<template>
    <el-card shadow="hover">
        <span>饼图2</span>
        <div id="pieTwo" style="width: 1200px;height: 550px;"></div>
    </el-card>
</template>

<script>

    import echarts from 'echarts'
    import { pieOptionTwo } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{},
        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('pieTwo')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(pieOptionTwo(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
