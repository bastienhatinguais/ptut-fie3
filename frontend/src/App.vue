<template>
  <NavBar></NavBar>
  <div class="mt-4">
    <router-view></router-view>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import Auth from "@/Auth";
import { reactive } from "@vue/reactivity";
import { provide } from "@vue/runtime-core";
import { axiosApi } from "@/api/api";
import router from "@/router";

let auth = reactive(new Auth());
provide("auth", auth);

/**
 * Intercepte les requêtes pour ajouter le header d'identification
 */
axiosApi.interceptors.request.use(
  function (config) {
    console.log("stop ! ");
    config.headers.common = Object.assign(
      {
        ...config.headers.common,
      },
      getAuthorization()
    );
    return config;
  },
  function (error) {
    // Do something with request error
    console.log(error);
    return Promise.reject(error);
  }
);

function getAuthorization() {
  if (auth.getEstConnecté())
    return { Authorization: "Bearer " + auth.getUtilisateur().token };
  return {};
}

router.beforeEach(async (to, from) => {
  if (
    // make sure the user is authenticated
    !auth.getEstConnecté() &&
    // ❗️ Avoid an infinite redirect
    to.path !== "/" &&
    to.path !== "/inscription"
  ) {
    // redirect the user to the login page
    return { path: "/" };
  }
});
</script>

