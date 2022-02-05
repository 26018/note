import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import write from './pages/write.vue'

Vue.use(ElementUI);

Vue.config.productionTip = false

Vue.prototype.$axios = axios

axios.defaults.baseURL = 'http://121.43.152.151:1026'

Vue.prototype.GLOBAL = write


new Vue({
  el: '#app',
  router,
  render:h=>h(App),
  components: { App },
  template: '<App/>'
})
