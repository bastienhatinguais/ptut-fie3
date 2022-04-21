<template>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Directeur</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(i, index) in personnel" :key="index">
        <th scope="row">{{ i.nom }}</th>
        <td>{{ i.prenom }}</td>
        <td>{{ i.estDirecteur }}</td>
        <td class="d-flex justify-content-center gap-3">
          <button
            type="button"
            class="btn btn-danger"
            data-bs-toggle="modal"
            data-bs-target="#test"
            @click="
              () => {
                setCurrent(selfLinkToId(i._links.self.href));
                supprimer()
              }
            "
          >
            <i class="bi bi-trash-fill"></i>
            Supprimer
          </button>

          <router-link
              :to="{
                name: 'modifierPersonnel',
                params: { id: selfLinkToId(ue._links.self.href) },
              }"
            >
          <button
            type="button"
            class="btn btn-warning text-white"
            data-bs-toggle="modal"
            data-bs-target="#test"
            @click="
              () => {
                setCurrent(selfLinkToId(i._links.self.href));
              }
            "
          >
            <i class="bi bi-pencil-fill"></i>
            Modifier
          </button>
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
let personnel = ref([]);
let current = null;

onMounted(function () {
  recupererPersonnels();
});
function setCurrent(id) {
  current = id;
  console.log(current);
}

function recupererPersonnels() {
  axiosApi
    .get("personnel")
    .then((response) => {
      personnel.value = response.data._embedded.personnel;
      console.log(personnel.value);
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