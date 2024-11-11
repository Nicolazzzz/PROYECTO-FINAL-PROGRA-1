package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHomePaciente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnAgendar, btnReprogramar, btnCancelar, btnVolver;
	private JLabel imgP;

	public PanelHomePaciente() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);

		imgP = new JLabel();
		ImageIcon imagenPortada2 = new ImageIcon("src/media/homePaciente.png");
		Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		imgP.setIcon(new ImageIcon(portadaRedimensionada2));
		imgP.setBounds(0, 0, 1280, 720);
		imgP.setVisible(true);

		btnAgendar = new JButton("AGENDAR CITA");
		btnAgendar.setActionCommand("AGENDARC");
		btnAgendar.setBounds(500, 200, 270, 70);
		btnAgendar.setFont(new Font("Arial", Font.BOLD, 20));
		btnAgendar.setBackground(new Color(27, 167, 161));
		btnAgendar.setBorder(BorderFactory.createBevelBorder(1));

		btnReprogramar = new JButton("REPROGRAMAR CITA");
		btnReprogramar.setActionCommand("REPROGRAMARC");
		btnReprogramar.setBounds(500, 300, 270, 70);
		btnReprogramar.setFont(new Font("Arial", Font.BOLD, 20));
		btnReprogramar.setBackground(new Color(27, 167, 161));
		btnReprogramar.setBorder(BorderFactory.createBevelBorder(1));

		btnCancelar = new JButton("CANCELAR CITA");
		btnCancelar.setActionCommand("CANCELARC");
		btnCancelar.setBounds(500, 400, 270, 70);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 20));
		btnCancelar.setBackground(new Color(213, 101, 49));
		btnCancelar.setBorder(BorderFactory.createBevelBorder(1));

		btnVolver = new JButton("VOLVER");
		btnVolver.setActionCommand("VOLVERHPACIENTE");
		btnVolver.setOpaque(true);
		btnVolver.setBounds(30, 600, 180, 70);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(new Color(213, 101, 49));
		btnVolver.setBorder(BorderFactory.createBevelBorder(1));

		add(btnAgendar);
		add(btnVolver);
		add(btnReprogramar);
		add(btnCancelar);
		add(imgP);
	}

	public JButton getBtnAgendar() {
		return btnAgendar;
	}

	public void setBtnAgendar(JButton btnAgendar) {
		this.btnAgendar = btnAgendar;
	}

	public JButton getBtnReprogramar() {
		return btnReprogramar;
	}

	public void setBtnReprogramar(JButton btnReprogramar) {
		this.btnReprogramar = btnReprogramar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JLabel getImgP() {
		return imgP;
	}

	public void setImgP(JLabel imgP) {
		this.imgP = imgP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
