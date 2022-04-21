<template>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Role</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(personnel, index) in personnels" :key="index">
        <th scope="row">{{ personnel.nom }}</th>
        <td>{{ personnel.prenom }}</td>
        <td>{{ personnel.role }}</td>
        <td class="d-flex justify-content-center gap-3">
          <button
            type="button"
            class="btn btn-danger"
            data-bs-toggle="modal"
            data-bs-target="#test"
            @click="
              () => {
                setCurrent(personnel.id);
                supprimer();
              }
            "
          >
            <i class="bi bi-trash-fill"></i>
            Supprimer
          </button>

          <router-link :to="`/personnel/${personnel.id}/modifier`">
            <button
              type="button"
              class="btn btn-warning text-white"
              data-bs-toggle="modal"
              data-bs-target="#test"
            >
              <i class="bi bi-pencil-fill"></i>
              Modifier
            </button>
          </router-link>
        </td>
      </tr>
    </tbody>
  </table>
  <!--<SupprimerModal id="test" @supprimer="supprimer">test</SupprimerModal>-->
</template>

<script setup>
//import SupprimerModal from "@/components/modal/SupprimerModal.vue";
import { axiosApi } from "@/api/api";
import { onMounted, ref } from "@vue/runtime-core";
import { selfLinkToId } from "@/utils";
import { useToast } from "vue-toastification";

const toast = useToast();
let personnels = ref([]);
let current = null;

onMounted(function () {
  recupererPersonnels();
});

function setCurrent(id) {
  current = id;
}

function recupererPersonnels() {
  axiosApi
    .get("personnel-detail/")
    .then((response) => {
      personnels.value = response.data;
      console.log(personnels.value);
    })
    .catch((e) => {
      console.log(e);
    });
}

function supprimer() {
  axiosApi
    .delete("personnel/" + current)
    .then((response) => {
      toast.success("Le personnel a bien été supprimé !", {
        timeout: 5000,
      });
      recupererPersonnels();
    })
    .catch((e) => {
      console.log(e);
    });
}
</script>

<style>
</style>