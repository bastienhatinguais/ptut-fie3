<template>
  <div v-if="auth.getEstConnecté()">
    <Annee></Annee>
  </div>
  <div v-else>
    <h1 class="text-center font-weight-bold">Syllabus et maquettes</h1>
    <h4 class="text-center mt-5">
      Bienvenue sur le syllabus d'ISIS. <br />
      Vous allez pouvoir y trouver la liste de vos cours par semestre et par
      année. <br />
      Vous aurez également accès au détail de chaque cours.
    </h4>
  </div>
</template>

<script setup>
import { reactive } from "@vue/reactivity";
import { inject } from "vue";

let auth = reactive(inject("auth"));
</script>

<style scoped>
h4 {
  line-height: 40px;
}
</style>

