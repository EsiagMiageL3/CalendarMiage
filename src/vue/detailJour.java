package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.Jour;
import modele.ModeleStatiqueObjet;
import modele.Semaine;



public class detailJour extends JFrame {
    public Semaine semaine;
   
	public detailJour(Semaine semaine) {
        super();
        
        this.semaine = semaine;
        setTitle("JTable avec mod�le statique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JTable tableau = new JTable(new ModeleStatiqueObjet( semaine.getLstjours() ));
 

        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
       
        pack();
    }
 

	
    public static void main(String[] args) {
    	Semaine semaine2015 = new Semaine( "2015", 4 );
    	
        new detailJour(semaine2015).setVisible(true);
    }
    
}