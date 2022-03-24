import Vue from "vue";
import Router from "vue-router";
import write from "../pages/write.vue";
import read from "../pages/read.vue";
import setting from "../pages/setting.vue";
import NotFound from "../pages/404.vue";
import login from "../pages/login.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    { path: "/", name: "index", component: write },
    { path: "/write", name: "write", component: write },
    { path: "/read", name: "read", component: read },
    { path: "/setting", name: "setting", component: setting },
    { path: "/login", name: "login", component: login },
    { path: "*", name: "NotFound", component: NotFound }
  ]
});
