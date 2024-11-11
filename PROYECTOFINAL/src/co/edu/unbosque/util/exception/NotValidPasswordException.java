package co.edu.unbosque.util.exception;

/**
 * La clase {@code NotValidPasswordException} es una excepción personalizada que se lanza 
 * cuando el formato de una contraseña no es válido.
 * <p>
 * Esta excepción se utiliza para gestionar situaciones en las que la contraseña proporcionada
 * no cumple con los requisitos de longitud, que deben ser entre 5 y 10 dígitos.
 * </p>
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class NotValidPasswordException extends Exception {

    /**
     * Serial version UID para la serialización de la excepción.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje predeterminado
     * que indica que el formato de contraseña no es válido, especificando que debe tener 
     * entre 5 y 10 dígitos.
     */
    public NotValidPasswordException() {
        super("Formato de contraseña no válido, ingrese al menos 5 dígitos y máximo 10.");
    }
}