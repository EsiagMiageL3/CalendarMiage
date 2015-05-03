package src.test;


import src.controleur.controleurPlanning;
import src.modele.modelePlanning;
import src.vue.*;

import java.awt.Color;


import src.exception.RechercheSeanceException;
import src.modele.Seance;

public class MainTest {
	
	public static void main(String[] args) {
		
		modelePlanning modele = new modelePlanning(); 
		controleurPlanning controleur = new controleurPlanning(modele);
		//vueAcceuil acc = new vueAcceuil(controleur);
		//vueAcceuil2 acc2 = new vueAcceuil2(controleur);

		vueAcceuil2 acc2 = new vueAcceuil2(controleur);
		//hahahahahahaha//
	}


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

