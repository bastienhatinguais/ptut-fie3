<template>
  <form class="row g-2 needs-validation" @submit="ajouterUE">
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
        <option v-for="(annee, index) in annees" :key="index">
          {{ annee.numero }}
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
        <option v-for="(semestre, index) in semestres" :key="index">
          {{ semestre.titre }}
        </option>
      </select>
    </div>

    <!-- STATUT -->
    <div class="form-group">
      <label for="statut">Statut</label>
      <textarea
        class="form-control"
        id="modalitesEvaluation"
        rows="3"
        v-model="cours.modalitesEvaluation"
      ></textarea>
    </div>
  </form>
</template>

<script setup>
import { axiosApi } from "@/api/api";
import { ref, reactive, onMounted } from "vue";
import { useToast } from "vue-toastification";

const ueInitial = {
  titre: "",
  code: 0,
  creditEcts: 0,
  annee: "",
  semestre: "",
  statut: 0,
};

let ue = reactive({ ...ueInitial });

let annees = ref([]);
let semestres = ref([]);

let afficherAlerte = ref(false);
//let dureeAlerte = 5000;
let ajoutEnCours = ref(false);
const toast = useToast();

onMounted(function () {
  axiosApi.get("annee").then((response) => {
    annees.value = response.data._embedded.annee;
  });
  axiosApi.get("semestre").then((response) => {
    semestres.value = response.data._embedded.semestre;
  });
});

function ajouterUE(e) {
  e.preventDefault();
  ajoutEnCours.value = true;
  axiosApi.post("ue", ue).then(function (response) {
    ajoutEnCours.value = false;

    //succès
    if (response.status == 201) {
      //reset valeurs du form
      Object.assign(ue, ueInitial);

      afficherAlerte.value = true;
      toast.success("Le cours a bien été ajouté !", {
        timeout: 5000,
      });
    }
  });
}
</script>
