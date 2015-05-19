package controleur;
import java.awt.Color;
import java.awt.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import modele.Module;
import modele.modelePlanning;

public class controleurPlanning {
	// Instance du modele du planning
	private modelePlanning modele;
	private String pathFichier = "";
    ObjectInputStream input;
    ObjectOutputStream output;
    
	public controleurPlanning(modelePlanning modele){
		this.modele = modele;
	}

	public boolean nouvelleFormation(JTextField txtFormation, String anneeFormation){
		if( !txtFormation.getText().trim().equals( "" ) && anneeFormation != null && !anneeFormation.equals("")  ){
			this.modele.addFormation( txtFormation.getText(), anneeFormation );
			JOptionPane.showMessageDialog(null,"Formation creee !");
			return true;
		}
		else{
			System.out.println( "Nom de formation eronnee" );
			JOptionPane.showMessageDialog(null,"Le nom ou l annee de la formation ne peut etre vide." , "Nom de formation incorrect", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	/*
	 * Methode verifiant si la couleur choisie pour un module a deja et attribuer un autre
	 * Retourne false si la couleur est deja prise
	 * Retourne true le cas contraire 
	 */
	public boolean checkCouleurPrise(Color couleurChoisie){
		
		boolean trouve = false;
		
		/* On cree l'iterateur permettant de parcourir la map */
		Iterator it = this.modele.getFormation().getModules().entrySet().iterator();
		
		/* Si l'on rencontre la couleur lors du parcours, le booleen trouver passe true */ 
		while (it.hasNext()) {
			final Map.Entry pair = (Map.Entry) it.next();
			if( this.modele.getFormation().getModules().get( (String) pair.getKey() ).couleurModule == couleurChoisie  ){
				trouve = true;
			}
		}
		
		/* Si la couleur est deja presente, affichage du message d'erreur et renvoie de la valeur False */ 
		if( trouve ){
			JOptionPane.showMessageDialog(null, "La couleur est deja prise pour un autre module, veuillez en choisir une autre ou modifier celle du module.");
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
	 * Methode verifiant si le nom du nouveau module est celui d'un deja existant
	 * Retourne false si la couleur est deja prise
	 * Retourne true le cas contraire 
	 */
	public boolean checkNomModule(String nomModule){
		boolean trouve = false;
		
		/* On cree l'iterateur permettant de parcourir la map */
		Iterator it = this.modele.getFormation().getModules().entrySet().iterator();
		
		/* Si l'on rencontre le nom lors du parcours, le booleen trouve passe  true */ 
		while (it.hasNext()) {
			final Map.Entry pair = (Map.Entry) it.next();
			if( this.modele.getFormation().getModules().get( (String) pair.getKey() ).nom_m.equals( nomModule ) ){
				trouve = true;
			}
		}
		
		/* Si le nom est deja present, affichage du message d'erreur et renvoie de la valeur False */ 
		if( trouve ){
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
		int reponse = JOptionPane.showConfirmDialog(null,"Vous etes sur le point de supprimer le module " + key + ".\n" + "Les eventuelles seances attribuees a ce module seront egalement supprime.\n" + "Souhaitez-vous continuer ?", "Suppression du module " + key, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
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
		chooser.setDialogTitle("Choisissez le dossier ou enregistrer le fichier de votre planning");
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			
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

		   
			this.input = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(
		                  new File( chooser.getSelectedFile().getAbsolutePath()))));

						  this.pathFichier = chooser.getSelectedFile().getAbsolutePath();
						  
		      try {
		    	  	this.modele = null;
		    	  	this.modele = (modelePlanning) input.readObject();
			      } catch (ClassNotFoundException e) {
			    	  JOptionPane.showMessageDialog(null, "Ce fichier ne contient aucune donnee de planning !");
			      } catch (InvalidClassException e) {
			    	  JOptionPane.showMessageDialog(null, "Ce fichier ne contient pas de donnees de planning valides.");
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
