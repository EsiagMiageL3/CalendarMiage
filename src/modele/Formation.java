
package modele;

import java.util.ArrayList;

public class Formation {
	
	public String nom_f;
	public int duree;
	public ArrayList<Module> liste_modules;
	
	public Formation(String nom_f, int duree, ArrayList<Module> liste_modules){
		this.nom_f = nom_f;
		this.duree = duree;
		this.liste_modules = liste_modules;
	}
	
	 	public String getNom_f() {
			return nom_f;
		}
		public void setNom_f(String nom_f) {
			this.nom_f = nom_f;
		}
		public int getDuree() {
			return duree;
		}
		public void setDuree(int duree) {
			this.duree = duree;
		}
		public ArrayList<Module> getListe_modules() {
			return liste_modules;
		}
		public void setListe_modules(ArrayList<Module> liste_modules) {
			this.liste_modules = liste_modules;
		}
}
