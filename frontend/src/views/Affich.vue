<!--vue qui affiche la liste des cours, chacun etant clicable et renvoie vers sa page sp&cifique-->
<template>
  <div>
    <h3>Liste des cours</h3>
    <ul id="v-for-object" class="demo">
      <li v-for="(cour, index) in cours" v-bind:key="index">
         <a :href="'/cours/' + selfLinkToId(cour._links.self.href)">{{cour.titre}}</a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { selfLinkToId } from "@/utils";
import { ref, onMounted } from 'vue'
import { axiosApi } from "@/api/api";

let cours = ref([]);

onMounted(function () {
  axiosApi.get("cours").then((response) => {
    cours.value = response.data._embedded.cours;
    console.log(response);
  });
});

</script>