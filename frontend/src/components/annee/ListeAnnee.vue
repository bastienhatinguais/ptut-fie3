<!-- AFFICHAGE DES ANNEES -->

<template>
  <div class="container" id="tout">
    <div class="row">
      <div class="m-3 col">
        <h4>Cycle Préparatoire</h4>
        <div id="cyclePrépa" class="p-3">
          <ul>
            <li class="listeAnnee">FIE1 - 1ère année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 1, statut: 'etudiant', annee:'FIE1'},
                  }"
                  >Semestre 1</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 2, statut: 'etudiant', annee:'FIE1' },
                  }"
                  >Semestre 2</router-link
                >
              </li>
            </ul>
            <li class="listeAnnee">FIE2 - 2ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 3, statut: 'etudiant', annee:'FIE2' },
                  }"
                  >Semestre 3</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 4, statut: 'etudiant', annee:'FIE2' },
                  }"
                  >Semestre 4</router-link
                >
              </li>
            </ul>
          </ul>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="m-3 col">
        <h4>Cycle Etudiant</h4>
        <div id="cycleEtud" class="p-3">
          <ul>
            <li class="listeAnnee">FIE3 - 3ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 5, statut: 'etudiant', annee:'FIE3' },
                  }"
                  >Semestre 5</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 7, statut: 'etudiant', annee:'FIE3' },
                  }"
                  >Semestre 6</router-link
                >
              </li>
            </ul>
            <li class="listeAnnee">FIE4 - 4ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 9, statut: 'etudiant', annee:'FIE4' },
                  }"
                  >Semestre 7</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 11, statut: 'etudiant', annee:'FIE4' },
                  }"
                  >Semestre 8</router-link
                >
              </li>
            </ul>
            <li class="listeAnnee">FIE5 - 5ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 13, statut: 'etudiant', annee:'FIE5' },
                  }"
                  >Semestre 9</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 15, statut: 'etudiant', annee:'FIE5' },
                  }"
                  >Semestre 10</router-link
                >
              </li>
            </ul>
          </ul>
        </div>
      </div>
      <div class="m-3 col">
        <h4>Cycle Apprenti</h4>
        <div id="cycleApp" class="p-3">
          <ul>
            <li class="listeAnnee">FIA3 - 3ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 6, statut: 'apprenti', annee:'FIA3' },
                  }"
                  >Semestre 5</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 8, statut: 'apprenti', annee:'FIA3' },
                  }"
                  >Semestre 6</router-link
                >
              </li>
            </ul>
            <li class="listeAnnee">FIA4 - 4ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 10, statut: 'apprenti', annee:'FIA4' },
                  }"
                  >Semestre 7</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 12, statut: 'apprenti', annee:'FIA4' },
                  }"
                  >Semestre 8</router-link
                >
              </li>
            </ul>
            <li class="listeAnnee">FIA5 - 5ème année</li>
            <ul>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 14, statut: 'apprenti', annee:'FIA5' },
                  }"
                  >Semestre 9</router-link
                >
              </li>
              <li>
                <router-link
                  class="link-dark"
                  :to="{
                    name: 'listeParSemestre',
                    params: { id: 16, statut: 'apprenti', annee:'FIA5' },
                  }"
                  >Semestre 10</router-link
                >
              </li>
            </ul>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h4 {
  color:#039BE5;
}

.link-dark {
  color:#283593 ;
  text-decoration: none;
}

li {
  list-style-image: url(@/assets/fleche3.svg);
  
}
.listeAnnee {
  list-style-image: url(@/assets/fleche4.svg);
}

div {
  border-radius: 6px;
}

.link-dark:hover {
    color: #FF8183;
}

#cycleApp, 
#cyclePrépa, 
#cycleEtud {
  background-color: white ;
  background: hsl(199, 98%, 89%, .2) ;
  box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
}

#tout {
  box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
}
</style>