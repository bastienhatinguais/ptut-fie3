<template>
  <div class="container">
    <!-- BREADCRUMB -->

    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <router-link
            :to="{
              name: 'annee',
            }"
            >Accueil</router-link
          >
        </li>
        <li class="breadcrumb-item active" aria-current="page">{{route.params.annee}}</li>
        <li class="breadcrumb-item active" aria-current="page">Semestre {{route.params.id}}</li>
      </ol>
    </nav>

    <!-- AFFICHAGE LISTE DES UE -->

    <table class="table table-hover">
      <thead>
        <tr>
          <th>Code</th>
          <th>Titre</th>
          <th>Crédits ECTS</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(ue, code) in lesUEDuSemestre"
          :key="code"
          @click="
            () => {
              setCurrent(selfLinkToId(ue._links.self.href));
              recupererCours();
            }
          "
          data-toggle="collapse"
          data-target="#accordion"
        >
          <td id="code">{{ ue.code }}</td>
          <td id="titre">{{ ue.titre }}</td>
          <td id="creditEcts">{{ ue.creditEcts }}</td>
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
            <router-link
              :to="{
                name: 'modifier',
                params: { id: selfLinkToId(ue._links.self.href) },
              }"
            >
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
        <tr
          v-for="(cours, id) in lesCours"
          :key="id"
          id="accordion"
          class="collapse"
        >
          <td id="titre">{{ cours.titre }}</td>
        </tr>
      </tbody>
    </table>
    <router-link to="/ue/ajouter">
      <button type="button" class="btn btn-success">Ajouter UE</button>
    </router-link>
  </div>
</template>

<script setup>
import { axiosApi } from "@/api/api";
import { ref, reactive, onMounted, defineProps, getCurrentInstance } from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";
import { useRoute } from "vue-router";
import { selfLinkToId, trimLink } from "@/utils";

const toast = useToast();
let lesUE = ref([]);
let lesCours = ref([]);
let current = null;
const route = useRoute();
let lesUEDuSemestre = ref([]);

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
  axiosApi.get("semestre/" + route.params.id + "/ue").then((response) => {
    console.log(route.params.id);
    lesUEDuSemestre.value = response.data._embedded.ue;
    console.log(lesUEDuSemestre.value);
  });
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

function recupererCours() {
  axiosApi.get("ue/" + current + "/cours").then((response) => {
    console.log(response);
    lesCours.value = response.data._embedded.cours;
    console.log(lesCours.value);
  });
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>