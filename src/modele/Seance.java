package modele;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import exception.RechercheSeanceException;
import modele.Module;


public class Seance extends Module implements Serializable{

	public Module referenceModule;
	
	public String moduleSeance;
	public String dateSeance;
	public int heureDebut;
	public int dureeSeance;
	
	public int nb_seances;
	public int rang_seances;

	public int genererRangSeance() {
		return this.rang_seances++;
	}
	
	public Seance (){
		super();
	}

	public Seance( String module, int rang, int duree, Module refModule ){
		this.referenceModule = refModule;
		this.rang_seances = rang + 1;
		this.moduleSeance = module;
		this.dureeSeance = duree;
	}
	
	public void setDateSeance( String date ){
		this.dateSeance = date;
	}
	
	public void setHeureDebutSeance( int heure ){
		this.heureDebut = heure;
	}
	
	public int getDureeSeance(){
		return this.dureeSeance;
	}
	
	public void setDureeSeance( int nbHeure ){
		this.dureeSeance = nbHeure;
	}
	
	public void AjoutSeanceNewModule(String nom_m, String abreviation, Color couleur, int nb_seances){
      //  this.ListModuleAjout.add( new Module(nom_m, abreviation, couleur, nb_seances));
        //new Module(nom_m, abreviation, couleur, nb_seances,genererRangSeance()));
    }

	public void AjoutSeance(String nom_m, String abreviation, Color couleur, int nb_seances){
        //this.ListModuleAjout.add( new Module(nom_m, abreviation, couleur, nb_seances) );
    }
	
	public Seance (String nom_m, String abrev, Color couleur,int m){
					super(nom_m, abrev, couleur, m);	
					this.nb_seances = m;

	}

	public int getNb_seances() {
		return nb_seances;
	}
	
	public String getMod() {
		return this.moduleSeance;
	}
	
	public Module getRefModule() {
		return this.referenceModule;
	}

	public void setNb_seances(int nb_seances) {
		this.nb_seances = nb_seances;
	}

	public Color getCouleurModule(){
		return this.referenceModule.getCouleur();
	}

	public int getRangSeance() {
		return rang_seances;
	}


	public void setRang_seances(int rang_seances) {
		this.rang_seances = rang_seances;
	}

}
