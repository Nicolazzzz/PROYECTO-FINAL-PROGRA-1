package co.edu.unbosque.view;

import javax.swing.JOptionPane;

/**
 * La clase ViewConsole proporciona métodos para interactuar con el usuario
 * mediante mensajes emergentes (usando JOptionPane) o mediante la consola
 * (Scanner). Los métodos permiten mostrar mensajes, pedir entradas al usuario y
 * realizar validaciones de entradas en diversas formas.
 * 
 * @author Juan de la Hoz
 * @version 1.0
 */
public class ViewConsole {
	/**
	 * Constructor que inicializa un objeto Scanner para lectura desde la consola.
	 */
	public ViewConsole() {
	}

	/**
	 * Muestra un mensaje emergente informativo al usuario.
	 * 
	 * @param texto El texto que se muestra en el cuadro de diálogo.
	 */
	public void mostrarMensajeEmergente(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Muestra una alerta emergente al usuario.
	 * 
	 * @param texto El texto de la alerta que se muestra en el cuadro de diálogo.
	 */
	public void mostrarAlerta(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Alerta", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Muestra un mensaje de error emergente al usuario.
	 * 
	 * @param texto El mensaje de error que se muestra en el cuadro de diálogo.
	 */
	public void mostrarError(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Pide un número de tipo Long al usuario mediante un cuadro de diálogo, con
	 * validación para asegurarse de que la entrada sea un número válido.
	 * 
	 * @param mensaje El mensaje que se muestra al usuario.
	 * @return El número de tipo Long ingresado por el usuario, o null si el usuario
	 *         cancela.
	 */
	public Long pedirLong(String mensaje) {
		Long numero = null;
		boolean esValido = false;

		while (!esValido) {
			String input = JOptionPane.showInputDialog(null, mensaje, "Ingresar número", JOptionPane.QUESTION_MESSAGE);
			if (input == null) {
				return null;
			}

			try {
				numero = Long.parseLong(input);
				esValido = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido", "Entrada inválida",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		return numero;
	}

	/**
	 * Pide un texto al usuario mediante un cuadro de diálogo, con validación para
	 * asegurarse de que la entrada no esté vacía.
	 * 
	 * @param mensaje El mensaje que se muestra al usuario.
	 * @return El texto ingresado por el usuario, o null si el usuario cancela.
	 */
	public String pedirString(String mensaje) {
		String input = null;

		while (input == null || input.trim().isEmpty()) {
			input = JOptionPane.showInputDialog(null, mensaje, "Ingresar texto", JOptionPane.QUESTION_MESSAGE);
			if (input == null) {
				return null;
			} else if (input.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor ingrese un texto válido.", "Entrada inválida",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		return input;
	}

	/**
	 * Muestra un cuadro de diálogo con una lista de opciones y pide al usuario que
	 * seleccione una opción. Si la entrada no es válida, se vuelve a pedir.
	 * 
	 * @param mensaje       El mensaje que se muestra al usuario.
	 * @param listaMensajes Las opciones que se muestran al usuario.
	 * @return El índice de la opción seleccionada, o -1 si el usuario cancela.
	 */
	public int pedirOpcionDeLista(String mensaje, String[] listaMensajes) {
		StringBuilder mensajeCompleto = new StringBuilder(mensaje + "\n\nOpciones:\n");

		for (int i = 0; i < listaMensajes.length; i++) {
			mensajeCompleto.append((i + 1) + ". " + listaMensajes[i] + "\n");
		}

		int opcion = -1;
		boolean esValido = false;

		while (!esValido) {
			String input = JOptionPane.showInputDialog(null, mensajeCompleto.toString(), "Elegir opción",
					JOptionPane.QUESTION_MESSAGE);

			if (input == null) {
				return -1;
			}

			try {
				opcion = Integer.parseInt(input) - 1;

				if (opcion >= 0 && opcion < listaMensajes.length) {
					esValido = true;
				} else {
					JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido de la lista.",
							"Entrada inválida", JOptionPane.WARNING_MESSAGE);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", "Entrada inválida",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		return opcion;
	}

	/**
	 * Muestra un cuadro de diálogo de confirmación con opciones de sí/no.
	 * 
	 * @param txt El mensaje que se muestra al usuario.
	 * @return El valor seleccionado por el usuario: JOptionPane.YES_OPTION para
	 *         "Sí" o JOptionPane.NO_OPTION para "No".
	 */
	public int mostrarYesOrNo(String txt) {
		int op = JOptionPane.showConfirmDialog(null, txt, "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return op;
	}

	/**
	 * Valida la respuesta a una pregunta de sí/no y devuelve un valor booleano.
	 * 
	 * @param op El valor de la opción seleccionada (JOptionPane.YES_OPTION o
	 *           JOptionPane.NO_OPTION).
	 * @return true si el usuario seleccionó "Sí", false si seleccionó "No".
	 */
	public boolean validarYesOrNo(int op) {
		return op == JOptionPane.YES_OPTION;
	}

}