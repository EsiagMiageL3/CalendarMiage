package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements MouseListener{
	
	//Menu Creation
	private JMenu fichier;
	private JMenu editMenu;
	private JMenu aide;
	
	//Sous-Menu creation
	private JMenuItem nouvelleItem;
    private JMenuItem ouvrirItem;
    private JMenuItem fermerItem;
    private JMenuItem chargerItem;
    private JMenuItem sauverItem;
    private JMenuItem QuitterItem;
    
    //Menu edition
    private JMenuItem undoItem;
    private JMenuItem redoItem;
    private JMenuItem couperItem;
    private JMenuItem copierItem;
    private JMenuItem collerItem;
    private JMenuItem suprimerItem;
 
    private Mois moisAcceuil;
    private JMenuItem apropos;
	
	private JPanel panel = new JPanel();
	private Dimension size;
	
	public Fenetre(){
		//Structure de Fenetre 
		//this.moisAcceuil = moisCourant;
		
		this.setTitle("CalendarMiage");
	    this.setSize(5000,5000);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.size = new Dimension(this.getWidth(), this.getHeight());
	   
	    addWindowListener (new WindowAdapter () 
        {
        public void windowClosing (WindowEvent e)
        {
        	JOptionPane.showMessageDialog(null, "Just testing");
            System.exit(0);
        }


        });
	    
	    
	    	////////////// Creation des menu ////////////////
	       /////////////////////////////////////////////////
	   
	    
	    
	    //////////////////////////////////////////////////////////////
<<<<<<< Updated upstream
	    /////////////////Création de la barre de menu////////////////
=======
	    /////////////////création de la barre de menu////////////////
>>>>>>> Stashed changes
	    //////////////////////////////////////////////////////////////
	    
        JMenuBar Menu = new JMenuBar();
        setJMenuBar(Menu);
        
        
		////////////////////////////////////////////////////////////
        /////////ajout du menu fichier et de ses sous menus/////////
        /////////////////////////////////////////////////////////////
        
	   
        fichier = new JMenu("Fichier");
        fichier.setMnemonic('f');
        Menu.add(fichier);
        //////////////////////////////////////////////////////////////

        
        nouvelleItem = new JMenuItem("Nouveau");
        nouvelleItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
        
        nouvelleItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//JOptionPane.showMessageDialog(null, "bouh");
				panel.removeAll();
				CreationEmploi c = new CreationEmploi(size);
				panel.add(c.getPanel(), BorderLayout.CENTER);
				//panel.revalidate();
			}	    	
	    });
       
        fichier.add(nouvelleItem);
        
        //////////////////////////////////////////////////////////////

        
        ouvrirItem = new JMenuItem("Ouvrir");
        ouvrirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        
        ouvrirItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
 	   
        fichier.add(ouvrirItem);
  
        //////////////////////////////////////////////////////////////
        
        fermerItem = new JMenuItem("Fermer");
        fermerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                InputEvent.CTRL_MASK));
        
        fermerItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        fichier.add(fermerItem);
        fichier.addSeparator();
        //////////////////////////////////////////////////////////////

        chargerItem = new JMenuItem("Charger");
        chargerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                InputEvent.CTRL_MASK));
        
        chargerItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        fichier.add(chargerItem);
        
        //////////////////////////////////////////////////////////////

        
        sauverItem = new JMenuItem("Enregistrer");
        sauverItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        
        sauverItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        fichier.add(sauverItem);
        fichier.addSeparator();
        
        //////////////////////////////////////////////////////////////


        QuitterItem = new JMenuItem("Quitter");
        QuitterItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
        												KeyEvent.CTRL_MASK));
        QuitterItem.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.exit(0);
        		}
        	});
        fichier.add(QuitterItem);
        
        
        //////////////////////////////////////////////////////////////
      	////////ajout du menu edition et de ses sous menus///////////
       //////////////////////////////////////////////////////////////

        
        editMenu = new JMenu("Edition");
        Menu.add(editMenu);
        //////////////////////////////////////////////////////////////

        undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				KeyEvent.CTRL_MASK));
        undoItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        editMenu.add(undoItem);
        //////////////////////////////////////////////////////////////

        redoItem = new JMenuItem("Do");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				KeyEvent.CTRL_MASK));
        redoItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        editMenu.add(redoItem);
        editMenu.addSeparator();
        //////////////////////////////////////////////////////////////

        couperItem = new JMenuItem("Couper");
        couperItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        editMenu.add(couperItem);
        //////////////////////////////////////////////////////////////

        copierItem = new JMenuItem("Copier");
        copierItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        editMenu.add(copierItem);
        //////////////////////////////////////////////////////////////

        collerItem = new JMenuItem("Coller");
        collerItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        editMenu.add(collerItem);
        //////////////////////////////////////////////////////////////

        suprimerItem = new JMenuItem("Supprimer");
        suprimerItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel.removeAll();
				//panel.revalidate();
			}	    	
	    });
        editMenu.add(suprimerItem);
        
        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////

        aide = new JMenu("Aide");
        aide.setMnemonic('f');
        Menu.add(aide);
        
        
        apropos = new JMenuItem("Equipe");
	    apropos.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		JOptionPane.showMessageDialog(null,
							    		          "Créateur : Deora Julien, Mintombou Cyrielle, and Sotou Camille\nLicence : MIAGE l3 2/3 B\n",
							    		          "Informations", JOptionPane.NO_OPTION);
	    		panel.removeAll();
	    		panel.add(new PlanningAcceuil(size).getPanel());
	    		panel.revalidate();
	    	}
	    });
	    aide.add(apropos);
	    
        //////////////////////////////////////////////////////////////

	    //Aceuil 
	    
	    this.panel.setPreferredSize(this.size);
	    this.panel.setBackground(Color.white);
	    this.panel.add(new PlanningAcceuil(this.size).getPanel());
	    //Cnew CréationMois());
	    this.setContentPane(this.panel);
	    this.getContentPane().add(new Mois(), null);
	   
	    
	}
	
	public void accueil(){
		panel.removeAll();
		panel.add(new PlanningAcceuil(size).getPanel());
		panel.revalidate();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Je hais le Java");
		
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
