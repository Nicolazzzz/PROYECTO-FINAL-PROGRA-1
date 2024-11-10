package co.edu.unbosque.view;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class ViewConsole {

	private Scanner sc;

	/**
	 * Muestra un mensaje emergente simple.
	 * 
	 * @param texto el texto a mostrar en el cuadro de diálogo
	 */
	public void mostrarMensajeEmergente(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Muestra una alerta emergente.
	 * 
	 * @param texto el texto de alerta a mostrar
	 */
	public void mostrarAlerta(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Alerta", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Muestra un mensaje de error emergente.
	 * 
	 * @param texto el mensaje de error a mostrar
	 */
	public void mostrarError(String texto) {
		JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.ERROR_MESSAGE);
	}

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

	public int mostrarYesOrNo(String txt) {
		int op = JOptionPane.showConfirmDialog(null, txt, "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return op;
	}

	public boolean validarYesOrNo(int op) {
		if (op == JOptionPane.YES_OPTION) {
			return true;
		} else if (op == JOptionPane.NO_OPTION) {
			return false;
		}
		return false;
	}

	public ViewConsole() {
		sc = new Scanner(System.in);
	}

	public void printLine(String txt) {
		System.out.println(txt);
	}

	public void print(String txt) {
		System.out.print(txt);
	}

	public void burnLine() {
		sc.nextLine();
	}

	public int readInt() {
		return sc.nextInt();
	}

	public long readLong() {
		return sc.nextLong();
	}

	public double readDouble() {
		return sc.nextDouble();
	}

	public String readLine() {
		return sc.nextLine();
	}

	public String readWord() {
		return sc.next();
	}

}
