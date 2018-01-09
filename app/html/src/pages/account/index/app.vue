<template>
  <div id="app">
    <div>
      <div class="account-item" v-for="item in accounts">
        <div class="account-content">
          <div class="account-time">{{item.date}} {{item.time}}</div>
          <div class="account-cost">￥{{item.cost}}</div>
          <div class="account-serial">{{item.serial}}</div>
        </div>
        <div class="account-action">
          <v-btn color="error" small @click="deleteAccount(item.id)">delete</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      accounts:[{"id":1,"cost":12225,"date":"2018-01-05","time":"08:03","serial":"Alaska"},{"id":2,"cost":645,"date":"2018-01-05","time":"18:03","serial":"Arizona"}]
    }
  },

  methods: {
    getAllAccounts () {
        if(window.native){
            let res = native.getAllAccounts()
            this.accounts = JSON.parse(res);
        }else{
            console.log("getAllAccounts");
        }
    },
    deleteAccount(id){
        if(window.native){
            let res = native.deleteAccount(id);
            this.getAllAccounts();
        }else{
            console.log("deleteAccount",JSON.stringify(this.accounts.filter((i)=>i.id===id)));
        }
    }
  }
}
</script>

<style>
  .account-item{
    position: relative;
    height: 3rem;
  }
  .account-action{
    position: absolute;
    right: 0;
  }
  .account-content{
    position: absolute;
    display: inline-block;
    line-height: 3rem;
    text-indent:0.3rem;
  }
  .account-content>div{
    display: inline-block;
  }
  .account-cost{
    min-width: 5rem;
  }
  .account-time{
    color: #888;
  }
  .account-cost{
    color: #ffb300;
  }
  button.error{
    background-color: #FF5252 !important;
  }
</style>
