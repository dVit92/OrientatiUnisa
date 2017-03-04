package eccezioni;

public class UndeletableNodeException extends RuntimeException {

	private static final long serialVersionUID = -3309612070689849891L;

	public UndeletableNodeException(String err){
		super(err);
	}
}
