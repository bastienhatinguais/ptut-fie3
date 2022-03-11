<template>
  <div>
    <h1>UE</h1>
    <UECall></UECall>
    <router-link to="/ue/ajout">
     <button type="button" class="btn btn-success">
      Ajouter UE
    </button>
    </router-link>
  </div>
</template>

<script setup>
import UECall from "@/components/ue/UECall.vue";


</script>
