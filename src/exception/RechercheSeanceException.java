package exception;

@SuppressWarnings("serial")

public class RechercheSeanceException extends Exception{
	public RechercheSeanceException(){
		System.out.println("Le nombre de seance de cette matiére est superieur");
	}

}
