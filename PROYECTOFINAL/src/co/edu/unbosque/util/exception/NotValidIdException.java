package co.edu.unbosque.util.exception;

/**
 * La clase {@code NotValidIdException} es una excepción personalizada que se lanza 
 * cuando el formato de una cédula o identificación no es válido.
 * <p>
 * Esta excepción se utiliza para gestionar situaciones en las que el ID proporcionado
 * no cumple con los requisitos de un formato válido, el cual debe tener entre 8 y 10 dígitos enteros positivos.
 * </p>
 * @author Nicolas Zambrano
 * 
 * @version 1.0
 */
public class NotValidIdException extends Exception {

    /**
     * Serial version UID para la serialización de la excepción.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje predeterminado
     * que indica que el formato de cédula no es válido.
     */
    public NotValidIdException() {
        super("Formato de cédula no válido, verifique que tenga entre 8 y 10 dígitos enteros positivos.");
    }
}