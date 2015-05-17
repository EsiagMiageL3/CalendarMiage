package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Formation implements Serializable {
	
	public String nom_f;
	//public String anneeFormation;
	public int duree;
	public HashMap<String, Module> liste_Modules;
	public HashMap<String, Semaine> lst_Semaines; /* Cle = Numero de la semaine */
	
	
	
	public Formation(String nom_f){
		this.nom_f = nom_f;
		this.liste_Modules = new HashMap<String, Module>();
		this.lst_Semaines =  new HashMap<String, Semaine>();
		
	}
	
	public Formation(String nom_f, int duree, ArrayList<Module> liste_modules){
		this.nom_f = nom_f;
		this.duree = duree;
		//this.liste_modules = liste_modules;
	}
	
	 	public String getNomFormation() {
			return nom_f;
		}
	 	
	 	public void addSemaine(String num, Semaine semaine) {
			this.lst_Semaines.put(num, semaine);
		} 	 	
	 	
	 	public Semaine getSemaine(int num) {
			return this.lst_Semaines.get( Integer.toString(num) );
		} 
	 	
	 	
	 	
	 	public void setNomFormation(String nom) {
			this.nom_f = nom;
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
		public HashMap<String, Module> getModules() {
			return this.liste_Modules;
		}
}
