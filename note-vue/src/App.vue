<template>
    <div id="app">
        <home />
        <router-view class="routerView" ref="child" />
    </div>
</template>

<script>
import home from './pages/home.vue';
export default {
    name: 'App',
    components: { home },
    data() {
        return {};
    },
    created() {
        // console.log(this.$refs);
    },
};
</script>

<style scoped>
.routerView {
    width: 100%;
    height: 90%;
    /* border: 1px saddlebrown solid; */
    overflow: scroll;
}

.routerView::-webkit-scrollbar {
    width: 0;
}
</style>
