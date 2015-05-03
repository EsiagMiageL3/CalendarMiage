package src.test;

import src.modele.Module;
import src.modele.Formation;
//<<<<<<< Updated upstream
//=======
import src.modele.Seance;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class ApplicationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Module m1 = new Module("Java" ,"sersdf",  Color.red,45);
		Module m2 = new Module("Base de donnees" , "ser",  Color.red,12);
		Module m3 = new Module("Comptabilite" ,"ser", Color.red, 12);
		
		ArrayList<Module> l1= new ArrayList<Module>();
		l1.add(m1);
		l1.add(m2);
		l1.add(m3);
		
		Formation f1 = new Formation("f1", 3, l1);
		
		
		//Seance coursJava = new Seance(m1.getNom(),m1.getAbreviation(),m1.getCouleur(),m1.getNb_heures(),m1.nb_seances,10);
		
	
		for (Module m : l1){
			System.out.println("La dur�e du module "+m.getNom()+" est de "+m.getNb_heures()+" heures");
		}
		
		int duree_totale = m1.getNb_heures()+m2.getNb_heures()+m3.getNb_heures();
		System.out.println("La dur�e totale de la formation est de "+ duree_totale+" heures");
		
		//final String chemin = "D:/workspace/CalendarMiage/file/modules.txt";
		final String chemin = "/Users/cyriellemintombou/Documents/workspace/CalendarMiage/file/modules.txt";
	    final File fichier =new File(chemin); 
        try {
            // Creation du fichier 
            fichier.createNewFile();
            // creation d'un writer (un ecrivain)
            final FileWriter writer = new FileWriter(fichier);
            try {
                writer.write("Modules\r\n"+ m1.nom_m +" "+m1.abreviation+" "+m1.couleur+" "+m1.nb_seances+"\r\n");
                writer.write(m2.nom_m+" "+m2.abreviation+" "+m2.couleur+" "+m2.nb_seances+"\r\n");
                writer.write(m3.nom_m+" "+m3.abreviation+" "+m3.couleur+" "+m3.nb_seances+"\r\n");
                
            } finally {
                // quoiqu'il arrive, on ferme le fichier
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Impossible de creer le fichier");
        }
	    
	}
	
}
