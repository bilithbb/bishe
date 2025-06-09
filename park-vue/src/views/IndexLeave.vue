<template>
    <div style="height: 100%">
        <!-- banner -->
        <div class="message-banner" :style="cover">
            <!-- 弹幕输入框 -->
            <div class="message-container">
                <h1 class="message-title">留言板</h1>
                <div class="message-input-wrapper">
                    <el-input class="input" v-model="content" placeholder="说点什么吧" @keyup.enter.native="addToList"
                              @focus="show = true"></el-input>
                    <el-button style="opacity: .6;" class="ml-3" round @click="addToList" v-show="show">发送</el-button>
                </div>
            </div>
            <!-- 弹幕列表 -->
            <div class="barrage-container">
                <!--                哈哈哈哈-->
<!--                                {{barrageList}}-->
                <div class="barrage-container">
                    <vue-baberrage :barrageList="barrageList">
                        <template v-slot:default="slotProps">
                        <span class="barrage-items">
                            <img :src="slotProps.item.imageUrl" width="30" height="30" style="border-radius:50%" />
                            <span class="ml-2">{{ slotProps.item.realName }} :</span>
                            <span class="ml-2">{{ slotProps.item.leaveMessage}}</span>
                        </span>
                        </template>
                    </vue-baberrage>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { getLeaveList, saveLeaveInfo } from '../api/leave';
    import {  MESSAGE_TYPE } from 'vue-baberrage';
    export default {
        metaInfo: {
            meta: [{
                name: 'keyWords',
                content: "拾壹博客,开源博客,www.shiyit.com"  //变量或字符串
            }, {
                name: 'description',
                content: "一个专注于技术分享的博客平台,大家以共同学习,乐于分享,拥抱开源的价值观进行学习交流"
            }]
        },
        mounted() {
            document.title = "留言板";
            this.getData();
        },
        data() {
            return {
                show: false,
                content: "",
                count: null,
                timer: null,
                list: [
                    {
                        id: 1,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "人生若只如初见",
                        time: 1,
                        barrageStyle: 'red'
                    },
                    {
                        id: 2,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "何事秋风悲画扇",
                        time: 2,
                        barrageStyle: 'green'
                    },
                    {
                        id: 3,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "等闲变却故人心",
                        time: 3,
                        barrageStyle: 'normal'
                    },
                    {
                        id: 4,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "却道故人心易变",
                        time: 4,
                        barrageStyle: 'blue'
                    },
                    {
                        id: 5,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "骊山语罢清宵半",
                        time: 5,
                        barrageStyle: 'yellow'
                    },
                    {
                        id: 6,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "泪雨霖铃终不怨",
                        time: 6,
                        barrageStyle: 'normal'
                    },
                    {
                        id: 7,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "何如薄幸锦衣郎",
                        time: 7,
                        barrageStyle: 'red'
                    },
                    {
                        id: 8,
                        avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
                        msg: "比翼连枝当日愿",
                        time: 8,
                        barrageStyle: 'normal'
                    },

                ],
                barrageList:[],
            };
        },
        methods: {
            getData() {
                getLeaveList({pageSize:1000}).then(res => {
                    console.log(res);
                    this.barrageList = res.data.list;
                });
            },

            addToList() {
                if (this.count) {
                    this.$message.error('30秒后才能再次留言');
                    return false;
                }
                if (this.content.trim() === "") {
                    this.$message.error('留言不能为空');
                    return false;
                }
                const message = {
                    userId:this.$store.state.userInfo.id,
                    leaveMessage: this.content,
                    imageUrl:this.$store.state.userInfo.imageUrl,
                    time: Math.floor(Math.random() * (21 - 10) + 10)
                };

                this.content = "";
                saveLeaveInfo(message).then(res => {
                    this.barrageList.push(message);
                    this.$message({
                        message: '留言成功',
                        type: 'success'
                    });
                }).catch(err => {
                });
                // const TIME_COUNT = 30;
                // if (!this.timer) {
                //     this.count = TIME_COUNT;
                //     this.timer = setInterval(() => {
                //         if (this.count > 0 && this.count <= 30) {
                //             this.count--;
                //         } else {
                //             clearInterval(this.timer);
                //             this.timer = null;
                //         }
                //     }, 1000);
                // }
            },

        },
        computed: {
            cover() {
                const cover = require('@/assets/image/back.png');
                return "background: url(" + cover + ") center center / cover no-repeat";
            }
        }
    };
</script>

<style  scoped>
    /deep/ .el-input__inner {
        border-radius: 50px;
        opacity: .6;
    }

    .message-banner {
        /*position: absolute;*/
        /*top: 100px;*/
        /*left: 0;*/
        /*right: 0;*/
        /*bottom: 0;*/
        /*width: 100%;*/
        height: 500px;
        background-color: #49b1f5;
        animation: header-effect 1s;


        .message-container {
            position: absolute;
            width: 360px;
            top: 35%;
            left: 0;
            right: 0;
            text-align: center;
            z-index: 5;
            margin: 0 auto;
            color: #fff;

            .message-title {
                color: #eee;
                animation: title-scale 1s;
            }

            .message-input-wrapper {
                display: flex;
                justify-content: center;
                height: 2.5rem;
                margin-top: 2rem;

                .ml-3 {
                    animation: left-in 1s ease;

                    @keyframes left-in {
                        0% {
                            transform: translateY(-500%);
                        }

                        100% {
                            transform: translateX(0);
                        }
                    }
                }
            }
        }

        .barrage-container {
            /*position: absolute;*/
            top: 50px;
            left: 0;
            right: 0;
            bottom: 0;
            height: calc(100% -50px);
            width: 100%;

            .barrage-items {
                background: #000;
                border-radius: 100px;
                color: #fff;
                padding: 5px 10px 5px 5px;
                align-items: center;
                display: flex;
                margin-top: 10PX;
            }
        }

    }

    /deep/.message-banner .message-container .message-input-wrapper .ml-3{
      height: 30px;
    }
</style>
