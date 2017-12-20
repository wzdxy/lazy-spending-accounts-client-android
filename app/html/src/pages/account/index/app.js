import Vue from 'vue'
import App from './app.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'

Vue.use(Vuetify)
new Vue({
    el: '#app',
    render: h => h(App)
})
