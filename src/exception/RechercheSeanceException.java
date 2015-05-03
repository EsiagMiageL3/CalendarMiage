package exception;

@SuppressWarnings("serial")

public class RechercheSeanceException extends Exception{
	public RechercheSeanceException(String mess ){
		System.out.println(mess);
	}

}
