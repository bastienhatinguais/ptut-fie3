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
      <router-link to="/cours">Cours</router-link>
    </p>
    <!-- route outlet -->
    <!-- component matched by the route will render here -->
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
