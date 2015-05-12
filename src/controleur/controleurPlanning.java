package src.controleur;
import java.awt.Color;
import java.awt.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import src.modele.Module;
import src.modele.modelePlanning;

public class controleurPlanning {
	// Instance du modele du planning
	private modelePlanning modele;
	private String pathFichier = "";
    ObjectInputStream input;
    ObjectOutputStream output;
    
	public controleurPlanning(modelePlanning modele){
		this.modele = modele;
	}

	public boolean nouvelleFormation(JTextField txtFormation){
		if( !txtFormation.getText().trim().equals( "" ) ){
			this.modele.addFormation( txtFormation.getText() );
			JOptionPane.showMessageDialog(null,"Formation créée !");
			return true;
		}
		else{
			System.out.println( "Nom de formation éronnée" );
			JOptionPane.showMessageDialog(null,"Le nom de la formation ne peut être vide." , "Nom de formation incorrect", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	/*
	 * Méthode vérifiant si la couleur choisie pour un module a déjà été attribué à un autre
	 * Retourne false si la couleur est déjà prise
	 * Retourne true le cas contraire 
	 */
	public boolean checkCouleurPrise(Color couleurChoisie){
		
		boolean trouvé = false;
		
		/* On crée l'itérateur permettant de parcourir la map */
		Iterator it = this.modele.getFormation().getModules().entrySet().iterator();
		
		/* Si l'on rencontre la couleur lors du parcours, le booleen trouvé passe à true */ 
		while (it.hasNext()) {
			final Map.Entry pair = (Map.Entry) it.next();
			if( this.modele.getFormation().getModules().get( (String) pair.getKey() ).couleurModule == couleurChoisie  ){
				trouvé = true;
			}
		}
		
		/* Si la couleur est déjà présente, affichage du message d'erreur et renvoie de la valeur False */ 
		if( trouvé ){
			JOptionPane.showMessageDialog(null, "La couleur est déjà prise pour un autre module, veuillez en choisir une autre ou modifier celle du module.");
			return false;
		}
		else{
			JOptionPane.showMessageDialog(null, "OK");
			return true;
		}
	}
	
	/*
	 * Méthode vérifiant si le nom du nouveau module est celui d'un déjà existant
	 * Retourne false si la couleur est déjà prise
	 * Retourne true le cas contraire 
	 */
	public boolean checkNomModule(String nomModule){
		boolean trouvé = false;
		
		/* On crée l'itérateur permettant de parcourir la map */
		Iterator it = this.modele.getFormation().getModules().entrySet().iterator();
		
		/* Si l'on rencontre le nom lors du parcours, le booleen trouvé passe à true */ 
		while (it.hasNext()) {
			final Map.Entry pair = (Map.Entry) it.next();
			if( this.modele.getFormation().getModules().get( (String) pair.getKey() ).nom_m.equals( nomModule ) ){
				trouvé = true;
			}
		}
		
		/* Si le nom est déjà présent, affichage du message d'erreur et renvoie de la valeur False */ 
		if( trouvé ){
				return false;
			
		}
		else{
			return true;
		}
	}
	
	public modelePlanning getModele(){
		return this.modele;
	}
	

	
	public void nouveauModule(String key, String abreviation, Color couleur, int nbSeances){
		this.modele.addModule( key, abreviation, couleur, nbSeances);
	}
	
	public boolean supprModule(String key){
		int reponse = JOptionPane.showConfirmDialog(null,"Vous êtes sur le point de supprimer le module " + key + ".\n" + "Les éventuelles séances attribuées à ce module seront également supprimé.\n" + "Souhaitez-vous continuer ?", "Suppression du module " + key, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if( reponse == JOptionPane.YES_NO_OPTION){
			this.modele.supprModule(key);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void savePlanning() throws IOException{
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choisissez le dossier où enregistrer le fichier de votre planning");
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   System.out.println("You chose to open this directory: " +
		        chooser.getSelectedFile().getAbsolutePath());
		   
			this.output = new ObjectOutputStream(
		              new BufferedOutputStream(
		                new FileOutputStream(
		                  new File( chooser.getSelectedFile().getAbsolutePath() ))));
			
		      //Ecriture de l'objet dans le fichier
		      try {
				this.output.writeObject(this.modele);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //Fermeture du flux
			output.close();
		}
		
	}
	
	public modelePlanning openPlanning() throws IOException{
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choisissez le fichier de votre planning");
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   System.out.println("You chose to open this directory: " +
		        chooser.getSelectedFile().getAbsolutePath());
		   
			this.input = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(
		                  new File( chooser.getSelectedFile().getAbsolutePath()))));

						  this.pathFichier = chooser.getSelectedFile().getAbsolutePath();
						  
		      try {
		    	  	this.modele = null;
		    	  	this.modele = (modelePlanning) input.readObject();
			      } catch (ClassNotFoundException e) {
			    	  JOptionPane.showMessageDialog(null, "Ce fichier ne contient aucune donnée de planning !");
			      } catch (InvalidClassException e) {
			    	  JOptionPane.showMessageDialog(null, "Ce fichier ne contient pas de données de planning valides.");
			      } catch (IOException e) {
			    	  JOptionPane.showMessageDialog(null, "Ouverture du fichier impossible.");
				}
		      
				input.close();
				return this.modele;
		}
		else{
			return modele;
		}
		
	}
	

	public String getChemin(){
		return this.pathFichier;
	}
	
}
