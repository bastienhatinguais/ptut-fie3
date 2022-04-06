<template>
  <h1>Attention ! Vous n'etes pas autorisé à atteindre cette page.</h1>
  <h2>
    Si vous pensez que c'est une erreur, veuillez contacter le directeur
    d'étude.
  </h2>
  <router-link to="/">
    <button class="btn btn-warning mx-auto">Retourner à l'accueil</button>
  </router-link>
</template>

<script setup>
import { reactive } from "@vue/reactivity";
import { inject } from "vue";

let auth = reactive(inject("auth"));
</script>

