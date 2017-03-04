package eccezioni;

public class NonEmptyTreeException extends RuntimeException {

	private static final long serialVersionUID = 5144885146198937359L;

	public NonEmptyTreeException(String err){
		super(err);
	}
}
