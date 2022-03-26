<template>
  <div id="index">
    <div class="index">
      <div
        @click="utils.navClick('/write')"
        @dblclick="utils.noteSave(noteSaveCallBack)"
        class="block"
      >
        ✍️
      </div>
      <div
        class="block"
        id="settingIcon"
        @dblclick="utils.navClick('/setting')"
      >
        🎉
      </div>
      <div @click="utils.navClick('/read')" class="block">📖</div>
    </div>
    <router-view class="routerView" ref="child" />
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {};
  },
  methods: {
    noteSaveCallBack() {
      this.$refs.child.$refs.poemBox.focus();
      localStorage.removeItem("unSavePoemString");
      this.$refs.child.poemString = "";
    }
  }
};
</script>

<style>
:root {
  --baseline: 8vh;
}

* {
  padding: 0;
  margin: 0;
}

body {
  width: 100%;
  height: 100%;
}

.el-message {
  min-width: 90% !important;
}

.el-message-box {
  width: 90% !important;
}

#index {
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
  position: relative;
}

.block {
  width: 20%;
  height: 100%;
  display: inline-block;
  text-align: center;
  line-height: var(--baseline);
  font-size: 20px;
  user-select: none;
}

.index {
  /* border: 1px solid ; */
  user-select: none;
  width: 100%;
  height: var(--baseline);
  top: 0;
  position: fixed;
  text-align: center;
  text-decoration: none;
}

.routerView {
  margin-top: var(--baseline);
  width: 100%;
  position: fixed;
  height: 94vh;
  overflow: scroll;
}

.routerView::-webkit-scrollbar {
  width: 0;
}
</style>
