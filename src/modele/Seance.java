package modele;

import exception.RechercheSeanceException;
public class Seance {
	protected String nom ;
	protected String abreviation;
	protected String couleur;
	protected int nbreSeance; 
	protected int rangSeance;
	
	public Seance(String nom, String abreviation, String couleur, int n, int m){
					this.nom = nom;
					this.abreviation = abreviation;
					this.couleur = couleur;
					this.nbreSeance = n;
					this.rangSeance = m; 
	}
	
	
	public void AjoutSeance( int n, int m) throws RechercheSeanceException {
			if (n > m) throw new RechercheSeanceException();
			else{
				this.nbreSeance = n;
				System.out.println("Seance crée");
				}
	}
	
	
	
}
