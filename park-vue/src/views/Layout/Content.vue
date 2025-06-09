<template>
    <div >
        <nav-header></nav-header>
        <router-view></router-view>
        <LayoutFooter ></LayoutFooter>
    </div>
</template>

<script>
    import NavHeader from './LayoutNav.vue';
    import LayoutFooter from './LayoutFooter.vue';
    export default {
        name: 'Index',
        components: { LayoutFooter,  NavHeader }
    };
</script>


