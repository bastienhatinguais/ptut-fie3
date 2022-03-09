<template>
  <div v-if="afficher" class="alert alert-success" role="alert">
    <slot></slot>
  </div>
</template>

<script setup>
import { defineProps, ref } from "vue";

let afficher = ref(false);

const props = defineProps({
  duree: {
    type: Number,
    default: 5000,
  },
});

function afficherAlerte() {
  afficher.value = true;
  setTimeout(() => {
    afficher.value = false;
  }, props.duree);
}
</script>
