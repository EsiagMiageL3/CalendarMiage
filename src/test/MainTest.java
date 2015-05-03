<<<<<<< HEAD
package test;
import controleur.controleurPlanning;
import modele.modelePlanning;
import vue.*;
import exception.RechercheSeanceException;
import modele.Seance;
=======
package src.test;


import src.controleur.controleurPlanning;
import src.modele.modelePlanning;
import src.vue.*;

import java.awt.Color;


import src.exception.RechercheSeanceException;
import src.modele.Seance;

>>>>>>> origin/master
public class MainTest {
	
	public static void main(String[] args) {
		
<<<<<<< HEAD
		//modelePlanning modele = new modelePlanning(); 
		//controleurPlanning controleur = new controleurPlanning(modele);
		//vueAcceuil acc = new vueAcceuil(controleur);
		//vueAcceuil2 acc2 = new vueAcceuil2(controleur);
	}
}
=======
		modelePlanning modele = new modelePlanning(); 
		controleurPlanning controleur = new controleurPlanning(modele);

		vueAcceuil2 acc2 = new vueAcceuil2(controleur);

	}


		/*
		Seance seance1 = new Seance(10,10);
		try {
						seance1.AjoutSeance("test",10);
						seance1.AjoutSeance("test",152);
						seance1.AjoutSeance("test",10);
			}
					catch (RechercheSeanceException e){}
				
			
*/
}

>>>>>>> origin/master
