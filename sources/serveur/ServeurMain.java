/**
 * Classe serveur Main
 * Instanciation d'un serveur pour ensuite le mettre en service
 * Traitements faites dans la classe Serveur
 */

package serveur;

class ServeurMain {
	
	/**
	 * Fonction main de la classe Serveur Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		Serveur serveur = new Serveur();
		serveur.miseEnService();
		
	}

}
