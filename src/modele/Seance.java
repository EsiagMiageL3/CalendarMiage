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
	}

	
	
	public void AjoutSeance(String nom_module, int n) throws RechercheSeanceException {
		if (super.nom_m.equals(nom_module));
		/*
		if (n > m.getNb_Seance()) throw new RechercheSeanceException();
			else{
				this.nb_heures = nb_seances;
				System.out.println("Seance crée");
				
				}
				*/
	}
	
	//Constructeur 
	public int getrang_seances() {
		return rang_seances;
	}
	public void setrang_seances(int n) {
		this.rang_seances = n;
	}

}
