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
    created() {},
};
</script>

<style scoped>
.routerView {
    width: 100%;
    height: 90%;
    overflow: scroll;
}

.routerView::-webkit-scrollbar {
    width: 0;
}
</style>

<style>
.el-message,
.el-message-box {
    max-width: 90% !important;
}
</style>
