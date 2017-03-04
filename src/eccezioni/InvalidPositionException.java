package eccezioni;

public class InvalidPositionException extends RuntimeException {

	private static final long serialVersionUID = 9113871509330906555L;

	public InvalidPositionException(String err){
		super(err);
	}
}
