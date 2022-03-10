<template>
  <div>
    <h3>ici on va afficher tous les cours qu'on a</h3>
  </div>
  <div>
    <ul class="iciliste">
        <li v-for="(cour, index) in cours" :key="index">
            {{ cour.titre }} {{selfLinkToId(cour._links.self.href)}}
        </li>
    </ul>
  </div>
  <!-- >
  <div class="liste" v-for="(cour, index) in cours" :key="index">
    <router-link :to "{name : 'cours/id', params: {courId=selfLinkToId(cour._links.self.href)}}" class="dropdown-item">Home</router-link>
  </div>
  <!--> 
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