package co.edu.unbosque.util.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase utilitaria que contiene métodos estáticos para verificar diversas condiciones de entrada 
 * y lanzar excepciones personalizadas cuando estas condiciones no se cumplen.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class ExceptionChecker {

    /**
     * Verifica si una cadena de texto contiene caracteres no alfabéticos ni espacios 
     * y lanza una excepción {@link NotValidStringInputException} si es el caso.
     * 
     * @param txt Cadena de texto a verificar.
     * @throws NotValidStringInputException Si la cadena contiene caracteres no válidos.
     */
    public static void notValidStringInputException(String txt) throws NotValidStringInputException {
        Pattern p = Pattern.compile("[^a-zA-ZñÑ ]");
        Matcher m = p.matcher(txt);

        if (m.find()) {
            throw new NotValidStringInputException();
        }
    }

    /**
     * Verifica si un valor booleano es nulo y lanza una excepción {@link NotValidBooleanException}
     * si el valor es nulo.
     * 
     * @param b Valor booleano a verificar.
     * @throws NotValidBooleanException Si el valor booleano es nulo.
     */
    public static void notValidBooleanException(Boolean b) throws NotValidBooleanException {
        if (b == null) {
            throw new NotValidBooleanException();
        }
    }

    /**
     * Verifica si un número entero es negativo y lanza una excepción {@link NegativeIntNumberException}
     * si el número es negativo.
     * 
     * @param n Número entero a verificar.
     * @throws NegativeIntNumberException Si el número entero es negativo.
     */
    public static void negativeIntNumberException(int n) throws NegativeIntNumberException {
        if (n < 0) {
            throw new NegativeIntNumberException();
        }
    }

    /**
     * Verifica si una dirección de correo electrónico tiene un formato válido y lanza una excepción 
     * {@link EmailNotValidException} si el formato no es válido.
     * 
     * @param email Dirección de correo electrónico a verificar.
     * @throws EmailNotValidException Si el formato del correo electrónico no es válido.
     */
    public static void checkEmail(String email) throws EmailNotValidException {
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailNotValidException();
        }
    }

    /**
     * Verifica si un número de identificación es válido, en función de ciertas reglas, y lanza una excepción 
     * {@link NotValidIdException} si el número no es válido.
     * 
     * @param num Número de identificación a verificar.
     * @throws NotValidIdException Si el número de identificación no es válido.
     */
    public static void notValidIdException(long num) throws NotValidIdException {

        String numTxt = Long.toString(num);
        if (num < 0) {
            throw new NotValidIdException();
        }

        if (numTxt.charAt(0) == 0 || numTxt.length() < 8 || numTxt.length() > 10) {
            throw new NotValidIdException();
        }
    }

    /**
     * Verifica si una contraseña es válida, en función de su longitud y formato, y lanza una excepción 
     * {@link NotValidPasswordException} si la contraseña no es válida.
     * 
     * @param password Contraseña a verificar.
     * @throws NotValidPasswordException Si la contraseña no es válida.
     */
    public static void notValidPasswordException(long password) throws NotValidPasswordException {

        String passTxt = Long.toString(password);
        if (password < 0) {
            throw new NotValidPasswordException();
        }

        if (passTxt.charAt(0) == 0 || passTxt.length() < 5 || passTxt.length() > 10) {
            throw new NotValidPasswordException();
        }
    }

}