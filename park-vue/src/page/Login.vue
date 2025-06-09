<template>
    <div class="login-container">
        <LoginThree login-title="停车场推荐系统"></LoginThree>
    </div>
</template>

<script>
    import LoginThree from '../components/login/LoginThree.vue';

    export default {
        name: 'Login',
        components:{
            LoginThree,
        }
    }
</script>


<style scoped>
    .login-container{
        height: 100%;
    }
</style>
