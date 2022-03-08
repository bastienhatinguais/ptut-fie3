<template>
  <form class="row g-2 needs-validation" @submit="ajouterCours">
    <div v-if="afficherAlerte" class="alert alert-success" role="alert">
      Le cours a bien été ajouté
    </div>

    <div>
      <label for="validationTitre" class="form-label">Titre</label>
      <input
        class="form-control"
        type="text"
        id="validationTitre"
        v-model="cours.titre"
        required
      />
    </div>

    <div>
      <label for="validationCreditEcts" class="form-label">Crédits ECTS</label>
      <input
        type="number"
        class="form-control"
        id="validationCreditEcts"
        v-model="cours.creditEcts"
        required
      />
    </div>
    <div>
      <label class="form-label">Personnel responsable</label>

      <select
        class="form-select"
        aria-label="Choisissez le personnel responsable du cours"
        v-model="cours.responsable"
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
    <div class="col-12">
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
import Succes from "@/components/alert/Succes.vue";

let personnels = ref([]);
let cours = reactive({ titre: "", creditEcts: 0, responsable: 0 });
let afficherAlerte = ref(false);
let dureeAlerte = 5000;
let ajoutEnCours = ref(false);

onMounted(function () {
  console.log(ajoutEnCours.value);
  axiosApi.get("personnel").then((response) => {
    personnels.value = response.data._embedded.personnel;
    console.log(personnels.value);
  });
});

function ajouterCours(e) {
  e.preventDefault();
  ajoutEnCours.value = true;
  axiosApi.post("cours", cours).then(function (response) {
    ajoutEnCours.value = false;
    if (response.status == 201) {
      afficherAlerte.value = true;
      setTimeout(() => {
        afficherAlerte.value = false;
      }, dureeAlerte);
    }
  });
}
</script>
