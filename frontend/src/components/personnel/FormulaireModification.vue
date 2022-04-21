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
    @submit="modifierPersonnel"
  >
  <h3>Ajout d'un personnel</h3>
    <!-- NOM -->
    <div>
      <label for="validationNom" class="form-label">Nom</label>
      <input
        class="form-control"
        type="text"
        id="validationNom"
        v-model="personnel.nom"
        required
      />
    </div>

    <!-- PRENOM -->
    <div>
      <label for="validationPrenom" class="form-label">Prénom</label>
      <input
        type="text"
        class="form-control"
        id="validationPrenom"
        v-model="personnel.prenom"
        required
      />
    </div>


    <!--estDirecteur-->
    <div>
        <label for="validationDirecteur" class="form-label">Est le directeur</label>
        <!--<input
            type="radio"
            class="form-control"
            id="validationDirecteur"
            value="true"
            v-model="personnel.estDirecteur"
            required
        />-->
        <select
        class="form-select"
        aria-label="Le personnel est-il directeur ?"
        v-model="personnel.estDirecteur"
      >
        <option :value="true"> oui</option>
        <option :value="false">non </option> 
      </select>
    </div>

    <!-- RESPONSABLE -->
    <div>
      <label class="form-label">Responsable des cours :</label>
      <select
        class="form-select"
        aria-label="Choisissez les cours associés au personnel :"
        v-model="personnel.responsableDesCours"
        multiple = "true"
      >
        <option
          v-for="(cour, index) in cours"
          :key="index"
          :ref="cour.ref"
          :value="cour._links.self.href"
        >
          {{ cour.titre }}
        </option>
      </select>
    </div>

    <!-- DONNE LES COURS -->
    <div>
      <label class="form-label">Dispense les cours :</label>
      <select
        class="form-select"
        aria-label="Choisissez les cours dispensés par le personnel :"
        v-model="personnel.donneLesCours"
        
        multiple
        style =""
      >
      

        <option
          v-for="(cour, index) in cours"
          :key="index"
          :ref="cour.ref"
          :value="cour._links.self.href"
        >
          {{ cour.titre }}
        </option>

      
      </select>
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
import { ref, reactive, onMounted, defineProps, getCurrentInstance } from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";
import { useRoute } from "vue-router";
import { selfLinkToId, trimLink } from "@/utils";


let personnel = reactive({});
let personnels = reactive({ ...personnelInitial });
const personnelInitial = {
  nom: "",
  prenom: "",
  estDirecteur: false,
  responsableDesCours: [],
  donneLesCours: [],
};

let cours = ref([]);
const props = defineProps({ id: Number });
let responsable = ref(null);
let modificationEnCours = ref(false);
const toast = useToast();
const route = useRoute();
let self = getCurrentInstance();



onMounted(function () {
  axiosApi.get("personnel/" + route.params.id).then((response) => {
    Object.assign(personnel, response.data);
  });
  axiosApi
    .get("personnel")
    .then((response) => {
      console.log(response.data);
      personnels.value = response.data._embedded.personnel;
    })
    .catch((e) => console.log(e));
  });
  axiosApi.get("cours").then((response) => {
    cours.value = response.data._embedded.cours;
});


function modifierPersonnel(e) {
  e.preventDefault();
  modificationEnCours.value = true;
  axiosApi
    .put("personnel/" + route.params.id, personnel)
    .then(function (response) {
      modificationEnCours.value = false;
      console.log(response);
      //succès
      if (response.status == 200) {
        //reset valeurs du form
        Object.assign(personnel, personnelInitial);
        toast.success("Le personnel a bien été modifié !", {
          timeout: 5000,
        });
        router.push("/personnel");
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