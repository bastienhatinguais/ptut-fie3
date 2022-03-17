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
    <h2>Ajouter un UE</h2>

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
    <!-- le liste des semestres est vide par défaut et prend les valeur qui correspondent
    à l'année choisi dans l'option au dessus -->
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
    <div class="form-group">
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



<script setup>
import { axiosApi } from "@/api/api";
import { ref, reactive, onMounted } from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";

const ueInitial = {
  titre: "",
  code: "",
  creditEcts: 0,
  annee: "",
  semestre: "",
  statut: "",
};

let ue = reactive({ ...ueInitial });

let annees = ref([]);
let semestres = ref([]);
let statuts = ref([]);

let afficherAlerte = ref(false);
//let dureeAlerte = 5000;
let ajoutEnCours = ref(false);
const toast = useToast();

/**
 * Au chargerment de la page on récupère les années et les statuts
 */
onMounted(function () {
  axiosApi.get("annee").then((response) => {
    annees.value = response.data._embedded.annee;
  });
  axiosApi.get("semestre").then((response) => {
    semestres.value = response.data._embedded.semestre;
  });
  axiosApi.get("statut").then((response) => {
    statuts.value = response.data._embedded.statut;
  });
});

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
