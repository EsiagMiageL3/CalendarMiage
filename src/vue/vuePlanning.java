package vue;

import java.awt.event.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import controleur.controleurPlanning;
import modele.Semaine;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.*;

public class vuePlanning extends JFrame {
	
	/* Represente l'annee du planning */
	private int annee;
	/* Variables contenant la hauteur et largeur de la fenetre */
	private int largeurConteneur, hauteurConteneur; 
	/* Instance de notre controleur */
	private controleurPlanning Controleur; 
	/* JPanel qui deviendra notre ContentPane */
	private JPanel conteneur;
	/* JScrollPane qui contiendra notre JPanel contenant le planning */
	private JScrollPane Jscroll;
	/* JPanel qui contiendra notre planning (mois, jours, etc...) */
	private JPanel planning;
	
	private JPanel pnlInfos;
	private JLabel lblDisplayFormation, lblDisplayNbModules, nbJours, lblDisplayNbJours;

	/* Tableau des noms de mois, utiles pour la creation des mois de l'annee */
	private static String[] tbl = { "Septembre", "Octobre", "Novembre", "Decembre", "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout" };


    
	/**
	 * Constructeur
	 * Prend en parametres notre controleur, et le numero de l'annee
	 */
	public vuePlanning( controleurPlanning Controleur, int annee) {
		
		/* Creation des composant de la fenetre */
		initComponents( annee );
		
		this.annee = annee;
		this.Controleur = Controleur;
		
		/* Creation de toutes les semaines de l'annee et de leurs jours */
		this.createWeeks( annee );
		
		/* Affichage de notre fenetre */
		this.setVisible(true);
	}

	/**
	 * Creation des different composant de la fenetre
	 * Prend en parametre l'annee de debut de la formation pour pouvoir creer dynamiquement les jours
	 *
	 */
	private void initComponents(int annee) {
		/* Definition de la taille de la fenetre (egale a celle de l'ecran en l'occurence) */
		this.setSize(getLargeurEcran(), getHauteurEcran());

		/* Creation du panel destine a devenir le futur ContentPane de notre fenetre */
		this.conteneur = new JPanel() {
			@Override
			/* Permet de lui ajouter un degrade de couleur comme decoration de fond */
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
				Color couleurDebut = new Color(93, 70, 96); 
				Color couleurFin = new Color(247, 238, 199);
				GradientPaint gp = new GradientPaint(0, 0, couleurDebut, 0, h, couleurFin);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};

		/* Le conteneur n'aura aucun LayoutManager afin de positionner librement les composant en proportion a la taille de l'ecran ( plus de detail dans le compte rendu ) */
		this.conteneur.setLayout(null);
		
		/* Le JPanel "conteneur" devient le ContentPane de la fenetre */
		this.setContentPane( this.conteneur );
		/* Permet de supprimer la barre de titre avec les boutons de fermeture, réduction et agrandissement de la fenêtre */
		this.setUndecorated(true);
		largeurConteneur = this.getWidth(); /* Affection de la largeur du conteneur a la variable */
		hauteurConteneur = this.getHeight(); /* Affection de la hauteur du conteneur a la variable */
		
		/* Creation du JPanel qui contiendra les mois du planning */
		this.planning = new JPanel();
		
		/* Choix d'un GridLayout de 3 colonnes, le nombre de lignes sera deduit => 3 mois par ligne */
		this.planning.setLayout(new GridLayout(0, 3, 10, 10));

		/* Creation du JScrollPane qui contiendra le JPanel contennant le planning */
		this.Jscroll = new JScrollPane();
		
		/* Definition de sa taille et positions en pourcentages de la taille de l'ecran */
		this.Jscroll.setBounds((int)(largeurConteneur * 0.01), (int)(hauteurConteneur * 0.05), (int)(largeurConteneur * 0.9), (int)(hauteurConteneur * 0.9));

		/* Le JPanel contenant le planning devient le ViewportView de notre JScrollPane */
		this.Jscroll.setViewportView( this.planning );
		
		/* Le JScrollPane sera transparent */
		this.Jscroll.setOpaque(false);
		
		/* Le ViewPort sera transparent egalement */
		this.Jscroll.getViewport().setOpaque(false);
		
		/* Pas de bordures*/
		this.Jscroll.setBorder(null);	
		
		/* On ajoute le JScrollPane au ContentPane */
		this.conteneur.add( this.Jscroll );
		        
		JLabel settings = new JLabel( "\u2699", JLabel.CENTER);

		settings.setOpaque(false);
		settings.setForeground(Color.WHITE);
		settings.setBounds((int)(largeurConteneur * 0.92), (int)(hauteurConteneur * 0.45), (int)(hauteurConteneur * 0.1), (int)(hauteurConteneur * 0.1));

		settings.setFont(new Font("Arial", Font.PLAIN, 42));
		settings.setHorizontalAlignment(JLabel.CENTER);

		this.conteneur.add(settings);

		final JPanel sideBar = new JPanel(new GridLayout(10, 1, 20, 0));
		sideBar.setOpaque(true);
		sideBar.setBounds((int)(largeurConteneur + 1), 0, (int)(largeurConteneur * 0.15), (int)(hauteurConteneur));
		sideBar.setBackground(new Color(80, 80, 80));

		JLabel iconYear = new JLabel("\uD83D\uDCC5", JLabel.CENTER);
		iconYear.setVisible(true);
		iconYear.setOpaque(false);
		iconYear.setFont(new Font("Symbola", Font.PLAIN, 35));

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

		this.conteneur.add(sideBar);

		settings.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				TimerTask task = new TimerTask() {

					int i = 0;
					int positionDepart = sideBar.getX();
					int destination = largeurConteneur - sideBar.getWidth();

					public void run() {
						if (sideBar.getX() > destination) {
							i += 1;
							sideBar.setLocation((positionDepart) - i, 0);
						} else {
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
//
		Date vraiDateduJour;
		SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");

		final GregorianCalendar dateDuJour = new GregorianCalendar();
		vraiDateduJour = dateDuJour.getTime();
		String DateduJour = format1.format(vraiDateduJour);
		int cptMois = 8;
		
		for (int i = 0; i < tbl.length; ++i) {
			JPanel mois = new JPanel();
			mois.setPreferredSize(new Dimension((int)(this.Jscroll.getWidth() * 0.3), (int)(this.Jscroll.getWidth() * 0.2)));
			mois.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			mois.setLayout(new BorderLayout());
			JLabel nomMois = new JLabel(tbl[i] + " " + annee, SwingConstants.CENTER);
			nomMois.setFont(new Font("Arial", Font.PLAIN, 12));
			nomMois.setForeground(Color.WHITE);
			nomMois.setBackground(Color.BLUE);
			nomMois.setOpaque(true);

			mois.add(nomMois, BorderLayout.NORTH);
			JPanel cadreMois = new JPanel();

			cadreMois.setLayout(new GridLayout(0, 7, 3, 3));
			cadreMois.setBackground(Color.WHITE);

			/* Creation des JLabel contenant le nom des jours de la semaine */
			JLabel Lundi = new JLabel("Lun", SwingConstants.CENTER);
			JLabel Mardi = new JLabel("Mar", SwingConstants.CENTER);
			JLabel Mercredi = new JLabel("Mer", SwingConstants.CENTER);
			JLabel Jeudi = new JLabel("Jeu", SwingConstants.CENTER);
			JLabel Vendredi = new JLabel("Ven", SwingConstants.CENTER);
			JLabel Samedi = new JLabel("Sam", SwingConstants.CENTER);
			JLabel Dimanche = new JLabel("Dim", SwingConstants.CENTER);

			/* Ajout des Jlabels de jours au JPanel contenant les informations du mois */
			cadreMois.add(Lundi);
			cadreMois.add(Mardi);
			cadreMois.add(Mercredi);
			cadreMois.add(Jeudi);
			cadreMois.add(Vendredi);
			cadreMois.add(Samedi);
			cadreMois.add(Dimanche);

			GregorianCalendar calendrier = new GregorianCalendar(annee, cptMois, 1);

			int nbJours = calendrier.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

			int premierJour = calendrier.get(GregorianCalendar.DAY_OF_WEEK);

			if (premierJour == 1) {
				premierJour = 8;
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
					//Creation d'un calendrier a la date du bouton
					final GregorianCalendar calendrier2 = new GregorianCalendar(annee, cptMois, (j - (premierJour - 2)));

					//Definition du format utilise pour extraire la date
					DateFormat df = new SimpleDateFormat("dd_MM_yyyy");

					//On recupere la date
					Date dateButton = calendrier2.getTime();

					//
					int week = calendrier2.get(Calendar.WEEK_OF_YEAR);

					//Formattage de la date voulue selon le format voulu
					String dateBouton = df.format(dateButton);
					//Le nom du boutton est celui de sa date
					jour.setName(dateBouton);
					if (calendrier2.get(calendrier2.DAY_OF_WEEK) == calendrier2.SUNDAY || calendrier2.get(calendrier2.DAY_OF_WEEK) == calendrier2.SATURDAY) {
						jour.setOpaque(true);
						jour.setBorderPainted(false);
						jour.setBackground(Color.BLACK);
						jour.setForeground(Color.WHITE);
						jour.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								JOptionPane.showMessageDialog(null, "Les jours de weekend ne sont pas editables.", "Jour non editable", JOptionPane.WARNING_MESSAGE);

							}
						});
					} else {
						jour.setOpaque(true);
						jour.setBorderPainted(false);
						jour.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {

								/* Appel de la methode permettant de creer la semaine */
								openSemaine(calendrier2.get(Calendar.WEEK_OF_YEAR));

								((JButton) evt.getSource()).setBackground(new Color(85, 140, 137));

							}
						});
					}

					/* Si le bouton en cours représentant la date d'aujourd'hui, on lui affecte une couleur particuliere */
					if (dateBouton.equals(DateduJour)) {

						jour.setBackground(new Color(0, 90, 49));

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
	

	/**
	 * Mettre a jour l'instance du controleur apres avoir edite une semaine
	 * Sera appele a la fermeture du formulaire contenant la fenetre
	 */
	public void setControleur( controleurPlanning cont){
		this.Controleur = cont;
	}
	


	/**
	 * Methode permetant de creer les objets semaines pour l'annee passée en parametre
	 * Par exemple, les semaine de l'année 2014 - 2015 seront creees de Septembre 2014 a Aout 2015 
	 * Info: En creant une semaine, ses jours le seront aussi grace au constructeur de la semaine creee
	 */
	public void createWeeks( int annee ) {

		/* 
		 * Creation des semaines de l'annee de debut 
		 */
		
		/* Instanciation du calendrier à la date de début de la formation */
		GregorianCalendar calendrier = new GregorianCalendar( annee, 8, 1);
		
		/* Recupere le numero de semaine de la toute premiere semaine */
		int semaineDebut = calendrier.get(Calendar.WEEK_OF_YEAR);

		/* Enregistrement sous forme de DateTime de la date de debut de l'annee et de la date de fin (utilisation de JodaTime) */
		DateTime septembreAnneeDebut = new DateTime( annee, 9, 1, 0, 0, 0 );
		DateTime decembreAnneeDebut = new DateTime( annee, 12, 31, 0, 0, 0);

		/* Calcul du nombre de semaines entre la date de debut et celle de fin de l'annee (utilisation de JodaTime) */
		int weeksAnneeDebut = Weeks.weeksBetween(septembreAnneeDebut, decembreAnneeDebut).getWeeks();
		
		/* Création itérative de chaque semaine en partant du numero de la premiere semaine jusqu'a celui de celle de fin */
		for( int j = semaineDebut; j < semaineDebut + weeksAnneeDebut + 1; ++j){
			Semaine semaineAnneeDebut = new Semaine( Integer.toString( annee ), j );
			this.Controleur.getModele().getFormation().addSemaine(Integer.toString(j), semaineAnneeDebut);
		}	
		
		/* 
		 * Creation des semaines de l'annee superieur 
		 */
		
		/* Enregistrement sous forme de DateTime de la date de debut de l'annee et de la date de fin (utilisation de JodaTime) */
		DateTime janvierAnneeSuperieure = new DateTime( annee + 1, 1, 1, 0, 0, 0 );
		DateTime aoutAnneeSuperieure = new DateTime( annee + 1, 8, 31, 0, 0, 0);

		/* Calcul du nombre de semaines entre la date de debut et celle de fin de l'annee (utilisation de JodaTime) */
		int weeks = Weeks.weeksBetween(janvierAnneeSuperieure, aoutAnneeSuperieure).getWeeks();
		
		/* Création itérative de chaque semaine en partant du numero de la premiere semaine jusqu'a celui de celle de fin */
		for( int i = 1; i < weeks + 2; ++i){
			Semaine semaine2015 = new Semaine( Integer.toString( annee + 1 ), i );
			this.Controleur.getModele().getFormation().addSemaine(Integer.toString(i), semaine2015);
		}
		
	}
	
	/**
	 * ActionListener des boutons de la fenetre representants les jours de chaque mois
	 * Permet de creer la fenetre (contenue dans une JDialog) affichant la semaine pour saisir les seances
	 * Le numero de la semaine correspondant est passe en parametre
	 */
	public void openSemaine( int numSemaine) {
		
		/* Reference a la fenetre en cours, qui deviendra le parent de la JDialog */
		final Window fenetreParent = SwingUtilities.getWindowAncestor(this);

		/* Instanciation de la semaine ( le numero de semaine et le controleur en parmametres ) */
		detailSemaine semaine = new detailSemaine( numSemaine, this.Controleur);

		/* Instanciation de la JDialog qui contiendra l'objet semaine cree precedemment */
		JDialog dialogSemaine = new JDialog(fenetreParent, "Semaine " + numSemaine, ModalityType.APPLICATION_MODAL);

		/* La fenetre de dialogue est modale */
		dialogSemaine.setModal(true); 	
		/* On définit le ContentPane de la fenêtre avec l'instance de la classe detailSemaine */
		dialogSemaine.getContentPane().add(semaine);    	 
		/* Définition de la taille de la fenêtre modale par celle de son ContentPane */
		dialogSemaine.setPreferredSize( semaine.getSize() ); 
		 /* La fenêtre ne sera pas réajustable */
		dialogSemaine.setResizable( false );     
		/* Obvious */
		dialogSemaine.pack(); 				
		/* Permet de centrer la fenêtre */
		dialogSemaine.setLocationRelativeTo(null);  
		 /* Obvious */
		dialogSemaine.setVisible(true);                     
		
		/* Action exécutée lors de la fermeture de la fenêtre modale, mise à jour du controleur */
		this.setControleur( semaine.getControleur() ); 
	}
	
	/**
	 * Recuperer la hauteur de l'ecran
	 */
	public static int getHauteurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	/**
	 * Recuperer la largeur de l'ecran
	 */
	public static int getLargeurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

}