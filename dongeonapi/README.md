# Projet IHM ACDA 3.1 Initiaux


## Contexte

Il s’agit d’un prototype de jeu simpliste inspiré de grands classiques comme Zork.
Un joueur se déplace dans des pièces, reliées entre elles par des passages. Les pièces peuvent
contenir des objets que le joueur peut transporter et déposer. Le joueur peut agir sur les passages
et sur lui-même avec des objets (le mécanisme d’action reste très simpliste).
On peut imaginer qu’il s’agit d’un vieux jeu en mode textuel pour lequel on dispose d’une API.

## Organisation du code
Il y a 3 paquets.
- un paquet avec l'API proprement dite : package fr.iutfbleau.projetIHM2020FI2.API
- un paquet avec une version non persistante du modèle : package fr.iutfbleau.projetIHM2020FI2.MNP
- un paquet pour mettre des tests (pour l'instant assez partiel et sans JUnit qui est plutôt une illustration de l'utilisation de l'API qu'un véritable test) : package fr.iutfbleau.projetIHM2020FI2.test

## Détails concernant l'API

L'API correspond au paquet suivant : package fr.iutfbleau.projetIHM2020FI2.API

Il y a tout d'abord des types énumérés :
- Direction.java
- EtatPassage.java
- TypeTruc.java

Les interfaces centrales de l'API sont :
- Joueur.java
- Piece.java
- Passage.java
et dans une moindre mesure
- Truc.java

Comme le jeu est initialement basé sur un interface purement textuelle, la grande majorité des interfaces de l'API vont étendre l'interface suivante qui offre une méthode permettant de récupérer une description textuelle.
- Descriptible.java

Il y a une autre interface qui sera étendue par les interfaces sur lesquelles on peut agir (pour l'instant seulement Passage et Joueur).
- Activable.java

On a ensuite une interface pour gérer les inventaires d'objets (les trucs) étendues par Joueur et Piece:
- ContientTrucs.java

Comme il faut un mécanisme de création d'instance des interface centrales dont on ne connaît pas la réalisation concrète, il est nécessaire d'avoir des usines abstraites (abstract Factory dans le bestiaire des patrons de conception qu'on reverra en ACDA 3.3) dont la réalisation concrète se chargera d'appeller les constructeurs de classes choisies pour mettre en oeuvre le modèle.
Il y a deux usines abstraites :
- PassagePieceFactory.java
- TrucFactory.java

## Détails concernant le modèle non persistant.

Le modèle non persistant correspond au paquet suivant : package fr.iutfbleau.projetIHM2020FI2.MNP

On y retrouve une implémentation de toutes les interfaces de l'API.
Les noms sont ceux des interfaces correspondantes avec le suffixe NP.




