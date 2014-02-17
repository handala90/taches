package taches;

public class Taches implements Comparable<Taches> {
	
	private String libelle, auteur, statut, affecte;

	public Taches(String libelle, String auteur) {
		
		this.libelle = libelle;
		this.auteur = auteur;
		statut = "Libre";
		affecte = null;
		
	}

	public String libelle() {
		
		return libelle;
		
	}

	public String auteur() {
		
		return auteur;
		
	}

	public String statut() {
		return statut;
	}

	public String affecte() {
		
		return affecte;
		
	}

	public void affecter(String nom) {
		
		statut 	= "Affectée";
		affecte = nom;
		
	}

	public void finir() {
		
		statut = "Réalisée";
		affecte = null;
		
	}

	public int compareTo(Taches t) {
		
		return libelle.compareTo(t.libelle());
		
	}

	public String toString() {
		
		String reponse = "Tâche : " + libelle + "\n";
		reponse += "\tAuteur : " + auteur + "\n";
		if(statut.equals("Affectée"))
			reponse += "\tAttribuée à : " + affecte;
		else if(statut.equals("Réalisée"))
			reponse += "\tTâche déjà réalisée.";
		else
			reponse += "\tTâche libre.";

		return reponse;
	}
}
