package modele;

import java.awt.Color;
import java.io.Serializable;

import modele.Formation;
import vue.vuePlanning;




import vue.vuePlanning;



public class modelePlanning implements Serializable{
	private vuePlanning nouveauPlanning; 
	private Formation formationPlanning;
	
	private static final long serialVersionUID = 1349L;
	
	public modelePlanning(){
		//this.nouveauPlanning = new vuePlanning();
	}
	
	public void addPlanning(vuePlanning planning){
		this.nouveauPlanning = planning;
	}
	
	public void addFormation(String nomFormation, String annee){
		this.formationPlanning = new Formation(nomFormation, annee);
	}
	
	public void addModule(String key, String abreviation, Color couleur, int nbSeances){
		this.formationPlanning.getModules().put(key, new Module( key, abreviation, couleur, nbSeances));
	}
	
	public void supprModule(String key){
		this.formationPlanning.getModules().remove( key );
	}
	
	public Formation getFormation(){
		return this.formationPlanning;
	}

}
