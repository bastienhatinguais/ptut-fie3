import Home from "@/views/Home.vue";
import Api from "@/views/Api.vue";
import CoursListe from "@/views/cours/Liste.vue";
import CoursAjouter from "@/views/cours/Ajouter.vue";
import CoursModifier from "@/views/cours/Modifier.vue";
import Inscription from "./views/Inscription.vue";
import CoursAffichage from "@/views/cours/Affichage.vue";

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
        path: "/cours/:id/modifier",
        component: CoursModifier,
        props: true,
    },
    { path: "/cours/:id", component: CoursAffichage, props: true }
];