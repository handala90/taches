/**
 * Classe Client
 * Connexion à la socket (localhost, 9921)
 * Traitement des données faites de Client Main (entre autre)
 */
package client;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class Client {
	
	String nom;

	Socket clientSocket = null;
	PrintWriter envoi = null;
	BufferedReader reception = null;

	/**
	 * Constructeur du Client
	 * Avec la création d'une socket se connectant au serveur lancé juste avant : localhost, 9921 
	 * Au lancement de la connexion, on demande le nom de l'utilisateur
	 */
	public Client()	{
		
		nom = nomUtilisateur();

		try {
			clientSocket = new Socket("localhost", 9921);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	    try {
			envoi = new PrintWriter(clientSocket.getOutputStream(), true);
			reception = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	    
	}

	/**
	 * Méthode String nomUtilisateur
	 * @return le nom d'utilisateur qui se connecte
	 */
	public String nomUtilisateur()	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Qui êtes-vous ?");
		return sc.nextLine();
	}

	/**
	 * Méthode String envoyer
	 * @param message
	 * On envoie au serveur le message reçu en parametre
	 * 
	 * Si le message est Ajouter  
	 * 		=> Le serveur sera déjà dans sa méthode "Ajouter", il attend une réponse du client.
	 * 		   C'est le nom de la tâche que l'on va envoyer après l'affichage
	 * 
	 * Si le message est Affecter
	 * 		=> Le serveur sera déjà dans sa méthode "Affecter", il attend une réponse du client.
	 * 		   C'est l'id de la tâche et nom de la tâche que l'on va envoyer après l'affichage
	 *
	 * Si le message est Terminer ou Supprimer
	 * 		=> Le serveur sera déjà dans sa méthode "Terminer", il attend une réponse du client.
	 * 		   C'est l'id de la tâche que l'on va envoyer après l'affichage
	 * 
	 * @return le message du serveur
	 */
	public String envoyer(String message) {
	    envoi.println(message);
		Scanner sc = new Scanner(System.in);
		if(message.equals("Ajouter")) {

			envoi.println(nom);
			System.out.println(nom);
			System.out.println("Quel est le nom de la tache ?");
			envoi.println(sc.nextLine());

		} else if(message.equals("Affecter")) {

			System.out.println("Quel est l'id de la tâche ?");
			envoi.println( sc.nextLine() );
			System.out.println("A qui souhaitez vous l'affecter ?");
			envoi.println( sc.nextLine() );

		} else if(message.equals("Terminer") || message.equals("Supprimer")) {

			System.out.println("Quel est l'id de la tâche ?");
			envoi.println( sc.nextLine() );

		}
		
		String reponse = "";
		String line;

	    try {
	    	while(!(line = reception.readLine()).equals("STOP")) 
	    		reponse += line + "\n";
	    	
			return reponse;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	    
	    return null;
	}

	/**
	 * Méthode fermer
	 * Permet de fermer le client et de se déconnecter du serveur
	 */
	public void fermer() {
		envoi.println("STOP");
	}
}