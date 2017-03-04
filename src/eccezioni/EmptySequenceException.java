package eccezioni;

public class EmptySequenceException extends RuntimeException {

	private static final long serialVersionUID = -951051606344655878L;

	public EmptySequenceException(String err){
		super(err);
	}
}
