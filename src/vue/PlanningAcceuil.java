package src.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PlanningAcceuil extends PlanningContainer{
		
		public PlanningAcceuil(Dimension dim){
			super(dim);
			initPanel();
		}

		public void initPanel(){
			JLabel titre = new JLabel("Bienvenue");
			titre.setHorizontalAlignment(SwingConstants.CENTER);
			titre.setFont(comics30);
			this.conteneur.add(titre, BorderLayout.SOUTH);
			
			this.conteneur.add(new JLabel(new ImageIcon()), BorderLayout.SOUTH);
			
			JTextArea texte = new JTextArea();
			texte.setEditable(false);
			texte.setFont(arial);
			texte.setBackground(Color.white);
			this.conteneur.add(texte, BorderLayout.SOUTH);		
		}
		
}
