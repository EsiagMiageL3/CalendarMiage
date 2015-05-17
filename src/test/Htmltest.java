package test;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import vue.detailSemaine;
import controleur.controleurPlanning;
import modele.Seance;
import modele.modelePlanning;
import modele.Formation;

public class Htmltest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		modelePlanning modele = new modelePlanning(); 
		controleurPlanning controleur = new controleurPlanning(modele );
<<<<<<< HEAD
		final String nom_f = "F1";
		final String key = "Module1";
		final String abr = "M1";
		
		final String chemin_planning = "D:/test.html";
	    final File fichier_planning =new File(chemin_planning); 
        try {
        	controleur.getModele().addFormation(nom_f);
        	controleur.getModele().addModule(key, abr, Color.red, 5);
        	controleur.getModele().getFormation().getSemaine(34).getLstjours().get("lundi").getListeSeances().put("9", new Seance());
=======
		final String nom_f = "Master MIAGE";
		
		//final String chemin_planning = "/Users/nounoursmoelleux/test.html/";

		final String chemin_planning = "/Users/cyriellemintombou/Downloads/test.html";

	    final File fichier_planning =new File(chemin_planning); 
        try {
        	controleur.getModele().addFormation(nom_f, "2014");
>>>>>>> origin/master
            // Creation du fichier 
        	fichier_planning.createNewFile();
            // creation d'un writer
            final FileWriter writer = new FileWriter(fichier_planning);
            try {
                writer.write("<html><head>"
			+ "<title>Planning</title>"

<<<<<<< HEAD
			+ "<link rel=stylesheet href=D:/test.css>"
=======

			+ "<link rel=stylesheet href=/Users/nounoursmoelleux/test.css/>"

			+ "<link rel=stylesheet href=/Users/cyriellemintombou/Downloads/test.css />"

			+ "<h1><center>2014-2015</h1></center>"

>>>>>>> origin/master

			+ "</head>"
			+ "<body>"
			+ "<center><table>"
			+ "<caption><h1>" 
			
			 + controleur.getModele().getFormation().getNomFormation()
			 + " 2014-2015 "
			 + controleur.getModele().getFormation().getDuree()
			 
			+ "</h1></caption>"
			+ "</br></br></br></br></br></br>"
			+ "<tr>"
				+ "<th></th>"
				+ "<th>Lundi</th>"
				+ "<th>Mardi</th>"
				+ "<th>Mercredi</th>"
				+ "<th>Jeudi</th>"
				+ "<th>Vendredi</th>"
				+ "<th>Samedi</th>"
				+ "<th>Dimanche</th>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>9h00</th>"
				+ "<td bgcolor="+controleur.getModele().getFormation().getModules().get(key).getCouleur() +">"
				+ controleur.getModele().getFormation().getModules().get(key).getAbreviation()
				+ " "
				+ controleur.getModele().getFormation().getModules().get(key).getQuotas()
				+ controleur.getModele().getFormation().getSemaine(34).getLstjours().get("lundi").getListeSeances().get("9").getRangSeance()
				+ "</td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>10h00</hd>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>11h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>12h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>13h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>14h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>15h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>16h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>17h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>18h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th>19h00</th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "</tbody>"
			+ "</table></center>"
			+ "</body>"
			+ "</html>");
                
                
            } finally {
                // quoiqu'il arrive, on ferme le fichier
                writer.close();
            }
        } catch (Exception e) {

            System.out.println("Impossible de créer le fichier");

        }
        
<<<<<<< HEAD
        try{
            Desktop d = Desktop.getDesktop();
            d.browse(new URI("D:/test.html"));
            }catch(URISyntaxException e){
            	
            }catch(IOException e){
            	
            }
=======



        try{
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("/Users/cyriellemintombou/Downloads/test.html"));
        }catch(URISyntaxException e){
        	
        }catch(IOException e){
        	
        }

>>>>>>> origin/master
    }

}
