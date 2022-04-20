<template>
  <nav class="navbar navbar-light bg-light navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img
          src="@/assets/logo.png"
          alt=""
          width="70"
          height="60"
          class="d-inline-block align-text-top"
        />
      </a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" to="/annee">Accueil</router-link>
        </li>
        <li class="nav-item">
          <!--<router-link class="nav-link" to="/personnel">Personnel</router-link>-->
        </li>
      </ul>
      <ConnexionButton v-if="!auth.getEstConnecté()"></ConnexionButton>
      <div class="nav-item dropdown me-3" v-else>
        <a
          class="nav-link dropdown-toggle"
          href="#"
          id="navbarDropdown"
          role="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          <span class="text-primary me-2"
            ><i class="bi bi-person-circle"></i>
            {{ auth.getUtilisateur().nom }}</span
          >
        </a>
        <ul
          class="dropdown-menu dropdown-menu-end"
          aria-labelledby="navbarDropdown"
        >
          <li><a class="dropdown-item" href="#">Mon Compte</a></li>
          <li class="mt-2 mx-2"><DeconnexionButton></DeconnexionButton></li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import ConnexionButton from "@/components/auth/ConnexionButton.vue";
import DeconnexionButton from "@/components/auth/DeconnexionButton.vue";

import { inject, reactive, ref } from "vue";

let auth = reactive(inject("auth"));
console.log(auth.getUtilisateur());
</script>

<style>
.navbar-expand-lg .navbar-nav {
  flex-direction: row;
}
</style>