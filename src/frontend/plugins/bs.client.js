import Vue from  'vue'
import $ from "jquery";
window.$ = $;
import money from 'v-money'
import "bootstrap";
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/locale/pt-br';
import VueNavigationBar from "vue-navigation-bar";
import VueTheMask from 'vue-the-mask';
import 'dayjs/locale/pt-br';
import customParseFormat from 'dayjs/plugin/customParseFormat';
import VueDayjs from 'vue-dayjs-plugin'
import VueCurrencyFilter from 'vue-currency-filter'
 

dayjs.extend(customParseFormat);
dayjs.locale('pt-br');
window.dayjs = dayjs;

Vue.use(VueCurrencyFilter);
Vue.use(VueDayjs)
Vue.use(VueTheMask);
Vue.component("vue-navigation-bar", VueNavigationBar);
Vue.use(DatePicker);
Vue.use(money, {precision: 2});

import "bootstrap/dist/css/bootstrap.min.css";
import "vue-navigation-bar/dist/vue-navigation-bar.css";
import 'vue2-datepicker/index.css';import dayjs from 'dayjs';

