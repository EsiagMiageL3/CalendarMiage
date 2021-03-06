package modele;

import java.awt.RenderingHints;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Jour implements Serializable {
	private String dateJour, nomJour, numJour, nomMois;
	private int numSemaine;
	
	private Hashtable<String, Seance> lst_Seances = new Hashtable<String, Seance>(); /* Cleee = module de la seeeance */
	
	public Jour(String date, int numSemaine, String Nom, String Mois){
		this.dateJour = date;
		this.numSemaine = numSemaine;
		this.numJour = this.extractNumJour(date);
		this.nomJour = this.toTitleCase(Nom);
		this.nomMois = this.toTitleCase(Mois);
	}
	
	public String extractNumJour(String date){
		return date.substring(0, 2);
	}
	
	public Hashtable<String, Seance> getListeSeances(){
		return this.lst_Seances;
	}
	public String getNumJour(){
		return this.numJour;
	}
	
	public String getNomMois(){
		return this.nomMois;
	}
	
	public String getDate(){
		return this.dateJour;
	}
	
	public int getSemaine(){
		return this.numSemaine;
	}
	
	public String getNomJour(){
		return this.nomJour;
	}

	public String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder();
	    boolean nextTitleCase = true;

	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}


}
