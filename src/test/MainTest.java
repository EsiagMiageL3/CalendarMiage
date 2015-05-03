package src.test;

import src.controleur.controleurPlanning;
import src.modele.modelePlanning;
import src.vue.*;

public class MainTest {
	
	public static void main(String[] args) {
		
		modelePlanning modele = new modelePlanning(); 
		controleurPlanning controleur = new controleurPlanning(modele);
		//vueAcceuil acc = new vueAcceuil(controleur);
		
		vueAcceuil2 acc2 = new vueAcceuil2(controleur);
		//hahahahahahaha//
	}

}
