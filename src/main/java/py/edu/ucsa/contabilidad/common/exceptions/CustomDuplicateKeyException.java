package py.edu.ucsa.contabilidad.common.exceptions;

public class CustomDuplicateKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomDuplicateKeyException(String message) {
        super(message);
    }

    public CustomDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
