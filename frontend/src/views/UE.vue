<template>
  <div>
    <h1>UE</h1>
    <ListeUE></ListeUE>
    <router-link to="/ue/ajouter">
     <button type="button" class="btn btn-success">
      Ajouter UE
    </button>
    </router-link>
  </div>
</template>

<script setup>
import ListeUE from "@/components/ue/ListeUE.vue";


</script>
