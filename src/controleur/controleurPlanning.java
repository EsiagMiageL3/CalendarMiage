package controleur;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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

	public void nouvelleFormation(JTextField txtFormation){
		if( !txtFormation.getText().equals( "" ) ){
			this.modele.addFormation( txtFormation.getText() );
		}
		else{
			System.out.println( "Nom de formation éronnée" );
			JOptionPane.showMessageDialog(null,"Le nom de la formation ne peut être vide." , "Nom de formation incorrect", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public modelePlanning getModele(){
		return this.modele;
	}
	

	
	public void nouveauModule(String key, Color couleur){
		this.modele.addModule(key, couleur);
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
		//chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   System.out.println("You chose to open this directory: " +
		        chooser.getSelectedFile().getAbsolutePath());
		   
			this.input = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(
		                  new File( chooser.getSelectedFile().getAbsolutePath()))));
						  
						  // Enregistrement du chemin vers le fichier de suavegarde
						  this.pathFichier = chooser.getSelectedFile().getAbsolutePath();
						  
		      try {
			        this.modele = ((modelePlanning) input.readObject());
			      } catch (ClassNotFoundException e) {
			    	  JOptionPane.showMessageDialog(null, "Ce fichier ne contient aucune donnée de planning !");
			        
			      } catch (IOException e) {
					// TODO Auto-generated catch block
			    	  System.out.println("fuck");
			    	  JOptionPane.showMessageDialog(null, "Ce fichier ne contient aucune donnée de planning !");
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
