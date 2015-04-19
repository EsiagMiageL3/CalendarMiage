package test;

import exception.RechercheSeanceException;
import modele.Seance;

public class SeanceTest {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		Seance seance1 = new Seance("Mathématique","Math","vert",10,10);
		try {
			seance1.AjoutSeance(12,10);
			seance1.AjoutSeance(2,10);
			}
		catch (RechercheSeanceException e){}
	}
}
