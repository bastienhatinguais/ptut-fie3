<template>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Titre</th>
        <th>Crédits ECTS</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(c, index) in cours" :key="index">
        <th scope="row">{{ c.titre }}</th>
        <td>{{ c.creditsEcts }}</td>
        <td>
          <button
            type="button"
            class="btn btn-danger"
            data-bs-toggle="modal"
            data-bs-target="#test"
            @click="
              () => {
                setCurrent(selfLinkToId(c._links.self.href));
              }
            "
          >
            <i class="bi bi-trash-fill"></i>
            Supprimer
          </button>
        </td>
      </tr>
    </tbody>
  </table>
  <SupprimerModal id="test" @supprimer="supprimer">test</SupprimerModal>
</template>

<script setup>
import SupprimerModal from "@/components/modal/SupprimerModal.vue";

import { axiosApi } from "@/api/api";
import { onMounted, ref } from "@vue/runtime-core";
import { selfLinkToId } from "@/utils";
import { useToast } from "vue-toastification";

const toast = useToast();
let cours = ref([]);
let current = null;

onMounted(function () {
  recupererCours();
});

function setCurrent(id) {
  current = id;
  console.log(current);
}

function recupererCours() {
  axiosApi
    .get("cours")
    .then((response) => {
      cours.value = response.data._embedded.cours;
      console.log(cours.value);
    })
    .catch((e) => {
      console.log(e);
    });
}

function supprimer() {
  axiosApi
    .delete("cours/" + current)
    .then((response) => {
      toast.success("Le cours a bien été supprimé !", {
        timeout: 5000,
      });
      recupererCours();
    })
    .catch((e) => {
      console.log(e);
    });
}
</script>

<style>
</style>