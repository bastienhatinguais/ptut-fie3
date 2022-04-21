<template>
  <div id="tableau">
    <h1>Liste du personnel</h1>
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
              id="suppr"
              type="button"
              class="btn btn-danger"
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
                id="modif"
                type="button"
                class="btn btn-warning text-white"
                data-bs-toggle="modal"
                data-bs-target="#test"
              >
                <i class="bi bi-pencil-fill"></i>
                Modifier
              </button>
            </router-link>

            <button id="res"
              type="button"
              class="btn btn-success"
              data-bs-toggle="modal"
              data-bs-target="#test"
              @click="
                () => {
                  resiliserMdp(personnel.id);
                }
              "
            >
              <i class="bi bi-arrow-clockwise"></i>
              Résilier le mot de passe
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
#tableau {
  margin: 60px !important;
}

button#modif {
  background-color: #039be5 ;
  border-color: #039be5 ;
}
button#suppr, button#res {
  background-color: #283593 ;
  border-color: #283593 ;
}

button:hover {
  background-color: #ff8183 !important;
  border-color: #ff8183 !important;
}
</style>

<script setup>
//import SupprimerModal from "@/components/modal/SupprimerModal.vue";
import { axiosApi } from "@/api/api";
import { onMounted, ref } from "@vue/runtime-core";
import { selfLinkToId } from "@/utils";
import { useToast } from "vue-toastification";
import router from "@/router";

const toast = useToast();
let personnels = ref([]);
let current = null;

onMounted(function () {
  recupererPersonnels();
});

function setCurrent(id) {
  current = id;
}

function resiliserMdp(id) {
  axiosApi
    .post(`resilier-mdp?id=${id}`)
    .then((response) => {
      console.log(response);
      toast.success(response.data.message, {
        timeout: 5000,
      });
    })
    .catch((e) => {
      console.log(e);
    });
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

