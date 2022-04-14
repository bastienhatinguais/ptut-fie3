<template>
  <div
    class="modal fade"
    :id="id"
    tabindex="-1"
    :aria-labelledby="id + 'Label'"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <div
            class="
              icon-circle
              rounded-circle
              bg-danger
              position-relative
              d-flex
              me-2
            "
          >
            <i
              class="h3 text-center text-white bi bi-trash-fill mx-auto my-auto"
            ></i>
          </div>

          <h5 class="modal-title text-center" :id="id + 'Label'">
            Êtes-vous sûr ?
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body"><slot></slot></div>
        <div class="modal-footer justify-content-center">
          <button
            type="button"
            class="btn btn-outline-secondary"
            data-bs-dismiss="modal"
          >
            Annuler
          </button>
          <button
            @click="$emit('supprimer')"
            type="button"
            class="btn btn-danger"
            data-bs-dismiss="modal"
          >
            <i class="bi bi-trash-fill"></i> Supprimer
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, emit } from "vue";

const props = defineProps({
  id: String,
  action: Function,
});
</script>

<style scoped>
.modal-footer {
  border-top: none;
}

.modal-header {
  border-bottom: none;
}

.icon-circle {
  width: 50px;
  height: 50px;
}
</style>