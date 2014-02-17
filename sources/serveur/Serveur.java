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

	public Serveur() {
		
		taches = new ArrayList<Taches>();

		try {
			
			socket = new ServerSocket(9921);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		
	}

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

	private void realiseService(Socket unClient) {
		
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
					
					envoi.println( lister() );
					
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
						((terminer(reception))
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

	private String lister()	{
		
		String reponse = "";
		
		for(int i=0; i<taches.size(); i++) {
			reponse += i + ": \n";
			reponse += taches.get(i).toString() + "\n";
			reponse += "\n====================\n\n";
		}
		
		if(reponse.length() > 0) {
			reponse = "\n" + reponse.substring(0, reponse.length() - 22);
		} else {
			reponse = "Aucune tâche pour le moment.\n";
		} 
		
		reponse += "STOP";
		return reponse;
		
	}

	private boolean ajouter(BufferedReader reception)
	{
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

	private boolean terminer(BufferedReader reception) {
		
		int id;

		try {
			
			id = Integer.parseInt(reception.readLine());
			
		} catch( Exception e ) {
			
			return false;
			
		}

		try {
			
			taches.get(id).finir();
			return true;
			
		} catch( Exception e ) {
			
			return false;
			
		}
	}

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
