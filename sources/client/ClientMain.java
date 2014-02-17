package client;

import java.util.Scanner;

class ClientMain {
	
	public static void main(String[] args) {
		
		Client client = new Client();
		Scanner sc = new Scanner(System.in);
		boolean reponseClient = true;

		while(reponseClient) {
			
			System.out.println("Que souhaitez vous faire ?");
			System.out.println("- Lister");
			System.out.println("- Ajouter");
			System.out.println("- Affecter");
			System.out.println("- Terminer");
			System.out.println("- Supprimer");
			System.out.println("- Quitter\n");

			String demandeClient = sc.nextLine();
			
			if(demandeClient.equals("Quitter")) {
				
				reponseClient = false;
				client.fermer();
				
			} else {
				
				if(!demandeClient.equals("Lister") && !demandeClient.equals("Ajouter") && !demandeClient.equals("Affecter") 
						&& !demandeClient.equals("Terminer") && !demandeClient.equals("Supprimer")) {
					
					System.out.println("Vous avez mal écris votre demande.");
					
				} else {
					
					String resultat = client.envoyer(demandeClient);
					System.out.print(resultat);
					
					
				}
	
				System.out.println("Souhaitez vous continuer ? 1. Oui ou 2. Non"); 
				String continuer = sc.nextLine();
				
				if(continuer.equals("2") || continuer.equals("Non") || continuer.equals("non")){
					
					reponseClient = false;
					client.fermer();
					
				}
				
			}
			
		}

		System.out.println("Fini ! Au revoir");
		
	}

}
