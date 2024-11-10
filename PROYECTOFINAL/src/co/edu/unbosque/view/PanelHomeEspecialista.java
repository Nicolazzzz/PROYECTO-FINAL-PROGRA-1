package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelHomeEspecialista extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel img;
	private JButton btnVer, btnSolicitarE, btnEnviarRM, btnGenerarD, btnGenerarT, btnChange, btnVolver;
	private JTextArea txtEntrada;
	private JTextField txtId;
	private JScrollPane scroll;

	public PanelHomeEspecialista() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);

		img = new JLabel();
		ImageIcon imagenPortada2 = new ImageIcon("src/media/homeEspecialista.png");
		Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon(portadaRedimensionada2));
		img.setBounds(0, 0, 1280, 720);
		img.setVisible(true);

		txtEntrada = new JTextArea(10, 30);
		txtEntrada.setLineWrap(true);
		txtEntrada.setWrapStyleWord(true);

		scroll = new JScrollPane(txtEntrada);

		txtId = new JTextField("", SwingConstants.CENTER);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 30));
		txtId.setForeground(Color.BLACK);
		txtId.setBackground(new Color(240, 240, 240));
		txtId.setBounds(830, 280, 300, 50);
		txtId.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		btnVolver = new JButton("VOLVER");
		btnVolver.setActionCommand("VOLVERHOMEESPECIALISTA");
		btnVolver.setOpaque(true);
		btnVolver.setBounds(30, 600, 180, 70);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(new Color(213, 101, 49));
		btnVolver.setBorder(BorderFactory.createBevelBorder(1));

		btnVer = new JButton("VER TURNOS");
		btnVer.setActionCommand("VERTURNOS");
		btnVer.setOpaque(true);
		btnVer.setBounds(930, 100, 270, 70);
		btnVer.setFont(new Font("Arial", Font.BOLD, 20));
		btnVer.setBackground(new Color(213, 101, 49));
		btnVer.setBorder(BorderFactory.createBevelBorder(1));

		btnSolicitarE = new JButton("MODIFICAR PERFIL");
		btnSolicitarE.setActionCommand("MODIFICARPERFIL");
		btnSolicitarE.setOpaque(true);
		btnSolicitarE.setBounds(930, 200, 270, 70);
		btnSolicitarE.setFont(new Font("Arial", Font.BOLD, 20));
		btnSolicitarE.setBackground(new Color(27, 167, 161));
		btnSolicitarE.setBorder(BorderFactory.createBevelBorder(1));

		btnEnviarRM = new JButton("APARTADO PACIENTES");
		btnEnviarRM.setActionCommand("PACIENTES");
		btnEnviarRM.setOpaque(true);
		btnEnviarRM.setBounds(930, 300, 270, 70);
		btnEnviarRM.setFont(new Font("Arial", Font.BOLD, 18));
		btnEnviarRM.setBackground(new Color(27, 167, 161));
		btnEnviarRM.setBorder(BorderFactory.createBevelBorder(1));

		btnGenerarD = new JButton("APARTADO ESPECIALISTAS");
		btnGenerarD.setActionCommand("ESPECIALISTAS");
		btnGenerarD.setOpaque(true);
		btnGenerarD.setBounds(930, 400, 270, 70);
		btnGenerarD.setFont(new Font("Arial", Font.BOLD, 18));
		btnGenerarD.setBackground(new Color(27, 167, 161));
		btnGenerarD.setBorder(BorderFactory.createBevelBorder(1));

		btnGenerarT = new JButton("APARTADO ESPECIALIDADES");
		btnGenerarT.setActionCommand("ESPECIALIDADES");
		btnGenerarT.setOpaque(true);
		btnGenerarT.setBounds(930, 500, 270, 70);
		btnGenerarT.setFont(new Font("Arial", Font.BOLD, 18));
		btnGenerarT.setBackground(new Color(27, 167, 161));
		btnGenerarT.setBorder(BorderFactory.createBevelBorder(1));

		btnChange = new JButton("APARTADO ESPECIALIDADES");
		btnChange.setActionCommand("ESPECIALIDADES");
		btnChange.setOpaque(true);
		btnChange.setBounds(930, 500, 270, 70);
		btnChange.setFont(new Font("Arial", Font.BOLD, 18));
		btnChange.setBackground(new Color(27, 167, 161));
		btnChange.setBorder(BorderFactory.createBevelBorder(1));

		add(btnVolver);
		add(btnVer);
		add(btnSolicitarE);
		add(btnEnviarRM);
		add(btnGenerarT);
		add(btnGenerarD);
		add(btnChange);
		add(txtId);
		add(scroll);
		add(img);
	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

	public JButton getBtnVer() {
		return btnVer;
	}

	public void setBtnVer(JButton btnVer) {
		this.btnVer = btnVer;
	}

	public JButton getBtnSolicitarE() {
		return btnSolicitarE;
	}

	public void setBtnSolicitarE(JButton btnSolicitarE) {
		this.btnSolicitarE = btnSolicitarE;
	}

	public JButton getBtnEnviarRM() {
		return btnEnviarRM;
	}

	public void setBtnEnviarRM(JButton btnEnviarRM) {
		this.btnEnviarRM = btnEnviarRM;
	}

	public JButton getBtnGenerarD() {
		return btnGenerarD;
	}

	public void setBtnGenerarD(JButton btnGenerarD) {
		this.btnGenerarD = btnGenerarD;
	}

	public JButton getBtnGenerarT() {
		return btnGenerarT;
	}

	public void setBtnGenerarT(JButton btnGenerarT) {
		this.btnGenerarT = btnGenerarT;
	}

	public JButton getBtnChange() {
		return btnChange;
	}

	public void setBtnChange(JButton btnChange) {
		this.btnChange = btnChange;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JTextArea getTxtEntrada() {
		return txtEntrada;
	}

	public void setTxtEntrada(JTextArea txtEntrada) {
		this.txtEntrada = txtEntrada;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
