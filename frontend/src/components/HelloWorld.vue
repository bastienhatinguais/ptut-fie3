<template>
  <div>
    <button @click="callApi" type="button" class="btn btn-success">
      OUAIS OUAIS OUAIS APPEL API
    </button>
    <h2>Message : {{ message }}</h2>
  </div>
</template>
<script setup>
import { axiosApi } from "@/api/api.js";
import { ref } from "vue";

let message = ref("");

function callApi() {
  axiosApi
    .get(`hello`)
    .then((response) => {
      message.value = response.data;
    })
    .catch((e) => {
      this.errors.push(e);
    });
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
