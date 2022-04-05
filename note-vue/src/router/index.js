import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      component: () => import("../pages/write.vue")
    },
    { path: "/write", component: () => import("../pages/write.vue") },
    { path: "/read", component: () => import("../pages/read.vue") },
    { path: "/setting", component: () => import("../pages/setting.vue") },
    { path: "/signup", component: () => import("../components/signup.vue") },
    { path: "/signin", component: () => import("../components/signin.vue") },
    {
      path: "*",
      name: "NotFound",
      component: () => import("../pages/write.vue")
    }
  ]
});
