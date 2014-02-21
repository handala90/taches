-----------------------------------------------------------------
		  			 Information Général
-----------------------------------------------------------------

Contibuteur : Mathieu Vandenbussche
Nom Projet  : Client/Serveur
Type        : Application Java


-----------------------------------------------------------------
						Description
-----------------------------------------------------------------


Application Java mettant en oeuvre une liste de tâches partagée 
qui permettra à une équipe de gérer des tâches communes :
- création d'une tâche : description, auteur. Le serveur 
attribuera un identifiant à chaque tâche pour la gestion interne.
Par défaut une tâche aura un statut fixé à libre.
- obtenir la liste des tâches (toutes, par statut, par affectation)
- affectation d'une tâche à une personne (statut affectée, 
login de la personne)
- changement du statut d'une tâche (libre, affectée, réalisée)
- suppression d'une tâche
Dans cette application on ne gérera pas les utilisateurs qui 
seront simplement identifiés par une chaîne de caractères
contenant leur login. La gestion de la persistance n'est pas
primoridale.

-----------------------------------------------------------------
						Installation							
-----------------------------------------------------------------


Pour pouvoir utiliser l'application :
- Extraire les dossier bin et src du .zip
- Importer les fichier du src dans un nouveau projet sur Eclipse

Il ne reste plus qu'à utiliser l'application

-----------------------------------------------------------------
						Utilisation							
-----------------------------------------------------------------

Il faut tout d'abord lancer ServeurMain.java pour ensuite 
lancer ClientMain.java

Le client se connecte à la socket que le serveur aura créée. 
Une fois ceci fait, le serveur attend une réponse, ou plutôt 
une tâche.
Le client aura donc le choix entre : Lister, Créer une tâche, 
affecter une tâche à quelqu'un, rendre une tâche réalisée, etc...

Pour Lister, il pourra lister toutes les tâches, les tâches 
libres, les tâches réalisées, les tâches affectées à quelqu'un.


-----------------------------------------------------------------
			Installation & Utilisation du script
-----------------------------------------------------------------


Type        : Script Bash
But			: Création de tâches, affectation de tâche, 
			  réalisation d'une tâche et lister

Le serveur devra être lancé !
Une fois ceci fait, le "script.sh" étant en exécutable, 
on pourra lancer le script.

Il permettra de créer des tâches, d'affecter des tâches 
à des personnes, de mettre un projet terminé et enfin de lister 
toutes les tâches pour voir le résultat du script.