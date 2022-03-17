<template>
  <form @submit="authConnexion">
    <h2>Se connecter</h2>
    <div
      v-if="erreur"
      class="alert alert-danger d-flex align-items-center"
      role="alert"
    >
      <i class="bi bi-exclamation-triangle-fill"></i>
      <span class="ms-2">Mauvais pseudo ou mot de passe.</span>
    </div>
    <div class="mb-3">
      <label for="pseudo" class="form-label">Pseudo</label>
      <input
        type="text"
        class="form-control"
        id="pseudo"
        v-model="utilisateur.pseudo"
        required
      />
    </div>
    <div class="mb-3">
      <label for="motDePasse" class="form-label">Mot de Passe</label>
      <input
        type="password"
        class="form-control"
        id="motDePasse"
        v-model="utilisateur.motDePasse"
        required
      />
    </div>
    <div class="d-flex justify-content-center">
      <button v-if="enCours" class="btn btn-primary" type="button" disabled>
        <span
          class="spinner-border spinner-border-sm"
          role="status"
          aria-hidden="true"
        ></span>
        Connexion...
      </button>
      <button v-else type="submit" class="btn btn-primary">Valider</button>
    </div>
  </form>
</template>
<script setup>
import { reactive, ref } from "@vue/reactivity";
import { useToast } from "vue-toastification";
import { inject } from "vue";

let auth = inject("auth");
const toast = useToast();
let utilisateur = reactive({});
let enCours = ref(false);
let erreur = ref(false);

function authConnexion(e) {
  e.preventDefault();
  enCours.value = true;

  auth
    .connexion(utilisateur)
    .then(function (response) {
      erreur.value = false;
      console.log(response);
      if (response.token) {
        toast.success("Vous êtes bien connecté !", {
          timeout: 5000,
        });
      }
    })
    .catch((e) => {
      console.log(e);
      erreur.value = true;
      utilisateur.motDePasse = "";
    })
    .then(() => {
      enCours.value = false;
    });
}
</script>
