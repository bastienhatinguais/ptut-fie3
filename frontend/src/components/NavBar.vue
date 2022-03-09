<template>
  <nav class="navbar navbar-light bg-light navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img
          src="@/assets/logo.png"
          alt=""
          width="30"
          height="24"
          class="d-inline-block align-text-top"
        />
        Bootstrap
      </a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" to="/">Accueil</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/api">Api</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/cours">Cours</router-link>
        </li>
      </ul>
      <form class="d-flex">
        <input
          class="form-control me-2"
          type="search"
          placeholder="Search"
          aria-label="Search"
        />
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </nav>
</template>

<script setup>
</script>

<style>
.navbar-expand-lg .navbar-nav {
  flex-direction: row;
}
</style>