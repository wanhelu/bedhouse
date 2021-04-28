import Vue from 'vue'
import './plugins/axios'
import Router from 'vue-router'
import App from './App.vue'
import router from './router'
import store from './store'
import VCharts from 'v-charts'
import Vuex from 'vuex'


Vue.config.productionTip = false
Vue.use(Router)
Vue.use(Vuex)
Vue.use(VCharts)


new Vue({
  el: '#app',
  router: new Router(router),
  store: new Vuex.Store(store),
  render: h => h(App)
}).$mount('#app')
