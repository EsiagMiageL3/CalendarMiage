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
			+ "<table border=1 style=overflow:auto>"
			+ "<caption><color=blue><b>Mai</b></caption>"
			+ "</br></br></br></br></br></br></br>"
			+ "<tr>"
				+ "<th><center><b>Lun</b></center></th>"
				+ "<th><center><b>Mar</b></center></th>"
				+ "<th><center><b>Mer</b></center></th>"
				+ "<th><center><b>Jeu</b></center></th>"
				+ "<th><center><b>Ven</b></center></th>"
				+ "<th><center><b>Sam</b></center></th>"
				+ "<th><center><b>Dim</b></center></th>"
			+ "</tr>"
			+ "<tr>"
				+ "<td><font color=white><center></center></td>"
				+ "<td><font color=white><center></center></td>"
				+ "<td><font color=white><center></center></td>"
				+ "<td><font color=white><center></center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>1</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>2</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>3</center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td bgcolor=#48D1CC><font color=white><center>4</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>5</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>6</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>7</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>8</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>9</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>10</center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td bgcolor=#48D1CC><font color=white><center>11</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>12</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>13</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>14</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>15</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>16</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>17</center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td bgcolor=#48D1CC><font color=white><center>18</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>19</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>20</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>21</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>22</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>23</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>24</center></td>"
			+ "</tr>"
			+ "<tr>"
				+ "<td bgcolor=#48D1CC><font color=white><center>25</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>26</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>27</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>28</center></td>"
				+ "<td bgcolor=#48D1CC><font color=white><center>29</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>30</center></td>"
				+ "<td bgcolor=#000000><font color=white><center>31</center></td>"
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
