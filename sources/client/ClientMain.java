/**
 * Classe Client Main
 * Permettra de recevoir et d'envoyer des messages au client
 * Traitements faites dans la classe Client
 */
package client;

import java.util.Scanner;

class ClientMain {
	
	/**
	 * Fonction main de la classe Client
	 * @param args
	 * 
	 * On instancie un nouveau client
	 * Tant que la réponse du client est vrai (Il sera faux que si celui-ci souhaite quitter le programme)
	 * Affichage d'un message et d'une attente de réponse tant que la réponse n'est pas 1.Lister/2.Ajouter/3.Affecter/4.Terminer/5.Supprimer/6.Quitter
	 * 
	 * Si la réponse = 6 <-> Quitter
	 * 		=> On passe la réponse du client à faux et on quitte le programme
	 * 
	 * Si la réponse = 1 <-> Lister
	 * 		=>  Affichage d'un message et d'une attente de réponse tant que la réponse n'est pas 1.Toutes les tâches/2.Libres/3.Affectées/4.Effectuees
	 *	
	 * => On a donc la demande du client en fonction de ces choix que l'on envoie 
	 *    à la méthode "envoi" du client pour traiter sa demande avant de l'envoyer au serveur
	 * 
	 */
	public static void main(String[] args) {
		
		Client client = new Client();
		Scanner sc = new Scanner(System.in);
		boolean reponseClient = true;
		String demandeClient = "";
		while(reponseClient) {
			
			do{
				System.out.println("\nQue souhaitez vous faire ?");
				System.out.println("1 - Lister les tâches ");
				System.out.println("2 - Ajouter une tâche");
				System.out.println("3 - Affecter une tâche");
				System.out.println("4 - Mettre le statut réalisée à une tâche");
				System.out.println("5 - Supprimer une tâche");
				System.out.println("6 - Quitter\n");
				
				demandeClient = sc.nextLine();
				
			}while(!demandeClient.equals("1") && !demandeClient.equals("2") && !demandeClient.equals("3") 
					&& !demandeClient.equals("4") && !demandeClient.equals("5") && !demandeClient.equals("6"));
			
			
			if(demandeClient.equals("6")) {	

				demandeClient = "";
				reponseClient = false;
				client.fermer();				
				
			} else {
				if(demandeClient.equals("1")){

					demandeClient = "";
					
					do{
						System.out.println("\nSouhaitez vous lister : ");
						System.out.println("1 - toutes les tâches");
						System.out.println("2 - les tâches libres"); 
						System.out.println("3 - les tâches affectées");
						System.out.println("4 - les tâches effectuées");
					
						demandeClient = sc.nextLine();
						
					}while(!demandeClient.equals("1") && !demandeClient.equals("2") && 
							!demandeClient.equals("3") && !demandeClient.equals("4"));
					
					if(demandeClient.equals("1"))
						demandeClient = "Lister";
					else if(demandeClient.equals("2"))
						demandeClient = "ListerLibres";
					else if(demandeClient.equals("3"))
						demandeClient = "ListerAffectees";
					else if(demandeClient.equals("4"))
						demandeClient = "ListerEffectuees";
				}else if(demandeClient.equals("2"))
					demandeClient = "Ajouter";
				else if(demandeClient.equals("3"))
					demandeClient = "Affecter";
				else if(demandeClient.equals("4"))
					demandeClient = "Terminer";
				else if(demandeClient.equals("5"))
					demandeClient = "Supprimer"; 
				
				String resultat = client.envoyer(demandeClient);
				System.out.print(resultat);
			}
			 
		}

		System.out.println("Fini ! Au revoir");
		
	}

}
