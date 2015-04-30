package test;

import java.awt.Color;

import controleur.controleurPlanning;
import modele.modelePlanning;
import vue.*;
import exception.RechercheSeanceException;
import modele.Seance;
public class MainTest {
	
	public static void main(String[] args) {
		
		modelePlanning modele = new modelePlanning(); 
		controleurPlanning controleur = new controleurPlanning(modele);
		//vueAcceuil acc = new vueAcceuil(controleur);
		//vueAcceuil2 acc2 = new vueAcceuil2(controleur);
		
		// Test Seance 
		Seance seance1 = new Seance("Mathématique",Color.CYAN,10,10);
		try {
						seance1.AjoutSeance(1,10);
						seance1.AjoutSeance(2,10);
						seance1.AjoutSeance(12,10);
			}
					catch (RechercheSeanceException e){}
				}
			
}

