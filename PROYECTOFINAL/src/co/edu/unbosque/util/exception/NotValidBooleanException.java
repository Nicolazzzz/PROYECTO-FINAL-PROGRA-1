package co.edu.unbosque.util.exception;

/**
 * La clase {@code NotValidBooleanException} es una excepción personalizada que
 * se lanza cuando una respuesta booleana no es válida.
 * <p>
 * Esta excepción se utiliza para gestionar situaciones en las que la respuesta
 * proporcionada no corresponde con los valores de verdad esperados, es decir,
 * respuestas de tipo sí/no.
 * </p>
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class NotValidBooleanException extends Exception {

    /**
     * Serial version UID para la serialización de la excepción.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje predeterminado.
     */
    public NotValidBooleanException() {
        super("NO CORRESPONDE SU RESPUESTA CON EL VALOR DE VERDAD. EN PREGUNTAS CON RESPUESTAS SÍ/NO --> VERIFIQUE QUE SU RESPUESTA SEA ESA");
    }
}