package modele;
// je pense que tu tes tromper cela devais etre dans les controleur 
//et ta classe qui ce trouve dans les controleeur doit etre dans le modéle 
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import vue.vuePlanning;

public class modelePlanning implements Serializable{
	private vuePlanning nouveauPlanning; 
	private Formation formationPlanning;
    ObjectInputStream input;
    ObjectOutputStream output;
	
	public modelePlanning(){
		//this.nouveauPlanning = new vuePlanning();
	}
	
	public void addPlanning(vuePlanning planning){
		this.nouveauPlanning = planning;
	}
	
	public void addFormation(String nomFormation){
		this.formationPlanning = new Formation(nomFormation);
	}
	
	public void addModule(String key, Color couleur){
		this.formationPlanning.getModules().put(key, new Module( key, couleur));
	}
	
	public Formation getFormation(){
		return this.formationPlanning;
	}

}
