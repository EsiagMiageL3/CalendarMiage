package vue;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controleur.controleurPlanning;
import modele.Module;
import modele.modelePlanning;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;



/**
 *
 * @author nounoursmoelleux
 */
public class vueAcceuil2 extends JFrame implements ActionListener {

	/* Tableau des couleurs disponnibles pour les modules */
	private Color[] tblCouleurs = {
		Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.PINK, Color.CYAN, Color.ORANGE, Color.LIGHT_GRAY, Color.MAGENTA
	};

	private Color couleur; /* Couleur du cercle permettant de choisir la couleur du module */
	private modelePlanning modele;

	private controleurPlanning controleur; /* Controleur de la vue */
	private vuePlanning planning;
	private ArrayList < Module > lstModules;
	private int largeurConteneur, hauteurConteneur; /* Variables contenant la hauteur et largeur de la fen�tre */

	private JPanel conteneur; /* Conteneur de la JFrame, deviendra son ContentPane */

	private JButton btnOpenPlanning; /* Permet d'ouvrir un fichier de planning */
	private JLabel lblPath; /* Label affichant le chemin du fichier apr�s ouverture */
	private JButton btnSavePlanning; /* Permet de sauvegarder les modifications dans un fichier de planning */
	private JButton btnLoadPlanning; /* Bouton pour g�n�rer le planning automatiquement */
	private JButton btnCancel; /* Quitter le programme */

	private JLabel lblTitre; /* Premier label du formulaire, haut de page */
	private JLabel lblSousTitre; /* Second label du formulaire, haut de page */

	private JLabel lblFormation; /* Label juxtapos� � la saisie du nom de la formation */
	private JTextField txtFormation; /* Zone de texte permettant de saisir le nom de la formation */
	private JButton btnSaveFormation; /* Bouton qui sauvegarde la formation */

	private JPanel zoneModules; /* Panel englobant les composants relatifs aux modules */

	private JLabel lblModule; /* Label juxtapos� � la saisie du nom du module */
	private JTextField txtModule; /* Zone de texte permettant de saisir le nom du module */

	private JLabel lblAbrevModule; /* Label juxtapos� � la saisie de l'abr�viation du module */
	private JTextField txtAbrev; /* Zone de texte permettant de saisir l'abr�viation du module */

	private JLabel lblCouleur; /* Label juxtapos� au choix de la couleur du module */
	private JPanel lblCurrentColor; /* Label permettant de choisir la couleur du module */
	private JPanel pnlCouleur; /* Panel proposant les couleurs disponibles pour le module */

	private JPanel listeModules; /* Panel affichant les modules enregistr�s sous forme de Labels */
	private JLabel lblSavedModules; /* Label situ� au dessus de la liste des modules enregistr�s */
	private JScrollPane scrollModules; /* JScrollPane contenant le panel des modules enregsitr�s */
	private JButton btnSaveModule; /* Permet d'enregistrer le module */

	/*
	 * Constructeur
	 * Prend un controleur en param�tre
	 */
	public vueAcceuil2(controleurPlanning controleur) {

		this.controleur = controleur; /* Permet d'enregistrer le module */

		initComponents(); /* Cr�ation des composants de la fen�tre */

		this.setVisible(true); /* Affichage de la fen�tre */

		this.btnSaveFormation.addActionListener(this); /* Ajout des Listeners aux boutons */

	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == this.btnSaveFormation) {

			this.controleur.nouvelleFormation(this.txtFormation);

		} else if (evt.getSource() == this.btnOpenPlanning) {

			try {
				this.controleur.openPlanning();
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
							couleur = controleur.getModele().getFormation().getModules().get((String) pair.getKey()).couleurModule;
							repaint();
						}
					});
					supprModule.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {


						}
					});
					supprModule.setName("Supprimer");
					module.setText((String) pair.getKey());
					module.setName((String) pair.getKey());


					module.setPreferredSize(new Dimension(0, (int)(hauteurConteneur * 0.1)));
					supprModule.setPreferredSize(new Dimension(0, (int)(hauteurConteneur * 0.1)));

					if (o == 0) {
						module.setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
					} else if (o == (this.controleur.getModele().getFormation().getModules().size() * 2) - 2) {
						module.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
					} else {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
					}
					this.listeModules.add(module);
					this.listeModules.add(supprModule);
					o += 2;
				}

				if (this.controleur.getModele().getFormation().getModules().size() > 1) {
					this.scrollModules.setBounds((int)(largeurConteneur * 0.52), (int)(hauteurConteneur * 0.575), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.1) * 2);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (evt.getSource() == this.btnCancel) {
			System.exit(0);
		} else if (evt.getSource() == this.btnSaveModule) {
			
			this.controleur.nouveauModule(this.txtModule.getText(), couleur);
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
					module.setPreferredSize(new Dimension(50, (int)(50)));
					supprModule.setPreferredSize(new Dimension(50, (int)(50)));


					this.listeModules.add(module);
					this.listeModules.add(supprModule);
					this.listeModules.setOpaque(true);

					if (o == 0) {
						module.setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
					} else if (o == (this.controleur.getModele().getFormation().getModules().size() * 2) - 2) {
						module.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
						supprModule.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
					} else {
						module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
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
						module.setPreferredSize(new Dimension(50, (int)(50)));
						supprModule.setPreferredSize(new Dimension(50, (int)(50)));

						this.listeModules.add(module);
						this.listeModules.add(supprModule);
						this.listeModules.setOpaque(true);

						if (o == 0) {
							module.setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
							supprModule.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
						} else if (o == (this.controleur.getModele().getFormation().getModules().size() * 2) - 2) {
							module.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
							supprModule.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
						} else {
							module.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
							supprModule.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
						}
					}
				}
				o += 2;
			}
			validate();
			if (this.controleur.getModele().getFormation().getModules().size() > 1) {
				this.scrollModules.setBounds((int)(largeurConteneur * 0.52), (int)(hauteurConteneur * 0.575), (int)(largeurConteneur * 0.36), (int)(hauteurConteneur * 0.1) * 2);
			}
			
		} else if (evt.getSource() == this.btnSavePlanning) {
			try {
				this.controleur.savePlanning();
				JOptionPane.showMessageDialog(null, "Modification enregistr�es sur le disque youpi !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.planning = new vuePlanning(this.modele);
			this.modele.addPlanning(this.planning);
		}

	}

	public String nomFormation() {
		return this.txtFormation.getText();
	}

	private void initComponents() {

		/* D�finition de la taille et de la position de la fen�tre principale */
		this.setSize((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 0.75), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height * 0.75));

		this.setDefaultCloseOperation(EXIT_ON_CLOSE); /* La fermeture de la fen�tre entraine la fermeture du programme */
		this.setUndecorated(true); /* Suppression de la barre de titre de la fen�tre */
		this.setLocationRelativeTo(null); /* Permet de positionner la fen�tre au centre de l'�cran */

		this.btnCancel = new JButton("Quitter");
		this.btnSaveFormation = new JButton("Enregistrer");
		this.btnOpenPlanning = new JButton("Ouvrir un fichier");
		this.btnLoadPlanning = new JButton("G�n�rer le planning");
		this.btnSaveModule = new JButton("Enregistrer le module");
		this.btnSavePlanning = new JButton("Enregistrer sur le disque");

		this.lblPath = new JLabel("", JLabel.CENTER);
		this.lblPath.setHorizontalAlignment(JLabel.LEFT);
		this.lblCouleur = new JLabel("Couleur", JLabel.CENTER);
		this.lblAbrevModule = new JLabel("Abr�viation", JLabel.CENTER);
		this.lblSavedModules = new JLabel("Modules enregistr�s", JLabel.CENTER);

		this.lblSousTitre = new JLabel("Pour commencer, vous pouvez soit ouvrir un fichier de planning existant, soit cr�er une nouvelle formation.", JLabel.CENTER);
		this.lblFormation = new JLabel("Nom de la formation", JLabel.CENTER);
		this.lblModule = new JLabel("Nom du module", JLabel.CENTER);
		this.lblTitre = new JLabel("Bienvenue sur votre outil de gestion de planning", JLabel.CENTER);

		/*
		 * Instanciation du conteneur de la fen�tre
		 * R�ecriture de la m�thode paint component afin d'y ajouter un d�grad� de couleurs
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
		this.conteneur.setSize(this.getSize()); /* Taille du conteneur �gale � celle de la fen�tre */
		this.setContentPane(this.conteneur); /* Le conteneur devient le ContentPane de la fen�tre principale */
		largeurConteneur = this.conteneur.getWidth(); /* Affection de la largeur du conteneur � la variable */
		hauteurConteneur = this.conteneur.getHeight(); /* Affection de la hauteur du conteneur � la variable */


		/*
		 * Instanciation du Panel de s�lection de la couleur du module
		 * R�ecriture de la m�thode paintComponent pour l'afficher en tant que cercle
		 * Cercle vide si la variable "couleur" est nulle
		 * Cercle plein si cette derni�re ne l'est pas
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

		this.lblAbrevModule.setBounds((int)(largeurConteneur * 0.1 + (hauteurConteneur * 0.46 - hauteurConteneur * 0.45)), (int)(hauteurConteneur * 0.55), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.lblAbrevModule.setOpaque(false);
		this.lblAbrevModule.setForeground(Color.WHITE);
		this.conteneur.add(this.lblAbrevModule);


		this.lblCouleur.setBounds((int)(largeurConteneur * 0.1 + (hauteurConteneur * 0.46 - hauteurConteneur * 0.45)), (int)(hauteurConteneur * 0.64), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.lblCouleur.setOpaque(false);
		this.lblCouleur.setForeground(Color.WHITE);
		this.conteneur.add(this.lblCouleur);

		this.txtModule.setBounds((int)(largeurConteneur * 0.27), (int)(hauteurConteneur * 0.46), (int)(largeurConteneur * 0.2), (int)(hauteurConteneur * 0.075));

		this.txtModule.setOpaque(false);
		this.txtModule.setForeground(Color.BLACK);
		this.conteneur.add(this.txtModule);

		this.lblCurrentColor.setBounds((int)(this.txtModule.getX()), (int)(hauteurConteneur * 0.64), (int)(hauteurConteneur * 0.075), (int)(hauteurConteneur * 0.075));
		this.lblCurrentColor.setLayout(null);
		this.lblCurrentColor.setOpaque(false);
		this.lblCurrentColor.setToolTipText("Cliquer pour afficher les couleurs disponibles");

		/* Permet d'afficher ou non le Panel de choix de la couleur */
		this.lblCurrentColor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!pnlCouleur.isVisible()) {
					pnlCouleur.setVisible(true);
					lblCurrentColor.setToolTipText("Cliquer pour masquer les couleurs");
				} else {
					pnlCouleur.setVisible(false);
					lblCurrentColor.setToolTipText("Cliquer pour afficher les couleurs disponibles");
				}
			}
		});
		/* Fin permet d'afficher ou non le Panel de choix de la couleur */

		this.conteneur.add(this.lblCurrentColor); /* Ajout du Label au conteneur*/

		this.pnlCouleur = new JPanel(new GridLayout(3, 3));
		this.pnlCouleur.setBounds((int)((this.txtModule.getX() + this.txtModule.getWidth()) - (int)(hauteurConteneur * 0.21)), (int)(hauteurConteneur * 0.64), (int)(hauteurConteneur * 0.21), (int)(hauteurConteneur * 0.21) + 1);
		this.pnlCouleur.setVisible(false);
		this.pnlCouleur.setBackground(new Color(80, 80, 80));
		this.pnlCouleur.setOpaque(false);

		for (int i = 0; i < 9; ++i) {

			final Color couleurEnCour = tblCouleurs[i]; /* La couleur du Panel proposant la couleur prend la valeur du tableau de couleurs */

			JPanel lblChxCouleur = new JPanel() {@Override
				protected void paintComponent(Graphics g) {
					g.setColor(couleurEnCour);
					g.fillOval(0, 0, (int)(hauteurConteneur * 0.069), (int)(hauteurConteneur * 0.069));
				}
			};

			lblChxCouleur.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					couleur = couleurEnCour;
					repaint();
				}
			});
			pnlCouleur.add(lblChxCouleur);
		}

		this.conteneur.add(this.pnlCouleur);

		this.txtAbrev.setBounds((int)(largeurConteneur * 0.27), (int)(hauteurConteneur * 0.55), (int)(largeurConteneur * 0.2), (int)(hauteurConteneur * 0.075));
		this.txtAbrev.setOpaque(false);
		this.txtAbrev.setForeground(Color.BLACK);
		this.conteneur.add(this.txtAbrev);


		this.btnSaveModule.setBounds((int) this.lblModule.getX(), (int)(hauteurConteneur * 0.7), (int)(largeurConteneur * 0.15), (int)(hauteurConteneur * 0.075));
		this.btnSaveModule.addActionListener(this);
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

}