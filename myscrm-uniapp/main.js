import App from './App'

// #ifndef VUE3
import Vue from 'vue'
import uView from '@/uni_modules/uview-ui'

import 'common/public.css'; /*引入公共样式*/ 
Vue.use(uView)
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import uView from '@/uni_modules/uview-ui'
Vue.use(uView)
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif