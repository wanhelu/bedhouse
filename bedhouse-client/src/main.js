import Vue from 'vue'
import './plugins/axios'
import Router from 'vue-router'
import App from './App.vue'
import router from './router'
import store from './store'
import VCharts from 'v-charts'
import Vuex from 'vuex'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/main.css'

Vue.config.productionTip = false
Vue.use(Router)
Vue.use(Vuex)
Vue.use(VCharts)
Vue.use(ElementUI)

new Vue({
  el: '#app',
  router: router,
  store: new Vuex.Store(store),
  render: h => h(App)
}).$mount('#app')
