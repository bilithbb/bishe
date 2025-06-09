<template>
    <div>
    <el-card shadow="hover">
        <span>折线图4</span>
        <div id="mainFour" style="width: 1200px;height: 550px;"></div>
    </el-card>
    </div>
</template>

<script>

    import echarts from 'echarts'
    import { curveLineOptionFour } from './oneEcharts';
    export default {
        name: 'History',

        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('mainFour')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(curveLineOptionFour(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
