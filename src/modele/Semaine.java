package modele;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Semaine {
	
	private String annee;
	private int numeroSemaine;
	private Hashtable<String, Jour> lst_JourSemaine; /* ClŽ = date du jour */
	
	
	public Semaine(String annee, int numero){
		this.annee = annee;
		this.numeroSemaine = numero;
		this.lst_JourSemaine = new Hashtable<String, Jour>();
		createJours( Integer.parseInt( annee ),numero);
	}
	
	public void addJour(String date, Jour j){
		this.lst_JourSemaine.put(date, j);
	}
	
	public Jour getJour(String dateJour){
		return  this.lst_JourSemaine.get(dateJour);
	}
	
	public Hashtable<String, Jour> getLstjours(){
		return  this.lst_JourSemaine;
	}
	
	public void createJours(int annee, int numSemaine){
		/* Instanciation du calendrier */
		Calendar c = Calendar.getInstance();

		/* Positionnement du calendrier ˆ l'annee passŽe en param�tre */
		c.set(Calendar.YEAR, annee);
		
		/* Positionnement du calendrier au numŽro de semaine passŽ en param�tre */
		c.set(Calendar.WEEK_OF_YEAR, numSemaine);

		/* Positionnement du calendrier au jour premier jour de la semaine */
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		/* DŽfinition du format de restitution de la date du jour */
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy");
		
		for (int i = 0; i < 7; i++) {
		   
		   DateFormat formatter = new SimpleDateFormat("EEEE"); 
		   String nom = formatter.format( c.getTime() );
		   
		   DateFormat formatterMois = new SimpleDateFormat("MMMM"); 
		   String mois = formatterMois.format( c.getTime() );
		   
		   /* CrŽation du jour */
		   Jour jourSemaine = new Jour( df.format(c.getTime()), numSemaine, nom, mois );
		   
		    
		   /* Ajout du jour dans la Hashtable des jours de la semaine en cours */
		   try{
			   this.addJour( df.format(c.getTime()), jourSemaine );
		   }
		   catch(NullPointerException e){
			   JOptionPane.showMessageDialog(null, "Fuck");
		   }
		   
		   /* On passe au jour suivant */
		   c.add(Calendar.DATE, 1);
		}
	}
}
