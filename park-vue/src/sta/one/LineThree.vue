<template>
    <div>
        <div id="mainThree" style="width: 1250px;height: 550px;"></div>
    </div>
</template>

<script>

    import echarts from 'echarts'
    import { curveLineOptionThree } from './oneEcharts';
    export default {
        name: 'LineThree',

        props:{
            data:{},
            name:{}

        },

        mounted() {
            setTimeout(() =>{
                this.$nextTick(function() {
                    this.drawLine('mainThree')
                })
            },1000)
        },

        methods:{
            drawLine(id) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption(curveLineOptionThree(this.data,this.name));
            },
        }
    };
</script>

<style scoped>

</style>
