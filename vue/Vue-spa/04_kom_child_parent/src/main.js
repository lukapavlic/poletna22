import { createApp } from 'vue';

import App from "./App.vue";
import Stik from "./components/Stik.vue";

const app = createApp(App);
app.component('uporabniski-stiki', Stik);

app.mount('#app');
