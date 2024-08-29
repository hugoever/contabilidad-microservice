package py.edu.ucsa.contabilidad.common.exceptions;

public class CustomNotFoundException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public CustomNotFoundException(String message) {
        super(message);
    }
	public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}