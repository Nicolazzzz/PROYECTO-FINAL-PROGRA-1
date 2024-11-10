package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEntrada extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel img;
	private JButton btnDirector, btnEspecialista, btnPaciente;

	public PanelEntrada() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);

		img = new JLabel();
		ImageIcon imagenPortada2 = new ImageIcon("src/media/welcome.png");
		Image portadaRedimensionada2 = imagenPortada2.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon(portadaRedimensionada2));
		img.setBounds(0, 0, 1280, 720);
		img.setVisible(true);

		btnDirector = new JButton("DIRECTOR");
		btnDirector.setActionCommand("DIRECTOR");
		btnDirector.setOpaque(true);
		btnDirector.setBounds(850, 220, 250, 70);
		btnDirector.setFont(new Font("Arial", Font.BOLD, 20));
		btnDirector.setBackground(new Color(27, 167, 161));
		btnDirector.setBorder(BorderFactory.createBevelBorder(1));

		btnEspecialista = new JButton("ESPECIALISTA");
		btnEspecialista.setActionCommand("ESPECIALISTA");
		btnEspecialista.setOpaque(true);
		btnEspecialista.setBounds(850, 370, 250, 70);
		btnEspecialista.setFont(new Font("Arial", Font.BOLD, 20));
		btnEspecialista.setBackground(new Color(27, 167, 161));
		btnEspecialista.setBorder(BorderFactory.createBevelBorder(1));

		btnPaciente = new JButton("PACIENTE");
		btnPaciente.setActionCommand("PACIENTE");
		btnPaciente.setOpaque(true);
		btnPaciente.setBounds(850, 520, 250, 70);
		btnPaciente.setFont(new Font("Arial", Font.BOLD, 20));
		btnPaciente.setBackground(new Color(27, 167, 161));
		btnPaciente.setBorder(BorderFactory.createBevelBorder(1));

		add(btnDirector);
		add(btnEspecialista);
		add(btnPaciente);
		add(img);

	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

	public JButton getBtnDirector() {
		return btnDirector;
	}

	public void setBtnDirector(JButton btnDirector) {
		this.btnDirector = btnDirector;
	}

	public JButton getBtnEspecialista() {
		return btnEspecialista;
	}

	public void setBtnEspecialista(JButton btnEspecialista) {
		this.btnEspecialista = btnEspecialista;
	}

	public JButton getBtnPaciente() {
		return btnPaciente;
	}

	public void setBtnPaciente(JButton btnPaciente) {
		this.btnPaciente = btnPaciente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
