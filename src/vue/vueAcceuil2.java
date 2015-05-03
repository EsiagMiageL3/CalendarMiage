package src.vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import src.controleur.controleurPlanning;
import src.modele.Module;
import src.modele.modelePlanning;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;



/**
 *
 * @author nounoursmoelleux
 */
public class vueAcceuil2 extends JFrame implements ActionListener, FocusListener, MouseListener {
//
	/* Tableau des couleurs disponnibles pour les modules */
	private Color[] tblCouleurs = {
		new Color(25, 25, 25), new Color(198, 61, 15), new Color(0, 90, 49), new Color(85, 140, 137), new Color(222, 27, 27), new Color(125, 25, 53), new Color(88, 88, 88), new Color(255, 240, 86), new Color(126, 143, 124),
		new Color(235, 101, 160), new Color(63, 40, 96), new Color(52, 40, 0)		
	};

	private Color couleur; /* Couleur du cercle permettant de choisir la couleur du module */
	private modelePlanning modele;

	private controleurPlanning controleur; /* Controleur de la vue */
	private vuePlanning planning;
	private ArrayList < Module > lstModules;
	private int largeurConteneur, hauteurConteneur; /* Variables contenant la hauteur et largeur de la fenêtre */

	private JPanel conteneur; /* Conteneur de la JFrame, deviendra son ContentPane */

	private JButton btnOpenPlanning; /* Permet d'ouvrir un fichier de planning */
	private JLabel lblPath; /* Label affichant le chemin du fichier après ouverture */
	private JButton btnSavePlanning; /* Permet de sauvegarder les modifications dans un fichier de planning */
	private JButton btnLoadPlanning; /* Bouton pour générer le planning automatiquement */
	private JButton btnCancel; /* Quitter le programme */

	private JLabel lblTitre; /* Premier label du formulaire, haut de page */
	private JLabel lblSousTitre; /* Second label du formulaire, haut de page */

	private JLabel lblFormation; /* Label juxtaposé à la saisie du nom de la formation */
	private JTextField txtFormation; /* Zone de texte permettant de saisir le nom de la formation */
	private JButton btnSaveFormation; /* Bouton qui sauvegarde la formation */

	private JPanel zoneModules; /* Panel englobant les composants relatifs aux modules */

	private JLabel lblModule; /* Label juxtaposé à la saisie du nom du module */
	private JTextField txtModule; /* Zone de texte permettant de saisir le nom du module */

	private JLabel lblAbrevModule; /* Label juxtaposé à la saisie de l'abréviation du module */
	private JTextField txtAbrev; /* Zone de texte permettant de saisir l'abréviation du module */

	private JLabel lblCouleur; /* Label juxtaposé au choix de la couleur du module */
	private JPanel lblCurrentColor; /* Label permettant de choisir la couleur du module */
	private JPanel pnlCouleur; /* Panel proposant les couleurs disponibles pour le module */

	private JLabel lblNbSeances; /* Label juxtaposé à la saisie du nombre de séances */
	private JTextField txtNbSeances; /* Zone de texte permettant de saisir le nombre de séances maximal du module */
	
	private JPanel listeModules; /* Panel affichant les modules enregistrés sous forme de Labels */
	private JLabel lblSavedModules; /* Label situé au dessus de la liste des modules enregistrés */
	
	private JScrollPane scrollModules; /* JScrollPane contenant le panel des modules enregsitrés */
	private JScrollPane scrollColors; /* JScrollPane contenant le panel des couleurs à choisir */
	
	private JButton btnSaveModule; /* Permet d'enregistrer le module */

	/*
	 * Constructeur
	 * Prend un controleur en paramètre
	 */
	public vueAcceuil2(controleurPlanning controleur) {

		this.controleur = controleur; /* Permet d'enregistrer le module */

		initComponents(); /* Création des composants de la fenêtre */

		this.setVisible(true); /* Affichage de la fenêtre */

	    this.txtModule.addMouseListener(this); /* Ajout du Listener de focus à la zone de saisie du nom de module */
	    this.txtModule.addFocusListener(this);
	}

	@Override
	public void focusLost(FocusEvent e) {

		
	}
	
	@Override
	public void focusGained(FocusEvent e){ 
		validate();
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.txtModule){
			if( this.controleur.getModele().getFormation() == null ){
				JOptionPane.showMessageDialog(null, "Veillez à créer une formation en premier lieu.");
				this.txtFormation.requestFocus();
			}
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == this.btnSaveFormation) {
			
			/* Cas où le nom de la formation est incorrect */
			if( !this.controleur.nouvelleFormation(this.txtFormation) ){

				this.txtFormation.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
				this.txtFormation.setOpaque(true);
				
			}
			
			this.btnSaveFormation.setText("Modifier"); /* Le bouton permet désormais de modifier le nom de la formation et non d'en créer une nouvelle */
			this.btnSaveModule.setEnabled(true); /* On 	active le bouton permettant d'enregistrer les modules */
			
		} else if (evt.getSource() == this.btnOpenPlanning) {
			
			try {
				this.controleur.openPlanning();
				
				this.initSaisie();
				this.txtFormation.setText(this.controleur.getModele().getFormation().nom_f);
				repaint();
				this.lblPath.setText(this.controleur.getChemin());

				Iterator it = this.controleur.getModele().getFormation().getModules().entrySet().iterator();

				int o = 0;
				while (it.hasNext()) {
					final Map.Entry pair = (Map.Entry) it.next();

					JLabel module = new JLabel("BOUH!", JLabel.CENTER);
					JLabel supprModule = new JLabel("Supprimer", JLabel.CENTER);
					module.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							txtModule.setText(controleur.getModele().getFormation().getModules().get((String) pair.getKey()).nom_m);
							txtAbrev.setText(controleur.getModele().getFormation().getModules().get((String) pair.getKey()).abreviation);
							couleur = controleur.getModele().getFormation().getModules().get((String) pair.getKey()).couleurModule;
							txtNbSeances.setText( Integer.toString( controleur.getModele().getFormation().getModules().get((String) pair.getKey()).nb_seances ) );
							repaint();
						}
					});
					
					supprModule.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Hashtable supprModules = new Hashtable();
							
							if( controleur.supprModule( (String) pair.getKey() ) ){
								
								Component[] tblSupprModules = listeModules.getComponents();
								
								for (int i = 0; i < tblSupprModules.length; ++i) {
									supprModules.put(((String) tblSupprModules[i].getName()), tblSupprModules[i]);
								}
								
								removeBTN(supprModules, (Component) e.getSource(), (String) pair.getKey() );
								
							}
							
							
						}
					});
					
					supprModule.setName("Supprimer");
					module.setText((String) pair.getKey());
					module.setName((String) pair.getKey());


					module.setPreferredSize(new Dimension(0, (int)(hauteurConteneur * 0.1)));
					supprModule.setPreferredSize(new Dimension(0, (int)(hauteurConteneur * 0.1)));

					if (o == 0) {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					} else if (o == (this.controleur.getModele().getFormation().getModules().size() * 2) - 2) {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					} else {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					}
					this.listeModules.add(module);
					this.listeModules.add(supprModule);
					o += 2;
				}
				
				this.btnSaveModule.setEnabled(true);

				if (this.controleur.getModele().getFormation().getModules().size() > 1) {
					this.scrollModules.setBounds((int)(largeurConteneur * 0.52), (int)(hauteurConteneur * 0.575), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.1) * 2);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (evt.getSource() == this.btnCancel) {
			System.exit(0);
		} else if (evt.getSource() == this.btnSaveModule) {
			
			if( !this.txtModule.getText().trim().equals("") ){
				
				if( this.controleur.checkNomModule( this.txtModule.getText().trim() ) ){
					this.nouveauModule();
					this.txtModule.setText("");
				}
				else{
					int reponse = JOptionPane.showConfirmDialog(null,"Ce nom de module exite déjà.\n" + "Les données concernant ce module seront modifiés.\n" + "Souhaitez-vous continuer ?", "Module existant", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if( reponse == JOptionPane.YES_NO_OPTION){
						this.controleur.nouveauModule( this.txtModule.getText(), this.txtAbrev.getText(), couleur, Integer.parseInt(this.txtNbSeances.getText()) );
						JOptionPane.showMessageDialog(null, "Modifications enregistrés.");
					}
					
				}
			}
			else{
				
				JOptionPane.showMessageDialog(null, "Le nom du module ne peut être vide.", "Nom de module incorrect", JOptionPane.WARNING_MESSAGE);
				this.couleur = null;
				repaint();
				
			}
			
		} else if (evt.getSource() == this.btnSavePlanning) {
			try {
				this.controleur.savePlanning();
				JOptionPane.showMessageDialog(null, "Modification enregistrées sur le disque youpi !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.planning = new vuePlanning(this.modele);
			this.modele.addPlanning(this.planning);
		}

	}

	public void removeBTN(Hashtable mp, Component c, String key){
		listeModules.remove( (Component) mp.get( key ) );
		listeModules.remove( c ); 
		if (this.controleur.getModele().getFormation().getModules().size() == 1) {
			this.scrollModules.setBounds((int)(largeurConteneur * 0.52), (int)(hauteurConteneur * 0.575), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.1) );

		}
		this.txtModule.requestFocus();
	}

	public String nomFormation() {
		return this.txtFormation.getText();
	}

	private void initComponents() {

		/* Définition de la taille et de la position de la fenêtre principale */
		this.setSize((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 0.75), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height * 0.75));

		this.setDefaultCloseOperation(EXIT_ON_CLOSE); /* La fermeture de la fenêtre entraine la fermeture du programme */
		this.setUndecorated(true); /* Suppression de la barre de titre de la fenêtre */
		this.setLocationRelativeTo(null); /* Permet de positionner la fenêtre au centre de l'écran */

		this.btnCancel = new JButton("Quitter");
		this.btnSaveFormation = new JButton("Enregistrer");
		this.btnOpenPlanning = new JButton("Ouvrir un fichier");
		this.btnLoadPlanning = new JButton("Générer le planning");
		this.btnSaveModule = new JButton("Sauvegarder");
		this.btnSavePlanning = new JButton("Enregistrer sur le disque");

		this.lblPath = new JLabel("", JLabel.CENTER);
		this.lblPath.setHorizontalAlignment(JLabel.LEFT);
		this.lblCouleur = new JLabel("Couleur", JLabel.CENTER);
		this.lblAbrevModule = new JLabel("Abréviation", JLabel.CENTER);
		this.lblSavedModules = new JLabel("Modules enregistrés", JLabel.CENTER);

		this.lblSousTitre = new JLabel("Pour commencer, vous pouvez soit ouvrir un fichier de planning existant, soit créer une nouvelle formation.", JLabel.CENTER);
		this.lblFormation = new JLabel("Nom de la formation", JLabel.CENTER);
		this.lblModule = new JLabel("Nom du module", JLabel.CENTER);
		this.lblTitre = new JLabel("Bienvenue sur votre outil de gestion de planning", JLabel.CENTER);

		/*
		 * Instanciation du conteneur de la fenêtre
		 * Réecriture de la méthode paint component afin d'y ajouter un dégradé de couleurs
		 */
		this.conteneur = new JPanel() {@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
				Color color1 = new Color(93, 70, 96);
				Color color2 = new Color(247, 238, 199);
				GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};


		this.conteneur.setLayout(null); /* Affectation d'un layout null au ContentPane afin de positionner les composants comme voulu */
		this.conteneur.setOpaque(false); /* Le ContentPane sera transparent */
		this.conteneur.setSize(this.getSize()); /* Taille du conteneur égale à celle de la fenêtre */
		this.setContentPane(this.conteneur); /* Le conteneur devient le ContentPane de la fenêtre principale */
		largeurConteneur = this.conteneur.getWidth(); /* Affection de la largeur du conteneur à la variable */
		hauteurConteneur = this.conteneur.getHeight(); /* Affection de la hauteur du conteneur à la variable */


		/*
		 * Instanciation du Panel de sélection de la couleur du module
		 * Réecriture de la méthode paintComponent pour l'afficher en tant que cercle
		 * Cercle vide si la variable "couleur" est nulle
		 * Cercle plein si cette dernière ne l'est pas
		 */
		this.lblCurrentColor = new JPanel() {@Override
			protected void paintComponent(Graphics g) {
				if (couleur != null) {
					g.setColor(couleur);
					g.fillOval(0, 0, (int)(hauteurConteneur * 0.07), (int)(hauteurConteneur * 0.07));
				} else {
					g.drawOval(0, 0, (int)(hauteurConteneur * 0.07), (int)(hauteurConteneur * 0.07));
				}

			}
		};

		this.zoneModules = new JPanel();
		this.zoneModules.setLayout(null);

		this.listeModules = new JPanel(new GridLayout(0, 2));
		this.scrollModules = new JScrollPane();

		this.txtFormation = new JTextField(JTextField.CENTER);
		this.txtFormation.setHorizontalAlignment(JTextField.CENTER);

		this.txtAbrev = new JTextField();
		this.txtModule = new JTextField();
		this.txtModule.setHorizontalAlignment(JTextField.CENTER);


		this.lblTitre.setBounds((int)(largeurConteneur * 0.05), (int)(hauteurConteneur * 0.025), (int)(largeurConteneur * 0.9), (int)(hauteurConteneur * 0.0875));
		this.lblTitre.setOpaque(true);
		this.lblTitre.setBackground(new Color(80, 80, 80));
		this.lblTitre.setForeground(Color.WHITE);
		this.lblTitre.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.conteneur.add(this.lblTitre);


		this.lblSousTitre.setBounds((int)(largeurConteneur * 0.1), (int)(hauteurConteneur * 0.12), (int)(largeurConteneur * 0.8), (int)(hauteurConteneur * 0.07));
		this.lblSousTitre.setOpaque(true);
		this.lblSousTitre.setBackground(new Color(80, 80, 80));
		this.lblSousTitre.setForeground(Color.WHITE);
		this.lblSousTitre.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.conteneur.add(this.lblSousTitre);


		this.lblFormation.setBounds((int)(largeurConteneur * 0.1), (int)(hauteurConteneur * 0.325), (int)(largeurConteneur * 0.2), (int)(hauteurConteneur * 0.075));
		this.lblFormation.setOpaque(false);
		this.lblFormation.setForeground(Color.WHITE);
		this.lblFormation.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		this.conteneur.add(this.lblFormation);

		this.txtFormation.setBounds((int)(largeurConteneur * 0.32), (int)(hauteurConteneur * 0.325), (int)(largeurConteneur * 0.2), (int)(hauteurConteneur * 0.075));
		this.txtFormation.setOpaque(false);
		this.txtFormation.setForeground(Color.BLACK);
		this.conteneur.add(this.txtFormation);

		this.zoneModules.setBounds((int)(largeurConteneur * 0.1), (int)(hauteurConteneur * 0.45), (int)(largeurConteneur * 0.8), (int)(hauteurConteneur * 0.4));
		this.zoneModules.setOpaque(false);
		this.zoneModules.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));

		this.lblSavedModules.setBounds((int)(largeurConteneur * 0.52), (int)(hauteurConteneur * 0.5), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.05));
		this.lblSavedModules.setOpaque(false);
		this.lblSavedModules.setForeground(Color.BLACK);
		this.lblSavedModules.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		this.conteneur.add(this.lblSavedModules);

		this.scrollModules.setBounds((int)(largeurConteneur * 0.52), (int)(hauteurConteneur * 0.575), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.1));
		this.listeModules.setOpaque(false);
		this.scrollModules.setViewportView(this.listeModules);
		this.scrollModules.setOpaque(false);
		this.scrollModules.setBackground(new Color(80, 80, 80));
		this.scrollModules.getViewport().setOpaque(false);
		this.scrollModules.setBorder(null);
		conteneur.add(this.scrollModules);

		this.lblModule.setBounds((int)(largeurConteneur * 0.1 + (hauteurConteneur * 0.46 - hauteurConteneur * 0.45)), (int)(hauteurConteneur * 0.46), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.lblModule.setOpaque(false);
		this.lblModule.setForeground(Color.WHITE);
		this.conteneur.add(this.lblModule);

		this.txtModule.setBounds((int)(largeurConteneur * 0.27), (int)(hauteurConteneur * 0.46), (int)(largeurConteneur * 0.2), (int)(hauteurConteneur * 0.075));
		this.txtModule.setOpaque(false);
		this.txtModule.setForeground(Color.BLACK);
		this.txtModule.addFocusListener(this);
		this.conteneur.add(this.txtModule);
		
		this.lblAbrevModule.setBounds((int)(largeurConteneur * 0.1 + (hauteurConteneur * 0.46 - hauteurConteneur * 0.45)), (int)(hauteurConteneur * 0.55), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.lblAbrevModule.setOpaque(false);
		this.lblAbrevModule.setForeground(Color.WHITE);
		this.conteneur.add(this.lblAbrevModule);

		this.txtAbrev.setBounds((int)(largeurConteneur * 0.27), (int)(hauteurConteneur * 0.55), (int)(largeurConteneur * 0.2), (int)(hauteurConteneur * 0.075));
		this.txtAbrev.setOpaque(false);
		this.txtAbrev.setForeground(Color.BLACK);
		this.txtAbrev.addFocusListener(this);
		this.conteneur.add(this.txtAbrev);

		this.lblCouleur.setBounds((int)(largeurConteneur * 0.1 + (hauteurConteneur * 0.46 - hauteurConteneur * 0.45)), (int)(hauteurConteneur * 0.64), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.lblCouleur.setOpaque(false);
		this.lblCouleur.setForeground(Color.WHITE);
		this.conteneur.add(this.lblCouleur);
		
		this.lblCurrentColor.setBounds((int)(this.txtModule.getX()), (int)(hauteurConteneur * 0.64), (int)(hauteurConteneur * 0.075), (int)(hauteurConteneur * 0.075));
		this.lblCurrentColor.setLayout(null);
		this.lblCurrentColor.setOpaque(false);
		this.lblCurrentColor.setToolTipText("Cliquer pour afficher les couleurs disponibles");

		/* Permet d'afficher ou non le Panel de choix de la couleur */
		this.lblCurrentColor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!pnlCouleur.isVisible()) {
					btnSaveModule.setVisible(false);
					pnlCouleur.setVisible(true);
					scrollColors.setVisible(true);
					lblCurrentColor.setToolTipText("Cliquer pour masquer les couleurs");
				} else {
					
					pnlCouleur.setVisible(false);
					scrollColors.setVisible(false);
					lblCurrentColor.setToolTipText("Cliquer pour afficher les couleurs disponibles");
					btnSaveModule.setVisible(true);
				}
			}
		});
		/* Fin permet d'afficher ou non le Panel de choix de la couleur */

		this.conteneur.add(this.lblCurrentColor); /* Ajout du Label au conteneur*/

		this.pnlCouleur = new JPanel(new GridLayout(0, 3));
		this.pnlCouleur.setVisible(false);
		this.pnlCouleur.setOpaque(false);
		
		initColorPane();
		/*
		for (int i = 0; i < this.tblCouleurs.length; ++i) {

			final Color couleurEnCour = tblCouleurs[i];
			JLabel lblChxCouleur = new JLabel() {@Override
				protected void paintComponent(Graphics g) {
					g.setColor(couleurEnCour);
					g.fillOval(0, 0, (int)(hauteurConteneur * 0.069), (int)(hauteurConteneur * 0.069));
				}
			
			};
			lblChxCouleur.setPreferredSize( new Dimension((int)(hauteurConteneur * 0.070), (int)(hauteurConteneur * 0.070)));
			
			lblChxCouleur.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if( controleur.checkCouleurPrise(couleurEnCour) ){
						
						couleur = couleurEnCour;
						repaint();
					}

				}
			});
			
			pnlCouleur.add(lblChxCouleur);
		}
*/
		this.scrollColors = new JScrollPane();
		this.scrollColors.setVisible(false);
		this.scrollColors.setBounds((int)((this.txtModule.getX() + this.txtModule.getWidth()) - (int)(hauteurConteneur * 0.21)), (int)(hauteurConteneur * 0.64), (int)(hauteurConteneur * 0.21) + this.scrollColors.getVerticalScrollBar().getPreferredSize().width, (int) ( (hauteurConteneur * 0.21)  / 1.5 ));
		this.scrollColors.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollColors.setBorder(null);
		this.scrollColors.setOpaque(false);
		this.scrollColors.setViewportView(this.pnlCouleur);
		this.scrollColors.getViewport().setOpaque(false);
		this.conteneur.add(this.scrollColors);
		
		this.lblNbSeances = new JLabel("Nombre de séances", JLabel.CENTER);
		this.lblNbSeances.setBounds((int)(largeurConteneur * 0.1 + (hauteurConteneur * 0.46 - hauteurConteneur * 0.45)), (int)(hauteurConteneur * 0.73), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.lblNbSeances.setOpaque(false);
		this.lblNbSeances.setForeground(Color.WHITE);
		this.conteneur.add(this.lblNbSeances);

		this.txtNbSeances = new JTextField( JTextField.CENTER );
		this.txtNbSeances.setHorizontalAlignment( JTextField.CENTER );
		this.txtNbSeances.setBounds((int)(largeurConteneur * 0.27), (int)(hauteurConteneur * 0.73), (int)(hauteurConteneur * 0.075), (int)(hauteurConteneur * 0.075));
		this.txtNbSeances.setOpaque(false);
		this.txtNbSeances.setForeground(Color.BLACK);
		this.txtNbSeances.addFocusListener(this);
		this.conteneur.add(this.txtNbSeances);
		
		this.btnSaveModule.setBounds( (int)(largeurConteneur * 0.29) + this.txtNbSeances.getWidth(), (int)(hauteurConteneur * 0.73), (int) ( this.txtModule.getWidth() - ( ( (largeurConteneur * 0.29) + this.txtNbSeances.getWidth() ) - this.txtNbSeances.getX() ) ), (int)(hauteurConteneur * 0.075));
		this.btnSaveModule.addActionListener(this);
		this.btnSaveModule.setEnabled(false);
		this.conteneur.add(this.btnSaveModule);

		conteneur.add(this.zoneModules);

		this.btnCancel.setBounds((int)(largeurConteneur * 0.875), (int)(hauteurConteneur * 0.925 - (largeurConteneur - largeurConteneur * 0.975)), (int)(largeurConteneur * 0.1), (int)(hauteurConteneur * 0.075));
		this.btnCancel.addActionListener(this);
		this.conteneur.add(this.btnCancel);

		this.btnSaveFormation.setBounds((int)(largeurConteneur * 0.53), (int)(hauteurConteneur * 0.325), (int)(largeurConteneur * 0.1), (int)(hauteurConteneur * 0.075));
		this.btnSaveFormation.addActionListener(this);
		this.conteneur.add(this.btnSaveFormation);

		this.btnOpenPlanning.setBounds((int)(largeurConteneur * 0.425), (int)(hauteurConteneur * 0.22), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.btnOpenPlanning.addActionListener(this);
		this.conteneur.add(this.btnOpenPlanning);

		this.btnSavePlanning.setBounds((int)(largeurConteneur * 0.3), (int)(hauteurConteneur * 0.925 - (largeurConteneur - largeurConteneur * 0.975)), (int)(largeurConteneur * 0.18), (int)(hauteurConteneur * 0.075));
		this.btnSavePlanning.addActionListener(this);
		this.conteneur.add(this.btnSavePlanning);

		this.btnLoadPlanning.setBounds((int)(largeurConteneur * 0.1), (int)(hauteurConteneur * 0.925 - (largeurConteneur - largeurConteneur * 0.975)), (int)(largeurConteneur * 0.18), (int)(hauteurConteneur * 0.075));
		this.btnLoadPlanning.addActionListener(this);
		this.conteneur.add(this.btnLoadPlanning);

		this.lblPath.setBounds((int)(largeurConteneur * 0.59), (int)(hauteurConteneur * 0.22), (int)(largeurConteneur * 0.35), (int)(hauteurConteneur * 0.075));
		this.lblPath.setOpaque(false);
		this.lblPath.setBackground(new Color(80, 80, 80));
		this.lblPath.setForeground(Color.BLACK);
		this.conteneur.add(this.lblPath);

	}

	public void nouveauModule(){

		this.controleur.nouveauModule( this.txtModule.getText(), this.txtAbrev.getText(), couleur, Integer.parseInt(this.txtNbSeances.getText()) );
		Hashtable hashModules = new Hashtable();
		Iterator it = this.controleur.getModele().getFormation().getModules().entrySet().iterator();

		int o = 0;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			if (this.listeModules.getComponentCount() == 0) {


				JLabel module = new JLabel((String) pair.getKey(), JLabel.CENTER);
				module.setName((String) pair.getKey());

				JLabel supprModule = new JLabel("Supprimer", JLabel.CENTER);
				supprModule.setName("Supprimer");

				module.setPreferredSize( new Dimension(0, (int)(hauteurConteneur * 0.1) ) );
				supprModule.setPreferredSize(new Dimension(0, (int)(hauteurConteneur * 0.1) ) );


				this.listeModules.add(module);
				this.listeModules.add(supprModule);
				this.listeModules.setOpaque(true);

				if (o == 0) {
					module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
					supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
				} else if (o == (this.controleur.getModele().getFormation().getModules().size() * 2) - 2) {
					module.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
					supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
				} else {
					module.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
					supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
				}
			} else {

				Component[] tblModules = this.listeModules.getComponents();
				
				for (int i = 0; i < tblModules.length; ++i) {
					hashModules.put(((String) tblModules[i].getName()), "");
				}

				if (!hashModules.containsKey((String) pair.getKey())) {

					JLabel module = new JLabel((String) pair.getKey(), JLabel.CENTER);
					module.setName((String) pair.getKey());
					JLabel supprModule = new JLabel("Supprimer", JLabel.CENTER);
					supprModule.setName((String) pair.getKey());
					module.setPreferredSize( new Dimension(0, (int)(hauteurConteneur * 0.1) ) );
					supprModule.setPreferredSize(new Dimension(0, (int)(hauteurConteneur * 0.1) ) );

					this.listeModules.add(module);
					this.listeModules.add(supprModule);
					this.listeModules.setOpaque(true);

					if (o == 0) {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					} else if (o == (this.controleur.getModele().getFormation().getModules().size() * 2) - 2) {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					} else {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					}
				}
			}
			o += 2;
		}
		couleur = null;
		repaint();
		validate();
		if (this.controleur.getModele().getFormation().getModules().size() > 1) {
			this.scrollModules.setBounds((int) (largeurConteneur * 0.52), (int)(hauteurConteneur * 0.575), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.1) * 2);
		}
	}

	/*
	 * Réinitialisation des saisie
	 */
	public void initSaisie(){
		this.txtFormation.setText("");
		this.txtModule.setText("");
		this.txtAbrev.setText("");
		this.txtNbSeances.setText("");
		this.couleur = null;
		repaint();
	}
	
	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


public void initColorPane(){
	this.pnlCouleur.removeAll();
	for (int i = 0; i < this.tblCouleurs.length; ++i) {

		final Color couleurEnCour = tblCouleurs[i]; /* La couleur du Panel proposant la couleur prend la valeur du tableau de couleurs */

		JLabel lblChxCouleur = new JLabel() {@Override
			protected void paintComponent(Graphics g) {
				g.setColor(couleurEnCour);
				g.fillOval(0, 0, (int)(hauteurConteneur * 0.069), (int)(hauteurConteneur * 0.069));
			}
		
		};
		lblChxCouleur.setPreferredSize( new Dimension((int)(hauteurConteneur * 0.070), (int)(hauteurConteneur * 0.070)));
		
		lblChxCouleur.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if( controleur.checkCouleurPrise(couleurEnCour) ){
					
					couleur = couleurEnCour;
					repaint();
				}

			}
		});
		
		pnlCouleur.add(lblChxCouleur);
	}
}






}