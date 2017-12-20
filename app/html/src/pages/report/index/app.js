import Vue from 'vue'
import App from './app.vue'
import vuetify from 'vuetify'
// import 'vuetify/dist/vuetify.css'

Vue.use(vuetify)
new Vue({
    el: '#app',
    render: h => h(App)
})
