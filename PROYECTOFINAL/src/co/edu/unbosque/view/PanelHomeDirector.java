package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelHomeDirector extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel img;
	private JTextField txtName, txtGmail, txtId;
	private JButton btnModificar, btnEliminar, btnPacientes, btnEspecialistas, btnEspecialidades, btnVolver;

	public PanelHomeDirector() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);

		img = new JLabel();
		ImageIcon imagenPortada2 = new ImageIcon("src/media/homeDirector.png");
		Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon(portadaRedimensionada2));
		img.setBounds(0, 0, 1280, 720);
		img.setVisible(true);

		txtId = new JTextField("", SwingConstants.CENTER);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtId.setForeground(Color.BLACK);
		txtId.setBackground(new Color(240, 240, 240));
		txtId.setBounds(60, 220, 350, 50);
		txtId.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		txtId.setEditable(false);

		txtName = new JTextField("", SwingConstants.CENTER);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtName.setForeground(Color.BLACK);
		txtName.setBackground(new Color(240, 240, 240));
		txtName.setBounds(60, 360, 350, 50);
		txtName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		txtName.setEditable(false);

		txtGmail = new JTextField("", SwingConstants.CENTER);
		txtGmail.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtGmail.setForeground(Color.BLACK);
		txtGmail.setBackground(new Color(240, 240, 240));
		txtGmail.setBounds(60, 510, 350, 50);
		txtGmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		txtGmail.setEditable(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.setActionCommand("VOLVERHOMEDIRECTOR");
		btnVolver.setOpaque(true);
		btnVolver.setBounds(30, 600, 180, 70);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(new Color(213, 101, 49));
		btnVolver.setBorder(BorderFactory.createBevelBorder(1));

		btnEliminar = new JButton("ELIMINAR PERFIL");
		btnEliminar.setActionCommand("ELIMINARPERFIL");
		btnEliminar.setOpaque(true);
		btnEliminar.setBounds(930, 100, 270, 70);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btnEliminar.setBackground(new Color(213, 101, 49));
		btnEliminar.setBorder(BorderFactory.createBevelBorder(1));

		btnModificar = new JButton("MODIFICAR PERFIL");
		btnModificar.setActionCommand("MODIFICARPERFIL");
		btnModificar.setOpaque(true);
		btnModificar.setBounds(930, 200, 270, 70);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificar.setBackground(new Color(27, 167, 161));
		btnModificar.setBorder(BorderFactory.createBevelBorder(1));

		btnPacientes = new JButton("APARTADO PACIENTES");
		btnPacientes.setActionCommand("PACIENTES");
		btnPacientes.setOpaque(true);
		btnPacientes.setBounds(930, 300, 270, 70);
		btnPacientes.setFont(new Font("Arial", Font.BOLD, 18));
		btnPacientes.setBackground(new Color(27, 167, 161));
		btnPacientes.setBorder(BorderFactory.createBevelBorder(1));

		btnEspecialistas = new JButton("APARTADO ESPECIALISTAS");
		btnEspecialistas.setActionCommand("ESPECIALISTAS");
		btnEspecialistas.setOpaque(true);
		btnEspecialistas.setBounds(930, 400, 270, 70);
		btnEspecialistas.setFont(new Font("Arial", Font.BOLD, 18));
		btnEspecialistas.setBackground(new Color(27, 167, 161));
		btnEspecialistas.setBorder(BorderFactory.createBevelBorder(1));

		btnEspecialidades = new JButton("APARTADO ESPECIALIDADES");
		btnEspecialidades.setActionCommand("ESPECIALIDADES");
		btnEspecialidades.setOpaque(true);
		btnEspecialidades.setBounds(930, 500, 270, 70);
		btnEspecialidades.setFont(new Font("Arial", Font.BOLD, 18));
		btnEspecialidades.setBackground(new Color(27, 167, 161));
		btnEspecialidades.setBorder(BorderFactory.createBevelBorder(1));

		add(btnVolver);
		add(btnEliminar);
		add(btnModificar);
		add(btnPacientes);
		add(btnEspecialistas);
		add(btnEspecialidades);
		add(txtId);
		add(txtName);
		add(txtGmail);
		add(img);
	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtGmail() {
		return txtGmail;
	}

	public void setTxtGmail(JTextField txtGmail) {
		this.txtGmail = txtGmail;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnPacientes() {
		return btnPacientes;
	}

	public void setBtnPacientes(JButton btnPacientes) {
		this.btnPacientes = btnPacientes;
	}

	public JButton getBtnEspecialistas() {
		return btnEspecialistas;
	}

	public void setBtnEspecialistas(JButton btnEspecialistas) {
		this.btnEspecialistas = btnEspecialistas;
	}

	public JButton getBtnEspecialidades() {
		return btnEspecialidades;
	}

	public void setBtnEspecialidades(JButton btnEspecialidades) {
		this.btnEspecialidades = btnEspecialidades;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
