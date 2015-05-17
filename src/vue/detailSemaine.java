package vue;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import test.Htmltest;
import controleur.controleurPlanning;
import modele.Formation;
import modele.Jour;
import modele.Seance;
import modele.Semaine;

public class detailSemaine extends JPanel implements ActionListener, FocusListener, MouseListener  {
	
	private controleurPlanning controleur;
	private Formation fm;
	private int numSemaine;
	private JPanel conteneur;
	private JPanel cadreSeances;
	private String[] tblJours = { "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche" };
	
	private JButton exportHTML;
	
	private HashMap<String, JLabel> lst_LblJours = new HashMap<String, JLabel>();

	private HashMap<String, JPanel> lst_PnlSeances = new HashMap<String, JPanel>();
	
	private int  largeurConteneur, hauteurConteneur;
	
	public detailSemaine(int numSemaine, controleurPlanning controleur){
		this.controleur = controleur;
		this.numSemaine = numSemaine;
		initComponents();
		this.setVisible(true);
		
	}
	
	private void initComponents() {
		
		/* Definition de la taille de la fenetre principale */
		this.setSize((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 0.95), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height * 0.95));

		/*
		 * Instanciation du conteneur de la fenetre
		 * Reecriture de la methode paint component afin d'y ajouter un degrade de couleurs
		 */
		this.conteneur = new JPanel() {@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
				Color color1 = new Color(0, 42, 74);
				Color color2 = new Color(247, 238, 199);
				GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};


		this.conteneur.setLayout(null); /* Affectation d'un layout null au ContentPane afin de positionner les composants comme voulu */
		this.conteneur.setOpaque(false); /* Le ContentPane sera transparent */
		this.conteneur.setSize(this.getSize()); /* Taille du conteneur egale ˆ celle de la fenetre */
		this.setLayout(null);
		this.add(this.conteneur);
		//this.setContentPane(this.conteneur); /* Le conteneur devient le ContentPane de la fenetre principale */
		largeurConteneur = this.conteneur.getWidth(); /* Affection de la largeur du conteneur a la variable */
		hauteurConteneur = this.conteneur.getHeight(); /* Affection de la hauteur du conteneur a la variable */
		
		this.cadreSeances = new JPanel( new GridLayout(0, 8, 7, 0) );
		this.cadreSeances.setOpaque(false);
		this.cadreSeances.setBounds((int)(largeurConteneur * 0.01), (int)(hauteurConteneur * 0.05), (int)(largeurConteneur * 0.98), (int)(hauteurConteneur * 0.9));
		buildSeances();

		this.exportHTML = new JButton("Export HTML");
		this.exportHTML.setBounds((int)(largeurConteneur * 0.01), (int)(largeurConteneur * 0.01), (int)(largeurConteneur * 0.08), (int)(hauteurConteneur * 0.06));
		this.exportHTML.setVisible(true);
		
		this.exportHTML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
					Htmltest export = new Htmltest( controleur );			
		
			}
		});
		this.conteneur.add( this.exportHTML );
		
		this.conteneur.add(this.cadreSeances);
		
	}
	
	
	private void buildSeances(){
		int heure = 8;
		
		for( int i = 0; i < 8; ++i ){
			if(i == 0){
				JLabel jour = new JLabel("", JLabel.CENTER);
				this.cadreSeances.add( jour );
			}
			else{
				JLabel jour = new JLabel("...", JLabel.CENTER);
				jour.setFont(new Font("Arial", Font.PLAIN, 11));
				jour.setOpaque(false);
				jour.setForeground(Color.WHITE);
				
				jour.setText( this.tblJours[i - 1] );
				
				this.cadreSeances.add( jour );
				this.lst_LblJours.put(this.tblJours[i - 1], jour);
			}

		}
		
		
		
		
		   /*
		    * Boucle permettant de charger la date de chaque jour de la semaine dans les Label du haut
		    */
		   Iterator it = this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().entrySet().iterator();
		   //
		   while (it.hasNext()) {
			   
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        String texteOriginale = (String) pair.getKey(); /* Récupération du nom du jour grace à la clé de la map des jours de la semaine */
		        
		        this.lst_LblJours.get( (String) pair.getKey() ).setText( texteOriginale + " " + this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNumJour() + " " +  this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNomMois() );
		    
		   }
			
		
		
		    
		    
		    
		    
		    
		for( int j = 0; j < 11; ++ j ){
			heure += 1;
			
			for( int i = 0; i < 8; ++i ){
				if( i == 0){
					JLabel lblHeure = new JLabel("", JLabel.CENTER);
					lblHeure.setOpaque(false);
					lblHeure.setForeground(Color.WHITE);
					
					lblHeure.setText( heure + "h00");
					this.cadreSeances.add( lblHeure );
				}
				else{
					JPanel seance = new JPanel( new GridLayout(0,1));
					seance.setOpaque(true);
					
					seance.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
					seance.setName(this.tblJours[i-1] + "_" + heure);

					
					final Window fenetreParent = SwingUtilities.getWindowAncestor(this); /* Référence à la fenêtre courante pour la JDialog prochaine */
					seance.addMouseListener(new MouseAdapter() {
		     			public void mousePressed(MouseEvent e) {
		     				
		     				if( ( (JPanel) e.getSource() ).getComponentCount() != 0 ){
		     					seanceExist( (JPanel) e.getSource() );
		     				}
		     				
		     				
		     				vueChoixModuleSeance chx = new vueChoixModuleSeance(controleur);
		     				
		     				
		     				JDialog dialogSemaine = new JDialog( fenetreParent, "Choix du module de la séance", ModalityType.APPLICATION_MODAL );

							
							dialogSemaine.setModal(true); /* La fenetre de dialogue est modale */
							dialogSemaine.getContentPane().add(chx); /* On définit le ContentPane de la fenêtre avec l'instance de la classe detailSemaine */
							dialogSemaine.setPreferredSize( chx.getSize() ); /* Définition de la taille de la fenêtre modale par celle de son ContentPane */
							dialogSemaine.setResizable( false ); /* La fenêtre ne sera pas réajustable */
							dialogSemaine.pack(); /* Obvious */
							dialogSemaine.setLocationRelativeTo(null); /* Permet de centrer la fenêtre */
							dialogSemaine.setVisible(true); /* Obvious */
		     				
		     				
		     				
		     				String nom = ( (JPanel) e.getSource() ).getName();
		     				String value = nom;
		     				
		     				String lettres =  value.replaceAll("\\d","");
		     				String intValue = value.replaceAll("[^0-9]", "");
		     				
			      			int taille = controleur.getModele().getFormation().getModules().get( chx.getNomModule() ).getSeances().size();
			     				
			      			controleur.getModele().getFormation().getSemaine( numSemaine ).getLstjours().get( nom.substring( 0, nom.indexOf( "_" ) ) ).getListeSeances().put( intValue.toString(), new Seance( chx.getNomModule(), taille, chx.getDureeSeance(), controleur.getModele().getFormation().getModules().get( chx.getNomModule() ) ));//   getListeSeances().put( intValue.toString(), new Seance("Java"));

			     			controleur.getModele().getFormation().getModules().get( chx.getNomModule() ).getSeances().put( Integer.toString(taille) + 1, new Seance( chx.getNomModule(), taille, chx.getDureeSeance(), controleur.getModele().getFormation().getModules().get( chx.getNomModule() ) ));  
			     			
		     				JLabel module = new JLabel( chx.getNomModule(), JLabel.CENTER );
		     				module.setBackground(chx.getColorModule() );
		     				module.setOpaque(true);
		     				

	     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) ) ).add(module); 
	     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) ) ).setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK ) );
	     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) ) ).setBackground( chx.getColorModule() );
	     					
		     				JLabel description = new JLabel( "Rang: " + Integer.toString(taille + 1) + "/" + taille, JLabel.CENTER );
		     				description.setBackground( chx.getColorModule() );
		     				description.setOpaque(true);
		     				description.setName( chx.getNomModule() );
		     				lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) )  ).add(description);
		     				
		     				for(int i = 1; i < chx.getDureeSeance(); ++i){
		     					
			     				JLabel moduleSuite = new JLabel( "", JLabel.CENTER );
			     				moduleSuite.setBackground(chx.getColorModule() );
			     				moduleSuite.setOpaque(true);
			     				description.setName( chx.getNomModule() );
		     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + i) ) ).add(moduleSuite); 
		     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + i) ) ).setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK ) );
		     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + i) ) ).setBackground( chx.getColorModule() );
		     					
		     				}
	     					lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + chx.getDureeSeance()) ) ).setBorder(new LineBorder(Color.BLACK ) );
	     					

		     				
		     			
		     				

							repaint();
		     				validate();
		     				
		     		 refreshSeances();
		     			}
					});
					
					this.cadreSeances.add( seance );
					
					this.lst_PnlSeances.put( this.tblJours[i - 1] + "_" + heure , seance );
					
				}
			}
			
			
		}
		
		for( int i = 0; i < this.tblJours.length; ++i ){
			this.lst_PnlSeances.get( this.tblJours[i] + "_" + 9 ).setBorder( new MatteBorder(1, 1, 1, 1, Color.BLACK) );
		}
		
		loadSeance();
	}
	
	
	
	public controleurPlanning getControleur() {
		return this.controleur;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void refreshSeances() {
		Component[] component;
		   Iterator it = this.lst_PnlSeances.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        if(  (  ( JPanel ) pair.getValue() ).getComponentCount() != 0 ){
		        	
		        component =  (  ( JPanel ) pair.getValue() ).getComponents() ;
		        
		        if(component.length != 0){
		        for( int i = 0; i < component.length; i++ ){
		        	
		        		if( ( ( JLabel ) component[i] ).getName() != null  ){
			                
		        			String nomModule = ( ( JLabel ) component[i] ).getName();
		        			String descriptionRang = ( ( JLabel ) component[i] ).getText();
		        			
			      			int taille = controleur.getModele().getFormation().getModules().get( nomModule ).getSeances().size();
			     			
			      			( ( JLabel ) component[i] ).setText( descriptionRang.substring(0, descriptionRang.length()-1) + Integer.toString(taille) );
			            
		        			 
		        		}
		            

		        }
		        }
		        
		        }
		        repaint();
		        validate();
		    }
		
	}
	
	public void seanceExist( JPanel seance ) {
		int reponse = JOptionPane.showConfirmDialog(null,"Une séance existe deja sur la plage horaire selectionnee\n" + "Souhaitez - vous supprimer cette derniere ?", "Suppression du module " , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if( reponse == JOptionPane.YES_NO_OPTION){
			int duree = 0;
			int debut = 0;
			
			String nom = seance.getName();
			
			String lettres =  nom.replaceAll("\\d","");
			String nomJour = lettres.substring(0, lettres.length()-1);
			String intValue = nom.replaceAll("[^0-9]", "");
			
			Component[] composants = seance.getComponents();
			
			for( int i = 0; i < composants.length; ++i ){
				
				duree = Integer.parseInt( ( ( JLabel ) composants[i] ).getName() );
				debut = Integer.parseInt( intValue ) -  Integer.parseInt( ( ( JLabel ) composants[i] ).getText() ) ;
				
			}

			for( int i = 0; i < duree; ++i){
				lst_PnlSeances.get( lettres + ( debut + i) ).removeAll();
				Color color = UIManager.getColor ( "Panel.background" );
				lst_PnlSeances.get( lettres + ( debut + i) ).setBackground( color );
				lst_PnlSeances.get( lettres + ( debut + i) ).setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK ) );
			}
			lst_PnlSeances.get( lettres + ( debut + duree - 1) ).setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK ) );
			
			Seance refSeance = controleur.getModele().getFormation().getSemaine( numSemaine ).getLstjours().get( nom.substring( 0, nom.indexOf( "_" ) ) ).getListeSeances().get( Integer.toString( debut ) );
			
  			controleur.getModele().getFormation().getSemaine( numSemaine ).getLstjours().get( nom.substring( 0, nom.indexOf( "_" ) ) ).getListeSeances().remove( Integer.toString( debut ) );

 			controleur.getModele().getFormation().getModules().get( refSeance.getMod() ).getSeances().remove( refSeance.getRangSeance() );  
 			
			repaint();
			validate();
			//JOptionPane.showMessageDialog(null, "Seance suprimee ! Taille " + controleur.getModele().getFormation().getModules().get( refSeance.getMod() ).getSeances().size() );
			
		}
		else{
			//
		}
	}
	
	public void loadSeance() {
		
		   Iterator it = this.lst_PnlSeances.entrySet().iterator();
		   
		   while (it.hasNext()) {
			   
		        Map.Entry curseur = (Map.Entry)it.next();
		        
				String nomPanel = (String) curseur.getKey();
				String value = nomPanel;
				
				String lettres =  value.replaceAll("\\d","");
				String nomJour = lettres.substring(0, lettres.length()-1);
				String intValue = value.replaceAll("[^0-9]", "");
		  
				if (controleur.getModele().getFormation().getSemaine(numSemaine).getLstjours().get( nomJour ).getListeSeances().containsKey( intValue ) ) {
					Seance nomSeance = controleur.getModele().getFormation().getSemaine(numSemaine).getLstjours().get( nomJour ).getListeSeances().get( intValue );

					JLabel module = new JLabel(nomSeance.getMod(), JLabel.CENTER);
					module.setBackground(nomSeance.getCouleurModule());
					module.setOpaque(true);


						lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) ) ).add(module); 
						lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) ) ).setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK ) );
						lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) ) ).setBackground( nomSeance.getCouleurModule() );
						
		 				JLabel description = new JLabel( "Rang: " + nomSeance.getRangSeance() + "/" + nomSeance.getRefModule().getSeances().size(), JLabel.CENTER );
		 				description.setBackground( nomSeance.getCouleurModule() );
		 				description.setOpaque(true);
		 				description.setName( nomSeance.getMod() );
		 				lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) ) )  ).add(description);
		 				
						
						for(int j = 1; j < nomSeance.getDureeSeance(); ++j){
							
		 				JLabel moduleSuite = new JLabel( "", JLabel.CENTER );
		 				moduleSuite.setText( Integer.toString( j ) );
		 				moduleSuite.setBackground( nomSeance.getCouleurModule() );
		 				moduleSuite.setOpaque(true);
		 				
		 				moduleSuite.setName( Integer.toString( nomSeance.getDureeSeance() ) );
		 				
							lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + j) ) ).add(moduleSuite); 
							lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + j) ) ).setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK ) );
							lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + j) ) ).setBackground( nomSeance.getCouleurModule() );
							
						}
						lst_PnlSeances.get( lettres + Integer.toString( (Integer.parseInt(intValue) + nomSeance.getDureeSeance() ) ) ).setBorder(new LineBorder(Color.BLACK ) );
						
				}
		   }
		   

					


		}
		

	
}


