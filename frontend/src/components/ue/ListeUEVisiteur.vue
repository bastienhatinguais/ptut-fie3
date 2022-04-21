<template>
  <div class="container">
    <!-- BREADCRUMB -->

    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <router-link id="lienAccueil"
            :to="{
              name: 'annee',
            }"
            >Accueil</router-link
          >
        </li>
        <li class="breadcrumb-item active" aria-current="page">
          {{ route.params.annee }}
        </li>
        <li class="breadcrumb-item active" aria-current="page">
          Semestre {{ route.params.id }}
        </li>
      </ol>
    </nav>

    <!-- AFFICHAGE LISTE DES UE -->
    <div class="accordion" id="accordionPanelsStayOpenExample">
      <div class="accordion-item" v-for="ue in semestre.ue" :key="ue.code">
        <h2 class="accordion-header" :id="`panelsStayOpen-heading${ue.code}`">
          <button
            class="accordion-button"
            type="button"
            data-bs-toggle="collapse"
            :data-bs-target="`#panelsStayOpen-collapse${ue.code}`"
            aria-expanded="true"
            :aria-controls="`panelsStayOpen-collapse${ue.code}`"
          >
            {{ ue.code }} - {{ ue.titre }}
          </button>
        </h2>
        <div
          :id="`panelsStayOpen-collapse${ue.code}`"
          class="accordion-collapse collapse show"
          :aria-labelledby="`panelsStayOpen-heading${ue.code}`"
        >
          <div class="accordion-body">
            <ul class="list-group list-group-flush">
              <li id="coursBouton"
                class="list-group-item list-group-item-action"
                @click="$router.push('/cours/' + cours.code)"
                v-for="cours in ue.cours"
                :key="cours.code"
              >
                {{ cours.titre }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.list-group-item-action {
  cursor: pointer;
}
.accordion-button{
  color:white !important;
  background-color:#039BE5 !important ;
}
.accordion-button:hover{
  color: #FF8183 !important;
  background-color: #283593 !important;
}

#coursBouton:hover {
  background: hsl(199, 98%, 89%, .4);
}

#lienAccueil:hover {
  color:#FF8183 ;
}

</style>

<script setup>
import { axiosApi } from "@/api/api";
import { ref, reactive, onMounted, defineProps, getCurrentInstance } from "vue";
import { useToast } from "vue-toastification";
import router from "@/router";
import { useRoute } from "vue-router";
import { selfLinkToId, trimLink } from "@/utils";

const toast = useToast();
const route = useRoute();
let semestre = reactive({});

onMounted(function () {
  chercherUE();
});

function chercherUE() {
  axiosApi.get("semestre-detail/" + route.params.id).then((response) => {
    Object.assign(semestre, response.data);
    console.log(semestre);
  });
}
</script>

