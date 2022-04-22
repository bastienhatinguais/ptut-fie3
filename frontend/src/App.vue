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

const PATHS_VISITEUR = ["/ue/etudiant/*", "ue/apprenti/*", "/cours/*"];
const PATHS_DIRECTEUR_ETUDES = [
  "/personnel",
  "/personnel/ajouter",
  "/personnel/*/modifier",
  "/cours/ajouter",
  "/cours",
  "/cours/*/modifier",
  "/ue",
  "/ue/*/modifier",
  "/ue/ajouter",
];

router.beforeEach(async function (to, from) {
  //est connecté ?
  if (!auth.getEstConnecté()) {
    console.log(to.path);
    if (!pathInAuthorizedPaths(to.path, PATHS_VISITEUR) && to.path !== "/") {
      return { path: "/" };
    }
  } else {
    console.log(auth.getUtilisateur().premiereConnexion);
    //premiere connexion ?
    if (
      auth.getUtilisateur().premiereConnexion &&
      to.path !== "/premiere-connexion"
    ) {
      return { path: "/premiere-connexion" };
    } else if (to.path !== "/premiere-connexion") {
      console.log("coucou");
      let rolesUtilisateur = auth.getUtilisateur().roles;
      if (
        !rolesUtilisateur.includes("ROLE_DIRECTEUR_ETUDES") ||
        (!pathInAuthorizedPaths(
          to.path,
          PATHS_VISITEUR.concat(PATHS_DIRECTEUR_ETUDES)
        ) &&
          to.path !== "/")
      ) {
        return { path: "/" };
      }
    }
  }
});

function pathInAuthorizedPaths(path, paths) {
  for (const p of paths) {
    let splitPath = path.split("/");
    let splitP = p.split("/");
    if (splitPath.length == splitP.length) {
      for (let i = 0; i < splitPath.length; i++) {
        if (splitPath[i] != splitP[i]) {
          if (splitP[i] != "*") break;
        }
        if (i == splitPath.length - 1) {
          return true;
        }
      }
    }
  }
  return false;
}
</script>

