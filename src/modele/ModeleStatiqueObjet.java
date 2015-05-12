package src.modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

public class ModeleStatiqueObjet extends AbstractTableModel {
   // private final Object[][] donnees;
 
	public Semaine semaine;
	private Hashtable<String, Jour> lst_JourSemaine;
	private final List<Jour> jours = new ArrayList<Jour>();
	
    private final String[] entetes = {"8h00", "9h00", "10h00","11h00","12h00", "Pause", "14h00", "15h00", "16h00","17h00","18h00"};
 /*
    public ModeleStatique() {
        super();
 
        donnees = new Object[][]{
                {"Johnathan", "Sykes", Color.red, true, Sport.TENNIS, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
                {"Nicolas", "Van de Kampf", Color.black, true, Sport.FOOTBALL, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
                {"Damien", "Cuthbert", Color.cyan, true, Sport.RIEN, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
                {"Corinne", "Valance", Color.blue, false, Sport.NATATION, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
                {"Emilie", "Schršdinger", Color.magenta, false, Sport.FOOTBALL, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
                {"Delphine", "Duke", Color.yellow, false, Sport.TENNIS, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
                {"Eric", "Trump", Color.pink, true, Sport.FOOTBALL, "Sykes", Color.red, true, Sport.TENNIS, Sport.TENNIS, Sport.TENNIS},
        };
    }
 */
    public enum Sport {
        TENNIS,
        FOOTBALL,
        NATATION,
        RIEN;
    }
    
    public ModeleStatiqueObjet( Hashtable<String, Jour> lst ) {
        super();
 

        //lst_JourSemaine = lstJours;
    }
 
    public int getRowCount() {
        return this.jours.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 /*
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return amis[rowIndex].getPrenom();
            case 1:
                return amis[rowIndex].getNom();
            case 2:
                return amis[rowIndex].getCouleur();
            case 3:
                return amis[rowIndex].isHomme();
            case 4:
                return amis[rowIndex].getSport();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
    */

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}