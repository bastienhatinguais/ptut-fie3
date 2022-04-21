import Home from "@/views/Home.vue";
import CoursListe from "@/views/cours/Liste.vue";
import CoursAjouter from "@/views/cours/Ajouter.vue";
import UE from "@/views/UE.vue";
import UEAjouter from "@/views/ue/AjoutUE.vue";
import UEModifier from "@/views/ue/ModificationUE.vue";
import CoursModifier from "@/views/cours/Modifier.vue";
import Annees from "@/views/Annee.vue";
import ListeUEParSemestre from "@/views/ue/ListeUEParSemestre.vue";
import CoursAffichage from "@/views/cours/Affichage.vue";
import AjoutPersonnel from "./views/personnel/AjoutPersonnel";
import ListePersonnel from "./views/personnel/ListePersonnel.vue";
import ModifierPersonnel from "./views/personnel/ModificationPersonnel";
import PremiereConnexion from "./views/personnel/PremiereConnexion";

export default [
    { path: "/", component: Annees },

    {
        path: "/cours",
        component: CoursListe,
        name: "cours",
    },
    {
        path: "/cours/ajouter",
        component: CoursAjouter,
    },
    {
        path: "/cours/:id",
        component: CoursAffichage,
        props: true,
    },
    {
        path: "/ue",
        component: UE,
    },
    {
        path: "/ue/:statut/:id",
        component: ListeUEParSemestre,
        name: "listeParSemestre",
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
    {
        path: "/annee",
        component: Annees,
        name: "annee",
    },
    {
        path: "/personnel",
        component: ListePersonnel,
    },
    {
        path: "/personnel/ajouter",
        component: AjoutPersonnel,
    },

  {
    path: "/personnel/:id/modifier",
    component: ModifierPersonnel,
    props: true,
  },

  {
    path: "/premiere-connexion",
    component: PremiereConnexion,
  },
];

