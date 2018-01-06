<template>
  <div id="app">
    <v-btn @click="getAccounts">getAccounts</v-btn>
    <div>
      <div v-for="item in accounts">
        <span>{{item.id+'-'+item.cost+' - '+item.date+' - '+item.time+' - '+item.serial}}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      accounts:[{"cost":12225,"date":"2018-01-05","time":"08:03","serial":"Alaska"},{"cost":645,"date":"2018-01-05","time":"18:03","serial":"Arizona"}]
    }
  },

  methods: {
    getAccounts () {
        if(window.native){
            let res = native.getAllAccounts()
            this.accounts = JSON.parse(res);
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
