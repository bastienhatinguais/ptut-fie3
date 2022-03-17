<template>
  <div v-if="auth.getEstConnecté()">
    <h2>Vous êtes connecté en tant que : {{ auth.getUtilisateur().pseudo }}</h2>
    <h2>Votre token est : {{ auth.getUtilisateur().token }}</h2>
    <img alt="Vue logo" src="@/assets/logo.png" />
  </div>
  <div v-else>Connectez vous :)</div>
</template>

<script setup>
import { reactive } from "@vue/reactivity";
import { inject } from "vue";

let auth = reactive(inject("auth"));
</script>

