package co.edu.unbosque.util.exception;

public class NotValidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValidPasswordException() {
		super("Formato de contraseña no valido, ingrese al menos 5 digitos y maximo 10");
	}

}
