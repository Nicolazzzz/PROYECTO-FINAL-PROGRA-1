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
	private PanelInput panelInput;
	private PanelHomeDirector panelHDirector;
	private PanelHomeEspecialista panelHEspecialista;
	private PanelHomePaciente panelHPaciente;
	private PanelApartado panelApartado;

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
		panelInput = new PanelInput();
		panelHDirector = new PanelHomeDirector();
		panelHEspecialista = new PanelHomeEspecialista();
		panelHPaciente = new PanelHomePaciente();
		panelApartado = new PanelApartado();

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

	public PanelInput getPanelInput() {
		return panelInput;
	}

	public void setPanelInput(PanelInput panelInput) {
		this.panelInput = panelInput;
	}

	public PanelHomeDirector getPanelHDirector() {
		return panelHDirector;
	}

	public void setPanelHDirector(PanelHomeDirector panelHDirector) {
		this.panelHDirector = panelHDirector;
	}

	public PanelHomeEspecialista getPanelHEspecialista() {
		return panelHEspecialista;
	}

	public void setPanelHEspecialista(PanelHomeEspecialista panelHEspecialista) {
		this.panelHEspecialista = panelHEspecialista;
	}

	public PanelHomePaciente getPanelHPaciente() {
		return panelHPaciente;
	}

	public void setPanelHPaciente(PanelHomePaciente panelHPaciente) {
		this.panelHPaciente = panelHPaciente;
	}

	public PanelApartado getPanelApartado() {
		return panelApartado;
	}

	public void setPanelApartado(PanelApartado panelApartado) {
		this.panelApartado = panelApartado;
	}

	public void mostrarPanelWelcome() {
		setContentPane(panelEntrada);
	}

	public void mostrarPanelLogin() {
		setContentPane(panelLogin);
	}

	public void mostrarPanelInput() {
		setContentPane(panelInput);
	}

	public void mostrarPanelHomeDirector() {
		setContentPane(panelHDirector);
	}

	public void mostrarPanelHomeEspecialista() {
		setContentPane(panelHEspecialista);
	}

	public void mostrarPanelHomePaciente() {
		setContentPane(panelHPaciente);
	}

	public void mostrarPanelApartado() {
		setContentPane(panelApartado);
	}

}
