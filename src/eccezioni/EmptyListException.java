package eccezioni;

public class EmptyListException extends RuntimeException {

	private static final long serialVersionUID = -7498513258930891946L;

	public EmptyListException(String err){
		super(err);
	}
}
