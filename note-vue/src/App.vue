<template>
  <div id="app">
    <index v-if="show"/>
    <p v-if="!show">此页面不支持PC端，请移步手机端。</p>
  </div>
  
</template>

<script>
import index from "./pages/index.vue";
export default {
  name: "App",
  components: { index },
  data(){
    return {
      show:true
    }
  },
  methods:{
    　　_isMobile() {
      let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      return flag;
    }
  },

  mounted() {

    if (!this._isMobile()) {
      this.show = false;  
    }else{
      this.show = true;
    }
  }
};
</script>

<style></style>
