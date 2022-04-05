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
    @submit="ajouterPersonnel"
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
      <button class="btn btn-primary" type="submit">Ajouter</button>
  </form>
</template>

<script setup>
import { axiosApi } from "@/api/api";
import { ref, reactive, onMounted, setup } from "vue";
import { useToast } from "vue-toastification";

const personnelInitial = {
  nom: "",
  prenom: "",
  estDirecteur: false,
  responsableDesCours: "",
  donneLesCours: "",
};

let personnel = reactive({ ...personnelInitial });

let cours = ref([]);
let personnels = ref([]);
let afficherAlerte = ref(false);
let dureeAlerte = 5000;
let ajoutEnCours = ref(false);
const toast = useToast();

onMounted(function () {
  axiosApi.get("cours").then((response) => {
    cours.value = response.data._embedded.cours;
  });
});

function ajouterPersonnel(e) {
  e.preventDefault();
  ajoutEnCours.value = true;
  axiosApi
    .post("personnels", personnel)
    .then(function (response) {
      ajoutEnCours.value = false;

      //succès
      if (response.status == 201) {
        //reset valeurs du form
        Object.assign(personnel, personnelInitial);

        afficherAlerte.value = true;
        toast.success("Le personnel a bien été ajouté !", {
          timeout: 5000,
        });
      }
    })
    .catch((e) => {});
}
</script>
