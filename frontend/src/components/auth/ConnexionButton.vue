<template>
  <div class="position-relative">
    <button type="button" class="btn btn-outline-light" @click="triggerForm">
      <i class="bi bi-box-arrow-in-right"></i>
      Connexion
    </button>
  </div>
  <div
    v-if="estVisible"
    class="
      form-container
      shadow-sm
      p-3
      mb-5
      bg-body
      rounded
      border border-primary
    "
  >
    <button
      type="button"
      class="btn-close"
      aria-label="Close"
      @click="triggerForm"
    ></button>
    <ConnexionForm></ConnexionForm>
  </div>
</template>

<script setup>
import { ref } from "vue";
import ConnexionForm from "./ConnexionForm.vue";

let estVisible = ref(false);

function triggerForm() {
  estVisible.value = !estVisible.value;
}
</script>

<style scoped>
.form-container {
  position: absolute;
  top: 60px;
  right: 10px;
  width: 300px;
}

.btn-close {
  position: absolute;
  right: 10px;
}
</style>