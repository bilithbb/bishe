<template>
    <div>
    <el-card shadow="hover">
        <span>折线图2</span>
        <div id="mainTwo" style="width: 1200px;height: 550px;"></div>
    </el-card>
    </div>
</template>

<script>

    import echarts from 'echarts'
    import { curveLineOptionTwo } from './oneEcharts';
    export default {
        name: 'LineTwo',

        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('mainTwo')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(curveLineOptionTwo(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
