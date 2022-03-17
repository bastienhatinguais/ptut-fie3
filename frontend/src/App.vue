<template>
  <NavBar></NavBar>
  <div class="mt-4">
    <router-view></router-view>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import Auth from "@/Auth";
import { reactive } from "@vue/reactivity";
import { provide } from "@vue/runtime-core";
import { axiosApi } from "@/api/api";

let auth = reactive(new Auth());
provide("auth", auth);

axiosApi.interceptors.request.use(
  function (config) {
    console.log("stop ! ");
    config.headers.common = Object.assign(
      {
        ...config.headers.common,
      },
      getAuthorization()
    );
    return config;
  },
  function (error) {
    // Do something with request error
    console.log(error);
    return Promise.reject(error);
  }
);

function getAuthorization() {
  if (auth.getUtilisateur())
    return { Authorization: "Bearer " + auth.getUtilisateur().token };
  return {};
}
</script>

