package modele;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;


import javax.swing.JOptionPane;

import exception.RechercheSeanceException;
import modele.Module;


public class Seance extends Module implements Serializable{

	public int nb_seances;
	public int rang_seances = 20;
	public ArrayList<Module> lst_Module;
	ArrayList<Module> ListModuleAjout= new ArrayList<Module>();

	public int genererRangSeance() {
		return this.rang_seances++;
	}
	public Seance (){
		super();
	}
	

	
	public void AjoutSeanceNewModule(String nom_m, String abreviation, Color couleur, int nb_seances){
        this.ListModuleAjout.add( new Module(nom_m, abreviation, couleur, nb_seances));
        //new Module(nom_m, abreviation, couleur, nb_seances,genererRangSeance()));
    }

	public void AjoutSeance(String nom_m, String abreviation, Color couleur, int nb_seances){
        this.ListModuleAjout.add( new Module(nom_m, abreviation, couleur, nb_seances));
        //new Module(nom_m, abreviation, couleur, nb_seances,genererRangSeance()));
    }
	
	public Seance (String nom_m, String abrev, Color couleur,int m){
					super(nom_m, abrev, couleur, m);	
					this.nb_seances = m;

	}
/*
	public void VerificationContrainte(int n,int m) throws RechercheSeanceException {
		if (n.getNb_seances() > m.getRang_seances()) throw new RechercheSeanceException("non");
		
	}
		
		*/
		



	//Constructeur 

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
