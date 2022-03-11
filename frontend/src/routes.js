import Home from "@/views/Home.vue";
import Api from "@/views/Api.vue";
import CoursListe from "@/views/cours/Liste.vue";
import CoursAjouter from "@/views/cours/Ajouter.vue";
import CoursModifier from "@/views/cours/Modifier.vue";

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
  {
    path: "/cours/:id/modifier",
    component: CoursModifier,
    props: true,
  },
];
