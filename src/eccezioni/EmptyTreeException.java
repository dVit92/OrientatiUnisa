package eccezioni;

public class EmptyTreeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7819132034763005730L;

	public EmptyTreeException(String err){
		super(err);
	}
}
