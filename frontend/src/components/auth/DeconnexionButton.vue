<template>
  <button class="btn btn-primary" @click="deconnexion">
    <i class="bi bi-box-arrow-left"></i> Déconnexion
  </button>
</template>

<script setup>
import { inject, ref } from "vue";

let auth = inject("auth");

function deconnexion() {
  auth.deconnexion();
}
</script>
