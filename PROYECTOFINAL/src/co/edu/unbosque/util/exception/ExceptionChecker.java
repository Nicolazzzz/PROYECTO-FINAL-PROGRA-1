package co.edu.unbosque.util.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionChecker {

	public static void notValidStringInputException(String txt) throws NotValidStringInputException {
		Pattern p = Pattern.compile("[^a-zA-ZñÑ ]");
		Matcher m = p.matcher(txt);

		if (m.find()) {
			throw new NotValidStringInputException();
		}
	}

	public static void notValidBooleanException(Boolean b) throws NotValidBooleanException {
		if (b == null) {
			throw new NotValidBooleanException();
		}
	}

	public static void negativeIntNumberException(int n) throws NegativeIntNumberException {
		if (n < 0) {
			throw new NegativeIntNumberException();
		}
	}

	public static void checkEmail(String email) throws EmailNotValidException {
		String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new EmailNotValidException();
		}

	}

	public static void notValidIdException(long num) throws NotValidIdException {

		String numTxt = Long.toString(num);
		if (num < 0) {
			throw new NotValidIdException();
		}

		if (numTxt.charAt(0) == 0 || numTxt.length() < 8 || numTxt.length() > 10) {
			throw new NotValidIdException();
		}

	}

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
