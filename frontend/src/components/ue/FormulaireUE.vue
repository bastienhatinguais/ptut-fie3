<!-- FORMULAIRE AJOUT UE -->

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
    @submit="ajouterUE"
  >
    <h2>Ajouter une UE</h2>

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
          v-for="(personnel, index) in personnels"
          :key="index"
          :ref="personnel.ref"
          :value="personnel._links.self.href"
        >
          {{ personnel.nom }} {{ personnel.prenom }}
        </option>
      </select>
    </div>

    <!-- BOUTON AJOUTER -->
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
        Ajout...
      </button>
      <button v-else class="btn btn-primary" type="submit">Ajouter</button>
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
import { selfLinkToId } from "@/utils";

const ueInitial = {
  titre: "",
  code: "",
  creditEcts: 0,
  annee: "",
  semestre: "",
  statut: "",
  responsable: "",
};

let ue = reactive({ ...ueInitial });

let annees = ref([]);
let semestres = ref([]);
let statuts = ref([]);
let personnels = ref([]);
let ajoutEnCours = ref(false);
const toast = useToast();

/**
 * Au chargerment de la page on récupère les années et les statuts
 */
onMounted(function () {
  axiosApi.get("annee").then((response) => {
    annees.value = response.data._embedded.annee;
  });
  axiosApi.get("personnel").then((response) => {
    personnels.value = response.data._embedded.personnel;
  });
});

//Afficher les semestres selon l'année choisie
function afficherSemestres(e) {
  axiosApi
    .get("annee/" + selfLinkToId(ue.annee) + "/semestre")
    .then((response) => {
      console.log(ue.annee);
      semestres.value = response.data._embedded.semestre;
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
/**
 * fonction pour ajouter un UE et affichage de la popup de success ou erreur
 */
function ajouterUE(e) {
  e.preventDefault();
  ajoutEnCours.value = true;
  console.log(ue);
  axiosApi
    .post("ue", ue)
    .then(function (response) {
      ajoutEnCours.value = false;

      //succès
      if (response.status == 201) {
        //reset valeurs du form
        Object.assign(ue, ueInitial);
        toast.success("L'UE a bien été ajouté !", {
          timeout: 2000,
        });

        router.push("/ue");
      }
    })
    .catch(function (error) {
      ajoutEnCours.value = false;
      toast.error(error, {
        timeout: 2000,
      });
    })
    .then(function (response) {
      console.log(response);
    });
}
</script>
