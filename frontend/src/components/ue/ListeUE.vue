<!-- AFFICHAGE LISTE DES UE -->

<template>
  <div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Code</th>
          <th>Titre</th>
          <th>Crédits ECTS</th>
          <th>Année</th>
          <th>Semestre</th>
          <th>Statut</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(ue, code) in lesUE" :key="code">
          <td id="code">{{ ue.code }}</td>
          <td id="titre">{{ ue.titre }}</td>
          <td id="creditEcts">{{ ue.creditEcts }}</td>
          <td id="annee">{{ ue.annee }}</td>
          <td id="semestre"> {{leSemestre}}</td>
          <td id="satut">{{ ue.statut }}</td>
          <td>
            <!-- BOUTON SUPPRIMER -->
            <button
              type="button"
              class="btn btn-danger"
              data-bs-toggle="modal"
              data-bs-target="#test"
              @click="
                () => {
                  setCurrent(selfLinkToId(ue._links.self.href));
                  supprimer();
                }
              "
            >
              <i class="bi bi-trash-fill"></i>
              Supprimer
            </button>
          </td>
          <td>
            <!-- BOUTON MODIFIER -->
            <router-link :to="{ name: 'modifier', params: { id: selfLinkToId(ue._links.self.href) }}">
            <button
              type="button"
              class="btn btn-primary"
              @click="
                () => {
                  setCurrent(selfLinkToId(ue._links.self.href));
                }
              "
            >
              <i class="bi bi-pencil-square"></i>
              Modifier
            </button>
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { axiosApi } from "@/api/api.js";
import { onMounted, ref } from "vue";
import { useToast } from "vue-toastification";
import { selfLinkToId } from "@/utils";

const toast = useToast();
let lesUE = ref([]);
let current = null;
let lAnnee = null;
let leSemestre = null;
let leStatut = null;

onMounted(function () {
  chercherUE();
});
/**
 * set l'id
 * @param {Integer} id 
 */
function setCurrent(id) {
  current = id;
  console.log(current);
}

function chercherUE() {
  axiosApi
    .get(`ue`)
    .then((response) => {
      lesUE.value = response.data._embedded.ue;
      console.log(lesUE.value);
      //boucle pour parcourir les UE et récupérer les valeurs de son année, son semestre et son statut
      for (let ue of lesUE.value) {
        let idUE = selfLinkToId(ue._links.self.href);
        console.log(idUE);
        recupererValeurs(idUE);
        console.log(leSemestre);
      }
    })
}

function recupererValeurs(id) {
  axiosApi
    .get(`ue/` + id + "/semestre")
    .then((response) => {
      console.log(response);
      leSemestre = response.data.titre;
      console.log(leSemestre);
      //let idSemestre = selfLinkToId(response.data._links.semestre);
      //console.log(idSemestre);
      lAnnee = response.data._links.annee.href;
      //leStatut.value = ;
    })
}

function supprimer() {
  axiosApi
    .delete("ue/" + current)
    .then((response) => {
      toast.success("L'UE a bien été supprimé !", {
        timeout: 5000,
      });
      chercherUE();
    })
    .catch((e) => {
      console.log(e);
    });
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>