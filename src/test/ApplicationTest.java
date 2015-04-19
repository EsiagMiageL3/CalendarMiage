package test;
import modèle.Module;
import modèle.Formation;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class ApplicationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Module m1 = new Module("Java" , "m1", "rouge", 10, 12);
		Module m2 = new Module("Base de données" , "m2", "bleu", 5, 7);
		Module m3 = new Module("Comptabilité" , "m3", "vert", 10, 11);
		
		ArrayList<Module> l1= new ArrayList<Module>();
		l1.add(m1);
		l1.add(m2);
		l1.add(m3);
		
		Formation f1 = new Formation("f1", 3, l1);
		
		for (Module m : l1){
			System.out.println("La durée du module "+m.getNom()+" est de "+m.getNb_heures()+" heures");
		}
		
		int duree_totale = m1.getNb_heures()+m2.getNb_heures()+m3.getNb_heures();
		System.out.println("La durée totale de la formation est de "+duree_totale+" heures");
		
		final String chemin = "D:/workspace/CalendarMiage/file/modules.txt";
	    final File fichier =new File(chemin); 
        try {
            // Creation du fichier 
            fichier.createNewFile();
            // creation d'un writer (un écrivain)
            final FileWriter writer = new FileWriter(fichier);
            try {
                writer.write("Modules\r\n"+m1.nom_m+" "+m1.abreviation+" "+m1.couleur+" "+m1.nb_seances+"\r\n");
                writer.write(m2.nom_m+" "+m2.abreviation+" "+m2.couleur+" "+m2.nb_seances+"\r\n");
                writer.write(m3.nom_m+" "+m3.abreviation+" "+m3.couleur+" "+m3.nb_seances+"\r\n");
                
            } finally {
                // quoiqu'il arrive, on ferme le fichier
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Impossible de créer le fichier");
        }
          
    }
	

}
