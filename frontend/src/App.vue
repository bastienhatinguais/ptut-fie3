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

const PATHS_VISITEUR = ["/", "/api"];
const PATHS_DIRECTEUR_ETUDES = ["/inscription"];

router.beforeEach(async function (to, from) {
  console.log(auth.getEstConnecté());
  if (!auth.getEstConnecté()) {
    if (!PATHS_VISITEUR.includes(to.path)) {
      return { path: "/" };
    }
  } else {
    let rolesUtilisateur = auth.getUtilisateur().roles;
    if (
      PATHS_DIRECTEUR_ETUDES.includes(to.path) &&
      !rolesUtilisateur.includes("ROLE_DIRECTEUR_ETUDES")
    ) {
      return { path: "/non-autorise" };
    }
  }
});
</script>

