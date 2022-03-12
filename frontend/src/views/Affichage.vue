<template>
  <div>
    <h1>{{cours.titre}}</h1>
      <nav id="navbar-example3" class="navbar navbar-light bg-light flex-column align-items-stretch p-3">
        <a class="navbar-brand" href="#">Sommaire</a>
        <nav class="nav nav-pills flex-column">
          <a class="nav-link" href="#item-1">Points clefs</a>
          <a class="nav-link" href="#item-2">Resume</a>
          <a class="nav-link" href="#item-3">Objectifs</a>
          <!-- demande de defossez : doit pouvoir etre cache -->
          <a class="nav-link" href="#item-4">Modaltes d'evaluation</a>
        </nav>
      </nav>
      <div data-bs-spy="scroll" data-bs-target="#navbar-example3" data-bs-offset="0" tabindex="0">
        <h4 id="item-1">Points clefs</h4>
          <h5>Credits ECTS : {{cours.creditEcts}}</h5><hr>
          <h5>Professeur en charge : {{cours.responsable}}</h5><hr>
          <h6>UE concernee : {{cours.UE}}</h6><h6>Semestre concerne : {{cours.Semestre}}</h6><hr>
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
                <td>{{cours.nbHeureCM}}</td>
              </tr>
              <tr>
                <th scope="row">TD</th>
                <td>{{cours.nbHeureTD}}</td>
              </tr>
              <tr>
                <th scope="row">TP</th>
                <td colspan="2">{{cours.nbHeureTP}}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <h4 id="item-2">Resume</h4>
          <p>{{cours.description}}</p><hr>
          <!-- demande de defossez : doit pouvoir etre cache -->
          <h4>Plan du cours</h4><br>
          <p>{{cours.Plan}}</p>
        <h4 id="item-3">Objectifs</h4>
          <p>{{cours.objectifs}}</p><hr>
          <!-- demande de defossez : doit pouvoir etre cache -->
          <h4>Competences visees</h4><br>
          <p>{{cours.Competences}}</p>
        <h4 id="item-4">Modalites d'evaluation</h4>
          <!-- demande de defossez : doit pouvoir etre cache -->
          <p>{{cours.modalitesEvaluation}}</p>
    <!--
    <div class="d-flex align-items-start">
      <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <button class="nav-link active" id="v-pills-p-tab" data-bs-toggle="pill" data-bs-target="#v-pills-p" type="button" role="tab" aria-controls="v-pills-p" aria-selected="true">Points clefs</button>
        <button class="nav-link" id="v-pills-r-tab" data-bs-toggle="pill" data-bs-target="#v-pills-r" type="button" role="tab" aria-controls="v-pills-r" aria-selected="false">Resume</button>
        <button class="nav-link" id="v-pills-o-tab" data-bs-toggle="pill" data-bs-target="#v-pills-o" type="button" role="tab" aria-controls="v-pills-o" aria-selected="false">Objectifs</button>
        COMMENTAIRE demande de defossez : doit pouvoir etre cache 
        <button class="nav-link" id="v-pills-m-tab" data-bs-toggle="pill" data-bs-target="#v-pills-m" type="button" role="tab" aria-controls="v-pills-m" aria-selected="false">Modalites d'examen</button>
      </div>
      <div class="tab-content" id="v-pills-t  abContent">
        <div class="tab-pane fade show active" id="v-pills-p" role="tabpanel" aria-labelledby="v-pills-p-tab">
          <h5>Credits ECTS : {{cours.creditEcts}}</h5><hr>
          <h5>Professeur en charge : {{cours.responsable}}</h5><hr>
          <h6>UE concernee : {{cours.UE}}</h6><h6>Semestre concerne : {{cours.Semestre}}</h6><hr>
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
                <td>{{cours.nbHeureCM}}</td>
              </tr>
              <tr>
                <th scope="row">TD</th>
                <td>{{cours.nbHeureTD}}</td>
              </tr>
              <tr>
                <th scope="row">TP</th>
                <td colspan="2">{{cours.nbHeureTP}}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="tab-pane fade" id="v-pills-r" role="tabpanel" aria-labelledby="v-pills-r-tab">
          <h4>Resume</h4><br>
          <p>{{cours.description}}</p><hr>
          COMMENTAIRE demande de defossez : doit pouvoir etre cache 
          <h4>Plan du cours</h4><br>
          <p>{{cours.Plan}}</p>
        </div>
        <div class="tab-pane fade" id="v-pills-o" role="tabpanel" aria-labelledby="v-pills-o-tab">
          <h4>Objectifs</h4><br>
          <p>{{cours.objectifs}}</p><hr>
          COMMENTAIRE demande de defossez : doit pouvoir etre cache 
          <h4>Competences visees</h4><br>
          <p>{{cours.Competences}}</p>
        </div>
        <div class="tab-pane fade" id="v-pills-m" role="tabpanel" aria-labelledby="v-pills-m-tab">
          COMMENTAIRE demande de defossez : doit pouvoir etre cache 
          <h4>Modalites d'evaluation</h4><br>
          <p>{{cours.modalitesEvaluation}}</p>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script setup>

import { selfLinkToId } from "@/utils";
import { ref, onMounted } from 'vue'
import { axiosApi } from "@/api/api";
import { useRoute } from "vue-router"
const route = useRoute();

let cours = ref([]);
console.log(cours)

onMounted(function () {
  axiosApi.get("cours/" + route.params.id).then((response) => {
    cours.value = response.data;
    console.log(response);
  });
});

</script>