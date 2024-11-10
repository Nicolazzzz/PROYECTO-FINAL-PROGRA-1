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
