
package src.modele;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Module implements Serializable{

	public String nom_m, abreviation, couleur;
	public Color couleurModule;
	public int nb_seances, nb_heures;
	public ArrayList<Seance> lst_Seance;
	
	public Module (){
		this.nom_m = "Module par défaut";
		this.abreviation = "MD";
		this.couleur = "WHITE";
		this.nb_seances = 0;
		this.nb_heures = 0;
	}
	
	public Module (String nom_m, String abreviation, Color couleur, int nbSeances){
		this.nom_m = nom_m;
		this.abreviation = abreviation;
		this.couleurModule = couleur;
		this.nb_seances = nbSeances;
	}
	 /*
 	public void addSeance(Seance nouvelleSeance) {
		this.lst_Seance.add(nouvelleSeance);
		JOptionPane.showMessageDialog(null, "La nouvelle " + nouvelleSeance.nom + " a été ajouté avec succès au module " + this.nom_m);
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

