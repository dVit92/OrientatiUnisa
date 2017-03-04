package eccezioni;

public class InvalidEntryException extends RuntimeException {

	private static final long serialVersionUID = 1073845586233535104L;
	
	public InvalidEntryException(String err){
		super(err);
	}

}
