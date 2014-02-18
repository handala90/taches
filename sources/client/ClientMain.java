package client;

import java.util.Scanner;

class ClientMain {
	
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
