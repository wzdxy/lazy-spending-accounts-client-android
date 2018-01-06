import 'babel-polyfill'
import Vue from 'vue'
import App from './app.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'

Vue.use(Vuetify)
window.vm = new Vue({
    el: '#app',
    render: h => h(App)
})

window.refreshList = function () {
    console.log('refreshList')
    window.vm.$children[0].getAccounts()
    return '已经刷新'
}

window.onload = function () {
    window.refreshList()
}
