package test;


import controleur.controleurPlanning;
import modele.Formation;
import modele.modelePlanning;
import vue.*;

import java.awt.Color;

public class MainTest {
	
	public static void main(String[] args) {
		
		modelePlanning modele = new modelePlanning(); 
		
		controleurPlanning controleur = new controleurPlanning(modele );
		
		vueAcceuil acc2 = new vueAcceuil(controleur);
		
		
		
		
	}
}

