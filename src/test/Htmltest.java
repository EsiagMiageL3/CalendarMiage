package test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import controleur.controleurPlanning;
import modele.modelePlanning;
import modele.Formation;

public class Htmltest {
	public static modelePlanning p = new modelePlanning();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final String chemin_planning = "D:/test.html";
	    final File fichier_planning =new File(chemin_planning); 
        try {
            // Creation du fichier 
        	fichier_planning.createNewFile();
            // creation d'un writer
            final FileWriter writer = new FileWriter(fichier_planning);
            try {
                writer.write("<html><head>"
			+ "<title>Planning</title>"
			+ "<link rel=stylesheet href=D:/test.css />"
			+ "<h1><center>2014-2015</h1></center>"
			+ "</head>"
			+ "<table>"
			+ "</br></br></br></br></br></br></br>"
			+ "<tr>"
				+ "<th></th>"
				+ "<th><center><b>Lundi</b></center></th>"
				+ "<th><center><b>Mardi</b></center></th>"
				+ "<th><center><b>Mercredi</b></center></th>"
				+ "<th><center><b>Jeudi</b></center></th>"
				+ "<th><center><b>Vendredi</b></center></th>"
				+ "<th><center><b>Samedi</b></center></th>"
				+ "<th><center><b>Dimanche</b></center></th>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>9h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>10h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>11h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>12h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>13h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>14h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>15h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>16h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>17h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>18h00</b></center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><center<b>19h00</b></center></td>"
			+ "</tr>"
			+ "</table>"
			+ "</body>"
			+ "</html>");
                
                
            } finally {
                // quoiqu'il arrive, on ferme le fichier
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Impossible de créer le fichier");
        }
        
        try{
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("D:/test.html"));
        }catch(URISyntaxException e){
        	
        }catch(IOException e){
        	
        }
    }

}
