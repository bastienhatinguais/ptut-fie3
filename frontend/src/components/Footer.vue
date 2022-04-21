<template>

    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 border-top" id="footer">
    <div class="col-md-4 d-flex align-items-center">
      <a href="https://isis.univ-jfc.fr/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1" target="_blank">
        <svg class="bi" width="10" height="24">s</svg>

      <img 
        src="@/assets/logoBlanc.png"
        width="28"
        height="24"/>
        <svg class="bi" width="10" height="24"></svg>
      </a>
      <span class="text-muted" style="color:white !important; width:300px" >PTUT FIE3 2022 - Projet Syllabus</span>
    </div>

    <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
      <li class="ms-3"><a class="text-muted" href="https://twitter.com/isis_ingenieur" target="_blank"><img src="@/assets/logoTwitter.png" width="20" height="16"/><use xlink:href="https://twitter.com/isis_ingenieur"></use></a></li>
      <li class="ms-3"><a class="text-muted" href="https://www.instagram.com/isis_ingenieur/" target="_blank"><img src="@/assets/logoInsta.png" width="18" height="18"/><use xlink:href="https://www.instagram.com/isis_ingenieur/"></use></a></li>
      <li class="ms-3"><a class="text-muted" href="https://www.facebook.com/pg/isis.castres/posts/" target="_blank"><img src="@/assets/logoFb.png" width="20" height="16"/><use xlink:href=""></use></a></li>
      <li class="ms-3"><svg class="bi" width="10" height="24">s</svg></li>
    </ul>
  </footer>

</template>

<style scoped>
#footer {
    width:100% ;
    background-color:#039BE5;
    margin-top: 20px ;
    padding : 0;
}
.text-muted {
    color:white !important;
    font-size: 15px;
}

</style>