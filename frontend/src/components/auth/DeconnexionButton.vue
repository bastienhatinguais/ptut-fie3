<template>
  <button class="btn btn-primary" @click="deconnexion">Déconnexion</button>
</template>

<script setup>
import { inject, ref } from "vue";

let auth = inject("auth");

function deconnexion() {
  auth.deconnexion();
}
</script>
