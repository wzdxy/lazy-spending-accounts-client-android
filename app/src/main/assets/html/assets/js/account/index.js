webpackJsonp([1],{130:function(t,n,e){"use strict";function c(t){e(335)}Object.defineProperty(n,"__esModule",{value:!0});var i=(e(64),e(92)),s={data:function(){return{accounts:[{id:1,cost:12225,date:"2018-01-05",time:"08:03",serial:"Alaska"},{id:2,cost:645,date:"2018-01-05",time:"18:03",serial:"Arizona"}]}},methods:{getAccounts:function(){if(window.native){var t=native.getAllAccounts();this.accounts=JSON.parse(t)}else console.log("getAllAccounts")},deleteAccount:function(t){if(window.native){native.deleteAccount(t);this.getAccounts()}else console.log("deleteAccount",JSON.stringify(this.accounts.filter(function(n){return n.id===t})))}}},o=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{attrs:{id:"app"}},[e("div",t._l(t.accounts,function(n){return e("div",{staticClass:"account-item"},[e("div",{staticClass:"account-content"},[e("div",{staticClass:"account-time"},[t._v(t._s(n.date)+" "+t._s(n.time))]),t._v(" "),e("div",{staticClass:"account-cost"},[t._v("￥"+t._s(n.cost))]),t._v(" "),e("div",{staticClass:"account-serial"},[t._v(t._s(n.serial))])]),t._v(" "),e("div",{staticClass:"account-action"},[e("v-btn",{attrs:{color:"error",small:""},on:{click:function(e){t.deleteAccount(n.id)}}},[t._v("delete")])],1)])}))])},a=[],r={render:o,staticRenderFns:a},l=r,u=e(93),d=c,v=u(s,l,!1,d,null,null),f=v.exports,w=e(94),_=e.n(w);e(95);i.a.use(_.a),window.vm=new i.a({el:"#app",render:function(t){return t(f)}}),window.refreshList=function(){return console.log("refreshList"),window.vm.$children[0].getAccounts(),"已经刷新"},window.onload=function(){window.refreshList()}},335:function(t,n,e){},95:function(t,n,e){}},[130]);