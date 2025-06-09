<template>
    <div class="notice">
        <ul>
            <li v-for="(item, index) in list" :key="index" @click="toDetail(item.id)">
                            <span class="index">
                                {{ index + 1 }}
                            </span>
                <span>{{ item.title }}</span>
                <span class="time">{{ item.createTime }}</span>
            </li>
        </ul>
        <el-pagination
                style="margin-top: 20px"
                :current-page="query.pageNum"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="query.pageSize"
                :total="total"
                background
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="pageSizeChange"
                @current-change="pageCurrentChange">
        </el-pagination>
    </div>
</template>

<script>

    import { getNoticeList } from '../api/notice';

    export default {
        name: 'IndexNotice',
        data(){
            return{
                list:[],
                query:{
                    pageNum:1,
                    pageSize:10
                },
                total:0,
            }
        },
        created() {
            this.getData();
        },
        methods:{
            getData(){
                getNoticeList(this.query).then(res =>{
                    this.list = res.data.list;
                    this.total = res.data.total;
                })
            },
            toDetail(id){
                this.$router.push({
                    path:'/notice-detail',
                    query:{id}
                })
            },

            /**
             * 分页(改变页码)
             * @param val
             */
            pageCurrentChange(val){
                this.$set(this.query, 'pageNum', val);
                this.getData();
            },

            /**
             * 分页(改变每页条数)
             * @param val
             */
            pageSizeChange(val){
                this.$set(this.query, 'pageSize', val);
                this.getData();
            },

        }
    };
</script>

<style scoped >

    .notice{
        /*height: 600px;*/
        height: auto;
        width: 1300px;
        /*margin: 20px auto;*/
        /*background-color: white;*/
        /*border: 1px solid #eaeaea;*/
        /*margin-top: 20px;*/
        /*background-color: red;*/
        box-sizing: border-box;
        padding: 10px;
        margin: 0 auto;
        ul {
            list-style: none;
            margin-top: 10px;
            background-color: white;

            li {
                margin-bottom: 20px;
                border-bottom: #ccc dashed 1px;
                cursor: pointer;
                font-size: 13px;
                height: 100%;
                padding: 5px;

                &:first-child .index {
                    background-color: #FE2D46;
                }

                &:nth-child(2) .index {
                    background-color: #FF6600;
                }

                &:nth-child(3) .index {
                    background-color: #FAA90E;
                }

                &:hover {
                    background-color: var(--hover-color);
                    border-radius: 3px;
                }

                span {
                    color: var(--text-color);
                }
                .time{
                    float: right;
                }
                .index {
                    display: inline-block;
                    text-align: center;
                    width: 20px;
                    height: 20px;
                    margin-right: 10px;
                    line-height: 20px;
                    background-color: #7f7f8c;
                    color: #fff;
                }
            }
        }
    }
    .notice .item{
        width: 100%;
        height: 60px;
        /*background-color: red;*/
        color: #343434;
        text-align: left;
        line-height: 60px;
        padding-left: 30px;
        clear: both;
        border-bottom: #ccc dashed 1px;
        cursor: pointer;
    }
    .item:visited {
        text-decoration: none;
    }

</style>
