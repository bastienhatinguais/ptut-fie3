<template>
  <div>
  <NavBar></NavBar>
  <div class="mt-4">
    <router-view></router-view>
  </div>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
</script>

