package co.edu.unbosque.util.exception;

public class NotValidIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValidIdException() {
		super("Formato de cedula no valido, verifique que tenga 10 digitos enteros positivos");
	}

}
