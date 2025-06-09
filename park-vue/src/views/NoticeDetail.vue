<template>
    <div class="notice-detail">
        <div class="top">
            {{info.title}}
        </div>

        <v-md-preview v-highlight style="line-height: 30px"  class="notice-content" :text="info.content" ref="preview" />
    </div>
</template>

<script>
    import { getNoticeInfo } from '../api/notice';

    export default {
        name: 'IndexNotice',
        data(){
            return{
                info:{}
            }
        },
        created() {
            this.getData();
        },
        methods:{
            getData(){
                getNoticeInfo({id:this.$route.query.id}).then(res =>{
                    this.info = res.data;
                })
            }
        }
    };
</script>

<style scoped>

    /deep/div.v-md-editor-preview.notice-content{
        line-height: 30px;
    }

    .notice-detail{
        /*height: 400px;*/
        width: 1200px;
        margin: 20px auto;
        background-color: white;
        border: 1px solid #eaeaea;
        /*margin-top: 20px;*/

    }
    .notice-detail .top{
        width: 100%;
        height: 100px;
        /*background-color: red;*/
        text-align: center;
        line-height: 100px;
        font-size: 24px;
        font-weight: 800;
        font-family: Microsoft Yahei;
        /*line-height: 40px;*/
        border-bottom: #cdcdcd 1px solid;
        margin-bottom: 10px;

    }

    .notice-detail .bottom{
        padding: 20px;
        line-height: 40px;
    }
    /deep/.vuepress-markdown-body:not(.custom){
      padding: 10px;
    }
    /deep/ .vuepress-markdown-body h3 {
      font-size: 0.46875rem;
    }
    /deep/ .vuepress-markdown-body h2 {
      font-size: 0.56875rem;
    }
    /deep/ .vuepress-markdown-body p{
      line-height: 0.625rem;
      font-size: 0.234375rem;
    }
</style>
