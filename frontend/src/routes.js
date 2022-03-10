import Home from "@/views/Home.vue";
import Api from "@/views/Api.vue";
import CoursListe from "@/views/cours/Liste.vue";
import CoursAjouter from "@/views/cours/Ajouter.vue";

export default [
  { path: "/", component: Home },
  { path: "/api", component: Api },
  {
    path: "/cours",
    component: CoursListe,
  },
  {
    path: "/cours/ajouter",
    component: CoursAjouter,
  },
];
