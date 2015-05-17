package test;

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
import modele.modelePlanning;
import modele.Formation;

public class Htmltest {
	
	private static String nom="nom";
	private static JTextField t;
	private Formation fm;
	private detailSemaine d;
	private static HashMap<String, JPanel> lst_PnlSeances = new HashMap<String, JPanel>();
	
	public Htmltest(HashMap<String, JPanel> lst_PnlSeances){
		this.lst_PnlSeances = lst_PnlSeances;
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		modelePlanning modele = new modelePlanning(); 
		controleurPlanning controleur = new controleurPlanning(modele );
		final String nom_f = "Master MIAGE";
		
		//final String chemin_planning = "/Users/nounoursmoelleux/test.html/";

		final String chemin_planning = "/Users/cyriellemintombou/Downloads/test.html";

	    final File fichier_planning =new File(chemin_planning); 
        try {
        	controleur.getModele().addFormation(nom_f, "2014");
            // Creation du fichier 
        	fichier_planning.createNewFile();
            // creation d'un writer
            final FileWriter writer = new FileWriter(fichier_planning);
            try {
                writer.write("<html><head>"
			+ "<title>Planning</title>"


			+ "<link rel=stylesheet href=/Users/nounoursmoelleux/test.css/>"

			+ "<link rel=stylesheet href=/Users/cyriellemintombou/Downloads/test.css />"

			+ "<h1><center>2014-2015</h1></center>"


			+ "</head>"
			+ "<body>"
			+ "<center><table>"
			+ "<caption><h1>" 
			
			 + controleur.getModele().getFormation().getNomFormation()
			 
			+ "</h1></caption>"
			+ "</br></br></br></br></br></br>"
			+ "<thead>"
			+ "<tr>"
				+ "<th></th>"
				+ "<th><font color=white><center>Lundi</center></th>"
				+ "<th><font color=white><center>Mardi</center></th>"
				+ "<th><font color=white><center>Mercredi</center></th>"
				+ "<th><font color=white><center>Jeudi</center></th>"
				+ "<th><font color=white><center>Vendredi</center></th>"
				+ "<th><font color=white><center>Samedi</center></th>"
				+ "<th><font color=white><center>Dimanche</center></th>"
			+ "</tr>"
			+ "</thead>"
			+ "<tfoot>"
			+ "</tfoot>"
			+ "<tbody>"
			+ "<tr>"
				+ "<th><font color=white><center<b>9h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>10h00</b></center></hd>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>11h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>12h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>13h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>14h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>15h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>16h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>17h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>18h00</b></center></th>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<th><font color=white><center<b>19h00</b></center></th>"
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
        



        try{
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("/Users/cyriellemintombou/Downloads/test.html"));
        }catch(URISyntaxException e){
        	
        }catch(IOException e){
        	
        }

    }

}
