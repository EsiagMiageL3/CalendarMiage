package modele;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JOptionPane;

/*import cours10.banque.Client;
import cours10.banque.Compte;
import cours10.banque.erreurs.RechercheClientException;*/
import exception.RechercheSeanceException;
import modele.Module;
import exception.RechercheSeanceException;
import modele.Module;


@SuppressWarnings("serial")
public class Seance extends Module implements Serializable{
	//public int nb_seances;
	//public int rang_seances =20;
	public ArrayList<Module> lst_Module;
	ArrayList<Module> ListModuleAjout= new ArrayList<Module>();
	public int rang_seances,nb_seances;


	
	public int genererRangSeance() {
		return this.rang_seances++;
	}
	public Seance (){
		super();
	}
	

	
	public void AjoutSeance(String nom_m, String abreviation, Color couleur, int nb_seances){
        this.ListModuleAjout.add(new Module(nom_m, abreviation, couleur, nb_seances,genererRangSeance()));
    }

	public Seance (String nom_m, String abrev, Color couleur,int m){
					super(nom_m, abrev, couleur, m);	
					this.nb_seances = m;

	}

	
	

	public void AjoutSeance(String nom_module, int n) throws RechercheSeanceException {
		if (super.nom_m.equals(nom_module));
		/*
		if (n > m.getNb_Seance()) throw new RechercheSeanceException();
	public void AjoutSeance(int n,int m) throws RechercheSeanceException {
		if (n>m) throw new RechercheSeanceException();
			else{
				this.nb_seances = this.rang_seances;
				System.out.println("Seance crée");
				
				}
				*/
	}

	/*public void VerificationContrainte(int n,int m) throws RechercheSeanceException {
		if (n.getNb_seances()>m.getRang_seances()) throw new RechercheSeanceException("non");}*/
		
 	
		
		
		
		/*public Client getClient(String nom)  throws RechercheClientException{ // retourne le client de nom "nom" dans le treeSet de clients
			{
				Client cl= new Client(nom,"","",0);

				if (trSetClNameOnly.contains(cl)) return trSetClNameOnly.floor(cl);
				//if (trSetCl.contains(cl)) return trSetCl.floor(cl); // avec le tri sur plusieurs crit�res si envisag��
				else throw new RechercheClientException("pas de client avec ce nom : ", nom); 
			}
			public String getNom() {
				return this.nom;
			}

			public void ajouterCompteClient(String nom, double solde, double debitMax, String typeCompte) {
				try {
					this.getClient(nom).ajouterCompte((Compte) Class.forName(Compte.class.getPackage().getName() +"." +typeCompte)
							.getConstructor(new Class[]{Integer.TYPE,Double.TYPE,Double.TYPE})
							.newInstance(new Object[] {genererNumeroCompte(),solde,debitMax})); // utilisation des m�thodes d'introspection n'�tait pas demand� ici, 
																								// mais vous pouvez le ragarder avec profit

				}catch (RechercheClientException e){
					System.out.println(e.getMessage());*/
		
		
		
		
		
		
		
		
		
		
	//Constructeur 

	public int getNb_seances() {
		return nb_seances;
	}


	public void setNb_seances(int nb_seances) {
		this.nb_seances = nb_seances;
	}


	public int getRang_seances() {
		return rang_seances;
	}


	public void setRang_seances(int rang_seances) {
		this.rang_seances = rang_seances;
	}

}
