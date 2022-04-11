<!-- AFFICHAGE DES ANNEES -->

<template>
  <div class="container border border-dark">
    <div class="row">
      <div class="m-3 col">
        <h4>Cycle Préparatoire</h4>
        <div id="cyclePrépa " class="p-3 border border-dark">
          <ul>
            <li>FIE1, 1ère année</li>
            <ul>
              <li>Semestre 1</li>
              <li>Semestre 2</li>
            </ul>
            <li>FIE2, 2ème année</li>
            <ul>
              <li>Semestre 3</li>
              <li>Semestre 4</li>
            </ul>
          </ul>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="m-3 col">
        <h4>Cycle Etudiant</h4>
        <div id="cycleEtud" class="p-3 border border-dark">
          <ul>
            <li>FIE3, 3ème année</li>
            <ul>
              <li>Semestre 5</li>
              <li>Semestre 6</li>
            </ul>
            <li>FIE4, 4ème année</li>
            <ul>
              <li>Semestre 7</li>
              <li>Semestre 8</li>
            </ul>
            <li>FIE5, 5ème année</li>
            <ul>
              <li>Semestre 9</li>
              <li>Semestre 10</li>
            </ul>
          </ul>
        </div>
      </div>
      <div class="m-3 col">
        <h4>Cycle Apprentit</h4>
        <div id="cycleApp" class="p-3 border border-dark">
          <ul>
            <li>FIA3, 3ème année</li>
            <ul>
              <li>Semestre 5</li>
              <li>Semestre 6</li>
            </ul>
            <li>FIA4, 4ème année</li>
            <ul>
              <li>Semestre 7</li>
              <li>Semestre 8</li>
            </ul>
            <li>FIA5, 5ème année</li>
            <ul>
              <li>Semestre 9</li>
              <li>Semestre 10</li>
            </ul>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { axiosApi } from "@/api/api.js";
import { onMounted, ref } from "vue";
import { useToast } from "vue-toastification";
import { selfLinkToId } from "@/utils";

const toast = useToast();
let lesUE = ref([]);
let lesCours = ref([]);
let current = null;

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
  axiosApi.get(`ue`).then((response) => {
    lesUE.value = response.data._embedded.ue;
    console.log(lesUE.value);
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