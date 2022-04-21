<template>
  <div id="tout">
    <NavBar></NavBar>
    <div class="mt-5">
      <router-view></router-view>
    </div>
  </div>
  <Footer></Footer>
</template>

<style scoped>
#tout {
  /* on règle la taille de manière à ce que le footer soit affiché en bas et ne remonte pas*/
  min-height: calc(100vh - 80px);
}
</style>
<script setup>
import NavBar from "@/components/NavBar.vue";
import Footer from "@/components/Footer.vue";
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

/**
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
 */
router.beforeEach(async (to, from) => {
  if (
    // make sure the user is authenticated
    !auth.getEstConnecté() &&
    // ❗️ Avoid an infinite redirect
    to.path !== "/"
  ) {
    // redirect the user to the login page
    return { path: "/" };
  } else {
    if (
      auth.getUtilisateur().premiereConnexion &&
      to.path !== "/premiere-connexion"
    ) {
      return { path: "/premiere-connexion" };
    }
  }
});
</script>

