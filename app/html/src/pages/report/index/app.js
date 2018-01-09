import 'babel-polyfill'
import Vue from 'vue'
import App from './app.vue'
import echarts from 'echarts'

Vue.prototype.$echarts = echarts

window.vm = new Vue({
    el: '#app',
    render: h => h(App)
})

window.refreshReport = function () {
    window.vm.$children[0].getAllAccounts()
    return 'report已经刷新'
}
