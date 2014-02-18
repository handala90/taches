/**
 * Classe Main du serveur
 * Instanciation d'un serveur pour le mettre ensuite en service
 */

package serveur;

class ServeurMain {
	
	public static void main(String[] args) {
		
		Serveur serveur = new Serveur();
		serveur.miseEnService();
		
	}

}
