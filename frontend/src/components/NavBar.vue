<template>
  <nav class="navbar navbar-custom navbar-expand-lg border-bottom border-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">
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
          <router-link class="nav-link light" to="/annee">Accueil</router-link>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cours
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="/cours">Liste des cours</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/cours/ajouter">Ajouter un cours</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            UE
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="/ue">Liste des UE</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/ue/ajouter">Ajouter une UE</a></li>
          </ul>
        </li><li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Personnel
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="/personnel">Liste du personnel</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/personnel/ajouter">Ajouter un membre</a></li>
          </ul>
        </li>
      </ul>
      <div>
        <img
          src="@/assets/image-ISIS.png"
          top="-100"
          width="110"
          height="70"
          class="d-inline-block align-text-top"
        />
      </div>
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
          <span class="text-light me-2"
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

<style scoped>
.navbar-expand-lg .navbar-nav {
  flex-direction: row;
}
.navbar-custom {
  background-color: #283593;
}
li {
  padding: 4px;
}

/* Modify brand and text color */
.navbar-custom .navbar-brand,
.navbar-custom .navbar-text,
.navbar-custom .nav-link {
  color: white;
}

.navbar-custom .nav-item.active .nav-link,
.navbar-custom .nav-item:hover .nav-link {
  color: #ff8183;
}
</style>