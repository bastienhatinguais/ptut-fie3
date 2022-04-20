<template>
  <div class="container">
    <h1>{{ cours.titre }}</h1>
    <div class="bd-example">
      <div class="row">
        <div class="col-4">
          <nav
            id="navbar-example3"
            class="
              navbar navbar-light
              bg-light
              flex-column
              align-items-stretch
              p-3
              position-sticky
              sticky-top
            "
          >
            <a class="navbar-brand" href="#">Sommaire</a>
            <nav class="nav nav-pills flex-column">
              <a class="nav-link active" href="#item-1">Points clefs</a>
              <a class="nav-link" href="#item-2">Objectifs</a>
              <a class="nav-link" href="#item-3">Description</a>
            </nav>
          </nav>
        </div>
        <div class="col-8">
          <div
            data-bs-spy="scroll"
            data-bs-target="#navbar-example3"
            data-bs-offset="0"
            class="scrollspy-example-2"
            tabindex="0"
          >
            <h4 id="item-1" style="color: #0d6efd">Points clefs</h4>
            <h5 v-if="cours.responsable">
              Professeur en charge : {{ cours.responsable.nom }}
              {{ cours.responsable.prenom }}
            </h5>
            <hr />
            <h6 v-if="cours.ue">UE : {{ cours.ue.code }} (mettre un lien)</h6>
            <h6 v-else>UE : Aucune</h6>

            <h6 v-if="cours.ue && cours.ue.semestre">
              Annee :
              {{ cours.ue.semestre.annee.intitule }}
              Semestre :
              {{ cours.ue.semestre.titre }}
            </h6>
            <hr />
            <h6>Détail des heures</h6>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Type</th>
                  <th scope="col">Nombre</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">CM</th>
                  <td>{{ cours.nbHeureCM }}</td>
                </tr>
                <tr>
                  <th scope="row">TD</th>
                  <td>{{ cours.nbHeureTD }}</td>
                </tr>
                <tr>
                  <th scope="row">TP</th>
                  <td>{{ cours.nbHeureTP }}</td>
                </tr>
                <tr>
                  <th scope="row">FOAD</th>
                  <td colspan="2">{{ cours.nbHeureFOAD }}</td>
                </tr>
              </tbody>
            </table>
            <hr />
            <h4 id="item-2" style="color: #0d6efd">Objectifs</h4>
            <p>{{ cours.objectifs }}</p>

            <!-- demande de defossez : doit pouvoir etre cache -->
            <h4>Competences visées</h4>
            <p>{{ cours.competences }}</p>
            <hr />
            <h4 id="item-3" style="color: #0d6efd">Description</h4>
            <p>{{ cours.description }}</p>

            <!-- demande de defossez : doit pouvoir etre cache -->
            <h4>Plan du cours</h4>
            <p>{{ cours.planDuCours }}</p>

            <!-- demande de defossez : doit pouvoir etre cache -->
            <h4>Modalites d'evaluation</h4>
            <p>{{ cours.modalitesEvaluation }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { selfLinkToId } from "@/utils";
import { ref, onMounted, reactive } from "vue";
import { axiosApi } from "@/api/api";
import { useRoute } from "vue-router";
const route = useRoute();

let cours = reactive({});

onMounted(function () {
  axiosApi.get("coursUeSemestre/1").then((response) => {
    Object.assign(cours, response.data);
    console.log(cours);
  });
});
</script>