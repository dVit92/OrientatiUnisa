package eccezioni;

public class EmptyQueueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1517851309061427570L;

	public EmptyQueueException(String err){
		super(err);
	}
}
