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
              <a class="nav-link" href="#item-1">Points clefs</a>
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
            <h4 id="item-1" style="color: #283593">Points clefs</h4>
            <h5 v-if="cours.responsable">
              Professeur en charge : {{ cours.responsable.nom }}
              {{ cours.responsable.prenom }}
            </h5>
            <hr />
            
              <h6 v-if="cours.ue && cours.ue.semestre">
                <router-link id="lien"
                :to="{
                    name: 'listeParSemestre',
                    params: { id: cours.ue.semestre.id, 
                    statut: cours.ue.semestre.annee.statut.intitule, 
                    annee: cours.ue.semestre.annee.intitule },
                  }"
                  >
                {{ cours.ue.semestre.annee.intitule }}
                , semestre
                {{ cours.ue.semestre.titre }}
                </router-link>
              </h6>
            
            <h6 v-if="cours.ue">
              UE : {{ cours.ue.titre }} ({{ cours.ue.code }})
            </h6>
            <h6 v-else>UE : Aucune</h6>
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
            <h4 id="item-2" style="color: #283593">Objectifs</h4>
            <p>{{ cours.objectifs }}</p>

            <!-- demande de defossez : doit pouvoir etre cache -->
            <h4>Competences visées</h4>
            <p>{{ cours.competences }}</p>
            <hr />
            <h4 id="item-3" style="color: #283593">Description</h4>
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

<style scoped>

h1 {
  color:#039BE5;
}
a{
  color : #283593;
}
a#lien:hover{
  color : #FF8183;
}

#navbar-example3 {
  top:20px;
  border-radius : 6px;
  box-shadow: 0 .5rem 1rem rgba(0,0,0,.15);
}
a.nav-link:hover {
  background-color : #039BE5 ;
  color : white ;
}

</style>

<script setup>
import { ref, onMounted, reactive } from "vue";
import { axiosApi } from "@/api/api";
import { useRoute } from "vue-router";
const route = useRoute();

let cours = reactive({});

onMounted(function () {
  axiosApi.get("coursUeSemestre/" + route.params.id).then((response) => {
    Object.assign(cours, response.data);
    console.log(cours);
  });
});
</script>

