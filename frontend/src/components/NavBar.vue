<template>
  <nav class="navbar navbar-light bg-light navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img
          src="@/assets/logo.png"
          alt=""
          width="30"
          height="24"
          class="d-inline-block align-text-top"
        />
      </a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" to="/">Accueil</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/api">Api</router-link>
        </li>
        <li class="nav-item dropdown" v-if="auth.getEstConnecté()">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            Cours
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li>
              <router-link class="dropdown-item" to="/cours">Liste</router-link>
            </li>
            <li><hr class="dropdown-divider" /></li>
            <li>
              <router-link class="dropdown-item" to="/cours/ajouter"
                >Ajouter</router-link
              >
            </li>
          </ul>
        </li>
      </ul>
      <form class="d-flex me-2">
        <input
          class="form-control me-2"
          type="search"
          placeholder="Search"
          aria-label="Search"
        />
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
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