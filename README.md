# PTUT FIE3

## Gestion des maquettes et syllabus de la formation

### Besoins fonctionnels

L’application doit permettre la saisie et l’affichage des maquettes pédagogiques des différentes années du cycle de formation d’ingénieurs ISIS.

### Utilisateurs cible

- Le directeur des études : Il établit les maquettes de la formation, définit les modalités d’évaluation et affecte les enseignants en charge des différentes unités d’enseignement. Il valide les renseignements pédagogiques transmis par les enseignants.
- Les enseignants et intervenants : ils renseignent le contenu pédagogique détaillé des enseignements, les prérequis, les objectifs pédagogiques, les compétences acquises à l’issue de ces enseignements.
- Les visiteurs du site : ils peuvent consulter le syllabus de la formation.

On pourra s’inspirer de [cet exemple](https://www.fib.upc.edu/en/studies/masters/master-data-science/curriculum) pour la présentation.

### Organisation du projet

- Nous utiliserons H2 en tant que système de gestion de base de données car il s'associe bien avec notre framework backend.
- Le backend de l'application est réalisé avec le framework [Spring-Boot](https://spring.io/projects/spring-boot) qui sera utilisé pour réaliser une api CRUD.
- Le frontend est réalisé avec [VueJs](https://vuejs.org/) 3.0 accédant aux données grâce à des requêtes Ajax appelées par la librairie axios (similaire à fetch mais en plus simple). Nous avons également utilisé le toolkit [Bootstrap](https://getbootstrap.com/) 5 pour nous permettre de créer une interface utilisateur facilement.

### Mise en place du projet

1.  Après avoir cloné le repo, accéder au dossier backend via un terminal de commande ([Cmder](https://cmder.net/) pour Windows par exemple).
2.  Exécuter : `./mvnw spring-boot:run` ou `mvn spring-boot:run`. On pourra utiliser l'extensions Spring Boot Dashboard dans VS Code pour simplifier le lancement du serveur.
3.  Dans un nouveau terminal, accéder au dossier frontend.
4.  Exécuter : `npm install` pour installer toutes les dépendances du projet. Puis, `npm run serve` pour lancer le serveur local.
5.  Nous pouvons à présent accéder à l'application front via http://localhost:8080. Le serveur back est lancé sur le port 8989.


Pour acceder à l'API : http://localhost:8989/swagger-ui/#/