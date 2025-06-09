<template>
        <div id="pieFour" style="width: 1250px;height: 400px;"></div>
</template>

<script>

    import echarts from 'echarts'
    import { pieOptionFour } from './oneEcharts';
    export default {
        props:{
            data:{},
            name:{},
        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('pieFour')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(pieOptionFour(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
