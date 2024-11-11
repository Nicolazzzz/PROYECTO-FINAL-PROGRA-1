package co.edu.unbosque.util.exception;

/**
 * La clase {@code NotValidStringInputException} es una excepción personalizada 
 * que se lanza cuando una entrada de texto no cumple con el formato esperado.
 * <p>
 * Esta excepción se utiliza para gestionar situaciones en las que la entrada 
 * contiene caracteres especiales o números, los cuales no son aceptables para 
 * el tipo de entrada esperada.
 * </p>
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class NotValidStringInputException extends Exception {

    /**
     * Serial version UID para la serialización de la excepción.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje 
     * que indica que no se permiten caracteres especiales o datos numéricos
     * en la entrada de texto.
     */
    public NotValidStringInputException() {
        super("NO INGRESE CARACTERES ESPECIALES O DATOS NUMERICOS");
    }
}
