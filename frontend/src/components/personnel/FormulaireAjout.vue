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

    <!-- PSEUDO -->
    <div>
      <label for="pseudo" class="form-label">Pseudo</label>
      <input
        type="text"
        class="form-control"
        id="pseudo"
        v-model="personnel.pseudo"
        required
      />
    </div>

    <!--EMAIL-->
    <div>
      <label for="email" class="form-label">Email</label>
      <input
        type="email"
        class="form-control"
        id="email"
        v-model="personnel.email"
        required
      />
    </div>

    <!--MOT DE PASSE-->
    <div>
      <label for="motDePasse" class="form-label">Mot de Passe</label>
      <div class="row g-2">
        <div class="col-10">
          <input
            type="text"
            class="form-control"
            id="motDePasse"
            :disabled="!mdpPerso"
            v-model="personnel.motDePasse"
          />
        </div>
        <i id="boutonmdp" class="btn btn-primary col-2" @click="mdpPerso = !mdpPerso">
          <i class="bi bi-lock-fill"></i>
        </i>
      </div>
    </div>

    <button class="btn btn-primary" type="submit">Ajouter</button>
  </form>
</template>


<style scoped>
button {
  background-color: #283593 ;
  border-color: #283593 ;
}
#boutonmdp {
  background-color: #039BE5 ;
  border-color: #039BE5 ;
}
button:hover, #boutonmdp:hover {
  background-color: #FF8183 !important;
  border-color: #FF8183 !important;
}
</style>

<script setup>
import { axiosApi } from "@/api/api";
import router from "@/router";
import { ref, reactive, onMounted, inject } from "vue";
import { useToast } from "vue-toastification";

let auth = inject("auth");

const personnelInitial = {
  nom: "",
  prenom: "",
  pseudo: "",
  email: "",
  motDePasse: "ptut2022",
};

let personnel = reactive({ ...personnelInitial });

let afficherAlerte = ref(false);
let ajoutEnCours = ref(false);
let mdpPerso = ref(false);
const toast = useToast();

function ajouterPersonnel(e) {
  e.preventDefault();
  ajoutEnCours.value = true;

  //ajout des informations personnelles du personnel qu'on ajoute
  auth
    .inscription(personnel)
    .then(function (response) {
      ajoutEnCours.value = false;

      //succès
      if (response.status == 201) {
        //reset valeurs du form
        Object.assign(personnel, personnelInitial);
        router.push("/personnel");
        afficherAlerte.value = true;
        toast.success("Le personnel a bien été ajouté !", {
          timeout: 5000,
        });
      }
    })
    .catch((e) => {});
}
</script>
