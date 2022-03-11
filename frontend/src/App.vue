<template>
  <div>
    <h1>Hello App!</h1>
    <p>
      <!-- use the router-link component for navigation. -->
      <!-- specify the link by passing the `to` prop. -->
      <!-- `<router-link>` will render an `<a>` tag with the correct `href` attribute -->
      <router-link to="/">Accueil</router-link>
      <br />
      <router-link to="/api">Api</router-link>
      <br />
      <router-link to="/ue">UE</router-link>
      <br />
      <router-link to="/cours">Cours</router-link>
    </p>
    <!-- route outlet -->
    <!-- component matched by the route will render here -->
    <div class="container">
      <router-view></router-view>
    </div>
  <NavBar></NavBar>
  <div class="mt-4">
    <router-view></router-view>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
</script>

