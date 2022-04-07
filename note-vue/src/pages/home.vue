<template>
    <div id="home">
        <div class="index">
            <div @click="utils.navClick('/write')" @dblclick="utils.noteSave(noteSaveCallBack)" class="block">
                ✍️
            </div>
            <div class="block" id="settingIcon" @dblclick="utils.navClick('/setting')">
                🎉
            </div>
            <div @click="utils.navClick('/read')" class="block">📖</div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'index',
    data() {
        return {};
    },
    methods: {
        noteSaveCallBack() {
            this.$parent.$refs.child.poemString = '';
            localStorage.removeItem('unSavePoemString');
        },
    },
    created() {},
};
</script>

<style scoped>
::root {
    --baseline: 8vh;
}

#home {
    /* border: 1px salmon solid; */
    width: 100%;
    height: 50px;
    padding: 0;
    margin: 0;
}

.block {
    width: 20%;
    display: inline-block;
    text-align: center;
    line-height: 50px;
    font-size: 20px;
    user-select: none;
}

.index {
    user-select: none;
    width: 100%;
    text-align: center;
    text-decoration: none;
}
</style>
