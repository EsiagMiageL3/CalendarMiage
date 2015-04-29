package modele;

import java.io.Serializable;

import exception.RechercheSeanceException;
import modele.Module;

public class Seance extends Module implements Serializable{
	public int rang_seances;
	
	public Seance (String nom_m, String abreviation, String couleur,int nb_seances,int nb_heures){
		super(nom_m, abreviation, couleur, nb_seances, nb_heures);	
	}

	
	public Seance (String nom_m, String abreviation, String couleur,int nb_seances,int nb_heures, int n){
					super(nom_m, abreviation, couleur, nb_seances, nb_heures);	
					this.rang_seances = n;
	}
	
	public void AjoutSeance(String nom_module, int n) throws RechercheSeanceException {
		if super.nom_m.equals(nom_module);
		if (n > m.getNb_Seance()) throw new RechercheSeanceException();
			else{
				this.nb_heures = nb_seances;
				System.out.println("Seance crée");
				
				}
	}
	
	//Constructeur 
	public int getrang_seances() {
		return rang_seances;
	}
	public void setrang_seances(int n) {
		this.rang_seances = n;
	}

}
