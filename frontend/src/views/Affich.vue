<template>
  <div>
    <h3>ici on va afficher tous les cours qu'on a</h3>
    <ul id="v-for-object" class="demo">
      <li v-for="(cour, index) in cours" v-bind:key="index">
          {{ value }}
      </li>
    </ul>
  </div>
  <!-- >

        <div v-for="(cour, index) in cours" v-bind:key="index">
      <a href="/cours/" + selfLinkToId(cour._links.self.href)>{{index}}. {{name}}</a>
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