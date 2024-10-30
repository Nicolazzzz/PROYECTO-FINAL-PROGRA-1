package co.edu.unbosque.util.exception;

public class EmailNotValidException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailNotValidException() {
		super("El correo electronico no tiene un formato valido");
	}
}
