import { createApp } from "vue";
import App from "./App.vue";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { createRouter, createWebHistory } from "vue-router";

import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import Home from "@/views/Home.vue";
import Api from "@/views/Api.vue";
import Cours from "@/views/Cours.vue";
import Affich from "@/views/Affich.vue";
import Affichage from "@/views/Affichage.vue";

const routes = [
    { path: "/", component: Home },
    { path: "/api", component: Api },
    { path: "/cours/", component: Cours },
    { path: "/Affich", component: Affich },
    { path: "/cours/:code", component: Affichage }
];

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = createRouter({
    history: createWebHistory(),
    routes: routes,
});

createApp(App).use(router).use(Toast).mount("#app");