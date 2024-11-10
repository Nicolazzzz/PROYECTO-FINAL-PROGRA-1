package co.edu.unbosque.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelEntrada panelEntrada;
	private PanelLogIn panelLogin;

	public VentanaPrincipal() {

		setLocation(50, 50);
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setTitle("BOSQUE HEALTH");

		ImageIcon logoApp = new ImageIcon("src/media/logo.png");
		setIconImage(logoApp.getImage());

		panelEntrada = new PanelEntrada();
		panelLogin = new PanelLogIn();
	}

	public PanelEntrada getPanelEntrada() {
		return panelEntrada;
	}

	public void setPanelEntrada(PanelEntrada panelEntrada) {
		this.panelEntrada = panelEntrada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PanelLogIn getPanelLogin() {
		return panelLogin;
	}

	public void setPanelLogin(PanelLogIn panelLogin) {
		this.panelLogin = panelLogin;
	}

	public void mostrarPanelWelcome() {
		setContentPane(panelEntrada);
	}

	public void mostrarPanelLogin() {
		setContentPane(panelLogin);
	}

}
