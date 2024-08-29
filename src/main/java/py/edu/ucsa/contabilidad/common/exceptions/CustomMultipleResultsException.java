package py.edu.ucsa.contabilidad.common.exceptions;

public class CustomMultipleResultsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CustomMultipleResultsException(String message) {
	        super(message);
	    }
	public CustomMultipleResultsException(String message, Throwable cause) {
        super(message, cause);
    }
}
