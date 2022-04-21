<template>
  <!-- AFFICHAGE LISTE DES UE -->
  <div class="container">
    <h1>Liste des UE</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Code</th>
          <th>Titre</th>
          <th>Crédits ECTS</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(ue, code) in lesUE"
          :key="code"
          data-toggle="collapse"
          data-target="#accordion"
        >
          <td id="code">{{ ue.code }}</td>
          <td
            id="titre"
            @click="
              () => {
                setCurrent(selfLinkToId(ue._links.self.href));
                recupererCours();
              }
            "
          >
            {{ ue.titre }}
          </td>
          <td id="creditEcts">{{ ue.creditEcts }}</td>
          <td>
            <!-- BOUTON SUPPRIMER -->
            <button
              id="suppr"
              type="button"
              class="btn btn-danger"
              data-bs-toggle="modal"
              data-bs-target="#test"
              
              @click="
                () => {
                  setCurrent(selfLinkToId(ue._links.self.href));
                  supprimer();
                }
              "
            >
              <i class="bi bi-trash-fill"></i>
              Supprimer
            </button>
            <!-- BOUTON MODIFIER -->
            <router-link
              :to="{
                name: 'modifier',
                params: { id: selfLinkToId(ue._links.self.href) },
              }"
            >
              <button
                id="modif"
                type="button"
                class="btn btn-primary"
                @click="
                  () => {
                    setCurrent(selfLinkToId(ue._links.self.href));
                  }
                "
              >
                <i class="bi bi-pencil-fill"></i>
                Modifier
              </button>
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
button {
  margin : 4px ; 
}
#modif {
  background-color: #039BE5 ;
  border-color: #039BE5 ;
}

#suppr {
  background-color: #283593 ;
  border-color: #283593 ;
}

button:hover{
  background-color: #FF8183 !important;
  border-color: #FF8183 !important;
}
</style>

<script setup>
import { axiosApi } from "@/api/api.js";
import { onMounted, ref } from "vue";
import { useToast } from "vue-toastification";
import { selfLinkToId } from "@/utils";

const toast = useToast();
let lesUE = ref([]);
let lesCours = ref([]);
let current = null;

onMounted(function () {
  chercherUE();
});
/**
 * set l'id
 * @param {Integer} id
 */
function setCurrent(id) {
  current = id;
  console.log(current);
}

function chercherUE() {
  axiosApi.get(`ue`).then((response) => {
    lesUE.value = response.data._embedded.ue;
    console.log(lesUE.value);
  });
}

function supprimer() {
  axiosApi
    .delete("ue/" + current)
    .then((response) => {
      toast.success("L'UE a bien été supprimé !", {
        timeout: 5000,
      });
      chercherUE();
    })
    .catch((e) => {
      console.log(e);
    });
}

function recupererCours() {
  axiosApi.get("ue/" +current+"/cours")
  .then((response) => {
    lesCours.value = response.data._embedded.cours;
    console.log(lesCours.value);
  });
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>