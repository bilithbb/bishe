<template>
    <div id="mainOne" style="width: 1200px;height: 550px;"></div>
</template>

<script>

    import echarts from 'echarts'
    import { curveLineOptionOne } from './oneEcharts';
    export default {
        name: 'LineOne',

        props:{
            data:{},
            name:{}

        },

        mounted() {

            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('mainOne')
                })
            },200)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(curveLineOptionOne(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
