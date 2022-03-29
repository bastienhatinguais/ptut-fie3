import Home from "@/views/Home.vue";
import Api from "@/views/Api.vue";
import CoursListe from "@/views/cours/Liste.vue";
import CoursAjouter from "@/views/cours/Ajouter.vue";
import UE from "@/views/UE.vue";
import UEAjouter from "@/views/ue/AjoutUE.vue";
import UEModifier from "@/views/ue/ModificationUE.vue";
import CoursModifier from "@/views/cours/Modifier.vue";
import Inscription from "./views/Inscription.vue";

export default [
  { path: "/", component: Home },
  { path: "/api", component: Api },
  { path: "/inscription", component: Inscription },

  {
    path: "/cours",
    component: CoursListe,
  },
  {
    path: "/cours/ajouter",
    component: CoursAjouter,
  },
  {
    path: "/ue",
    component: UE,
  },
  {
    path: "/ue/ajouter",
    component: UEAjouter,
  },
  {
    path: "/ue/modifier/:id",
    component: UEModifier,
    name: "modifier",
  },
{
    path: "/cours/:id/modifier",
    component: CoursModifier,
    props: true,
  },
];
