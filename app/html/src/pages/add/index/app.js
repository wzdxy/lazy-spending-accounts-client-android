import 'babel-polyfill'
import Vue from 'vue'
import App from './app.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'
import 'material-design-icons/iconfont/material-icons.css'

Vue.use(Vuetify)
new Vue({
    el: '#app',
    render: h => h(App)
})
