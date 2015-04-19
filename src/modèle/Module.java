package modèle;

import java.io.*;

public class Module implements Serializable{

	public String nom_m, abreviation, couleur;
	public int nb_seances, nb_heures;
	
	public Module (String nom_m, String abreviation, String couleur, int nb_seances, int nb_heures){
		this.nom_m = nom_m;
		this.abreviation = abreviation;
		this.couleur = couleur;
		this.nb_seances = nb_seances;
		this.nb_heures = nb_heures;
	}
	 
	
	
	
	
	
	
	
	public String getNom() {
		return nom_m;
	}
	public void setNom(String nom_m) {
		this.nom_m = nom_m;
	}
	public String getAbreviation() {
		return abreviation;
	}
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int getNb_seances() {
		return nb_seances;
	}
	public void setNb_seances(int nb_seances) {
		this.nb_seances = nb_seances;
	}
	public int getNb_heures() {
		return nb_heures;
	}
	public void setNb_heures(int nb_heures) {
		this.nb_heures = nb_heures;
	}
}

