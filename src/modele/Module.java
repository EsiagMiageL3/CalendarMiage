package modele;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

import modele.Seance;

public class Module implements Serializable{

	public String nom_m, abreviation, couleur;
	public Color couleurModule;
	public int nb_seances;
	public int quotasSeances;
	public Hashtable<String, Seance> lst_Seance = new Hashtable<String, Seance>(); /* Clee = module de la seeance */ 
	
	
	public Module (){
		this.nom_m = "Module par defaut";
		this.abreviation = "MD";
		this.couleur = "WHITE";
		this.nb_seances = 0;
	}
	


	public Module (String nom_m, String abreviation, Color couleur, int nbSeances){
		this.nom_m = nom_m;
		this.abreviation = abreviation;
		this.couleurModule = couleur;
		this.quotasSeances = nbSeances;

	}
	
	public int getNbSeances(){
		return this.lst_Seance.size();
	}
	/*
	public Module (String nom_m, String abreviation, Color couleur, int nb_seances, int rang_seances ){
		this.nom_m = nom_m;
		this.couleurModule = couleur;
		this.abreviation = abreviation;
		this.nb_seances = nb_seances;
		this.rang_seances = rang_seances;
	}
*/	
	
	
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
	public Color getCouleur() {
		return this.couleurModule;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int getQuotas() {
		return quotasSeances;
	}
	public void setNb_seances(int nb_seances) {
		this.nb_seances = nb_seances;
	}
	
	public Hashtable<String, Seance> getSeances() {
		return this.lst_Seance;
	}
	
}

