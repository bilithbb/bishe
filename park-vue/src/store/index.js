import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex);
import Element from 'element-ui'
export default new Vuex.Store({
    state:{
        // items:JSON.parse(localStorage.getItem('items') || '[]'),
        userInfo:null,
        categoryProductList:[],
        cartList:[],
    },
    mutations:{

        saveUserInfo(state,val){
            state.userInfo = val;
        },

        saveCategoryProductList(state,val){
            state.categoryProductList = val;
        },

        saveCartList(state,val){
            state.cartList = val;
        },

        addCart(state,val){
            state.cartList.push(val);
        },
        delCart(state,val){
            delCartInfo(val).then(res =>{
                Element.Message.success('删除成功');
                let idx = state.cartList.findIndex((item) =>val === item.id);
                state.cartList.splice(idx,1)
            })

        },

        //
        // setItems(state,data) {
        //     state.items = data;
        //     localStorage.setItem('items',JSON.stringify(data))
        // },
    },
    actions:{

    },
    getters:{

    },
    plugins: [
        createPersistedState({
            storage: window.sessionStorage
        })
    ],
})
