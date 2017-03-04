package eccezioni;

public class EmptyStackException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3190011898334129108L;

	public EmptyStackException(String err){
		super(err);
	}
}
