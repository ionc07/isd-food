import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import vueCookies from "vue-cookies";



import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'





Vue.config.productionTip = false;


new Vue({
  router,
  store,
  vuetify,
  vueCookies,
  render: h => h(App)
}).$mount("#app");
