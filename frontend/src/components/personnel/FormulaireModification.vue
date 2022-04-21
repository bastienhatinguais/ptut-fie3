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
    <h3>Modification d'un personnel</h3>
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

const personnelInitial = {
  nom: "",
  prenom: "",
  email: "",
  pseudo: "",
};

let cours = ref([]);
const props = defineProps({ id: Number });
let personnel = reactive({ ...personnelInitial });
let modificationEnCours = ref(false);
const toast = useToast();
const route = useRoute();

onMounted(function () {
  axiosApi.get("personnel/" + route.params.id).then((response) => {
    Object.assign(personnel, response.data);
  });
});

function modifierPersonnel(e) {
  e.preventDefault();
  modificationEnCours.value = true;
  axiosApi
    .patch("personnel/" + route.params.id, personnel)
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