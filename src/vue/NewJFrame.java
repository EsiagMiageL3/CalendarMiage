package vue;

import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Calendar;
import javax.swing.*;
import java.text.*;

public class NewJFrame extends javax.swing.JFrame implements ActionListener {
	private static JButton[] bouttons = new JButton[40];
	ArrayList < JPanel > lstMois = new ArrayList < JPanel > ();
	/**
	 * Constructeur
	 */
	public NewJFrame() {
		
		/*
		 * Chargement de l'aspect de l'UI
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/*
		 * Fin chargement de l'aspect de l'UI
		 */
		
		initComponents();
		this.setTitle("Calendar Miage - Mintombou Cyrielle, Souto Camille, Deora Julien");
		this.setSize(largeurEcran(), hauteurEcran());

		this.jScrollPane1.setSize(largeurEcran(), hauteurEcran());
		this.jScrollPane1.getVerticalScrollBar().setUnitIncrement(50);

		// Ajout des Panels de Mois à la liste les contenant
		this.lstMois.add(jPanel4);
		this.lstMois.add(jPanel6);
		this.lstMois.add(jPanel8);
		this.lstMois.add(jPanel10);
		this.lstMois.add(jPanel12);
		this.lstMois.add(jPanel14);
		this.lstMois.add(jPanel16);
		this.lstMois.add(jPanel18);
		this.lstMois.add(jPanel20);
		this.lstMois.add(jPanel22);
		this.lstMois.add(jPanel24);
		this.lstMois.add(jPanel26);

		// Création de l'inteface pour l'année 2014 - 2015
		createJoursMois(2014);
		this.setVisible(true);
	}

	// Récupérer la largeur de l'écran
	public static int largeurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	// Récupérer la hauteur de l'écran
	public static int hauteurEcran() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	private void createJoursMois(int annee) {
		int k = 8;

		Date vraiDateduJour;
		SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");

		final GregorianCalendar dateDuJour = new GregorianCalendar();
		vraiDateduJour = dateDuJour.getTime();
		String DateduJour = format1.format(vraiDateduJour);

		////this.lstMois.get(0).setBackground(Color.red);

		for (int j = 0; j < lstMois.size(); j++) {

			if (isTooLong(annee, k)) {
				this.lstMois.get(j).setLayout(new GridLayout(7, 7));
			} else {
				this.lstMois.get(j).setLayout(new GridLayout(6, 7));
			}
			// Création des Labels indiquant les jours
			JLabel Lundi = new JLabel("Lun", JLabel.CENTER);
			JLabel Mardi = new JLabel("Mar", JLabel.CENTER);
			JLabel Mercredi = new JLabel("Mer", JLabel.CENTER);
			JLabel Jeudi = new JLabel("Jeu", JLabel.CENTER);
			JLabel Vendredi = new JLabel("Ven", JLabel.CENTER);
			JLabel Samedi = new JLabel("Sam", JLabel.CENTER);
			JLabel Dimanche = new JLabel("Dim", JLabel.CENTER);

			// Ajout des Labels créés précedement aux panel de mois
			this.lstMois.get(j).add(Lundi);
			this.lstMois.get(j).add(Mardi);
			this.lstMois.get(j).add(Mercredi);
			this.lstMois.get(j).add(Jeudi);
			this.lstMois.get(j).add(Vendredi);
			this.lstMois.get(j).add(Samedi);
			this.lstMois.get(j).add(Dimanche);

			//9 = octobre, décalage au mois supérieur
			GregorianCalendar calendrier = new GregorianCalendar(annee, k, 1);

			int nbJours = calendrier.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

			int premierJour = calendrier.get(GregorianCalendar.DAY_OF_WEEK);

			// Juste un test
			System.out.println(premierJour);

			if (premierJour == 1) {
				premierJour = 8;
			}
			for (int i = 1; i < nbJours + (premierJour - 1); ++i) {
				// On insert un Label contenant une chaine vide pour les jours non comptés dans le mois
				if (i < premierJour - 1) {
					JLabel lbl = new JLabel("", JLabel.CENTER);
					this.lstMois.get(j).add(lbl);
				} else {
					//On crée un bouton au jour en cours puis insertion dans la liste
					bouttons[i] = new JButton("" + (i - (premierJour - 2)));

					//Création d'un calendrier à la date du bouton
					final GregorianCalendar calendrier2 = new GregorianCalendar(annee, k, (i - (premierJour - 2)));

					//Définition du format utilisé pour extraire la date
					DateFormat df = new SimpleDateFormat("dd_MM_yyyy");

					//On récup√®re la date
					Date dateButton = calendrier2.getTime();

					//Formattage de la date voulue selon le format voulu
					String dateBouton = df.format(dateButton);



					//Le nom du boutton est celui de sa date
					bouttons[i].setName(dateBouton);
					if (calendrier2.get(calendrier2.DAY_OF_WEEK) == calendrier2.SUNDAY || calendrier2.get(calendrier2.DAY_OF_WEEK) == calendrier2.SATURDAY) {
						bouttons[i].setBackground(Color.BLACK);
						bouttons[i].setForeground(Color.WHITE);
						bouttons[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								JOptionPane.showMessageDialog(rootPane, "Les jours de weekend ne sont pas éditables.", "Jour non éditable", JOptionPane.WARNING_MESSAGE);
							}
						});
					} else {

						bouttons[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								String buttonText = ((JButton) evt.getSource()).getName();
								JOptionPane.showMessageDialog(rootPane, buttonText);
								Date dateNo = calendrier2.getTime();
								detailJour jour = new detailJour(buttonText, dateNo);
							}
						});
					}
					if (dateBouton.equals(DateduJour)) { //Today

						bouttons[i].setBackground(Color.BLUE);

					}
					//Ajout du bouton
					this.lstMois.get(j).add(bouttons[i]);
				}

			}
			//On incrémente le compteur de mois (on passe au mois suivant)
			k = k + 1;

		}
	}

	// Teste si le mois courant commence un samedi ou Dimanche pour adapter son layout
	private boolean isTooLong(int annee, int mois) {

		GregorianCalendar calendrier = new GregorianCalendar(annee, mois, 1);

		int nbJours = calendrier.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		int premierJour = calendrier.get(GregorianCalendar.DAY_OF_WEEK);

		if (premierJour == 1 && nbJours > 29) {
			return true;
		} else if (premierJour == 7 && nbJours == 30) {
			return false;
		} else if (premierJour == 7 && nbJours == 31) {
			return true;
		} else {
			return false;
		}

	}

	public void actionPerformed(ActionEvent evt) {

	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jLabel1 = new JLabel();
		jPanel4 = new JPanel();
		jPanel5 = new JPanel();
		jLabel2 = new JLabel();
		jPanel6 = new JPanel();
		jPanel7 = new JPanel();
		jLabel3 = new JLabel();
		jPanel8 = new JPanel();
		jPanel9 = new JPanel();
		jLabel4 = new JLabel();
		jPanel10 = new JPanel();
		jPanel11 = new JPanel();
		jLabel5 = new JLabel();
		jPanel12 = new JPanel();
		jPanel13 = new JPanel();
		jLabel6 = new JLabel();
		jPanel14 = new JPanel();
		jPanel15 = new JPanel();
		jLabel7 = new JLabel();
		jPanel16 = new JPanel();
		jPanel17 = new JPanel();
		jLabel8 = new JLabel();
		jPanel18 = new JPanel();
		jPanel19 = new JPanel();
		jLabel9 = new JLabel();
		jPanel20 = new JPanel();
		jPanel21 = new JPanel();
		jLabel10 = new JLabel();
		jPanel22 = new JPanel();
		jPanel23 = new JPanel();
		jLabel11 = new JLabel();
		jPanel24 = new JPanel();
		jPanel25 = new JPanel();
		jLabel12 = new JLabel();
		jPanel26 = new JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setLocation(0,300);
		jScrollPane1.setSize(hauteurEcran(), largeurEcran());
//
		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de test"));
		jPanel1.setAutoscrolls(true);
		jPanel1.setName("panelPlanning"); // NOI18N
		jPanel1.setLayout(new java.awt.GridLayout(4, 3, 30, 15));

		jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel2.setName("pnlJanvier"); // NOI18N

		jLabel1.setBackground(new java.awt.Color(0, 102, 255));
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Septembre");
		jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel1.setName("lblName"); // NOI18N
		jLabel1.setOpaque(true);
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel1MouseClicked(evt);
			}
		});

		jPanel4.setName("joursJanvier"); // NOI18N
		jPanel4.setSize(new java.awt.Dimension(308, 100));
		jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel4MouseClicked(evt);
			}
		});



		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
		jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel2Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addContainerGap()));
		
		jPanel2Layout.setVerticalGroup(
		jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel2Layout.createSequentialGroup()
			.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addContainerGap()));                                                                   

		jPanel1.add(jPanel2);
		
		jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel5.setName("pnlJanvier"); // NOI18N

		jLabel2.setBackground(new java.awt.Color(0, 102, 255));
		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("Octobre");
		jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel2.setName("lblName"); // NOI18N
		jLabel2.setOpaque(true);
		jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel2MouseClicked(evt);
			}
		});

		jPanel6.setName("joursJanvier"); // NOI18N
		jPanel6.setSize(new java.awt.Dimension(308, 100));
		jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel6MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(
		jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(
		jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(
		jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel5Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel5Layout.setVerticalGroup(
		jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel5Layout.createSequentialGroup()
			.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel5);

		jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel7.setName("pnlJanvier"); // NOI18N

		jLabel3.setBackground(new java.awt.Color(0, 102, 255));
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Novembre");
		jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel3.setName("lblName"); // NOI18N
		jLabel3.setOpaque(true);
		jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel3MouseClicked(evt);
			}
		});

		jPanel8.setName("joursJanvier"); // NOI18N
		jPanel8.setSize(new java.awt.Dimension(308, 100));
		jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel8MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(
		jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(
		jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(
		jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel7Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel7Layout.setVerticalGroup(
		jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel7Layout.createSequentialGroup()
			.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel7);

		jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel9.setName("pnlJanvier"); // NOI18N
		jPanel9.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel4.setBackground(new java.awt.Color(0, 102, 255));
		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel4.setText("Décembre");
		jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel4.setName("lblName"); // NOI18N
		jLabel4.setOpaque(true);
		jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel4MouseClicked(evt);
			}
		});

		jPanel10.setName("joursJanvier"); // NOI18N
		jPanel10.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel10.setSize(new java.awt.Dimension(308, 189));
		jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel10MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(
		jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel10Layout.setVerticalGroup(
		jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(
		jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGroup(jPanel9Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel9Layout.setVerticalGroup(
		jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel9Layout.createSequentialGroup()
			.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel9);

		jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel11.setName("pnlJanvier"); // NOI18N
		jPanel11.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel5.setBackground(new java.awt.Color(0, 102, 255));
		jLabel5.setForeground(new java.awt.Color(255, 255, 255));
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel5.setText("Janvier");
		jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel5.setName("lblName"); // NOI18N
		jLabel5.setOpaque(true);
		jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel5MouseClicked(evt);
			}
		});

		jPanel12.setName("joursJanvier"); // NOI18N
		jPanel12.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel12.setSize(new java.awt.Dimension(308, 189));
		jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel12MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout.setHorizontalGroup(
		jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel12Layout.setVerticalGroup(
		jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout.setHorizontalGroup(
		jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel11Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel11Layout.setVerticalGroup(
		jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel11Layout.createSequentialGroup()
			.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel11);

		jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel13.setName("pnlJanvier"); // NOI18N
		jPanel13.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel6.setBackground(new java.awt.Color(0, 102, 255));
		jLabel6.setForeground(new java.awt.Color(255, 255, 255));
		jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel6.setText("Février");
		jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel6.setName("lblName"); // NOI18N
		jLabel6.setOpaque(true);
		jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel6MouseClicked(evt);
			}
		});

		jPanel14.setName("joursJanvier"); // NOI18N
		jPanel14.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel14.setSize(new java.awt.Dimension(308, 189));
		jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel14MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
		jPanel14.setLayout(jPanel14Layout);
		jPanel14Layout.setHorizontalGroup(
		jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel14Layout.setVerticalGroup(
		jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
		jPanel13.setLayout(jPanel13Layout);
		jPanel13Layout.setHorizontalGroup(
		jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel13Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel13Layout.setVerticalGroup(
		jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel13Layout.createSequentialGroup()
			.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel13);

		jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel15.setName("pnlJanvier"); // NOI18N
		jPanel15.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel7.setBackground(new java.awt.Color(0, 102, 255));
		jLabel7.setForeground(new java.awt.Color(255, 255, 255));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel7.setText("Mars");
		jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel7.setName("lblName"); // NOI18N
		jLabel7.setOpaque(true);
		jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel7MouseClicked(evt);
			}
		});

		jPanel16.setName("joursJanvier"); // NOI18N
		jPanel16.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel16.setSize(new java.awt.Dimension(308, 189));
		jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel16MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
		jPanel16.setLayout(jPanel16Layout);
		jPanel16Layout.setHorizontalGroup(
		jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel16Layout.setVerticalGroup(
		jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
		jPanel15.setLayout(jPanel15Layout);
		jPanel15Layout.setHorizontalGroup(
		jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel15Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel15Layout.setVerticalGroup(
		jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel15Layout.createSequentialGroup()
			.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel15);

		jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel17.setName("pnlJanvier"); // NOI18N
		jPanel17.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel8.setBackground(new java.awt.Color(0, 102, 255));
		jLabel8.setForeground(new java.awt.Color(255, 255, 255));
		jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel8.setText("Avril");
		jLabel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel8.setName("lblName"); // NOI18N
		jLabel8.setOpaque(true);
		jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel8MouseClicked(evt);
			}
		});

		jPanel18.setName("joursJanvier"); // NOI18N
		jPanel18.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel18.setSize(new java.awt.Dimension(308, 189));
		jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel18MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
		jPanel18.setLayout(jPanel18Layout);
		jPanel18Layout.setHorizontalGroup(
		jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel18Layout.setVerticalGroup(
		jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
		jPanel17.setLayout(jPanel17Layout);
		jPanel17Layout.setHorizontalGroup(
		jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel17Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel17Layout.setVerticalGroup(
		jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel17Layout.createSequentialGroup()
			.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel17);

		jPanel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel19.setName("pnlJanvier"); // NOI18N
		jPanel19.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel9.setBackground(new java.awt.Color(0, 102, 255));
		jLabel9.setForeground(new java.awt.Color(255, 255, 255));
		jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel9.setText("Mai");
		jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel9.setName("lblName"); // NOI18N
		jLabel9.setOpaque(true);
		jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel9MouseClicked(evt);
			}
		});

		jPanel20.setName("joursJanvier"); // NOI18N
		jPanel20.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel20.setSize(new java.awt.Dimension(308, 189));
		jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel20MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
		jPanel20.setLayout(jPanel20Layout);
		jPanel20Layout.setHorizontalGroup(
		jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel20Layout.setVerticalGroup(
		jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
		jPanel19.setLayout(jPanel19Layout);
		jPanel19Layout.setHorizontalGroup(
		jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel19Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel19Layout.setVerticalGroup(
		jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel19Layout.createSequentialGroup()
			.addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel19);

		jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel21.setName("pnlJanvier"); // NOI18N
		jPanel21.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel10.setBackground(new java.awt.Color(0, 102, 255));
		jLabel10.setForeground(new java.awt.Color(255, 255, 255));
		jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel10.setText("Juin");
		jLabel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel10.setName("lblName"); // NOI18N
		jLabel10.setOpaque(true);
		jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel10MouseClicked(evt);
			}
		});

		jPanel22.setName("joursJanvier"); // NOI18N
		jPanel22.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel22.setSize(new java.awt.Dimension(308, 189));
		jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel22MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
		jPanel22.setLayout(jPanel22Layout);
		jPanel22Layout.setHorizontalGroup(
		jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel22Layout.setVerticalGroup(
		jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
		jPanel21.setLayout(jPanel21Layout);
		jPanel21Layout.setHorizontalGroup(
		jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel21Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel21Layout.setVerticalGroup(
		jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel21Layout.createSequentialGroup()
			.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel21);

		jPanel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel23.setName("pnlJanvier"); // NOI18N
		jPanel23.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel11.setBackground(new java.awt.Color(0, 102, 255));
		jLabel11.setForeground(new java.awt.Color(255, 255, 255));
		jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel11.setText("Juillet");
		jLabel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel11.setName("lblName"); // NOI18N
		jLabel11.setOpaque(true);
		jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel11MouseClicked(evt);
			}
		});

		jPanel24.setName("joursJanvier"); // NOI18N
		jPanel24.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel24.setSize(new java.awt.Dimension(308, 189));
		jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel24MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
		jPanel24.setLayout(jPanel24Layout);
		jPanel24Layout.setHorizontalGroup(
		jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel24Layout.setVerticalGroup(
		jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
		jPanel23.setLayout(jPanel23Layout);
		jPanel23Layout.setHorizontalGroup(
		jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel23Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel23Layout.setVerticalGroup(
		jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel23Layout.createSequentialGroup()
			.addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel23);

		jPanel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		jPanel25.setName("pnlJanvier"); // NOI18N
		jPanel25.setPreferredSize(new java.awt.Dimension(322, 256));

		jLabel12.setBackground(new java.awt.Color(0, 102, 255));
		jLabel12.setForeground(new java.awt.Color(255, 255, 255));
		jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel12.setText("Août");
		jLabel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
		jLabel12.setName("lblName"); // NOI18N
		jLabel12.setOpaque(true);
		jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel12MouseClicked(evt);
			}
		});

		jPanel26.setName("joursJanvier"); // NOI18N
		jPanel26.setPreferredSize(new java.awt.Dimension(308, 189));
		jPanel26.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jPanel26MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
		jPanel26.setLayout(jPanel26Layout);
		jPanel26Layout.setHorizontalGroup(
		jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE));
		jPanel26Layout.setVerticalGroup(
		jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 507, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
		jPanel25.setLayout(jPanel25Layout);
		jPanel25Layout.setHorizontalGroup(
		jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
			.addGroup(jPanel25Layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
			.addContainerGap()));
		jPanel25Layout.setVerticalGroup(
		jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel25Layout.createSequentialGroup()
			.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
			.addContainerGap()));

		jPanel1.add(jPanel25);

		jScrollPane1.setViewportView(jPanel1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,largeurEcran() - 50, Short.MAX_VALUE)
			));
		layout.setVerticalGroup(
		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,  ( hauteurEcran() / 2 ) + ( hauteurEcran() / 4 )  , javax.swing.GroupLayout.PREFERRED_SIZE)
			));
		//jScrollPane1.setSize(hauteurEcran() + 300, largeurEcran() + 300);
		pack();
	} // </editor-fold>//GEN-END:initComponents

	private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel1MouseClicked

		JOptionPane.showMessageDialog(rootPane, "booh");
	} //GEN-LAST:event_jLabel1MouseClicked

	private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel4MouseClicked
		JOptionPane.showMessageDialog(rootPane, this.jPanel4.getSize());
	} //GEN-LAST:event_jPanel4MouseClicked

	private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel2MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel2MouseClicked

	private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel6MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel6MouseClicked

	private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel3MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel3MouseClicked

	private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel8MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel8MouseClicked

	private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel4MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel4MouseClicked

	private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel10MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel10MouseClicked

	private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel5MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel5MouseClicked

	private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel12MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel12MouseClicked

	private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel6MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel6MouseClicked

	private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel14MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel14MouseClicked

	private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel7MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel7MouseClicked

	private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel16MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel16MouseClicked

	private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel8MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel8MouseClicked

	private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel18MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel18MouseClicked

	private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel9MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel9MouseClicked

	private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel20MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel20MouseClicked

	private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel10MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel10MouseClicked

	private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel22MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel22MouseClicked

	private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel11MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel11MouseClicked

	private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel24MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel24MouseClicked

	private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jLabel12MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jLabel12MouseClicked

	private void jPanel26MouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jPanel26MouseClicked
		// TODO add your handling code here:
	} //GEN-LAST:event_jPanel26MouseClicked

	/**
	 * @param args the command line arguments
	 */


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel16;
	private javax.swing.JPanel jPanel17;
	private javax.swing.JPanel jPanel18;
	private javax.swing.JPanel jPanel19;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel20;
	private javax.swing.JPanel jPanel21;
	private javax.swing.JPanel jPanel22;
	private javax.swing.JPanel jPanel23;
	private javax.swing.JPanel jPanel24;
	private javax.swing.JPanel jPanel25;
	private javax.swing.JPanel jPanel26;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane1;
	// End of variables declaration//GEN-END:variables
}