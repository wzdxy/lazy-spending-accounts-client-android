<template>
  <div id="app">
    <div class="dd">
      Home
    </div>
    <img v-bind:src="logoImg">
    <br>
    <v-btn @click="getAccounts">getAccounts</v-btn>
    <div>{{accounts}}</div>
    <div>
      <div v-for="item in accounts">
        <span>{{item.cost+' - '+item.date+' - '+item.time+' - '+item.serial}}</span>
      </div>
    </div>

  </div>
</template>

<script>
import logo from 'assets/img/logo.png'
export default {
  data () {
    return {
      msg: 'Use Vue 2.0 Today!',
      logoImg: logo,
      accounts:[{"cost":12225,"date":"2018-01-05","time":"08:03","serial":"Alaska"}]
    }
  },

  methods: {
    startHacking () {
      this.$notify({
        title: 'It Works',
        message: 'We have laid the groundwork for you. Now it\'s your time to build something epic!',
        duration: 6000
      })
    },
    getAccounts () {
        if(window.native){
            this.accounts = native.getAllAccounts()
            console.log(this.accounts)
            return this.accounts
        }

    }
  }
}
</script>

<style lang="postcss">
  body {
    background-color: #f5f5f5;
  }
  :root h1 {
   --color: red;
   display: flex;
   color: var(--color);
  }
</style>
