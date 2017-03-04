package eccezioni;

public class EmptyDequeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9022211467846929136L;

	public EmptyDequeException(String err){
		super(err);
	}
}
