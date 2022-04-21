<template>
  <div id="tableau">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Titre</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(c, index) in cours" :key="index">
          <th scope="row">
            <router-link id="titres" :to="'/cours/' + selfLinkToId(c._links.self.href)">{{
              c.titre
            }}</router-link>
          </th>
          
          <td class="d-flex justify-content-center gap-3">
            <button
              id="suppr"
              type="button"
              class="btn btn-danger"
              data-bs-toggle="modal"
              data-bs-target="#test"
              @click="
                () => {
                  setCurrentDeleteId(selfLinkToId(c._links.self.href));
                }
              "
            >
              <i class="bi bi-trash-fill"></i>
              Supprimer
            </button>

            <button
              id="modif"
              type="button"
              class="btn btn-warning text-white"
              @click="
                () => {
                  router.push(
                    '/cours/' + selfLinkToId(c._links.self.href) + '/modifier'
                  );
                }
              "
            >
              <i class="bi bi-pencil-fill"></i> Modifier
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <SupprimerModal id="test" @supprimer="supprimer"
      >Voulez-vous vraiment supprimer ce cours ? Cette action sera
      irréversible.</SupprimerModal
    >
  </div>
</template>

<style scoped>
#titres:hover {
  color:#FF8183;
}
#titres {
  color:black;
  text-decoration: none;
}

button#modif {
  background-color: #039be5 ;
  border-color: #039be5 ;
}
button#suppr {
  background-color: #283593 ;
  border-color: #283593 ;
}

button:hover {
  background-color: #ff8183 !important;
  border-color: #ff8183 !important;
}

</style>

<script setup>

import SupprimerModal from "@/components/modal/SupprimerModal.vue";

import { axiosApi } from "@/api/api";
import { onMounted, ref } from "@vue/runtime-core";
import { selfLinkToId } from "@/utils";
import { useToast } from "vue-toastification";
import router from "@/router";

const toast = useToast();
let cours = ref([]);
let currentDeleteId = null;

onMounted(function () {
  recupererCours();
});

function setCurrentDeleteId(id) {
  currentDeleteId = id;
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
    .delete("cours/" + currentDeleteId)
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

