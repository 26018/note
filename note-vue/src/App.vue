<template>
  <div id="app">
    <index v-if="show" />
    <router-view class="routerView" ref="child" v-if="show" />
    <!-- <p v-if="!show">此页面不支持PC端，请移步移动端。</p> -->
  </div>
</template>

<script>
import index from "./pages/index.vue";
export default {
  name: "App",
  components: { index },
  data() {
    return {
      show: true
    };
  },
  methods: {
    // isMobile() {
    //   let flag = navigator.userAgent.match(
    //     /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
    //   );
    //   return flag;
    // }
  },
  created() {
    // console.log(this.isMobile());
    // if (!this.isMobile()) {
    //   this.show = false;
    // } else {
    //   this.show = true;
    // }
    // console.log("ljh");
  }
};
</script>

<style></style>
