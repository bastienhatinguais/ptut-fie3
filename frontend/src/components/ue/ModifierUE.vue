<!--FORMULAIRE MODIFICATION DES UE -->

<template>
  <form
    class="
      row
      g-2
      needs-validation
      container
      px-3
      shadow
      p-3
      mb-5
      bg-body
      rounded
    "
    @submit="modifierUE"
  >
    <h2>Modifier une UE</h2>

    <!-- TITRE -->
    <div>
      <label for="validationTitre" class="form-label">Titre</label>
      <input
        type="text"
        class="form-control"
        id="validationTitre"
        v-model="ue.titre"
        required
      />
    </div>

    <!-- CODE -->
    <div>
      <label for="validationCode" class="form-label">Code</label>
      <input
        class="form-control"
        type="text"
        id="validationCode"
        v-model="ue.code"
        required
      />
    </div>

    <!-- CREDITS ECTS -->
    <div>
      <label for="validationCreditEcts" class="form-label">Crédits ECTS</label>
      <input
        type="number"
        class="form-control"
        id="validationCreditEcts"
        v-model="ue.creditEcts"
        required
      />
    </div>

    <!-- ANNEE -->
    <div>
      <label class="form-label">Année</label>
      <select
        class="form-select"
        aria-label="Choisissez l'année'"
        v-model="ue.annee"
        @change="afficherSemestres($e), choisirLeStatut($e)"
      >
        <option
          v-for="(annee, index) in annees"
          :key="index"
          :ref="annee.ref"
          :value="annee._links.self.href"
        >
          {{ annee.intitule }}
        </option>
      </select>
    </div>

    <!-- SEMESTRE -->
    <div class="form-group">
      <label for="choixSemestre">Semestre</label>
      <select
        class="form-select"
        aria-label="Choisissez le semestre'"
        v-model="ue.semestre"
      >
        <option
          v-for="(semestre, index) in semestres"
          :key="index"
          :ref="semestre.ref"
          :value="semestre._links.self.href"
        >
          {{ semestre.titre }}
        </option>
      </select>
    </div>

    <!-- STATUT -->
    <div class="form-group" hidden>
      <label for="statut">Statut</label>
      <select
        class="form-select"
        aria-label="Choisissez le statut'"
        v-model="ue.statut"
      >
        <option v-for="(statut, index) in statuts" :key="index">
          {{ statut.intitule }}
        </option>
      </select>
    </div>

    <!-- RESPONSABLE -->
    <div>
      <label class="form-label">Personnel responsable</label>
      <select
        class="form-select"
        aria-label="Choisissez le personnel responsable de l'ue"
        v-model="ue.responsable"
      >
        <option
          v-for="(responsable, index) in responsables"
          :key="index"
          :ref="responsable.ref"
          :value="responsable._links.self.href"
        >
          {{ responsable.nom }} {{ responsable.prenom }}
        </option>
      </select>
    </div>

    <!-- BOUTON MODIFIER -->
    <div class="col-12 mx-auto">
      <button
        v-if="ajoutEnCours"
        class="btn btn-primary"
        type="button"
        disabled
      >
        <span
          class="spinner-border spinner-border-sm"
          role="status"
          aria-hidden="true"
        ></span>
        Modification...
      </button>
      <button v-else class="btn btn-primary" type="submit">Modifier</button>
    </div>
  </form>
</template>

<style scoped>

button {
  background-color: #283593 ;
  border-color: #283593 ;
  width:100%;
}
button:hover {
  background-color: #FF8183 !important;
  border-color: #FF8183 !important;
}

</style>

<script setup>
import { axiosApi } from "@/api/api";
import { ref, reactive, onMounted, watch } from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";
import { useRoute } from "vue-router";
import { selfLinkToId, trimLink } from "@/utils";
import config from "@/config.js";

let ue = reactive({});

let annees = ref([]);
let semestres = ref([]);
let statuts = ref([]);
let responsables = ref([]);

//let dureeAlerte = 5000;
let ajoutEnCours = ref(false);
let modificationEnCours = ref(false);
const toast = useToast();
const route = useRoute();

onMounted(function () {
  console.log(route.params.id);

  axiosApi.get("ueAnneeSemestre/" + route.params.id).then((response) => {
    delete response.data.cours;

    Object.assign(ue, response.data);

    ue.annee = response.data.semestre.annee;
    //récupérer l'adresse de l'année
    ue.annee = config.urlBackend + "/api/annee/" + ue.annee.id;

    ue.semestre = response.data.semestre;
    //récupérer l'adresse du semestre
    ue.semestre = config.urlBackend + "/api/semestre/" + ue.semestre.id;

    //ue.statut = response.data.semestre.annee.statut;

    ue.responsable = response.data.responsable;
    console.log(response.data);
    //récupérer l'adresse du responsable
    ue.responsable =
      config.urlBackend + "/api/personnel/" + ue.responsable.code;

    afficherSemestres();

    axiosApi.get("annee").then((response) => {
      annees.value = response.data._embedded.annee;
    });
    axiosApi.get("personnel").then((response) => {
      responsables.value = response.data._embedded.personnel;
    });

    console.log(ue);
  });
});

//Afficher les semestres selon l'année choisie
function afficherSemestres(e) {
  axiosApi
    .get("annee/" + selfLinkToId(ue.annee) + "/semestre")
    .then((response) => {
      console.log(ue.annee);
      semestres.value = response.data._embedded.semestre;
      console.log(semestres.value);
    });
}

//Choisir le statut selon l'année choisie
function choisirLeStatut(e) {
  axiosApi
    .get("annee/" + selfLinkToId(ue.annee) + "/statut")
    .then((response) => {
      console.log(response);
      ue.statut = response.data;
      console.log(ue.statut);
    });
}

function modifierUE(e) {
  e.preventDefault();
  modificationEnCours.value = true;
  console.log(ue);
  axiosApi
    .patch("ue/" + route.params.id, ue)
    .then(function (response) {
      modificationEnCours.value = false;
      console.log(response);
      //succès
      if (response.status == 200) {
        //reset valeurs du form
        toast.success("Le cours a bien été modifié !", {
          timeout: 5000,
        });

        router.push("/ue");
      }
    })
    .catch(function (error) {
      modificationEnCours.value = false;
      toast.error(error, {
        timeout: 5000,
      });
    });
}
</script>
