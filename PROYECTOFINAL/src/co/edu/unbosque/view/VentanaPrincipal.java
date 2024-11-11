package co.edu.unbosque.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * La clase VentanaPrincipal extiende JFrame y representa la ventana principal
 * de la aplicación. Se encarga de mostrar distintos paneles según la
 * interacción del usuario, como la pantalla de bienvenida, la pantalla de
 * inicio de sesión, y las pantallas de inicio de director, especialista,
 * paciente y apartado.
 * 
 * @author Juan de la Hoz
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	// Paneles de la aplicación
	private PanelEntrada panelEntrada;
	private PanelLogIn panelLogin;
	private PanelInput panelInput;
	private PanelHomeDirector panelHDirector;
	private PanelHomeEspecialista panelHEspecialista;
	private PanelHomePaciente panelHPaciente;
	private PanelApartado panelApartado;

	/**
	 * Constructor de la clase VentanaPrincipal. Inicializa la ventana principal con
	 * su tamaño, título, icono y paneles asociados a cada vista de la aplicación.
	 */
	public VentanaPrincipal() {

		setLocation(50, 50);
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setTitle("BOSQUE HEALTH");

		// Configuración del icono de la ventana
		ImageIcon logoApp = new ImageIcon("src/media/logo.png");
		setIconImage(logoApp.getImage());

		// Inicialización de los paneles
		panelEntrada = new PanelEntrada();
		panelLogin = new PanelLogIn();
		panelInput = new PanelInput();
		panelHDirector = new PanelHomeDirector();
		panelHEspecialista = new PanelHomeEspecialista();
		panelHPaciente = new PanelHomePaciente();
		panelApartado = new PanelApartado();
	}

	// Métodos getter y setter para cada panel

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

	/**
	 * Muestra el panel de bienvenida (PanelEntrada).
	 */
	public void mostrarPanelWelcome() {
		setContentPane(panelEntrada);
	}

	/**
	 * Muestra el panel de inicio de sesión (PanelLogIn).
	 */
	public void mostrarPanelLogin() {
		setContentPane(panelLogin);
	}

	/**
	 * Muestra el panel de entrada de datos (PanelInput).
	 */
	public void mostrarPanelInput() {
		setContentPane(panelInput);
	}

	/**
	 * Muestra el panel de inicio de director (PanelHomeDirector).
	 */
	public void mostrarPanelHomeDirector() {
		setContentPane(panelHDirector);
	}

	/**
	 * Muestra el panel de inicio de especialista (PanelHomeEspecialista).
	 */
	public void mostrarPanelHomeEspecialista() {
		setContentPane(panelHEspecialista);
	}

	/**
	 * Muestra el panel de inicio de paciente (PanelHomePaciente).
	 */
	public void mostrarPanelHomePaciente() {
		setContentPane(panelHPaciente);
	}


	/**
	 * Muestra el panel de apartado (PanelApartado).
	 */
	public void mostrarPanelApartado() {
		setContentPane(panelApartado);
	}

}
