package src.modele;

import java.awt.Color;
import java.io.Serializable;

import src.exception.RechercheSeanceException;
import src.modele.Module;

@SuppressWarnings("serial")
public class Seance extends Module implements Serializable{
	public int rang_seances,nb_seances;
	
	public Seance (int n,int m){
		super();
		this.rang_seances = n;
		this.nb_seances = m;
	}

	
	public Seance (String nom_m, String abreviation, Color couleur,int n,int m){
					//super(nom_m, couleur);	
					this.rang_seances = n;
					this.nb_seances = m;

	public int nb_seances,rang_seances;
	
	public Seance (int n,int m){
		super();
		this.nb_seances = n;
		this.rang_seances = m;	
	}

	
	public Seance (String nom_m,Color couleur,int n,int m){
					super(nom_m,couleur);	
					this.nb_seances = n;
					this.rang_seances = m;

	}

	
	

	public void AjoutSeance(String nom_module, int n) throws RechercheSeanceException {
		if (super.nom_m.equals(nom_module));
		/*
		if (n > m.getNb_Seance()) throw new RechercheSeanceException();
=======
	public void AjoutSeance(int n,int m) throws RechercheSeanceException {
		if (n>m) throw new RechercheSeanceException();
>>>>>>> origin/master
			else{
				this.nb_seances = this.rang_seances;
				System.out.println("Seance crée");
				
				}
				*/
	}

	
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
