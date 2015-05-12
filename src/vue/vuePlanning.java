package vue;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controleur.controleurPlanning;
import modele.Formation;
import modele.Jour;
import modele.Module;
import modele.Semaine;
import modele.modelePlanning;

import java.text.*;

public class vuePlanning extends javax.swing.JFrame implements ActionListener {
	
	private int largeurConteneur, hauteurConteneur; /* Variables contenant la hauteur et largeur de la fen�tre */
	private Formation fm;
	
	private controleurPlanning Controleur;
	
	private JPanel pnlInfos;
	private JLabel lblDisplayFormation, lblDisplayNbModules, nbJours, lblDisplayNbJours;
	
	private modelePlanning modele;
	private static String[] tbl = {"Septembre","Octobre","Novembre", "Decembre", "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout"};
	private static JButton[] bouttons = new JButton[40];
	ArrayList < JPanel > lstMois = new ArrayList < JPanel > ();
    
	/**
	 * Constructeur
	 */
	public vuePlanning(modelePlanning nouveauModele, controleurPlanning Controleur) {
		
		
		initComponents();
		this.Controleur = Controleur;
		this.modele = nouveauModele;

		//this.setTitle("Calendar Miage - Mintombou Cyrielle, Souto Camille, Deora Julien");
		//this.lblDisplayFormation.setText(this.modele.getLstFormation().get(0).nom_f);
		//this.lblDisplayNbModules.setText( Integer.toString(this.modele.getLstFormation().get(0).duree) );
		this.setVisible(true);
	}
 
	// RŽcupŽrer la largeur de l'Žcran
	public static int largeurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	// RŽcupŽrer la hauteur de l'Žcran
	public static int hauteurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	//public void saisieModule(){
	//0	vueNewModule saisieModule = new vueNewModule(this);
//	}






	public void actionPerformed(ActionEvent evt) {

	}

	private void initComponents() {
	            	
					/*
	        		try {
	        			for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
	        				if ("Nimbus".equals(info.getName())) {
	        					javax.swing.UIManager.setLookAndFeel(info.getClassName());
	        					break;
	        				}
	        			}
	        		} catch (ClassNotFoundException ex) {
	        			java.util.logging.Logger.getLogger(vuePlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        		} catch (InstantiationException ex) {
	        			java.util.logging.Logger.getLogger(vuePlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        		} catch (IllegalAccessException ex) {
	        			java.util.logging.Logger.getLogger(vuePlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        			java.util.logging.Logger.getLogger(vuePlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        		}
	        		*/
	        		
	              //  JFrame frame = new JFrame();
	                
	                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	                this.setSize( getLargeurEcran(), getHauteurEcran() );
	                
	                JPanel conteneur = new JPanel(){
	                    @Override
	                    protected void paintComponent(Graphics g) {
	                        super.paintComponent(g);
	                        Graphics2D g2d = (Graphics2D) g;
	                        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	                        int w = getWidth();
	                        int h = getHeight();
	                        Color color1 = new Color(93,70,96);
	                        Color color2 = new Color(247, 238, 199);
	                        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
	                        g2d.setPaint(gp);
	                        g2d.fillRect(0, 0, w, h);
	                    }           
	                };
	                
	                /*
	                 *  Aucun layout pour permettre de positionner les composants prŽcisemment
	                 *  en fonction de la taille de l'Žcran
	                 */
	                conteneur.setLayout(null);
	                
	                /*
	                 *  DŽfintion du ContentPane de la fen�tre
	                 */
	                this.setContentPane(conteneur);
	                this.setUndecorated(true);
	        		largeurConteneur = this.getWidth(); /* Affection de la largeur du conteneur ˆ la variable */
	        		hauteurConteneur = this.getHeight(); /* Affection de la hauteur du conteneur ˆ la variable */
	                /*
	                 *  Panel contenant le planning
	                 */
	                JPanel planning = new JPanel();
	                planning.setLayout( new GridLayout( 0, 3,10,10 ) );
	                //planning.setBounds(50, (int) (getHauteurEcran() * 0.20), getLargeurEcran() - 100, (int) (getHauteurEcran() * 0.75) );
		              
	                planning.setBackground(new Color(80,80,80));
	                /*
	                 *  JScrollPane contenant le Panel du planning
	                 *  DŽfinition de sa dimension et de sa position
	                 */
	                JScrollPane Jscroll = new JScrollPane();
	                Jscroll.setBounds( (int) ( largeurConteneur * 0.01 ), (int) ( hauteurConteneur * 0.05 ), (int) ( largeurConteneur * 0.9 ), (int) ( hauteurConteneur * 0.9) );
	              
	                JLabel settings = new JLabel( "\uD83D\uDEE0", JLabel.CENTER );
	                
	                settings.setOpaque(false);
	                settings.setForeground(Color.WHITE);
	                settings.setBounds( (int) ( largeurConteneur * 0.92 ), (int) ( hauteurConteneur * 0.45 ), (int) ( hauteurConteneur * 0.1 ), (int) ( hauteurConteneur * 0.1) );
	                //settings.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)); 
	                settings.setFont(new Font("Arial", Font.PLAIN, 40));
	                settings.setHorizontalAlignment(JLabel.CENTER);
	                

	                conteneur.add( settings );
	                
	                final JPanel sideBar = new JPanel(new GridLayout(10, 1, 20,0));
	                sideBar.setOpaque(true);
	                sideBar.setBounds( (int) ( largeurConteneur + 1), 0, (int) ( largeurConteneur * 0.15 ), (int) ( hauteurConteneur) );
	               sideBar.setBackground(new Color(80, 80, 80));
	               
	               
		               
		               	                JLabel iconYear = new JLabel("\uD83D\uDCC5", JLabel.CENTER);
		               	                iconYear.setVisible(true);
		               	                iconYear.setOpaque(false);
		               	                iconYear.setFont(new Font("Arial", Font.PLAIN, 35));
		       	                
		               	                JLabel iconSave = new JLabel("\uD83D\uDCBE", JLabel.CENTER);
		               	             iconSave.setVisible(true);
		               	          iconSave.setOpaque(false);
		               	       iconSave.setFont(new Font("Arial", Font.PLAIN, 35));

              	                JLabel iconQuit = new JLabel("\u274C", JLabel.CENTER);
              	              iconQuit.setVisible(true);
              	            iconQuit.setOpaque(false);
              	          iconQuit.setFont(new Font("Arial", Font.PLAIN, 35));
              	       
		               	    
		               	             sideBar.add(iconYear);
		               	             sideBar.add(iconSave);
		               	             sideBar.add(iconQuit);
		               	            
		               	             conteneur.add(sideBar);
	         

	              
	                settings.addMouseListener(new MouseAdapter() {
		     			public void mousePressed(MouseEvent e) {
		     				TimerTask task = new TimerTask(){
		     					
		     					int i = 0;
		     					int positionDepart = sideBar.getX();
		     					int destination = largeurConteneur - sideBar.getWidth();
		     					
		     					public void run(){
		     						if( sideBar.getX() > destination){
		     							i += 1;
			     						sideBar.setLocation( ( positionDepart ) - i, 0);
		     						}
		     						else{
		     							repaint();
		     							validate();
		     							cancel();
		     							
		     							
		     
		     						}
		     					}
		     					
		     				};
		     				
		     				Timer timer = new Timer();
		     				timer.scheduleAtFixedRate(task, 0, 2);
		     				
		    			}
		    		});
	                /*
	                 *  Le Panel du planning devient le Viewport du JScrollPane
	                 *  On l'ajoute au ContentPane
	                 */
	                Jscroll.setViewportView(planning);
	                Jscroll.setOpaque(false);
	                Jscroll.setBackground(new Color(80,80,80));
	                Jscroll.getViewport().setOpaque(false);
	                Jscroll.setBorder(null);
	                conteneur.add(Jscroll);
	                
	                /*
	                 *  Panel contenant les controles
	                 *  DŽfinition de ses tailles et positions
	                 *  Ajout au ContentPane
	                 */
	        		JPanel test = new JPanel();
	        		test.setOpaque(false);
	        		test.setLayout(null);
	        		test.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
	        		test.setBounds(0, 0, getLargeurEcran(), (int) (getHauteurEcran() * 0.2) );
	        		//conteneur.add(test);

	        		JLabel displayAnnee = new JLabel();
	        		
	        		this.pnlInfos = new JPanel();
	        		
	                this.pnlInfos.setOpaque(false);
	        		JLabel lblFormation = new JLabel("Formation du planning", JLabel.CENTER);
	        		lblDisplayFormation = new JLabel("...", JLabel.CENTER);
	        		JLabel lblNbModules = new JLabel("Nombre de modules", JLabel.CENTER);
	        		lblDisplayNbModules = new JLabel("...", JLabel.CENTER);
	        		
	        		this.nbJours = new JLabel("Jours de la formation", JLabel.CENTER);
	        		this.lblDisplayNbJours = new JLabel("...", JLabel.CENTER);
	        		JLabel test4 = new JLabel("...", JLabel.CENTER);
	        		JLabel test5 = new JLabel("...", JLabel.CENTER);
	        		this.pnlInfos.add(lblFormation);
	        		this.pnlInfos.add(lblDisplayFormation);
	        		this.pnlInfos.add(lblNbModules);
	        		this.pnlInfos.add(lblDisplayNbModules);
	        		this.pnlInfos.add(nbJours);
	        		this.pnlInfos.add(lblDisplayNbJours);
	        		this.pnlInfos.add(test4);
	        		this.pnlInfos.add(test5);
	        	
	        		lblFormation.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
	        		lblFormation.setForeground(new Color(255,255,255));
	        		lblFormation.setBackground(new Color(80,80,80));
	        		lblFormation.setOpaque(true);
	        		
	        		lblDisplayFormation.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
	        		lblDisplayFormation.setForeground(new Color(255,255,255));
	        		lblDisplayFormation.setBackground(new Color(80,80,80));
	        		lblDisplayFormation.setOpaque(true);
	        		
	        		lblNbModules.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
	        		lblNbModules.setForeground(new Color(255,255,255));
	        		lblNbModules.setBackground(new Color(80,80,80));
	        		lblNbModules.setOpaque(true);
	        		
	        		this.nbJours.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
	        		this.nbJours.setForeground(new Color(255,255,255));
	        		this.nbJours.setBackground(new Color(80,80,80));
	        		this.nbJours.setOpaque(true);
	        		
	        		this.lblDisplayNbJours.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
	        		this.lblDisplayNbJours.setForeground(new Color(255,255,255));
	        		this.lblDisplayNbJours.setBackground(new Color(80,80,80));
	        		this.lblDisplayNbJours.setOpaque(true);
	        		
	        		//titres.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
	        		
	        		this.pnlInfos.setBounds( (int) ( getLargeurEcran() * 0.05 ), (int) (test.getHeight() * 0.1), (int) 500, (int) (test.getHeight() * 0.8) );
	        		
	        		this.pnlInfos.setLayout(new GridLayout(0, 2, 10, (int) (this.pnlInfos.getHeight() * 0.05)));
	        		
	        		test.add(this.pnlInfos);
	        		
	               //Anciennne facon de dŽfinir les labels
	        		
	        		
	        		Date vraiDateduJour;
	        		SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");

	        		final GregorianCalendar dateDuJour = new GregorianCalendar();
	        		vraiDateduJour = dateDuJour.getTime();
	        		String DateduJour = format1.format(vraiDateduJour);
	        		int cptMois = 8;
	        		

	        		
	                for(int i = 0; i < tbl.length; ++i){
	                	JPanel mois = new JPanel();
	                	mois.setPreferredSize(new Dimension( (int) (Jscroll.getWidth() * 0.3), (int) (Jscroll.getWidth() * 0.2)));
	                	mois.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
	                	mois.setLayout(new BorderLayout());
	                	JLabel nomMois = new JLabel(tbl[i], SwingConstants.CENTER);
	                	nomMois.setFont(new Font("Arial", Font.PLAIN, 12));
	                	nomMois.setForeground(Color.WHITE);
	                	nomMois.setBackground(Color.BLUE);
	                	nomMois.setOpaque(true);
	                	
	                	mois.add(nomMois, BorderLayout.NORTH);
	                	JPanel cadreMois = new JPanel();
	                	
	                	cadreMois.setLayout(new GridLayout(0,7, 3, 3));
	                	cadreMois.setBackground(Color.WHITE);
	                	
	                	JLabel Lundi = new JLabel("Lun", SwingConstants.CENTER);
	                	
	                	JLabel Mardi = new JLabel("Mar", SwingConstants.CENTER);
	                	JLabel Mercredi = new JLabel("Mer", SwingConstants.CENTER);
	                	JLabel Jeudi = new JLabel("Jeu", SwingConstants.CENTER);
	                	JLabel Vendredi = new JLabel("Ven", SwingConstants.CENTER);
	                	JLabel Samedi = new JLabel("Sam", SwingConstants.CENTER);
	                	JLabel Dimanche = new JLabel("Dim", SwingConstants.CENTER);
	                	
	                	cadreMois.add(Lundi);
	                	cadreMois.add(Mardi);
	                	cadreMois.add(Mercredi);
	                	cadreMois.add(Jeudi);
	                	cadreMois.add(Vendredi);
	                	cadreMois.add(Samedi);
	                	cadreMois.add(Dimanche);
	                	
	        			//9 = octobre, dŽcalage au mois supŽrieur
	        			GregorianCalendar calendrier = new GregorianCalendar(2014, cptMois, 1);

	        			int nbJours = calendrier.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

	        			int premierJour = calendrier.get(GregorianCalendar.DAY_OF_WEEK);
	        			
	        			if (premierJour == 1) {
	        				premierJour = 8;
	        				System.out.println("" + premierJour);
	        			}
	        			
	        			for (int j = 1; j < nbJours + (premierJour - 1); ++j) {
	        				
	        				if (j < premierJour - 1) {
	        					JLabel lbl = new JLabel("", JLabel.CENTER);
	        					cadreMois.add(lbl);
	        				} else {
	        					JButton jour = new JButton();
	        					jour.setMargin(new Insets(0, 0, 0, 0));
	        					jour.setFont(new Font("Arial", Font.PLAIN, 9));
	                    		jour.setText("" + (j - (premierJour - 2)));
	                    		cadreMois.add(jour);
	                    		//CrŽation d'un calendrier ˆ la date du bouton
	        					final GregorianCalendar calendrier2 = new GregorianCalendar(2014, cptMois, (j - (premierJour - 2)));

	        					//DŽfinition du format utilisŽ pour extraire la date
	        					DateFormat df = new SimpleDateFormat("dd_MM_yyyy");

	        					//On rŽcup�re la date
	        					Date dateButton = calendrier2.getTime();
	        					
	        					//
	        					int week = calendrier2.get(Calendar.WEEK_OF_YEAR);
	        					
	        					//Formattage de la date voulue selon le format voulu
	        					String dateBouton = df.format(dateButton);
	        					//Le nom du boutton est celui de sa date
	        					jour.setName( dateBouton);
	        					if (calendrier2.get(calendrier2.DAY_OF_WEEK) == calendrier2.SUNDAY || calendrier2.get(calendrier2.DAY_OF_WEEK) == calendrier2.SATURDAY) {
	        						jour.setOpaque(true);
	        						jour.setBorderPainted(false);
	        						jour.setBackground(Color.BLACK);
	        						jour.setForeground(Color.WHITE);
	        						jour.addActionListener(new ActionListener() {
	        							public void actionPerformed(ActionEvent evt) {
	        								JOptionPane.showMessageDialog(null, "Les jours de weekend ne sont pas Žditables.", "Jour non Žditable", JOptionPane.WARNING_MESSAGE);
	        								//afficherModulesEnregistrŽs();
	        							}
	        						});
	        					} else {
	        						jour.setOpaque(true);
	        						jour.setBorderPainted(false);
	        						jour.addActionListener(new ActionListener() {
	        							public void actionPerformed(ActionEvent evt) {
	 
	        								createWeeks();
	        								
	        							 
	        								String buttonText = ((JButton) evt.getSource()).getName();
	        								
	        								/*
	        								JOptionPane.showMessageDialog(null, "Date du Jour: " + fm.getSemaine(calendrier2.get(Calendar.WEEK_OF_YEAR)).getJour(buttonText).getDate() );
	        								
	        								JOptionPane.showMessageDialog(null, "Semaine du Jour: " + fm.getSemaine(calendrier2.get(Calendar.WEEK_OF_YEAR)).getJour(buttonText).getSemaine() );
	        								
	        								JOptionPane.showMessageDialog(null, buttonText);
	        								JOptionPane.showMessageDialog(null, calendrier2.get(Calendar.WEEK_OF_YEAR));
*/
	        								
	        								
	        								detailSemaine semaine = new detailSemaine( calendrier2.get(Calendar.WEEK_OF_YEAR), Controleur );
	        							}
	        						});
	        					}
	        					if (dateBouton.equals(DateduJour)) { //Today

	        						jour.setBackground(Color.BLUE);

	        					}
	        				}
	                		
	                	}
	                	mois.add(cadreMois, BorderLayout.CENTER);
	                	planning.add(mois);
	                	cptMois += 1;
	                }
	                planning.setOpaque(false);
	                this.setVisible(true);

	            
	    	
		
	}
	
	// RŽcupŽrer la largeur de l'Žcran
	public static int getLargeurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public void createWeeks() {
		/* CrŽation des semaines de l'annŽe supŽrieure */
		//this.fm = new Formation("Java");
		
		DateTime dateTime1 = new DateTime( 2015, 1, 1, 0, 0, 0 );
		DateTime dateTime2 = new DateTime(2015, 8, 31, 0, 0, 0);

		int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
		int days = Days.daysBetween(dateTime1, dateTime2).getDays();

		//JOptionPane.showMessageDialog(null, weeks);
		
		for( int i = 1; i < weeks + 1; ++i){
			Semaine semaine2015 = new Semaine( "2015", i );
			//this.fm.addSemaine(Integer.toString(i), semaine2015);
			this.Controleur.getModele().getFormation().addSemaine(Integer.toString(i), semaine2015);
		}
		
	}
	
	
	
	// RŽcupŽrer la hauteur de l'Žcran
	public static int getHauteurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}