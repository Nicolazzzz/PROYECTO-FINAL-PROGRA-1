package co.edu.unbosque.util.exception;

/**
 * Excepción personalizada que se lanza cuando un correo electrónico no tiene un formato válido.
 * Esta excepción extiende la clase {@link Exception} y proporciona un mensaje predeterminado 
 * al ser lanzada.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class EmailNotValidException extends Exception {
    
    /**
     * SerialVersionUID utilizado para garantizar la compatibilidad durante la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la excepción que inicializa el mensaje de error con un texto predeterminado.
     */
    public EmailNotValidException() {
        super("El correo electronico no tiene un formato valido");
    }
}
