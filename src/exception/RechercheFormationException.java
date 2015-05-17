package exception;

	public class RechercheFormationException extends Exception{
		public RechercheFormationException(){
			super("Le nombre de seance de cette matiere est superieur");
		}

}
