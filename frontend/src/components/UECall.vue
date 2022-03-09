<template>
  <div>    
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Code</th>
        <th>Titre</th>
        <th>Année</th>
        <th>Semestre</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(ue, code) in lesUE" :key=code> 
        <td> {{ ue.code }} </td>
        <td> {{ ue.titre }} </td>
        <td> {{ ue.annee }} </td>
        <td> {{ ue.semestre }} </td>
      </tr>
      </tbody>
    </table>

    <button @click="ajouterUE" type="button" class="btn btn-success">
      Ajouter UE
    </button>
  </div>
</template>

<script setup>
import { axiosApi } from "@/api/api.js";
import { onMounted, ref } from 'vue'

let lesUE = ref([]);

onMounted(function(){
  callApi();
})
function callApi() {
  axiosApi
    .get(`ue`)
    .then((response) => {
      lesUE.value = response.data._embedded.ue;
      console.log(lesUE.value)
    })
    .catch((e) => {
      this.errors.push(e);
    });
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>