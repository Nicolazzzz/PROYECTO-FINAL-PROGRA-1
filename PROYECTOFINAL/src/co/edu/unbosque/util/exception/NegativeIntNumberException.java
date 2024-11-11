package co.edu.unbosque.util.exception;

/**
 * La clase {@code NegativeIntNumberException} es una excepción personalizada
 * que se lanza cuando se intenta ingresar un número entero negativo.
 * <p>
 * Esta excepción se utiliza para gestionar situaciones en las que el valor
 * ingresado no es válido debido a que es negativo.
 * </p>
 * 
 * @author Nicolas Zambrano 
 * @version 1.0
 */
public class NegativeIntNumberException extends Exception {
    /**
     * Serial version UID para la serialización de la excepción.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje predeterminado.
     */
    public NegativeIntNumberException() {
        super("NO PUEDE INGRESAR NÚMEROS NEGATIVOS");
    }
}