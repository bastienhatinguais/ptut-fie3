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
    @submit="ajouterCours"
  >
    <h3>Ajout d'un cours</h3>
    <!-- TITRE -->
    <div>
      <label for="validationTitre" class="form-label">Titre</label>
      <input
        class="form-control"
        type="text"
        id="validationTitre"
        v-model="cours.titre"
      />
    </div>

    <!-- CREDITS ECTS -->
    <div>
      <label for="validationCreditEcts" class="form-label">Crédits ECTS</label>
      <input
        type="number"
        class="form-control"
        id="validationCreditEcts"
        v-model="cours.creditsEcts"
        required
      />
    </div>

    <!-- RESPONSABLE -->
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

    <!-- DESCRIPTION -->
    <div class="form-group">
      <label for="description">Description</label>
      <textarea
        class="form-control"
        id="description"
        rows="3"
        v-model="cours.description"
      ></textarea>
    </div>

    <!-- MODALITES D'EVALUATION -->
    <div class="form-group">
      <label for="modalitesEvaluation">Modalités d'évaluation</label>
      <textarea
        class="form-control"
        id="modalitesEvaluation"
        rows="3"
        v-model="cours.modalitesEvaluation"
      ></textarea>
    </div>

    <!-- NB D'HEURES -->
    <div class="row gx-3 justify-content-around">
      <div class="col-lg-4 col-md-12">
        <label for="nbHeureCM" class="form-label">Nombre d'heures CM</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureCM"
          v-model="cours.nbHeureCM"
          required
        />
      </div>
      <div class="col-lg-4 col-md-12">
        <label for="nbHeureTD" class="form-label">Nombre d'heures TD</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureTD"
          v-model="cours.nbHeureTD"
          required
        />
      </div>
      <div class="col-lg-4 col-md-12">
        <label for="nbHeureTP" class="form-label">Nombre d'heures TP</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureTP"
          v-model="cours.nbHeureTP"
          required
        />
      </div>
    </div>

    <!-- OBJECTIFS -->
    <div class="form-group">
      <label for="objectifs">Objectifs</label>
      <textarea
        class="form-control"
        id="objectifs"
        rows="3"
        v-model="cours.objectifs"
      ></textarea>
    </div>

    <!-- PREREQUIS -->
    <div class="form-group">
      <label for="prerequis">Prérequis</label>
      <textarea
        class="form-control"
        id="prerequis"
        rows="3"
        v-model="cours.prerequis"
      ></textarea>
    </div>
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
import { ref, reactive, onMounted, inject } from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";
import { axiosApi } from "@/api/api";

const coursInitial = {
  titre: "",
  creditsEcts: 0,
  responsable: 0,
  description: "",
  modalitesEvaluation: "",
  nbHeureCM: 0,
  nbHeureTD: 0,
  nbHeureTP: 0,
  objectifs: "",
  prerequis: "",
};

let cours = reactive({ ...coursInitial });

let personnels = ref([]);
let ajoutEnCours = ref(false);
const toast = useToast();

onMounted(function () {
  console.log("get personnel");
  axiosApi.get("personnel").then((response) => {
    personnels.value = response.data._embedded.personnel;
  });
});

function ajouterCours(e) {
  e.preventDefault();
  ajoutEnCours.value = true;
  axiosApi
    .post("cours", cours)
    .then(function (response) {
      ajoutEnCours.value = false;

      //succès
      if (response.status == 201) {
        //reset valeurs du form
        Object.assign(cours, coursInitial);
        toast.success("Le cours a bien été ajouté !", {
          timeout: 5000,
        });

        router.push("/cours");
      }
    })
    .catch(function (error) {
      ajoutEnCours.value = false;
      toast.error(error, {
        timeout: 5000,
      });
    })
    .then(function (response) {
      console.log(response);
    });
}
</script>
