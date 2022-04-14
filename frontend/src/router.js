import { createRouter, createWebHistory } from "vue-router";
import Auth from "./Auth";
import routes from "./routes";

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

// export the router instance
export default router;
