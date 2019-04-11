# GestionCVs
Gérer à l'aide de la technologie JEE une liste de CVs. 

## L'objectif du mini projet est simple : gérer à l'aide de la technologie JEE une liste de CVs. Plus précisément,

Chaque personne est représentée par un ensemble d'informations : nom, prénom, adresse électronique, site WEB, date de naissance et mot de passe.
Chaque personne peut renseigner un curriculum vitae. Pour notre application, un CV est une liste d'activités. Chaque activité est décrite par
une année (obligatoire),
une nature (expérience professionnelle, formation, projets, autre) (obligatoire),
un titre (obligatoire) ,
un texte descriptif (facultatif),
une adresse WEB (facultatif),
Chaque personne peut créer de nouvelles personnes (cooptation).
La liste des personnes et les CV sont librement consultables. La modification implique une phase d'authentification.
Le logiciel doit être capable de gérer environ 100,000 personnes.

## Étape 1 : Couche métier (EJB/JPA/Junit)
Objectifs :

Réalisation d'une couche métier (EJB/JPA) accompagnée de ses tests unitaires.
Il faut prévoir les actions CRUD (create/read/update/delete) sur les personnes et les activités.
Il faut également prévoir les fonctions de recherche d'une personne sur une partie du nom, du prénom ou le titre d'une activité.
il est souhaitable de prévoir un EJB stateful pour représenter un utilisateur (authentifié ou pas).
il est souhaitable de prévoir un EJB stateless pour offrir les fonctions d'accès et de recherche.

## Étape 2 : Présentation (JSF/Primefaces)
Cette étape consiste à mettre en place une application WEB basée sur la technologie JSF/Primefaces qui respecte les contraintes suivantes :

Utilisation d'AJAX pour les phases de modification des CVs et des informations personnelles.
Utilisation de pages multiples pour le parcours de la liste des CVs.
