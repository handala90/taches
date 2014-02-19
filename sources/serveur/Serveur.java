/**
 * Classe Serveur
 * Permet de créer l'instanciation du serveur en créant une socket qui sera réservé à un cliant
 * Permet également de mettre en serveur le serveur
 * et de traiter les demandes que le serveur reçoit
 */
package serveur;

import java.util.Collections;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import taches.Taches;

class Serveur {
	
	private ServerSocket socket;
	private ArrayList<Taches> taches;

	/**
	 * Construction de la classe serveur
	 * Création d'une ArrayListe pour les tâches
	 * Création d'une socket sur le port 9921
	 */
	public Serveur() {
		
		taches = new ArrayList<Taches>();

		try {
			socket = new ServerSocket(9921);			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	/**
	 * Méthode miseEnService
	 * Cette méthode "ouvre" la socket pour accepter un client.
	 * Si celui-ci est accepté par un client,
	 * il pourra commencé les opérations que le client souhaite faire => realiseService
	 */
	public void miseEnService() {
		
		Socket unClient = null;

		while (true) {
			
			try {
				unClient = socket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			realiseService(unClient);
		}
	}

	/**
	 * Méthode realiseService
	 * @param Socket Client
	 * A partir du moment où il y a un client
	 * Nous allons créer deux variables : 
	 *  - envoie : permettra d'envoyer les résultats des demandes du client
	 *  - reception : permettra de lire les ordres du client
	 *  
	 * Tant que l'on ne reçoit pas l'ordre d'arrêter par "STOP" on lit chaque ligne envoyée
	 * Si le client envoie :
	 * 		- "Lister"			 : On liste toutes les tâches
	 * 		- "ListerLibres"	 : On liste toutes les tâches libres
	 * 		- "ListerAffectees"	 : On liste toutes les tâches affectées
	 * 		- "ListerEffectuees" : On liste toutes les tâches Effectuées/Réalisées
	 * 		- "Ajouter"			 : On appelle la fonction "Ajouter", pour ajouter une tâche et on retourne 
	 * 							   un message en fonction de la réussite ou de l'échec de la méthode
	 * 		- "Affecter"		 : On appelle la fonction "Affecter" pour affecter une tâche à une personne 
	 * 							   et on retourne un message en fonction de la réussite ou de l'échec de la méthode
	 * 		- "Terminer"		 : On appelle la fonction "Terminer" pour rendre une tâche réalisée/terminée
	 * 							   et on retourne un message en fonction de la réussite ou de l'échec de la méthode
	 * 		- "Supprimer"		 : On appelle la fonction "Supprimer" pour supprimer une tâche 
	 * 							   et on retourne un message en fonction de la réussite ou de l'échec de la méthode
	 * 
	 */
	public void realiseService(Socket unClient) {
		
		PrintWriter envoi = null;
		BufferedReader reception = null;

		try {
			envoi = new PrintWriter(unClient.getOutputStream(), true);
			reception = new BufferedReader(
                    new InputStreamReader(unClient.getInputStream()));

			String demande = "";

			while(!demande.equals("STOP")) {
				
				demande = reception.readLine();
				
				if(demande.equals("Lister")) {
					envoi.println(lister());
				} else if(demande.equals("ListerLibres")) {
					envoi.println(listerLibres());
				} else if(demande.equals("ListerAffectees")) {
					envoi.println(listerAffectees());
				} else if(demande.equals("ListerEffectuees")) {
					envoi.println(listerEffectuees());
				} else if(demande.equals("Ajouter")) {
					envoi.println( 	
						((ajouter(reception))
						?"L'ajout de la tâche s'est bien déroulée.\nSTOP"
						:"Une erreur s'est produite lors de l'ajout d'une tâche.\nSTOP")
					);
				} else if(demande.equals("Affecter")) {
					envoi.println( 	
						((affecter(reception))
						?"L'affectation de la tâche s'est bien faite.\nSTOP"
						:"Une erreur s'est produite lors de l'affectation de la tâche.\nSTOP")
					);
				} else if(demande.equals("Terminer")) {
					envoi.println( 	
						((realiser(reception))
						?"La Modification de la tâche s'est bien faite.\nLa tâche est désormais terminée.\nSTOP"
						:"Une erreur s'est produite dans la modification de la tâche.\nSTOP")
					);
				} else if(demande.equals("Supprimer")) {
					envoi.println( 	
						((supprimer(reception))
						?"La suppresion de la tâche a bien été faite.\nSTOP"
						:"Une erreur s'est produite lors de la suppression de la tâche.\nSTOP")
					);
				}
			}			 
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Méthode String Lister
	 * @return la liste de toutes les tâches
	 */
	private String lister()	{
		
		String reponse = "";
		
		for(int i=0; i<taches.size(); i++) {
			reponse += "ID : "+ i + " \n";
			reponse += taches.get(i).toString() + "\n";
			reponse += "\n====================\n\n";
		}
		
		if(reponse.length() > 0) {
			reponse = "\n" + reponse.substring(0, reponse.length() - 22);
		} else {
			reponse = "Aucune tâche disponible.\n";
		} 
		
		reponse += "STOP";
		return reponse;
		
	}
	
	/**
	 * Méthode String ListerLibres
	 * On regarde toutes les taches et on récupère que celles où leur statut est "Libres"
	 * @return la liste des tâches dont le statut est "Libres"
	 */
	private String listerLibres() { 
		
		String reponse = "";
		
		for(int i=0; i<taches.size(); i++) {
			if(taches.get(i).statut() == "Libre"){			
				reponse += "ID : "+ i + " \n";
				reponse += taches.get(i).toString() + "\n";
				reponse += "\n====================\n\n";
			}
		}
		
		if(reponse.length() > 0) {
			reponse = "\n" + reponse.substring(0, reponse.length() - 22);
		} else {
			reponse = "Pas encore de tâche.\n";
		} 
		
		reponse += "STOP";
		return reponse;
	}
	
	/**
	 * Méthode String ListerAffectees
	 * On regarde toutes les taches et on récupère que celles où leur statut est "Affectees"
	 * @return la liste des tâches dont le statut est "Affectees"
	 */
	private String listerAffectees() { 
		
		String reponse = "";
		
		for(int i=0; i<taches.size(); i++) {
			if(taches.get(i).statut() == "Affectée"){			
				reponse += "ID : "+ i + " \n";
				reponse += taches.get(i).toString() + "\n";
				reponse += "\n====================\n\n";
			}
		}
		
		if(reponse.length() > 0) {
			reponse = "\n" + reponse.substring(0, reponse.length() - 22);
		} else {
			reponse = "Pas encore de tâche affectée.\n";
		} 
		
		reponse += "STOP";
		return reponse;
	}
	
	/**
	 * Méthode String ListerEffectuees
	 * On regarde toutes les taches et on récupère que celles où leur statut est "Realisee"
	 * @return la liste des tâches dont le statut est "Realisee"
	 */
	private String listerEffectuees() { 
		String reponse = "";
		
		for(int i=0; i<taches.size(); i++) {
			if(taches.get(i).statut() == "Réalisée"){			
				reponse += "ID : "+ i + " \n";
				reponse += taches.get(i).toString() + "\n";
				reponse += "\n====================\n\n";
			}
		}
		
		if(reponse.length() > 0) {
			reponse = "\n" + reponse.substring(0, reponse.length() - 22);
		} else {
			reponse = "Pas encore de tâche réalisée.\n";
		} 
		
		reponse += "STOP";
		return reponse;
	}
	
	/**
	 * Méthode booléene Ajouter
	 * @param BufferReader reception
	 * Le première ligne que l'on recevra correspondra à l'auteur
	 * Le seconde ligne correspondra au libellé de la tâche
	 * Suite à ça on ajoute la tâche à l'arrayList tache
	 * @return vrai si tout c'est bien passé, faux sinon
	 */
	private boolean ajouter(BufferedReader reception) {
		String libelle, auteur;

		try {
			auteur = reception.readLine();
			libelle = reception.readLine();
		} catch( Exception e ) {
			return false;
		}

		Taches t = new Taches(libelle, auteur);

		try {
			taches.add(t);
			Collections.sort(taches);
			return true;
		} catch( Exception e ) {
			return false;
		}
		
	}
	
	/**
	 * Méthode booléene Terminer
	 * @param BufferReader reception
	 * On recoit un message qui correspond à l'identifiant d'une tache
	 * On va allé à l'index, étant l'identifiant entré, dans l'arrayList
	 * des tâches pour appelé la méthode "affecter" de la classe Taches.
	 * Grâce à cela, la tâche sera passé en "Affectée"
	 * @return vrai si tout c'est bien passé, faux sinon
	 */
	private boolean affecter(BufferedReader reception) {
		int id;
		String affecte;

		try {
			id = Integer.parseInt(reception.readLine());
			affecte = reception.readLine();
		} catch( Exception e ) {
			return false;
		}

		try {
			taches.get(id).affecter(affecte);
			return true;
		} catch( Exception e ) {
			return false;
		}
	}

	/**
	 * Méthode booléene Terminer
	 * @param BufferReader reception
	 * On recoit un message qui correspond à l'identifiant d'une tache
	 * On va allé à l'index, étant l'identifiant entré, dans l'arrayList
	 * des tâches pour appelé la méthode "réaliser" de la classe Taches.
	 * Grâce à cela, la tâche sera passé en "Réalisée"
	 * @return vrai si tout c'est bien passé, faux sinon
	 */
	private boolean realiser(BufferedReader reception) {
		int id;

		try {
			id = Integer.parseInt(reception.readLine());
		} catch( Exception e ) {
			return false;
		}

		try {
			taches.get(id).realiser();
			return true;
		} catch( Exception e ) {
			return false;
		}
	}

	/**
	 * Méthode booléene Supprimer
	 * @param BufferReader reception
	 * On recoit un message qui correspond à l'identifiant d'une tache
	 * On supprime directement, à partir de la méthode de arrayList, la tâche 
	 * correspondant à l'identifiant envoyé
	 * @return vrai si tout c'est bien passé, faux sinon
	 */
	private boolean supprimer(BufferedReader reception)	{
		int id;
		
		try {
			id = Integer.parseInt(reception.readLine());
		} catch( Exception e ) {
			return false;
		}
		
		try {
			taches.remove(id);
			return true;
		} catch( Exception e ) {
			return false;
		}
	}
	
}
