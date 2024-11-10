package co.edu.unbosque.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeDirector extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel img;
	private JTextField txtName, txtGmail, txtId;
	private JButton btnModificar, btnEliminar, btnPacientes, btnEspecialistas, btnEspecialidades, btnVolver;

	public HomeDirector() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
	}

}
