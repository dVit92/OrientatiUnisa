package eccezioni;

public class InvalidKeyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6325708950585230736L;

	public InvalidKeyException(String err){
		super(err);
	}
}
