package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class PlanningContainer {
		protected JPanel conteneur;
		
		protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
		protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 40);
		protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
		protected Font arial = new Font("Arial", Font.BOLD, 15);
		protected Font dialog = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);
		
		public PlanningContainer(Dimension dim){
			this.conteneur = new JPanel();
			this.conteneur.setPreferredSize(dim);
			this.conteneur.setBackground(Color.white);
			
		}
		
		protected JPanel getPanel(){
			return this.conteneur;
		}
		
		protected abstract void initPanel();	
		

}
