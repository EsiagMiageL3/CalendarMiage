package exception;

	public class RechercheFormationException extends Exception{
		public RechercheFormationException(){
			super("Le nombre de seance de cette matiére est superieur");
		}

}
