package eccezioni;

public class BoundaryViolationException extends RuntimeException {

	private static final long serialVersionUID = 5403508785754828403L;

	public BoundaryViolationException(String err){
		super(err);
	}
}
