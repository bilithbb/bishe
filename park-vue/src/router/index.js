import Vue from 'vue';
import Router from 'vue-router';
//import routerJs from '@vue/cli-service/generator/routerJs/template/src/routerJs';

Vue.use(Router);


const router = new Router({
    routes: [
        {
            path: '/Home',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
        meta: { title: '自述文件' },
        children: [
            {
                path: '/person',
                    component: () => import(/* webpackChunkName: "icon" */ '../page/Person'),
                meta: { title: '个人信息' }
            },
            {
                path: '/user',
                    component: () => import(/* webpackChunkName: "icon" */ '../page/User'),
                meta: { title: '用户信息' }
            },
            {
                path: '/leave',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Leave.vue'),
                meta: { title: '留言信息' }
            },
            {
                path: '/advert',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Advert.vue'),
                meta: { title: '广告信息' }
            },
            {
                path: '/stall',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Stall'),
                meta: { title: '停车位信息' }
            },
            {
                path: '/notice',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Notice.vue'),
                meta: { title: '公告信息' }
            },
            {
                path: '/car',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Car.vue'),
                meta: { title: '汽车信息' }
            },
            {
                path: '/appoint',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Appoint.vue'),
                meta: { title: '预约信息' }
            },
            {
                path: '/notice',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Notice.vue'),
                meta: { title: '公告信息' }
            },
            {
                path: '/sort',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Sort.vue'),
                meta: { title: '分类信息' }
            },
            {
                path: '/mon',
                component: () => import(/* webpackChunkName: "icon" */ '../page/Mon.vue'),
                meta: { title: '监控信息' }
            },
            {
                path: '/sta',
                component: () => import(/* webpackChunkName: "404" */ '../page/Sta.vue'),
                meta: { title: '统计管理'},
            },

            {
                path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../page/404.vue'),
                meta: { title: '404' }
            },
            {
                path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../page/403.vue'),
                meta: { title: '403' }
            }
        ]
        },

        {
            path:'/',
            redirect:'/index'
        },

        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../views/Layout/Content.vue'),
            meta: { title: '自述文件' },
            children: [

                {
                    path: '/index',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../views/Index.vue'),
                    meta: { title: '首页管理' }
                },
                {
                    path: '/index-leave',
                    component: () => import(/* webpackChunkName: "icon" */ '../views/IndexLeave.vue'),
                    meta: { title: '留言信息' }
                },
                {
                    path: '/index-advert',
                    component: () => import(/* webpackChunkName: "icon" */ '../views/IndexAdvert.vue'),
                    meta: { title: '广告信息' }
                },
                {
                    path: '/advert-detail',
                    component: () => import(/* webpackChunkName: "icon" */ '../views/AdvertDetail.vue'),
                    meta: { title: '广告信息' }
                },
                {
                    path: '/index-notice',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../views/IndexNotice.vue'),
                    meta: { title: '公告管理' }
                },
                {
                    path: '/notice-detail',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../views/NoticeDetail.vue'),
                    meta: { title: '详情管理' }
                },
                {
                    path: '/index-order',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../views/IndexOrder.vue'),
                    meta: { title: '订单管理' }
                },
                {
                    path: '/detail',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../views/IndexDetail.vue'),
                    meta: { title: '详情管理' }
                },
                {
                    path: '/member',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../views/Member/index.vue'),
                    meta: {title: '个人中心'},
                    children: [
                        {
                            path: '',
                            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Member/components/Person.vue'),
                            meta: {title: '个人信息'}
                        },
                        {
                            path: 'car',
                            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Member/components/Car.vue'),
                            meta: {title: '我的汽车'}
                        },
                        {
                            path: 'leave',
                            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Member/components/Leave.vue'),
                            meta: {title: '我的留言'}
                        },
                        {
                            path: 'collect',
                            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Member/components/Collect.vue'),
                            meta: {title: '我的收藏'}
                        },
                    ]
                },
            ]
        },

        {
            path: '/login',
                component: () => import(/* webpackChunkName: "login" */ '../page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '/register',
                component: () => import(/* webpackChunkName: "login" */ '../page/Register.vue'),
            meta: { title: '注册' }
        },
        //如果这里后面路由有问题，把这个404注释掉
        {
            path: '*',
                redirect: '/404'
        }
        ],
        });
router.afterEach((to,from,next) => {
    window.scrollTo(0,0);
});
router.beforeEach((to,from,next) =>{
    if (to.path === '/login' || to.path === '/register' || to.path === '/index' || to.path === '/detail') {
    next()
}else{
    const token = sessionStorage.getItem('userId');
    if (!token){
        next('/index')
    }else{
        next();
    }
}
});


export default router
