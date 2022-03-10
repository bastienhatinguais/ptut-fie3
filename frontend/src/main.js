import { createApp } from "vue";
import App from "./App.vue";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import Fragment from "vue-fragment";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import router from "./router";
// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.

createApp(App).use(router).use(Fragment.Plugin).use(Toast).mount("#app");
