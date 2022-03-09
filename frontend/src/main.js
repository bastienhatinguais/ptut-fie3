import { createApp } from "vue";
import App from "./App.vue";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { createRouter, createWebHistory } from "vue-router";

import Home from "@/views/Home.vue";
import Api from "@/views/Api.vue";
import UE from "@/views/UE.vue";

const routes = [
  { path: "/", component: Home },
  { path: "/api", component: Api },
  { path: "/ue", component: UE },
];

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

createApp(App).use(router).mount("#app");
