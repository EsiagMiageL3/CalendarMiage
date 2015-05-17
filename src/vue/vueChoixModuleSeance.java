package vue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import controleur.controleurPlanning;

public class vueChoixModuleSeance extends JPanel implements ItemListener{
	private controleurPlanning controleur;
	private JPanel conteneur;
	private int largeurConteneur, hauteurConteneur;
	
	private JLabel lblModules;
	private JLabel infoEnregistrement;
	private JComboBox cbxModules;
	
	private Color colorModule;
	private JLabel lblDuree;
	private JTextField txtDuree;
	
	private JLabel lblSeancesRestantes;
	
	private JButton btnOk;
	
	public vueChoixModuleSeance( controleurPlanning controleur ){
		this.controleur = controleur;
		this.initComponents();
		this.lblSeancesRestantes.setText( "Nombre de seeances disponibles pour " + this.cbxModules.getItemAt(0) + ": " +  Integer.toString( this.controleur.getModele().getFormation().getModules().get(  this.cbxModules.getItemAt(0) ).getQuotas() - this.controleur.getModele().getFormation().getModules().get(  this.cbxModules.getItemAt(0) ).getSeances().size() ) );
		
		this.setVisible(true);
	}
	
	
	private void initComponents() {
				/* Deefinition de la taille de la feneetre principale */
				this.setSize((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 0.25), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 0.25));

				this.conteneur = new JPanel() {
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
				this.conteneur.setSize(this.getSize()); /* Taille du conteneur egale a celle de la fenetre */
				this.setLayout(null);
				this.add(this.conteneur);

				largeurConteneur = this.conteneur.getWidth(); /* Affection de la largeur du conteneur a la variable */
				hauteurConteneur = this.conteneur.getHeight(); /* Affection de la hauteur du conteneur a la variable */
				
				this.lblSeancesRestantes = new JLabel("...", JLabel.CENTER);
				this.lblSeancesRestantes.setHorizontalAlignment( JLabel.CENTER );
				this.lblSeancesRestantes.setBounds( (int) 2, (int) 2, (int) (largeurConteneur) - 4, (int) (hauteurConteneur * 0.1) + 2 );
				
				this.lblSeancesRestantes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
				
				this.lblSeancesRestantes.setForeground( Color.WHITE );
				this.lblSeancesRestantes.setOpaque( false );
				this.conteneur.add( this.lblSeancesRestantes );
				
				this.lblModules = new JLabel("Module", JLabel.CENTER);
				this.lblModules.setOpaque( false );
				this.lblModules.setForeground( Color.WHITE);
				this.lblModules.setHorizontalAlignment( JLabel.CENTER );
				this.lblModules.setBounds( (int) 0, (int) (hauteurConteneur * 0.1), (int) (largeurConteneur * 0.3), (int) (hauteurConteneur * 0.2) );
				this.conteneur.add( this.lblModules );
				
				this.cbxModules = new JComboBox();
				
				this.cbxModules.setBounds( (int) (largeurConteneur * 0.31), (int) (hauteurConteneur * 0.1), (int) (largeurConteneur * 0.5), (int) (hauteurConteneur * 0.2) );
				
				   Iterator it = this.controleur.getModele().getFormation().getModules().entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pair = (Map.Entry)it.next();
				        
				        
				        String module = (String) pair.getKey(); //this.lst_LblJours.get( this.controleur.getModele().getFormation().getSemaine( this.numSemaine ).getLstjours().get( pair.getKey() ).getNomJour() ).getText();
				        this.cbxModules.addItem(module);
				    }
				    this.cbxModules.addItemListener(this);
				this.conteneur.add(this.cbxModules);
				
				this.lblDuree = new JLabel("Dureee", JLabel.CENTER);
				this.lblDuree.setOpaque( false );
				this.lblDuree.setForeground( Color.WHITE);
				this.lblDuree.setHorizontalAlignment( JLabel.CENTER );
				this.lblDuree.setBounds( (int) 0, (int) (hauteurConteneur * 0.32), (int) (largeurConteneur * 0.3), (int) (hauteurConteneur * 0.15) );
				this.conteneur.add( this.lblDuree );
				
				this.txtDuree = new JTextField("1");
				this.txtDuree.setBounds( (int) (hauteurConteneur * 0.31), (int) (hauteurConteneur * 0.32), (int) (largeurConteneur * 0.5), (int) (hauteurConteneur * 0.15) );
				this.conteneur.add(this.txtDuree);
				
				this.infoEnregistrement = new JLabel("La seeance sera ajoutee ee la fermeture de la fenetre", JLabel.CENTER);
				this.infoEnregistrement.setHorizontalAlignment( JLabel.CENTER );
				this.infoEnregistrement.setOpaque( false );
				this.infoEnregistrement.setForeground( new Color(80, 80, 80) );
				this.infoEnregistrement.setBounds( (int) 2, (int) (hauteurConteneur * 0.8), (int) (largeurConteneur) - 4, (int) (hauteurConteneur * 0.1)  );
				this.infoEnregistrement.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
				
				this.conteneur.add( this.infoEnregistrement );
				
				this.conteneur.add( this.lblDuree );
				
	}
	
	public int getDureeSeance(){
		return Integer.parseInt( this.txtDuree.getText() );
	}
	
	public String getNomModule(){
		return (String) this.cbxModules.getSelectedItem();
	}

	public Color getCouleurChoisie(){
		return this.colorModule;
	}
public Color getColorModule(){
	return colorModule;
}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if( this.controleur.getModele().getFormation().getModules().get( e.getItem() ).getSeances() != null){
		this.lblSeancesRestantes.setText( "Nombre de seeances disponibles pour " + e.getItem() + ": " +  Integer.toString( this.controleur.getModele().getFormation().getModules().get( e.getItem() ).getQuotas() - this.controleur.getModele().getFormation().getModules().get( e.getItem() ).getSeances().size() ) );
		this.colorModule = this.controleur.getModele().getFormation().getModules().get( e.getItem() ).getCouleur();
		
		repaint();
		validate();
		}
		
	}
}
