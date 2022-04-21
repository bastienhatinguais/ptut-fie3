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
    @submit="modifierCours"
  >
    <h3>Edition du cours {{ $route.params.id }}</h3>
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

    <!-- INTERVENANTS -->
    <div class="form-group">
      <label class="form-label">Intervenants :</label>
      <form class="d-flex">
        <input
          class="form-control me-2 mb-2"
          type="search"
          placeholder="Filtre..."
          aria-label="Search"
          v-model="recherche"
        />
      </form>
      <div class="list-group personnels" v-if="personnels">
        <label
          class="list-group-item"
          v-for="(p, index) in personnelsFiltre"
          :key="index"
        >
          <input
            class="form-check-input me-1"
            type="checkbox"
            v-if="p._links"
            :value="p._links.self.href"
            v-model="cours.intervenants"
          />
          {{ p.nom }} {{ p.prenom }}
        </label>
      </div>
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
      <label for="plan">Plan du cours</label>
      <textarea
        class="form-control"
        id="plan"
        rows="3"
        v-model="cours.planDuCours"
      ></textarea>
    </div>
    <div class="col-12 mx-auto">
      <button
        v-if="modificationEnCours"
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

<script setup>
import { axiosApi } from "@/api/api";
import {
  ref,
  reactive,
  onMounted,
  defineProps,
  getCurrentInstance,
  watch,
} from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";
import { useRoute } from "vue-router";
import { selfLinkToId, trimLink } from "@/utils";

const coursInitial = {
  titre: "",
  creditsEcts: 0,
  ue: "",
  responsable: 0,
  description: "",
  modalitesEvaluation: "",
  nbHeureCM: 0,
  nbHeureTD: 0,
  nbHeureTP: 0,
  objectifs: "",
  prerequis: "",
  intervenants: [],
};

const props = defineProps({ id: Number });

let cours = reactive({ ...coursInitial });
let personnels = ref([]);
let lesUE = ref([]);
let responsable = ref(null);
let modificationEnCours = ref(false);
const toast = useToast();
const route = useRoute();
let recherche = ref("");
let personnelsFiltre = ref([]);

let self = getCurrentInstance();

watch(recherche, () => {
  personnelsFiltre.value = personnels.value.filter(function (p) {
    let nom = p.nom.toLowerCase();
    let prenom = p.prenom.toLowerCase();
    return nom.includes(recherche.value) || prenom.includes(recherche.value);
  });
});

onMounted(function () {
  axiosApi.get("cours/" + route.params.id).then((response) => {
    Object.assign(cours, response.data);
    console.log(cours);
    let responsableLink = response.data._links.responsable.href;
    let ueLink = response.data._links.ue.href;

    //Récupération du responsable du cours
    axiosApi
      .get(trimLink(responsableLink))
      .then((res) => {
        console.log(res);
        cours.responsable = res.data._links.self.href;
      })
      .catch((e) => console.log(e));
  });

  axiosApi
    .get("cours/" + route.params.id + "/intervenants")
    .then((response) => {
      for (const [key, value] of Object.entries(
        response.data._embedded.personnel
      )) {
        cours.intervenants.push(value._links.self.href);
      }
      console.log(cours.intervenants);
    })
    .catch((e) => console.log(e));

  axiosApi
    .get("personnel")
    .then((response) => {
      console.log(response.data);
      personnels.value = response.data._embedded.personnel;
      personnelsFiltre.value = response.data._embedded.personnel;
    })
    .catch((e) => console.log(e));

  axiosApi
    .get("ue")
    .then((response) => {
      console.log(response.data);
      lesUE.value = response.data._embedded.ue;
    })
    .catch((e) => console.log(e));
});

function modifierCours(e) {
  e.preventDefault();
  modificationEnCours.value = true;
  console.log(cours);
  axiosApi
    .patch("cours/" + route.params.id, cours)
    .then(function (response) {
      modificationEnCours.value = false;
      console.log(response);
      //succès
      if (response.status == 200) {
        //reset valeurs du form
        Object.assign(cours, coursInitial);
        toast.success("Le cours a bien été modifié !", {
          timeout: 5000,
        });

        router.push("/cours");
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


<style scoped>
.personnels {
  height: 200px;
  overflow: auto;
}
</style>