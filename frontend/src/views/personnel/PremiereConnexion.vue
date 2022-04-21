<template>
  <div class="container">
    <h1>Première connexion</h1>
    <h6>
      Afin d'accéder à l'ensemble de l'application, veuillez modifier votre mot
      de passe.
    </h6>

    <div
      class="alert alert-danger d-flex align-items-center"
      role="alert"
      v-if="error != ''"
    >
      <i class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2"></i>
      <div>{{ error }}</div>
    </div>

    <form @submit.prevent="validerPremiereConnexion">
      <div class="mb-3">
        <label for="motDePasse" class="form-label">Mot de passe</label>
        <input
          type="password"
          class="form-control"
          id="motDePasse"
          v-model="motDePasse"
          required
          min="3"
        />
      </div>
      <div class="mb-3">
        <label for="motDePasse" class="form-label"
          >Confirmation du mot de passe</label
        >
        <input
          type="password"
          class="form-control"
          id="motDePasseConfirm"
          v-model="motDePasseConfirm"
          min="3"
          required
        />
      </div>

      <button type="submit" class="btn btn-primary">Valider</button>
    </form>
  </div>
</template>

<script setup>
import { axiosApi } from "@/api/api";
import { ref } from "@vue/reactivity";
import { inject } from "vue";
import { useToast } from "vue-toastification";

const toast = useToast();
let auth = inject("auth");

let motDePasse = ref("");
let motDePasseConfirm = ref("");
let error = ref("");

function validerPremiereConnexion() {
  error.value = "";
  if (motDePasse.value.length < 4) {
    error.value = "Le mot de passe n'est pas assez long.";
  } else {
    if (motDePasse.value != motDePasseConfirm.value) {
      error.value = "Les mots de passe ne correspondent pas.";
      motDePasse.value = "";
      motDePasseConfirm.value = "";
    } else {
      //premiere connexion validée
      let utilisateur = auth.getUtilisateur();
      axiosApi
        .post("/auth/premiere-connexion", {
          id: utilisateur.id,
          motDePasse: motDePasse.value,
        })
        .then((response) => {
          toast.success(response.data.message + " Veuillez vous reconnecter.", {
            timeout: 5000,
          });
          auth.deconnexion();
        })
        .catch((error) => {
          console.log(error);
          toast.error(error.message, {
            timeout: 5000,
          });
        });
    }
  }
}
</script>

<style>
</style>