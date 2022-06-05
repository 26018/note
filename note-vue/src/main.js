import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import qs from 'qs';
import { Message } from 'element-ui';

Vue.use(ElementUI);
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
axios.defaults.withCredentials = true;

// 全局监听是否登录
router.beforeEach((to, from, next) => {
    // 这里能够跟踪路径的变化;
    var token = localStorage.getItem('token');
    if (token != null || to.path.includes('/sign')) {
        next();
    } else {
        next('/signin');
    }
});

import Router from 'vue-router';

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch((err) => err);
};

import http from 'http'; //此处问http文件的路径
import { removeToken } from './common/user';
Vue.prototype.$http = http;
// 全局拦截 加token
axios.interceptors.request.use((config) => {
    // 解决axios 使用post方式发送数据后端取不到的问题
    if (config.method.toLowerCase() == 'post') {
        config.data = qs.stringify(config.data);
    }
    config.headers['token'] = localStorage.getItem('token');
    return config;
});

// respone拦截器
axios.interceptors.response.use(
    (response) => {
        /**
         * code:200,接口正常返回;
         */
        const res = response.data;
        if (res.code != 200) {
            if (res.code == 600) {
                removeToken();
                router.push('/signin');
            }
            Message({
                message: res.message,
                type: 'error',
                showClose: true,
                duration: 5 * 1000,
            });
        } else {
            // res.code === 200,正常返回数据
            return response.data;
        }
    },
    (error) => {
        Message({
            message: error.message,
            type: 'error',
            showClose: true,
            duration: 5 * 1000,
        });
    }
);

new Vue({
    el: '#app',
    router,
    render: (h) => h(App),
    components: { App },
    template: '<App/>',
});
