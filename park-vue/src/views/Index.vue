<template>
    <div class="index">
      <Banner></Banner>
      <LineInfo title="停车位" sub-title="Stall Info"></LineInfo>
      <IndexServe></IndexServe>
      <LineInfo title="为您推荐" sub-title="Recommend Info"></LineInfo>
      <Recommend></Recommend>
    </div>
</template>

<script>

    import Banner from '@/views/model/Banner.vue';
    import IndexServe from '@/views/IndexServe.vue';
    import LineInfo from '@/views/model/Line.vue';
    import Recommend from '@/views/model/Recommend.vue';

    export default {
        name: 'Index',
      components: { Recommend, LineInfo, IndexServe, Banner },
      created(){
        console.log('宽度'+document.body.clientWidth);
      }
    };
</script>

<style scoped >
    .index{
      width: 20.3125rem;
      margin: 0 auto;
      //background: red;
    }

</style>
