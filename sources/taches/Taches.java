/**
 * Classe des tâches
 * Getters et setters pour chaques tâches
 * Déterminer et d'assigner :
 * 		-> le libellé
 * 		-> l'auteur
 * 		-> le statut 
 *		-> l'affectation
 */

package taches;

public class Taches implements Comparable<Taches> {
	
	private String libelle, auteur, statut, affecter;

	/**
	 * Classe constructeur des tâches
	 * @param libelle
	 * @param auteur
	 * Statut libre par defaut
	 * affecte null par defaut
	 */
	public Taches(String libelle, String auteur) { 
		this.libelle = libelle;
		this.auteur = auteur;
		statut = "Libre";
		affecter = null; 
	}

	/**
	 * Getter du libelle
	 * @return String libelle
	 */
	public String libelle() {
		return libelle;
	}

	/**
	 * Getter Auteur
	 * @return String auteur
	 */
	public String auteur() {
		return auteur;
	}

	/**
	 * Getter statut
	 * @return String statut
	 */
	public String statut() {
		return statut;
	}

	/**
	 * Getter affecter
	 * @return String affecter
	 */
	public String affecter() {
		return affecter;
	}

	/**
	 * Setter affecte
	 * @param nom
	 * Statut => affectee
	 * affecter => nom passé en parametre
	 */
	public void affecter(String nom) {
		statut 	= "Affectée";
		affecter = nom;
	}

	/**
	 * Méthode realiser
	 * Permet de passer le statut d'une tâche à "réalisée"
	 * Statut => rélisée
	 * affecter => null
	 */
	public void realiser() {
		statut = "Réalisée";
		affecter = null;
	}

	/** 
	 * @return int
	 */
	public int compareTo(Taches t) { 
		return 0;
	}
	
	/**
	 * Méthode retournant les caractéristiques d'une tâche
	 * @return String
	 */
	public String toString() { 
		String reponse = "Libellé : " + libelle + "\n";
		reponse += "Auteur : " + auteur + "\n";
		if(statut.equals("Affectée"))
			reponse += "Attribuée à : " + affecter + ".";
		else if(statut.equals("Réalisée"))
			reponse += "Tâche réalisée.";
		else
			reponse += "Tâche libre.";

		return reponse;
	}
}