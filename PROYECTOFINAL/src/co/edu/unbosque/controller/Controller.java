package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.DirectorDTO;
import co.edu.unbosque.model.EspecialidadDTO;
import co.edu.unbosque.model.EspecialistaDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.PacienteDTO;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.util.exception.EmailNotValidException;
import co.edu.unbosque.util.exception.ExceptionChecker;
import co.edu.unbosque.util.exception.NegativeIntNumberException;
import co.edu.unbosque.util.exception.NotValidIdException;
import co.edu.unbosque.util.exception.NotValidPasswordException;
import co.edu.unbosque.util.exception.NotValidStringInputException;
import co.edu.unbosque.view.ViewFacade;

/**
 * Controlador principal de la aplicación que implementa la interfaz
 * {@link ActionListener}. Maneja las interacciones entre el modelo
 * ({@link ModelFacade}) y la vista ({@link ViewFacade}), y gestiona las
 * acciones del usuario.
 *
 * @author Mario Rodríguez
 * @version 1.0
 */
public class Controller implements ActionListener {

	private ModelFacade mf; // Fachada para manejar el modelo de la aplicación
	private ViewFacade vf; // Fachada para manejar la vista de la aplicación
	private Properties prop; // Propiedades cargadas desde archivo de configuración

	private String[] especialidades; // Arreglo de especialidades médicas

	private long directorTempId; // Identificación temporal del director
	private long pacienteTempId; // Identificación temporal del paciente
	private String pacienteTempECita; // Estado temporal de la cita del paciente
	private long especialistaTempId; // Identificación temporal del especialista

	private boolean loginDirector; // Indicador de sesión de director iniciada
	private boolean loginEspecialista; // Indicador de sesión de especialista iniciada

	private boolean createDirector; // Bandera para la creación de un director
	private boolean createEspecialista; // Bandera para la creación de un especialista
	private boolean createPaciente; // Bandera para la creación de un paciente

	private boolean modDirector; // Bandera para modificar un director
	private boolean modEspecialista; // Bandera para modificar un especialista
	private boolean modPaciente; // Bandera para modificar un paciente

	private boolean apartadoPaciente; // Bandera para gestionar el apartado de pacientes
	private boolean apartadoEspecialista; // Bandera para gestionar el apartado de especialistas
	private boolean apartadoEspecialidad; // Bandera para gestionar el apartado de especialidades

	private boolean solicitarE; // Bandera para solicitar una especialidad
	private boolean enviarRM; // Bandera para enviar resultados médicos
	private boolean generarD; // Bandera para generar un diagnóstico
	private boolean generarT; // Bandera para generar un tratamiento

	private boolean pacienteA; // Bandera para aceptar un paciente
	private boolean pacienteR; // Bandera para rechazar un paciente

	/**
	 * Constructor de la clase Controller. Inicializa las instancias de ModelFacade
	 * y ViewFacade, asigna los listeners a los componentes de la vista y carga las
	 * propiedades de configuración.
	 */
	public Controller() {
		mf = new ModelFacade();
		vf = new ViewFacade();

		asignarLectores();

		vf.getVp().getPanelEntrada().getImg().setVisible(true);
		vf.getVp().mostrarPanelWelcome();

		prop = FileHandler.loadProperties("config.properties");

		fillCombxEspecialidades();
	}

	/**
	 * Asigna los listeners a los componentes de la interfaz de usuario. Este método
	 * configura los eventos que serán escuchados por el controlador.
	 */
	public void asignarLectores() {
		// WELCOME
		// 3
		vf.getVp().getPanelEntrada().getBtnDirector().addActionListener(this);
		vf.getVp().getPanelEntrada().getBtnDirector().setActionCommand("DIRECTOR");

		vf.getVp().getPanelEntrada().getBtnEspecialista().addActionListener(this);
		vf.getVp().getPanelEntrada().getBtnEspecialista().setActionCommand("ESPECIALISTA");

		vf.getVp().getPanelEntrada().getBtnPaciente().addActionListener(this);
		vf.getVp().getPanelEntrada().getBtnPaciente().setActionCommand("PACIENTE");

		// LOGIN
		// 2
		vf.getVp().getPanelLogin().getBtnEntrar().addActionListener(this);
		vf.getVp().getPanelLogin().getBtnEntrar().setActionCommand("ENTRAR");

		vf.getVp().getPanelLogin().getBtnVolver().addActionListener(this);
		vf.getVp().getPanelLogin().getBtnVolver().setActionCommand("VOLVER");

		// HOME DIRECTOR
		// 6
		vf.getVp().getPanelHDirector().getBtnVolver().addActionListener(this);
		vf.getVp().getPanelHDirector().getBtnVolver().setActionCommand("VOLVERHOMEDIRECTOR");

		vf.getVp().getPanelHDirector().getBtnEliminar().addActionListener(this);
		vf.getVp().getPanelHDirector().getBtnEliminar().setActionCommand("ELIMINARPERFIL");

		vf.getVp().getPanelHDirector().getBtnModificar().addActionListener(this);
		vf.getVp().getPanelHDirector().getBtnModificar().setActionCommand("MODIFICARPERFIL");

		vf.getVp().getPanelHDirector().getBtnPacientes().addActionListener(this);
		vf.getVp().getPanelHDirector().getBtnPacientes().setActionCommand("PACIENTES");

		vf.getVp().getPanelHDirector().getBtnEspecialistas().addActionListener(this);
		vf.getVp().getPanelHDirector().getBtnEspecialistas().setActionCommand("ESPECIALISTAS");

		vf.getVp().getPanelHDirector().getBtnEspecialidades().addActionListener(this);
		vf.getVp().getPanelHDirector().getBtnEspecialidades().setActionCommand("ESPECIALIDADES");

		// HOME ESPECIALISTA
		vf.getVp().getPanelHEspecialista().getBtnVolver().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnVolver().setActionCommand("VOLVERHOMEESPECIALISTA");

		vf.getVp().getPanelHEspecialista().getBtnEnviar().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnEnviar().setActionCommand("ENVIARC");

		vf.getVp().getPanelHEspecialista().getBtnVer().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnVer().setActionCommand("VERTURNOS");

		vf.getVp().getPanelHEspecialista().getBtnSolicitarE().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnSolicitarE().setActionCommand("SOLICITARE");

		vf.getVp().getPanelHEspecialista().getBtnEnviarRM().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnEnviarRM().setActionCommand("ENVIARR");

		vf.getVp().getPanelHEspecialista().getBtnGenerarD().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnGenerarD().setActionCommand("GENERARD");

		vf.getVp().getPanelHEspecialista().getBtnGenerarT().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnGenerarT().setActionCommand("GENERART");

		vf.getVp().getPanelHEspecialista().getBtnChange().addActionListener(this);
		vf.getVp().getPanelHEspecialista().getBtnChange().setActionCommand("CAMBIART");

		// HOME PACIENTE
		vf.getVp().getPanelHPaciente().getBtnAgendar().addActionListener(this);
		vf.getVp().getPanelHPaciente().getBtnAgendar().setActionCommand("AGENDARC");

		vf.getVp().getPanelHPaciente().getBtnReprogramar().addActionListener(this);
		vf.getVp().getPanelHPaciente().getBtnReprogramar().setActionCommand("REPROGRAMARC");

		vf.getVp().getPanelHPaciente().getBtnCancelar().addActionListener(this);
		vf.getVp().getPanelHPaciente().getBtnCancelar().setActionCommand("CANCELARC");

		vf.getVp().getPanelHPaciente().getBtnVolver().addActionListener(this);
		vf.getVp().getPanelHPaciente().getBtnVolver().setActionCommand("VOLVERHPACIENTE");

		// APARTADOS
		vf.getVp().getPanelApartado().getBtnVolver().addActionListener(this);
		vf.getVp().getPanelApartado().getBtnVolver().setActionCommand("APARTADOBACK");

		vf.getVp().getPanelApartado().getBtnVer().addActionListener(this);
		vf.getVp().getPanelApartado().getBtnVer().setActionCommand("APARTADOVER");

		vf.getVp().getPanelApartado().getBtnAgregar().addActionListener(this);
		vf.getVp().getPanelApartado().getBtnAgregar().setActionCommand("APARTADOADD");

		vf.getVp().getPanelApartado().getBtnMod().addActionListener(this);
		vf.getVp().getPanelApartado().getBtnMod().setActionCommand("APARTADOMOD");

		vf.getVp().getPanelApartado().getBtnDelete().addActionListener(this);
		vf.getVp().getPanelApartado().getBtnDelete().setActionCommand("APARTADODEL");

		// Input
		// 2
		vf.getVp().getPanelInput().getBtnVolver().addActionListener(this);
		vf.getVp().getPanelInput().getBtnVolver().setActionCommand("VOLVERINPUT");

		vf.getVp().getPanelInput().getBtnGuardar().addActionListener(this);
		vf.getVp().getPanelInput().getBtnGuardar().setActionCommand("GUARDAR");

	}

	/**
	 * Método que maneja los eventos de acción generados por los componentes de la
	 * interfaz.
	 * 
	 * @param e el evento de acción que fue generado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		// WELCOME
		case "DIRECTOR":
			vf.getVp().getPanelEntrada().getImg().setVisible(false);
			vf.getVp().getPanelLogin().getImgD().setVisible(true);
			vf.getVp().getPanelLogin().getImgE().setVisible(false);
			loginDirector = true;
			loginEspecialista = false;

			if (mf.getDirectorDAO().checkAdmin() == false) {
				createDirector = true;
				vf.getVp().getPanelInput().getCmbxEspecialidad().setVisible(false);
				vf.getVp().getPanelInput().getImgD().setVisible(true);
				vf.getVp().getPanelInput().getImgE().setVisible(false);
				vf.getVp().getPanelInput().getImgP().setVisible(false);
				vf.getVp().mostrarPanelInput();

			} else {
				vf.getVp().mostrarPanelLogin();
			}

			break;

		case "ESPECIALISTA":
			vf.getVp().getPanelEntrada().getImg().setVisible(false);
			vf.getVp().getPanelLogin().getImgE().setVisible(true);
			vf.getVp().getPanelLogin().getImgD().setVisible(false);
			vf.getVp().mostrarPanelLogin();
			loginDirector = false;
			loginEspecialista = true;
			break;

		case "PACIENTE":
			createPaciente = true;

			vf.getVp().mostrarPanelHomePaciente();

			break;

		// LOGIN
		case "ENTRAR":
			if (loginDirector) {

				try {
					long id = Long.parseLong(vf.getVp().getPanelLogin().getTxtId().getText());
					ExceptionChecker.notValidIdException(id);

					long password = Long.parseLong(vf.getVp().getPanelLogin().getTxtPwd().getText());
					ExceptionChecker.notValidPasswordException(password);
					vf.getCon().mostrarAlerta(mf.getDirectorDAO().verifyPassword(id, password));
					if (mf.getDirectorDAO().checkLogIn(id, password) == true) {

						directorTempId = id;
						vf.getVp().getPanelHDirector().getTxtId().setText(directorTempId + "");
						vf.getVp().getPanelHDirector().getTxtName()
								.setText(mf.getDirectorDAO().pickData(directorTempId, true, false));

						vf.getVp().getPanelHDirector().getTxtGmail()
								.setText(mf.getDirectorDAO().pickData(directorTempId, false, true));

						vf.getVp().mostrarPanelHomeDirector();

					}

				} catch (NotValidIdException j) {
					vf.getCon().mostrarAlerta("Formato de id no valido, verifique que tenga 10 digitos");
					vf.getVp().getPanelLogin().getTxtId().setText("");
					vf.getVp().getPanelLogin().getTxtPwd().setText("");
				} catch (NotValidPasswordException j) {
					vf.getVp().getPanelLogin().getTxtId().setText("");
					vf.getVp().getPanelLogin().getTxtPwd().setText("");
					vf.getCon().mostrarAlerta("Formato de contraseña no valido, verifique su contraseña");
				} catch (NumberFormatException e2) {
					vf.getCon().mostrarAlerta("Ingrese sus datos");
				}
			}

			if (loginEspecialista == true) {

				try {
					long id = Long.parseLong(vf.getVp().getPanelLogin().getTxtId().getText());
					ExceptionChecker.notValidIdException(id);

					long password = Long.parseLong(vf.getVp().getPanelLogin().getTxtPwd().getText());
					ExceptionChecker.notValidPasswordException(password);
					vf.getCon().mostrarAlerta(mf.getEspecialistaDAO().verifyPassword(id, password));
					if (mf.getEspecialistaDAO().checkLogIn(id, password) == true) {
						especialistaTempId = id;
						vf.getVp().getPanelHEspecialista().getTxtId().setText(especialistaTempId + "");
						vf.getVp().getPanelHEspecialista().getTxtName().setText(
								mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false, false));
						vf.getVp().getPanelHEspecialista().getTxtGmail().setText(
								mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, false, false, true));
						vf.getVp().mostrarPanelHomeEspecialista();
					}

				} catch (NotValidIdException j) {
					vf.getCon().mostrarAlerta("Formato de id no valido, verifique que tenga 10 digitos");
				} catch (NotValidPasswordException j) {
					vf.getCon().mostrarAlerta("Formato de contraseña no valido, verifique su contraseña");
				}
			}
			break;

		case "VOLVER":
			vf.getVp().getPanelLogin().getTxtId().setText("");
			vf.getVp().getPanelLogin().getTxtPwd().setText("");
			vf.getVp().getPanelEntrada().getImg().setVisible(true);
			vf.getVp().mostrarPanelWelcome();
			break;

		// HOME DIRECTOR
		case "ELIMINARPERFIL":
			int eliminar = vf.getCon().mostrarYesOrNo("Esta seguro de eliminar este perfil?");
			boolean com = vf.getCon().validarYesOrNo(eliminar);
			if (com) {
				mf.getDirectorDAO().delete(new DirectorDTO(directorTempId, null, 0, null, null, 0));
				vf.getCon().mostrarMensajeEmergente("Perfil eliminado");
				vf.getVp().getPanelLogin().getTxtId().setText("");
				vf.getVp().getPanelLogin().getTxtPwd().setText("");
				vf.getVp().mostrarPanelWelcome();
			} else {
				vf.getCon().mostrarMensajeEmergente("Operación cancelada");
			}

			break;

		case "MODIFICARPERFIL":
			int modificar = vf.getCon().mostrarYesOrNo("Esta seguro de modificar este perfil?");
			boolean mod = vf.getCon().validarYesOrNo(modificar);
			if (mod) {
				modDirector = true;
				vf.getVp().getPanelInput().getImgD().setVisible(true);
				vf.getVp().getPanelInput().getCmbxEspecialidad().setVisible(false);
				vf.getVp().mostrarPanelInput();
			} else {
				vf.getCon().mostrarMensajeEmergente("Operación cancelada");
			}
			break;

		case "PACIENTES":
			apartadoPaciente = true;
			vf.getVp().getPanelApartado().getImgP().setVisible(true);
			vf.getVp().getPanelApartado().getImgE().setVisible(false);
			vf.getVp().getPanelApartado().getImgA().setVisible(false);
			vf.getVp().mostrarPanelApartado();
			break;
		case "ESPECIALISTAS":
			apartadoEspecialista = true;
			vf.getVp().getPanelApartado().getImgP().setVisible(false);
			vf.getVp().getPanelApartado().getImgE().setVisible(true);
			vf.getVp().getPanelApartado().getImgA().setVisible(false);
			vf.getVp().mostrarPanelApartado();
			break;

		case "ESPECIALIDADES":
			apartadoEspecialidad = true;
			vf.getVp().getPanelApartado().getImgP().setVisible(false);
			vf.getVp().getPanelApartado().getImgE().setVisible(false);
			vf.getVp().getPanelApartado().getImgA().setVisible(true);
			vf.getVp().mostrarPanelApartado();
			break;

		case "VOLVERHOMEDIRECTOR":
			directorTempId = 0;
			vf.getVp().getPanelLogin().getTxtId().setText("");
			vf.getVp().getPanelLogin().getTxtPwd().setText("");
			vf.getVp().getPanelLogin().getImgE().setVisible(false);
			vf.getVp().getPanelLogin().getImgD().setVisible(true);
			vf.getVp().mostrarPanelLogin();

			break;

		// HOME ESPECIALISTA
		case "VERTURNOS":
			vf.getVp().getPanelHEspecialista().getScrollP().setVisible(true);
			vf.getVp().getPanelHEspecialista().getTabla().setVisible(true);
			setUpTurnsTable(mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false, false));

			break;

		case "SOLICITARE":
			vf.getVp().getPanelHEspecialista().getScrollP().setVisible(true);
			vf.getVp().getPanelHEspecialista().getTabla().setVisible(true);
			setUpTurnsTable(mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false, false));
			try {

				Long id = vf.getCon().pedirLong("Ingrese el número de identificación del paciente");
				if (id == null) {
					vf.getCon().mostrarAlerta("Entrada inválida");
				} else {
					ExceptionChecker.notValidIdException(id);
					vf.getCon().mostrarMensajeEmergente("Ingrese los exámenes a solicitar en el recuadro");
					vf.getVp().getPanelHEspecialista().getScrollP().setVisible(false);
					vf.getVp().getPanelHEspecialista().getTabla().setVisible(false);
					vf.getVp().getPanelHEspecialista().getActionTxt().setText("SOLICITANDO EXAMENES");
					vf.getVp().getPanelHEspecialista().getActionTxt().setVisible(true);
					vf.getVp().getPanelHEspecialista().getScroll().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setOpaque(true);

					solicitarE = true;
					pacienteTempId = id;
				}
			} catch (NotValidIdException j) {
				vf.getCon().mostrarAlerta(
						"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
			}

			break;
		case "ENVIARR":
			vf.getVp().getPanelHEspecialista().getScrollP().setVisible(true);
			vf.getVp().getPanelHEspecialista().getTabla().setVisible(true);
			setUpTurnsTable(mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false, false));
			try {

				Long id = vf.getCon().pedirLong("Ingrese el número de identificación del paciente");
				if (id == null) {
					vf.getCon().mostrarAlerta("Entrada inválida");
				} else {
					ExceptionChecker.notValidIdException(id);
					vf.getCon().mostrarMensajeEmergente("Ingrese los resultados de los exámenes en el recuadro");
					vf.getVp().getPanelHEspecialista().getScrollP().setVisible(false);
					vf.getVp().getPanelHEspecialista().getActionTxt().setText("ENVIANDO RESULTADOS EXAMEN");
					vf.getVp().getPanelHEspecialista().getActionTxt().setVisible(true);
					vf.getVp().getPanelHEspecialista().getScroll().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setOpaque(true);

					enviarRM = true;
					pacienteTempId = id;
				}
			} catch (NotValidIdException j) {
				vf.getCon().mostrarAlerta(
						"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
			}

			break;
		case "GENERARD":
			vf.getVp().getPanelHEspecialista().getScrollP().setVisible(true);
			vf.getVp().getPanelHEspecialista().getTabla().setVisible(true);
			setUpTurnsTable(mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false, false));
			try {

				Long id = vf.getCon().pedirLong("Ingrese el número de identificación del paciente");
				if (id == null) {
					vf.getCon().mostrarAlerta("Entrada inválida");
				} else {
					ExceptionChecker.notValidIdException(id);
					vf.getCon().mostrarMensajeEmergente("Ingrese el diagnóstico en el recuadro");
					vf.getVp().getPanelHEspecialista().getScrollP().setVisible(false);
					vf.getVp().getPanelHEspecialista().getActionTxt().setText("GENERANDO DIAGNOSTICO");
					vf.getVp().getPanelHEspecialista().getActionTxt().setVisible(true);
					vf.getVp().getPanelHEspecialista().getScroll().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setText("GUARDAR");
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setOpaque(true);

					generarD = true;
					pacienteTempId = id;
				}
			} catch (NotValidIdException j) {
				vf.getCon().mostrarAlerta(
						"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
			}
			break;
		case "GENERART":
			vf.getVp().getPanelHEspecialista().getScrollP().setVisible(true);
			vf.getVp().getPanelHEspecialista().getTabla().setVisible(true);
			setUpTurnsTable(mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false, false));
			try {

				Long id = vf.getCon().pedirLong("Ingrese el número de identificación del paciente");
				if (id == null) {
					vf.getCon().mostrarAlerta("Entrada inválida");
				} else {
					ExceptionChecker.notValidIdException(id);
					vf.getCon().mostrarMensajeEmergente("Ingrese el diagnóstico en el recuadro");
					vf.getVp().getPanelHEspecialista().getScrollP().setVisible(false);
					vf.getVp().getPanelHEspecialista().getActionTxt().setText("GENERANDO TRATAMIENDO");
					vf.getVp().getPanelHEspecialista().getScroll().setVisible(true);
					vf.getVp().getPanelHEspecialista().getActionTxt().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setText("ENVIAR");
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setVisible(true);
					vf.getVp().getPanelHEspecialista().getBtnEnviar().setOpaque(true);

					generarT = true;
					pacienteTempId = id;
				}
			} catch (NotValidIdException j) {
				vf.getCon().mostrarAlerta(
						"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
			}
			break;
		case "CAMBIART":
			vf.getCon().mostrarError("Esta función no se encuentra disponible, proximamente....");
			break;
		case "ENVIARC":
			vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");

			if (solicitarE) {
				String areaEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, false, true,
						false);
				String nombreEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false,
						false);
				String examenSoli = vf.getVp().getPanelHEspecialista().getTxtEntrada().getText();
				EmailController.sendExaminationRequested(
						mf.getPacienteDAO().pickData(pacienteTempId, areaEspecialista, true, false),
						mf.getPacienteDAO().pickData(pacienteTempId, areaEspecialista, false, true), nombreEspecialista,
						examenSoli);
				vf.getVp().getPanelHEspecialista().getTxtEntrada().setText("");
			}

			if (enviarRM) {
				String areaEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, false, true,
						false);
				String nombreEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false,
						false);
				String resultados = vf.getVp().getPanelHEspecialista().getTxtEntrada().getText();
				EmailController.sendExaminationRequestedResults(
						mf.getPacienteDAO().pickData(pacienteTempId, areaEspecialista, true, false),
						mf.getPacienteDAO().pickData(pacienteTempId, areaEspecialista, false, true), nombreEspecialista,
						resultados);
				vf.getVp().getPanelHEspecialista().getTxtEntrada().setText("");

			}

			if (generarD) {

				String diagnos = vf.getVp().getPanelHEspecialista().getTxtEntrada().getText();

				int seguimiento = vf.getCon().mostrarYesOrNo("El paciente requiere seguimiento?");
				boolean esSeg = vf.getCon().validarYesOrNo(seguimiento);
				vf.getCon().mostrarMensajeEmergente(
						mf.getPacienteDAO().setData(pacienteTempId, null, false, diagnos, true, esSeg, true));
				vf.getVp().getPanelHEspecialista().getBtnEnviar().setText("ENVIAR");
				vf.getVp().getPanelHEspecialista().getTxtEntrada().setText("");

			}

			if (generarT) {
				String areaEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, false, true,
						false);
				String nombreEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false,
						false);
				String tratamiento = vf.getVp().getPanelHEspecialista().getTxtEntrada().getText();

				EmailController.sendTreatment(
						mf.getPacienteDAO().pickData(pacienteTempId, areaEspecialista, true, false),
						mf.getPacienteDAO().pickData(pacienteTempId, areaEspecialista, false, true), tratamiento,
						nombreEspecialista);
				vf.getVp().getPanelHEspecialista().getTxtEntrada().setText("");

			}

			solicitarE = false;
			enviarRM = false;
			generarD = false;
			generarT = false;
			vf.getVp().getPanelHEspecialista().getBtnEnviar().setVisible(false);
			vf.getVp().getPanelHEspecialista().getScroll().setVisible(false);
			vf.getVp().getPanelHEspecialista().getActionTxt().setVisible(false);

			break;
		case "VOLVERHOMEESPECIALISTA":
			especialistaTempId = 0;
			vf.getVp().getPanelLogin().getTxtId().setText("");
			vf.getVp().getPanelLogin().getTxtPwd().setText("");
			vf.getVp().getPanelLogin().getImgE().setVisible(true);
			vf.getVp().getPanelLogin().getImgD().setVisible(false);
			vf.getVp().mostrarPanelLogin();
			break;

		// APARTADOS
		case "APARTADOVER":
			vf.getVp().getPanelApartado().getScroll().setVisible(true);
			vf.getVp().getPanelApartado().getTabla().setVisible(true);

			if (apartadoPaciente)
				setUpPatientTable();

			if (apartadoEspecialista)
				setUpEspecialistTable();

			if (apartadoEspecialidad)
				setUpAreaTable();
			break;

		case "APARTADOADD":
			if (apartadoPaciente) {
				createPaciente = true;

				vf.getVp().getPanelInput().getImgE().setVisible(false);
				vf.getVp().getPanelInput().getImgP().setVisible(true);
				vf.getVp().getPanelInput().getImgD().setVisible(false);
				vf.getVp().getPanelInput().getCmbxEspecialidad().setVisible(true);
				vf.getVp().getPanelInput().getCmbxGenero().setVisible(true);
				vf.getVp().getPanelInput().getTxtId().setVisible(true);
				vf.getVp().getPanelInput().getTxtName().setVisible(true);
				vf.getVp().getPanelInput().getTxtEdad().setVisible(true);
				vf.getVp().getPanelInput().getTxtConfirmPswd().setVisible(false);
				vf.getVp().getPanelInput().getTxtPswd().setVisible(false);
				vf.getVp().getPanelInput().getTxtGmail().setVisible(true);

				vf.getVp().mostrarPanelInput();

			}

			if (apartadoEspecialidad) {
				String especialidad = vf.getCon().pedirString("Ingrese el nombre de la especialidad a agregar");
				if (especialidad != null) {
					if (mf.getEspecialidadDAO().add(new EspecialidadDTO(especialidad))) {
						vf.getCon().mostrarMensajeEmergente("Especialidad agregada");
						especialidades = mf.getEspecialidadDAO().getAll();
						fillCombxEspecialidades();
					}
				}

				else
					vf.getCon().mostrarAlerta("Intente nuevamente");
			}

			if (apartadoEspecialista) {
				createEspecialista = true;
				vf.getVp().getPanelInput().getImgE().setVisible(true);
				vf.getVp().getPanelInput().getImgD().setVisible(false);
				vf.getVp().getPanelInput().getImgP().setVisible(false);
				vf.getVp().getPanelInput().getCmbxEspecialidad().setVisible(true);
				vf.getVp().getPanelInput().getCmbxGenero().setVisible(true);
				vf.getVp().getPanelInput().getTxtId().setVisible(true);
				vf.getVp().getPanelInput().getTxtName().setVisible(true);
				vf.getVp().getPanelInput().getTxtEdad().setVisible(true);
				vf.getVp().getPanelInput().getTxtConfirmPswd().setVisible(true);
				vf.getVp().getPanelInput().getTxtPswd().setVisible(true);
				vf.getVp().getPanelInput().getTxtGmail().setVisible(true);

				vf.getVp().mostrarPanelInput();
			}

			break;
		case "APARTADOMOD":

			if (apartadoEspecialidad) {
				String msg[] = mf.getEspecialidadDAO().getAll();
				int op = vf.getCon().pedirOpcionDeLista("Especialidad a modifical", msg);
				if (op >= 0) {

					String name = especialidades[op];
					String newName = vf.getCon().pedirString("Ingrese el nombre de la especialidad: ");
					if (newName != null) {

						if (mf.getEspecialidadDAO().update(new EspecialidadDTO(name), new EspecialidadDTO(newName))) {
							vf.getCon().mostrarMensajeEmergente("Operacion Completada");
							especialidades = mf.getEspecialidadDAO().getAll();
							fillCombxEspecialidades();
						}
					}
				} else {
					vf.getCon().mostrarAlerta("Operacion incompleta");
				}
				break;
			}

			if (apartadoPaciente) {
				modPaciente = true;
				try {
					int op = vf.getCon().pedirOpcionDeLista(
							"Ingrese la especialidad a la cual esta citado el paciente: ", especialidades);
					ExceptionChecker.negativeIntNumberException(op);
					if (op < 0) {
						vf.getCon().mostrarAlerta("Entrada Inválida");
					} else {
						vf.getVp().getPanelApartado().getScroll().setVisible(true);
						vf.getVp().getPanelApartado().getTabla().setVisible(true);
						setUpPatientSpecialtyTable(especialidades[op]);
						Long id = vf.getCon().pedirLong("Ingrese el número de identificación");

						if (id == null) {
							vf.getCon().mostrarAlerta("Entrada inválida");
						} else {
							ExceptionChecker.notValidIdException(id);

							pacienteTempECita = especialidades[op];
							pacienteTempId = id;
							vf.getVp().getPanelInput().getImgD().setVisible(false);
							vf.getVp().getPanelInput().getImgE().setVisible(false);
							vf.getVp().getPanelInput().getImgP().setVisible(true);
							vf.getVp().mostrarPanelInput();
						}
					}

				} catch (NegativeIntNumberException j) {
					vf.getCon().mostrarAlerta("Entrada no valida, solo ingrese numeros enteros positivos");
				} catch (NotValidIdException j) {
					vf.getCon().mostrarAlerta(
							"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
				}

			}

			if (apartadoEspecialista) {
				modEspecialista = true;
				try {
					int op = vf.getCon().pedirOpcionDeLista(
							"Ingrese la especialidad a la cual pertenece el especialista: ", especialidades);
					ExceptionChecker.negativeIntNumberException(op);
					if (op < 0) {
						vf.getCon().mostrarAlerta("Entrada Inválida");
					} else {

						vf.getVp().getPanelApartado().getScroll().setVisible(true);
						vf.getVp().getPanelApartado().getTabla().setVisible(true);
						setUpEspecialistAreaTable(especialidades[op]);
						Long id = vf.getCon().pedirLong("Ingrese el número de identificación");
						if (id == null) {
							vf.getCon().mostrarAlerta("Entrada inválida");
						} else {
							ExceptionChecker.notValidIdException(id);
							especialistaTempId = id;
							vf.getVp().getPanelInput().getImgD().setVisible(false);
							vf.getVp().getPanelInput().getImgE().setVisible(true);
							vf.getVp().getPanelInput().getImgP().setVisible(false);
							vf.getVp().mostrarPanelInput();
						}
					}
				} catch (NegativeIntNumberException j) {
					vf.getCon().mostrarAlerta("Entrada no valida, solo ingrese numeros enteros positivos");
				} catch (NotValidIdException j) {
					vf.getCon().mostrarAlerta(
							"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
				}

			}

			break;

		case "APARTADODEL":
			if (apartadoEspecialidad) {
				String msg[] = mf.getEspecialidadDAO().getAll();
				int op = vf.getCon().pedirOpcionDeLista("Especialidad a eliminar", msg);
				if (op >= 0) {
					if (mf.getEspecialidadDAO().delete(new EspecialidadDTO(especialidades[op]))) {
						vf.getCon().mostrarMensajeEmergente("Operacion completada");
						especialidades = mf.getEspecialidadDAO().getAll();
						fillCombxEspecialidades();
					}
				} else {
					vf.getCon().mostrarAlerta("Operacion incompleta");
				}

			}

			if (apartadoEspecialista) {
				try {
					int op = vf.getCon().pedirOpcionDeLista(
							"Ingrese la especialidad a la cual pertenece el especialista: ", especialidades);
					ExceptionChecker.negativeIntNumberException(op);
					if (op < 0) {
						vf.getCon().mostrarAlerta("Entrada Inválida");
					} else {
						vf.getVp().getPanelApartado().getScroll().setVisible(true);
						vf.getVp().getPanelApartado().getTabla().setVisible(true);
						setUpEspecialistAreaTable(especialidades[op]);
						Long id = vf.getCon().pedirLong("Ingrese el número de identificación");
						if (id == null) {
							vf.getCon().mostrarAlerta("Entrada Inválida");
						} else {
							ExceptionChecker.notValidIdException(id);

							String nombre = mf.getEspecialistaDAO().pickSpecialistData(id, true, false, false);

							if (mf.getEspecialistaDAO().delete(new EspecialistaDTO(id, null, 0, null, null, null, 0))) {
								vf.getCon().mostrarMensajeEmergente("El especialista " + nombre + " ha sido eliminado");
							} else {
								vf.getCon().mostrarAlerta("Operacion incompleta");
							}
						}
					}

				} catch (NegativeIntNumberException j) {
					vf.getCon().mostrarAlerta("Entrada no valida, solo ingrese numeros enteros positivos");
				} catch (NotValidIdException j) {
					vf.getCon().mostrarAlerta(
							"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
				}

			}

			if (apartadoPaciente) {
				try {
					int op = vf.getCon().pedirOpcionDeLista("Ingrese la especialidad a la cual esta asignada la cita: ",
							especialidades);
					ExceptionChecker.negativeIntNumberException(op);
					if (op < 0) {
						vf.getCon().mostrarAlerta("Entrada Inválida");
					} else {
						vf.getVp().getPanelApartado().getScroll().setVisible(true);
						vf.getVp().getPanelApartado().getTabla().setVisible(true);
						setUpPatientSpecialtyTable(especialidades[op]);
						Long id = vf.getCon().pedirLong("Ingrese el número de identificación");
						if (id == null) {
							vf.getCon().mostrarAlerta("Entrada Inválida");
						} else {
							ExceptionChecker.notValidIdException(id);
							String tempCorreo = mf.getPacienteDAO().pickData(id, especialidades[op], true, false);
							String nombre = mf.getPacienteDAO().pickData(id, especialidades[op], false, true);

							if (mf.getPacienteDAO().delete(new PacienteDTO(id, null, 0, null, null, null, null,
									especialidades[op], null, false))) {
								vf.getCon().mostrarAlerta("Paciente " + nombre + " eliminado");
								vf.getCon()
										.mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
								EmailController.sendCanceled(tempCorreo, nombre);
							} else {
								vf.getCon().mostrarAlerta("Operacion incompleta");
							}
						}
					}

				} catch (NegativeIntNumberException j) {
					vf.getCon().mostrarAlerta("Entrada no valida, solo ingrese numeros enteros positivos");
				} catch (NotValidIdException j) {
					vf.getCon().mostrarAlerta(
							"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
				}

			}

			break;

		case "APARTADOBACK":
			vf.getVp().mostrarPanelHomeDirector();
			apartadoEspecialidad = false;
			apartadoEspecialista = false;
			apartadoPaciente = false;
			createDirector = false;
			createEspecialista = false;
			createPaciente = false;
			modDirector = false;
			modEspecialista = false;
			modPaciente = false;
			break;

		// HOME PACIENTE

		case "AGENDARC":
			pacienteA = true;
			createPaciente = true;

			vf.getVp().getPanelInput().getImgE().setVisible(false);
			vf.getVp().getPanelInput().getImgP().setVisible(true);
			vf.getVp().getPanelInput().getImgD().setVisible(false);
			vf.getVp().getPanelInput().getCmbxEspecialidad().setVisible(true);
			vf.getVp().getPanelInput().getCmbxGenero().setVisible(true);
			vf.getVp().getPanelInput().getTxtId().setVisible(true);
			vf.getVp().getPanelInput().getTxtName().setVisible(true);
			vf.getVp().getPanelInput().getTxtEdad().setVisible(true);
			vf.getVp().getPanelInput().getTxtConfirmPswd().setVisible(false);
			vf.getVp().getPanelInput().getTxtPswd().setVisible(false);
			vf.getVp().getPanelInput().getTxtGmail().setVisible(true);

			vf.getVp().mostrarPanelInput();
			break;

		case "REPROGRAMARC":
			pacienteR = true;
			modPaciente = true;
			try {
				int op = vf.getCon().pedirOpcionDeLista("Ingrese la especialidad a la cual esta citado el paciente: ",
						especialidades);
				ExceptionChecker.negativeIntNumberException(op);
				if (op < 0) {
					vf.getCon().mostrarAlerta("Entrada Inválida");
				} else {
					vf.getVp().getPanelApartado().getScroll().setVisible(true);
					vf.getVp().getPanelApartado().getTabla().setVisible(true);
					setUpPatientSpecialtyTable(especialidades[op]);
					Long id = vf.getCon().pedirLong("Ingrese el número de identificación");

					if (id == null) {
						vf.getCon().mostrarAlerta("Entrada inválida");
					} else {
						ExceptionChecker.notValidIdException(id);

						pacienteTempECita = especialidades[op];
						pacienteTempId = id;
						vf.getVp().getPanelInput().getImgD().setVisible(false);
						vf.getVp().getPanelInput().getImgE().setVisible(false);
						vf.getVp().getPanelInput().getImgP().setVisible(true);
						vf.getVp().mostrarPanelInput();
					}
				}

			} catch (NegativeIntNumberException j) {
				vf.getCon().mostrarAlerta("Entrada no valida, solo ingrese numeros enteros positivos");
			} catch (NotValidIdException j) {
				vf.getCon().mostrarAlerta(
						"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
			}

			break;

		case "CANCELARC":
			try {
				int op = vf.getCon().pedirOpcionDeLista("Ingrese la especialidad a la cual esta asignada la cita: ",
						especialidades);
				ExceptionChecker.negativeIntNumberException(op);
				if (op < 0) {
					vf.getCon().mostrarAlerta("Entrada Inválida");
				} else {
					Long id = vf.getCon().pedirLong("Ingrese el número de identificación");
					if (id == null) {
						vf.getCon().mostrarAlerta("Entrada Inválida");
					} else {
						ExceptionChecker.notValidIdException(id);
						String tempCorreo = mf.getPacienteDAO().pickData(id, especialidades[op], true, false);
						String nombre = mf.getPacienteDAO().pickData(id, especialidades[op], false, true);

						if (mf.getPacienteDAO().delete(new PacienteDTO(id, null, 0, null, null, null, null,
								especialidades[op], null, false))) {
							vf.getCon().mostrarAlerta("Paciente " + nombre + " eliminado");
							vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
							EmailController.sendCanceled(tempCorreo, nombre);
						} else {
							vf.getCon().mostrarAlerta("Operacion incompleta");
						}
					}
				}

			} catch (NegativeIntNumberException j) {
				vf.getCon().mostrarAlerta("Entrada no valida, solo ingrese numeros enteros positivos");
			} catch (NotValidIdException j) {
				vf.getCon().mostrarAlerta(
						"Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
			}

			break;

		case "VOLVERHPACIENTE":
			vf.getVp().mostrarPanelWelcome();
			pacienteA = false;
			pacienteR = false;
			break;

		// INPUT

		case "GUARDAR":
			if (createPaciente) {
				getDatosPaciente(true, false);
			}
			if (createDirector) {
				getDatosDirector(true, false);
			}
			if (createEspecialista) {
				getDatosEspecialista(true, false);
			}

			if (modDirector) {
				getDatosDirector(false, true);
			}
			if (modPaciente) {
				getDatosPaciente(false, true);
			}
			if (modEspecialista) {
				getDatosEspecialista(false, true);
			}

			vf.getVp().getPanelInput().getTxtId().setText("");
			vf.getVp().getPanelInput().getTxtName().setText("");
			vf.getVp().getPanelInput().getTxtEdad().setText("");
			vf.getVp().getPanelInput().getTxtConfirmPswd().setText("");
			vf.getVp().getPanelInput().getTxtPswd().setText("");
			vf.getVp().getPanelInput().getTxtGmail().setText("");

			createDirector = false;
			modDirector = false;
			createEspecialista = false;
			modEspecialista = false;
			createPaciente = false;
			modPaciente = false;
			pacienteA = false;
			pacienteR = false;
			break;

		case "VOLVERINPUT":

			if (apartadoEspecialista) {
				vf.getVp().getPanelApartado().getImgA().setVisible(false);
				vf.getVp().getPanelApartado().getImgE().setVisible(true);
				vf.getVp().getPanelApartado().getImgP().setVisible(false);
				vf.getVp().mostrarPanelApartado();
			}

			if (apartadoPaciente) {
				vf.getVp().getPanelApartado().getImgA().setVisible(false);
				vf.getVp().getPanelApartado().getImgE().setVisible(false);
				vf.getVp().getPanelApartado().getImgP().setVisible(true);
				vf.getVp().mostrarPanelApartado();
			}

			if (pacienteR || pacienteA) {
				pacienteA = false;
				pacienteR = false;
				vf.getVp().mostrarPanelApartado();
			}

			vf.getVp().getPanelInput().getTxtId().setText("");
			vf.getVp().getPanelInput().getTxtName().setText("");
			vf.getVp().getPanelInput().getTxtEdad().setText("");
			vf.getVp().getPanelInput().getTxtConfirmPswd().setText("");
			vf.getVp().getPanelInput().getTxtPswd().setText("");
			vf.getVp().getPanelInput().getTxtGmail().setText("");

			createDirector = false;
			modDirector = false;
			createEspecialista = false;
			modEspecialista = false;
			createPaciente = false;
			modPaciente = false;
			pacienteA = false;
			pacienteR = false;
			break;
		}

	}
	/**
     * Método que recibe los datos del director.
     * @param leer y extraer el texto del director.
     */
	public void getDatosDirector(boolean create, boolean update) {
		try {
			long id = Long.parseLong(vf.getVp().getPanelInput().getTxtId().getText());
			ExceptionChecker.notValidIdException(id);

			String nombre = vf.getVp().getPanelInput().getTxtName().getText();
			ExceptionChecker.notValidStringInputException(nombre);

			int edad = Integer.parseInt(vf.getVp().getPanelInput().getTxtEdad().getText());
			ExceptionChecker.negativeIntNumberException(edad);

			String genero = vf.getVp().getPanelInput().getCmbxGenero().getSelectedItem().toString();

			String correo = vf.getVp().getPanelInput().getTxtGmail().getText();
			ExceptionChecker.checkEmail(correo);

			long password = Long.parseLong(vf.getVp().getPanelInput().getTxtPswd().getText());
			ExceptionChecker.notValidPasswordException(password);

			long confirmP = Long.parseLong(vf.getVp().getPanelInput().getTxtConfirmPswd().getText());
			ExceptionChecker.notValidPasswordException(confirmP);
			if (password != confirmP) {
				vf.getCon().mostrarAlerta("No coinciden las contraseñas, vuelva a ingresarlas");
				vf.getVp().getPanelInput().getTxtPswd().setText("");
				vf.getVp().getPanelInput().getTxtConfirmPswd().setText("");
			} else if (password == confirmP) {

				if (create == true && update == false) {
					if (mf.getDirectorDAO().add(new DirectorDTO(id, nombre, edad, genero, correo, password)) == true) {
						vf.getCon().mostrarMensajeEmergente("Director creado exitosamente");
						vf.getVp().getPanelHDirector().getTxtId().setText(directorTempId + "");
						vf.getVp().getPanelHDirector().getTxtName()
								.setText(mf.getDirectorDAO().pickData(directorTempId, true, false));

						vf.getVp().getPanelHDirector().getTxtGmail()
								.setText(mf.getDirectorDAO().pickData(directorTempId, false, true));
						vf.getVp().mostrarPanelHomeDirector();
					} else {
						vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
					}
				} else if (update == true && create == false) {
					if (mf.getDirectorDAO().update(new DirectorDTO(directorTempId, null, 0, null, null, 0),
							new DirectorDTO(id, nombre, edad, genero, correo, password)) == true) {
						vf.getCon().mostrarMensajeEmergente("Director actualizado exitosamente");
						directorTempId = id;
						vf.getVp().getPanelHDirector().getTxtId().setText(directorTempId + "");
						vf.getVp().getPanelHDirector().getTxtName()
								.setText(mf.getDirectorDAO().pickData(directorTempId, true, false));

						vf.getVp().getPanelHDirector().getTxtGmail()
								.setText(mf.getDirectorDAO().pickData(directorTempId, false, true));
						vf.getVp().mostrarPanelHomeDirector();
					} else {
						vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
					}
				}
			}

		} catch (NotValidIdException e) {
			vf.getCon()
					.mostrarAlerta("Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().mostrarAlerta("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().mostrarAlerta("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().mostrarAlerta("Formato de correo no valido");
		} catch (NotValidPasswordException e) {
			vf.getCon().mostrarAlerta("Formato de contraseña no valido, minimo 5 digitos y maximo 10 digitos");
			vf.getVp().getPanelInput().getTxtPswd().setText("");
			vf.getVp().getPanelInput().getTxtConfirmPswd().setText("");
		} catch (InputMismatchException e) {
			vf.getCon().mostrarAlerta("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}

	/**
     * Método que recibe los datos del especialista.
     * @param leer y extraer el texto del especialista.
     */
	public void getDatosEspecialista(boolean create, boolean update) {
		try {
			long id = Long.parseLong(vf.getVp().getPanelInput().getTxtId().getText());
			ExceptionChecker.notValidIdException(id);

			String nombre = vf.getVp().getPanelInput().getTxtName().getText();
			ExceptionChecker.notValidStringInputException(nombre);

			int edad = Integer.parseInt(vf.getVp().getPanelInput().getTxtEdad().getText());
			ExceptionChecker.negativeIntNumberException(edad);

			String genero = vf.getVp().getPanelInput().getCmbxGenero().getSelectedItem().toString();

			String especialidad = vf.getVp().getPanelInput().getCmbxEspecialidad().getSelectedItem().toString();

			String correo = vf.getVp().getPanelInput().getTxtGmail().getText();
			ExceptionChecker.checkEmail(correo);

			long password = Long.parseLong(vf.getVp().getPanelInput().getTxtPswd().getText());
			ExceptionChecker.notValidPasswordException(password);

			long confirmP = Long.parseLong(vf.getVp().getPanelInput().getTxtConfirmPswd().getText());
			ExceptionChecker.notValidPasswordException(confirmP);

			if (password != confirmP) {
				vf.getCon().mostrarAlerta("No coinciden las contraseñas, vuelva a ingresarlas");
				vf.getVp().getPanelInput().getTxtPswd().setText("");
				vf.getVp().getPanelInput().getTxtConfirmPswd().setText("");
			}

			if (mf.getEspecialistaDAO().setLimitSpecialist(
					Integer.parseInt(prop.getProperty("bosquehealth.especialidad.limite")), especialidad) == true) {
				vf.getCon().mostrarAlerta("No es posible agregar mas especialistas al area, llego a su limite");
			} else {

				if (create == true && update == false) {
					if (mf.getEspecialistaDAO().add(
							new EspecialistaDTO(id, nombre, edad, genero, correo, especialidad, password)) == true) {
						if (apartadoEspecialista) {
							vf.getVp().getPanelApartado().getImgE().setVisible(true);
							vf.getVp().getPanelApartado().getImgA().setVisible(false);
							vf.getVp().getPanelApartado().getImgP().setVisible(false);
							vf.getVp().mostrarPanelApartado();
							vf.getCon().mostrarMensajeEmergente("Especialista creado con exito");
						}
						vf.getCon().mostrarMensajeEmergente("Especialista creado con exito");
					} else {
						vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
					}

				} else if (update == true && create == false) {
					if (mf.getEspecialistaDAO().update(
							new EspecialistaDTO(especialistaTempId, null, 0, null, null, null, 0),
							new EspecialistaDTO(id, nombre, edad, genero, correo, especialidad, password)) == true) {
						if (apartadoEspecialista) {
							vf.getVp().getPanelApartado().getImgE().setVisible(true);
							vf.getVp().getPanelApartado().getImgA().setVisible(false);
							vf.getVp().getPanelApartado().getImgP().setVisible(false);
							vf.getVp().mostrarPanelApartado();
							vf.getCon().mostrarMensajeEmergente("Especialista actualizado con exito");
						} else {
							vf.getCon().mostrarMensajeEmergente("Especialista actualizado con exito");
						}
					} else {
						vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
					}
				}
			}
		} catch (NotValidIdException e) {
			vf.getCon()
					.mostrarAlerta("Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().mostrarAlerta("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().mostrarAlerta("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().mostrarAlerta("Formato de correo no valido");
		} catch (NotValidPasswordException e) {
			vf.getCon().mostrarAlerta("Formato de contraseña no valido, minimo 5 digitos y maximo 10 digitos");
		} catch (InputMismatchException e) {
			vf.getCon().mostrarAlerta("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}
	/**
     * Método que recibe los datos del paciente.
     * @param leer y extraer el texto del paciente.
     */
	public void getDatosPaciente(boolean create, boolean update) {
		try {
			long id = Long.parseLong(vf.getVp().getPanelInput().getTxtId().getText());
			ExceptionChecker.notValidIdException(id);

			String nombre = vf.getVp().getPanelInput().getTxtName().getText();
			ExceptionChecker.notValidStringInputException(nombre);

			int edad = Integer.parseInt(vf.getVp().getPanelInput().getTxtEdad().getText());
			ExceptionChecker.negativeIntNumberException(edad);

			String genero = vf.getVp().getPanelInput().getCmbxGenero().getSelectedItem().toString();

			String especialidad = vf.getVp().getPanelInput().getCmbxEspecialidad().getSelectedItem().toString();

			String correo = vf.getVp().getPanelInput().getTxtGmail().getText();
			ExceptionChecker.checkEmail(correo);

			String especialistaAsignado = mf.getEspecialistaDAO().getRandomSpecialist(especialidad);

			if (create == true && update == false) {
				if (mf.getPacienteDAO().add(new PacienteDTO(id, nombre, edad, genero, correo, null, null, especialidad,
						especialistaAsignado, false)) == true) {
					if (apartadoPaciente) {
						vf.getVp().getPanelApartado().getImgE().setVisible(false);
						vf.getVp().getPanelApartado().getImgA().setVisible(false);
						vf.getVp().getPanelApartado().getImgP().setVisible(true);
						vf.getCon().mostrarMensajeEmergente("Paciente creado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						EmailController.sendScheduled(correo, nombre, especialistaAsignado, especialidad);
						vf.getVp().mostrarPanelApartado();

					} else if (pacienteA) {
						vf.getCon().mostrarMensajeEmergente("Paciente creado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						EmailController.sendScheduled(correo, nombre, especialistaAsignado, especialidad);
						vf.getVp().mostrarPanelHomePaciente();

					} else {

						vf.getCon().mostrarMensajeEmergente("Paciente creado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						pacienteTempId = id;
						pacienteTempECita = especialidad;
						EmailController.sendScheduled(correo, nombre, especialistaAsignado, especialidad);
					}
				} else {
					vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
				}

			} else if (update == true && create == false) {
				if (mf.getPacienteDAO()
						.update(new PacienteDTO(pacienteTempId, null, 0, null, null, null, null, pacienteTempECita,
								null, false),
								new PacienteDTO(id, nombre, edad, genero, correo, null, null, especialidad, null,
										false)) == true) {
					if (apartadoPaciente) {

						vf.getVp().getPanelApartado().getImgE().setVisible(false);
						vf.getVp().getPanelApartado().getImgA().setVisible(false);
						vf.getVp().getPanelApartado().getImgP().setVisible(true);
						vf.getCon().mostrarMensajeEmergente("Paciente actualizado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						EmailController.sendRescheduled(correo, nombre, especialistaAsignado, especialidad);
						vf.getVp().mostrarPanelApartado();
					} else if (pacienteR) {
						vf.getCon().mostrarMensajeEmergente("Paciente actualizado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						EmailController.sendRescheduled(correo, nombre, especialistaAsignado, especialidad);
						vf.getVp().mostrarPanelHomePaciente();
					} else {
						vf.getCon().mostrarMensajeEmergente("Paciente actualizado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						EmailController.sendRescheduled(correo, nombre, especialistaAsignado, especialidad);

					}
				} else {
					vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
				}
			}
		} catch (

		NotValidIdException e) {
			vf.getCon()
					.mostrarAlerta("Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().mostrarAlerta("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().mostrarAlerta("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().mostrarAlerta("Formato de correo no valido");
		} catch (InputMismatchException e) {
			vf.getCon().mostrarAlerta("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}
	/**
     * Método setea las tablas de paciente.
     * @param para rellenar las tablas.
     */
	public void setUpPatientTable() {
		String column[] = { "ID", "NOMBRE", "GMAIL", "EDAD", "GENERO", "ESPECIALISTA", "ESPECIALIDAD", "SEGUIMIENTO" };
		ArrayList<PacienteDTO> pList = mf.getPacienteDAO().getAll();

		String[][] rows = new String[1][8];
		if (pList != null) {
			rows = new String[pList.size()][8];
		}

		int i = 0;
		for (PacienteDTO p : pList) {
			rows[i][0] = Long.toString(p.getId());
			rows[i][1] = p.getNombre();
			rows[i][2] = p.getCorreo();
			rows[i][3] = Integer.toString(p.getEdad());
			rows[i][4] = p.getGenero();
			rows[i][5] = p.getEspecialistaAsignado();
			rows[i][6] = p.getEspecialidadCita();
			boolean translate = p.isRequiereSeguimiento();
			String mean = "";
			if (translate) {
				mean = "si";
			} else {
				mean = "no";
			}
			rows[i][7] = mean;
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(rows, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vf.getVp().getPanelApartado().getTabla().setModel(model);
		vf.getVp().getPanelApartado().getTabla().repaint();
	}
	
	/**
     * Método setea las tablas de turno.
     * @param para rellenar las tablas.
     */

	public void setUpTurnsTable(String especialista) {
		String column[] = { "ID PACIENTE", "NOMBRE PACIENTE", "CORREO PACIENTE", "EDAD PACIENTE", "GENERO PACIENTE" };
		ArrayList<PacienteDTO> pList = mf.getPacienteDAO().getAll();

		String[][] rows = new String[1][5];
		if (pList != null) {
			rows = new String[pList.size()][5];
		}

		int i = 0;
		for (PacienteDTO p : pList) {

			if (p.getEspecialistaAsignado().equalsIgnoreCase(especialista)) {

				rows[i][0] = Long.toString(p.getId());

				rows[i][1] = p.getNombre();
				rows[i][2] = p.getCorreo();
				rows[i][3] = Integer.toString(p.getEdad());
				rows[i][4] = p.getGenero();

				i++;
			}
		}

		DefaultTableModel model = new DefaultTableModel(rows, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vf.getVp().getPanelHEspecialista().getTabla().setModel(model);
		vf.getVp().getPanelHEspecialista().getTabla().repaint();
	}

	/**
     * Método setea las tablas de paciente especialidad.
     * @param para rellenar las tablas.
     */
	public void setUpPatientSpecialtyTable(String especialidad) {
		String column[] = { "ID", "NOMBRE", "GMAIL", "EDAD", "GENERO", "ESPECIALISTA", "ESPECIALIDAD", "SEGUIMIENTO" };
		ArrayList<PacienteDTO> pList = mf.getPacienteDAO().getAll();

		String[][] rows = new String[1][8];
		if (pList != null) {
			rows = new String[pList.size()][8];
		}

		int i = 0;
		for (PacienteDTO p : pList) {

			if (p.getEspecialidadCita().equalsIgnoreCase(especialidad)) {

				rows[i][0] = Long.toString(p.getId());

				rows[i][1] = p.getNombre();
				rows[i][2] = p.getCorreo();
				rows[i][3] = Integer.toString(p.getEdad());
				rows[i][4] = p.getGenero();
				rows[i][5] = p.getEspecialistaAsignado();
				rows[i][6] = p.getEspecialidadCita();
				boolean translate = p.isRequiereSeguimiento();
				String mean = "";
				if (translate) {
					mean = "si";
				} else {
					mean = "no";
				}
				rows[i][7] = mean;
				i++;
			}
		}

		DefaultTableModel model = new DefaultTableModel(rows, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vf.getVp().getPanelApartado().getTabla().setModel(model);
		vf.getVp().getPanelApartado().getTabla().repaint();
	}

	public void setUpEspecialistTable() {
		String column[] = { "ID", "NOMBRE", "GMAIL", "EDAD", "GENERO", "ESPECIALIDAD", "CONTRASEÑA" };
		ArrayList<EspecialistaDTO> eList = mf.getEspecialistaDAO().getAll();

		String[][] rows = new String[1][7];
		if (eList != null) {
			rows = new String[eList.size()][7];
		}

		int i = 0;
		for (EspecialistaDTO p : eList) {
			rows[i][0] = Long.toString(p.getId());
			rows[i][1] = p.getNombre();
			rows[i][2] = p.getCorreo();
			rows[i][3] = Integer.toString(p.getEdad());
			rows[i][4] = p.getGenero();
			rows[i][5] = p.getEspecialidad();
			rows[i][6] = Long.toString(p.getPassword());
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(rows, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vf.getVp().getPanelApartado().getTabla().setModel(model);
		vf.getVp().getPanelApartado().getTabla().repaint();
	}

	public void setUpEspecialistAreaTable(String especialidad) {
		String column[] = { "ID", "NOMBRE", "GMAIL", "EDAD", "GENERO", "ESPECIALIDAD" };
		ArrayList<EspecialistaDTO> eList = mf.getEspecialistaDAO().getAll();

		String[][] rows = new String[1][6];
		if (eList != null) {
			rows = new String[eList.size()][6];
		}

		int i = 0;
		for (EspecialistaDTO p : eList) {
			if (p.getEspecialidad().equals(especialidad)) {
				rows[i][0] = Long.toString(p.getId());
				rows[i][1] = p.getNombre();
				rows[i][2] = p.getCorreo();
				rows[i][3] = Integer.toString(p.getEdad());
				rows[i][4] = p.getGenero();
				rows[i][5] = p.getEspecialidad();
				i++;
			}
		}

		DefaultTableModel model = new DefaultTableModel(rows, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vf.getVp().getPanelApartado().getTabla().setModel(model);
		vf.getVp().getPanelApartado().getTabla().repaint();
	}

	public void setUpAreaTable() {
		String column[] = { "NOMBRE" };
		ArrayList<EspecialidadDTO> eList = mf.getEspecialidadDAO().getAllDto();

		String[][] rows = new String[1][1];
		if (eList != null) {
			rows = new String[eList.size()][1];
		}

		int i = 0;
		for (EspecialidadDTO p : eList) {
			rows[i][0] = p.getNombre();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(rows, column) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vf.getVp().getPanelApartado().getTabla().setModel(model);
		vf.getVp().getPanelApartado().getTabla().repaint();
	}

	public void fillCombxEspecialidades() {
		especialidades = mf.getEspecialidadDAO().getAll();
		for (int i = 0; i < especialidades.length; i++) {
			vf.getVp().getPanelInput().getCmbxEspecialidad().addItem(especialidades[i]);
		}
	}

}
