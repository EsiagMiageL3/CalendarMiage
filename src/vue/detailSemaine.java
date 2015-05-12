package vue;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controleur.controleurPlanning;
import modele.Formation;
import modele.Semaine;

public class detailSemaine extends JFrame implements ActionListener, FocusListener, MouseListener  {
	
	private controleurPlanning controleur;
	private Formation fm;
	private int numSemaine;
	private JPanel conteneur;
	private JPanel cadreSeances;
	private String[] tblJours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
	
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

		/* DŽfinition de la taille et de la position de la fen�tre principale */
		this.setSize((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 0.95), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height * 0.95));
		this.setUndecorated(true); /* Suppression de la barre de titre de la fen�tre */
		this.setLocationRelativeTo(null); /* Permet de positionner la fen�tre au centre de l'Žcran */
		/*
		 * Instanciation du conteneur de la fen�tre
		 * RŽecriture de la mŽthode paint component afin d'y ajouter un dŽgradŽ de couleurs
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
		this.conteneur.setSize(this.getSize()); /* Taille du conteneur Žgale ˆ celle de la fen�tre */
		this.setContentPane(this.conteneur); /* Le conteneur devient le ContentPane de la fen�tre principale */
		largeurConteneur = this.conteneur.getWidth(); /* Affection de la largeur du conteneur ˆ la variable */
		hauteurConteneur = this.conteneur.getHeight(); /* Affection de la hauteur du conteneur ˆ la variable */
		
		this.cadreSeances = new JPanel( new GridLayout(0, 8, 7, 0) );
		this.cadreSeances.setOpaque(false);
		//this.cadreSeances.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.cadreSeances.setBounds((int)(largeurConteneur * 0.01), (int)(hauteurConteneur * 0.05), (int)(largeurConteneur * 0.98), (int)(hauteurConteneur * 0.9));
		buildSeances();
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
				//+ " " + this.fm.getSemaine( this.numSemaine ).getLstjours()
				
				
				jour.setText( this.tblJours[i - 1] );
				
				this.cadreSeances.add( jour );
				this.lst_LblJours.put(this.tblJours[i - 1], jour);
			}

		}
		
		   Iterator it = this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        String texteOriginale = this.lst_LblJours.get( this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNomJour() ).getText();
		        this.lst_LblJours.get( this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNomJour() ).setText( texteOriginale + " " + this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNumJour() + " " +  this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNomMois() );
		      
		        
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
					JPanel seance = new JPanel();
					seance.setOpaque(true);
					
					seance.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
					seance.setName(this.tblJours[i-1] + "_" + heure);
					
					seance.addMouseListener(new MouseAdapter() {
		     			public void mousePressed(MouseEvent e) {
		     				JOptionPane.showMessageDialog(null, ( (JPanel) e.getSource() ).getName() );
		     				
		     				
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
	}
	/*
	public static void main(String[] args) {
		
		detailSemaine semaine = new detailSemaine();
	}
	*/
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


