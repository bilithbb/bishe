<template>
    <div class="sidebar">
        <el-menu
                class="sidebar-el-menu"
                :default-active="onRoutes"
                :collapse="collapse"
                background-color="#324157"
                text-color="#bfcbd9"
                active-text-color="#20a0ff"
                unique-opened
                router
        >
            <template v-for="item in items">
                <template v-if="item.subs">
                    <!--                    一级菜单-->
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-submenu
                                    v-if="subItem.subs"
                                    :index="subItem.index"
                                    :key="subItem.index"
                            >
                                <template slot="title"><i :class="subItem.icon"></i>{{ subItem.title }}</template>
                                <!--                                二级菜单-->
                                <el-menu-item
                                        v-for="(threeItem,i) in subItem.subs"
                                        :key="i"
                                        :index="threeItem.index"
                                ><i :class="threeItem.icon"></i>{{ threeItem.title }}</el-menu-item>
                            </el-submenu>
                            <el-menu-item
                                    v-else
                                    :index="subItem.index"
                                    :key="subItem.index"
                            ><i :class="subItem.icon"></i>{{ subItem.title }}</el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items:[],
                itemList: [
                    {
                        "id":4,
                        "pid":1,
                        "icon":"el-icon-s-order",
                        "index":"3",
                        "title":"统一管理",
                        "subs":[

                          {
                            "id":11,
                            "pid":4,
                            "icon":"el-icon-plus",
                            "index":"sta",
                            "title":"统计信息",
                            "subs":null
                          },

                            {
                                "id":11,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"person",
                                "title":"个人信息",
                                "subs":null
                            },
                            {
                                "id":9,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"user",
                                "title":"用户信息",
                                "subs":null
                            },
                          {
                            "id":9,
                            "pid":4,
                            "icon":"el-icon-plus",
                            "index":"sort",
                            "title":"分类信息",
                            "subs":null
                          },



                          // {
                          //   "id":9,
                          //   "pid":4,
                          //   "icon":"el-icon-plus",
                          //   "index":"book",
                          //   "title":"书籍信息",
                          //   "subs":null
                          // },

                          {
                            "id":9,
                            "pid":4,
                            "icon":"el-icon-plus",
                            "index":"stall",
                            "title":"停车位信息",
                            "subs":null
                          },
                          {
                            "id":9,
                            "pid":4,
                            "icon":"el-icon-plus",
                            "index":"car",
                            "title":"汽车信息",
                            "subs":null
                          },
                          {
                            "id":9,
                            "pid":4,
                            "icon":"el-icon-plus",
                            "index":"appoint",
                            "title":"预约信息",
                            "subs":null
                          },
                          {
                            "id":9,
                            "pid":4,
                            "icon":"el-icon-plus",
                            "index":"notice",
                            "title":"公告信息",
                            "subs":null
                          },
                          // {
                          //   "id":9,
                          //   "pid":4,
                          //   "icon":"el-icon-plus",
                          //   "index":"leave",
                          //   "title":"留言信息",
                          //   "subs":null
                          // },
                          // {
                          //   "id":9,
                          //   "pid":4,
                          //   "icon":"el-icon-plus",
                          //   "index":"advert",
                          //   "title":"广告信息",
                          //   "subs":null
                          // },
                          // {
                          //   "id":9,
                          //   "pid":4,
                          //   "icon":"el-icon-plus",
                          //   "index":"mon",
                          //   "title":"监控信息",
                          //   "subs":null
                          // },
                   ]
                    },
                ],
                itemList2: [
                    {
                        "id":4,
                        "pid":1,
                        "icon":"el-icon-s-order",
                        "index":"3",
                        "title":"员工管理",
                      "subs":[
                        {
                          "id":11,
                          "pid":4,
                          "icon":"el-icon-plus",
                          "index":"person",
                          "title":"个人信息",
                          "subs":null
                        },
                        {
                          "id":9,
                          "pid":4,
                          "icon":"el-icon-plus",
                          "index":"stall",
                          "title":"停车位信息",
                          "subs":null
                        },
                        {
                          "id":9,
                          "pid":4,
                          "icon":"el-icon-plus",
                          "index":"car",
                          "title":"汽车信息",
                          "subs":null
                        },
                        {
                          "id":9,
                          "pid":4,
                          "icon":"el-icon-plus",
                          "index":"appoint",
                          "title":"预约信息",
                          "subs":null
                        },
                        {
                          "id":9,
                          "pid":4,
                          "icon":"el-icon-plus",
                          "index":"notice",
                          "title":"公告信息",
                          "subs":null
                        },
                      ]
                    },
                ],
            };
        },
        computed: {
            onRoutes() {
                return this.$route.path.replace('/', '');
            },
        },
        created() {
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
                bus.$emit('collapse-content', msg);
            });
            //初始化menuList
            if ("1" === sessionStorage.getItem('userType')){
                this.items = this.itemList;
            }else if ("3" === sessionStorage.getItem('userType')){
                this.items = this.itemList2;
            }
        }
    };
</script>

<style scoped>
    .sidebar {
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom: 0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar {
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse) {
        width: 250px;
    }
    .sidebar > ul {
        height: 100%;
    }
</style>
