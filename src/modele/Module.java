
package src.modele;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

public class Module implements Serializable{

	public String nom_m, abreviation, couleur;
	public Color couleurModule;
	public int nb_seances, rang_seances;
	public ArrayList<Seance> lst_Seance;
	
	public Module (){
		this.nom_m = "Module par d�faut";
		this.abreviation = "MD";
		this.couleur = "WHITE";
		this.nb_seances = 0;
		this.rang_seances = 0;
	}
	
<<<<<<< HEAD
	public Module (String nom_m,Color couleur){
		this.nom_m = nom_m;
		this.couleurModule = couleur;
=======
	public Module (String nom_m, String abreviation, Color couleur, int nbSeances){
		this.nom_m = nom_m;
		this.abreviation = abreviation;
		this.couleurModule = couleur;
		this.nb_seances = nbSeances;
>>>>>>> origin/master
	}
	
	
	public Module (String nom_m, String abreviation, Color couleur,int nb_seances, int rang_seances ){
		this.nom_m = nom_m;
		this.couleurModule = couleur;
		this.abreviation = abreviation;
		this.nb_seances = nb_seances;
		this.rang_seances = rang_seances;
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

	public int getRang_seances() {
		return rang_seances;
	}

	public void setRang_seances(int rang_seances) {
		this.rang_seances = rang_seances;
	}
	
}

