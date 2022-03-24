import Vue from "vue";
import App from "./App";
import router from "./router";
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import write from "./pages/write.vue";
import utils from "./common/utils";
import qs from "qs";

Vue.prototype.utils = utils;
Vue.use(ElementUI);
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.prototype.GLOBAL = write;
axios.defaults.withCredentials = true;

// 全局监听是否登录
router.beforeEach((to, from, next) => {
  //这里能够跟踪路径的变化
  var token = localStorage.getItem("token");
  if (token != null || to.path == "/login" || to.path == "/register") {
    next();
  } else {
    next("/login");
  }
});

import Router from "vue-router";

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

import http from "http"; //此处问http文件的路径
Vue.prototype.$http = http;
// 全局拦截 加token
axios.interceptors.request.use(config => {
  // 解决axios 使用post方式发送数据后端取不到的问题
  if (config.method.toLowerCase() == "post") {
    config.data = qs.stringify(config.data);
  }
  config.headers["token"] = localStorage.getItem("token");
  // console.log(config);
  return config;
});

new Vue({
  el: "#app",
  router,
  render: h => h(App),
  components: { App },
  template: "<App/>"
});
