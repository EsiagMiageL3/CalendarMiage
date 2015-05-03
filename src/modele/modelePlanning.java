package src.modele;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import src.vue.vuePlanning;

@SuppressWarnings("serial")
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
