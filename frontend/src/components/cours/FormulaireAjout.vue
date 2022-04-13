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

    <!-- UE -->
    <div>
      <label class="form-label">UE</label>
      <select
        class="form-select"
        aria-label="Choisissez l'UE'"
        v-model="cours.ue"
      >
        <option
          v-for="(ue, index) in lesUE"
          :key="index"
          :ref="ue.ref"
          :value="ue._links.self.href"
        >
          {{ ue.titre }}
        </option>
      </select>
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
      <div class="col-lg-3 col-md-12">
        <label for="nbHeureCM" class="form-label">Nombre d'heures CM</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureCM"
          v-model="cours.nbHeureCM"
          required
        />
      </div>
      <div class="col-lg-3 col-md-12">
        <label for="nbHeureTD" class="form-label">Nombre d'heures TD</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureTD"
          v-model="cours.nbHeureTD"
          required
        />
      </div>
      <div class="col-lg-3 col-md-12">
        <label for="nbHeureTP" class="form-label">Nombre d'heures TP</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureTP"
          v-model="cours.nbHeureTP"
          required
        />
      </div>
      <div class="col-lg-3 col-md-12">
        <label for="nbHeureFOAD" class="form-label">Nombre d'heures FOAD</label>
        <input
          type="number"
          class="form-control"
          id="nbHeureFOAD"
          v-model="cours.nbHeureFOAD"
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

    <!-- COMPETENCES VISEES -->
    <div class="form-group">
      <label for="competences">Compétences visées</label>
      <textarea
        class="form-control"
        id="competences"
        rows="3"
        v-model="cours.competences"
      ></textarea>
    </div>

    <!-- PLAN DU COURS -->
    <div class="form-group">
      <label for="planDuCours">Plan du cours</label>
      <textarea
        class="form-control"
        id="planDuCours"
        rows="3"
        v-model="cours.planDuCours"
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
  ue:"",
  responsable: 0,
  description: "",
  modalitesEvaluation: "",
  nbHeureCM: 0,
  nbHeureTD: 0,
  nbHeureTP: 0,
  nbHeureFOAD : 0,
  objectifs: "",
  planDuCours : "",
  prerequis: "",
  competences : "" ,
};

let cours = reactive({ ...coursInitial });

let lesUE = ref([]);
let personnels = ref([]);
let ajoutEnCours = ref(false);
const toast = useToast();

onMounted(function () {
  console.log("get personnel");
  axiosApi.get("personnel").then((response) => {
    personnels.value = response.data._embedded.personnel;
  });
  axiosApi.get("ue").then((response) => {
    lesUE.value = response.data._embedded.ue;
  });
});

function ajouterCours(e) {
  e.preventDefault();
  ajoutEnCours.value = true;
  console.log(cours);
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
