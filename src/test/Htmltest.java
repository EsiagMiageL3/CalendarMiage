package test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

import vue.vueAcceuil2;
import controleur.controleurPlanning;
import modele.modelePlanning;
import modele.Formation;

public class Htmltest {
	public static modelePlanning p = new modelePlanning();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final String chemin_planning = "/Users/cyriellemintombou/Downloads/test4.html";
	    final File fichier_planning =new File(chemin_planning); 
        try {
            // Creation du fichier 
        	fichier_planning.createNewFile();
            // creation d'un writer
            final FileWriter writer = new FileWriter(fichier_planning);
            try {
                writer.write("<html><head>"
            			+ "<title>Planning</title>"
            			+ "<link rel=stylesheet href=/Users/cyriellemintombou/Downloads/Test2.css />"
                
                
            } finally {
                // quoiqu'il arrive, on ferme le fichier
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Impossible de creer le fichier");
        }
        
        try{
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("/Users/cyriellemintombou/Downloads/test4.html"));
        }catch(URISyntaxException e){
        	
        }catch(IOException e){
        	
        }

    }

}
