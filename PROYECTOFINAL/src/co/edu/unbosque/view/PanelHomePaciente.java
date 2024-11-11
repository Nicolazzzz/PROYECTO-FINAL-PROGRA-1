package co.edu.unbosque.view;

import java.awt.Image;

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
