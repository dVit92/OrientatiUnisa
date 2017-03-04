package eccezioni;

public class EmptyPriorityQueueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3888210498523569216L;

	public EmptyPriorityQueueException(String err){
		super(err);
	}
}
