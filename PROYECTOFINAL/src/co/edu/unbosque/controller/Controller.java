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

public class Controller implements ActionListener {

	private ModelFacade mf;
	private ViewFacade vf;
	private Properties prop;

	private String[] especialidades;

	private long directorTempId;
	private long pacienteTempId;
	private String pacienteTempECita;
	private long especialistaTempId;

	private boolean loginDirector;
	private boolean loginEspecialista;

	private boolean createDirector;
	private boolean createEspecialista;
	private boolean createPaciente;

	private boolean modDirector;
	private boolean modEspecialista;
	private boolean modPaciente;

	private boolean apartadoPaciente;
	private boolean apartadoEspecialista;
	private boolean apartadoEspecialidad;

	private boolean solicitarE;
	private boolean enviarRM;
	private boolean generarD;
	private boolean generarT;

	private AppointmentController gestor;

	public Controller() {
		gestor = new AppointmentController();

		// Generar varias citas
//		for (int i = 0; i < 5; i++) {
//			System.out.println("\nGenerando cita " + (i + 1) + ":");
//		}
//
//		// Mostrar todas las citas asignadas

		mf = new ModelFacade();
		vf = new ViewFacade();

		vf.getCon().printLine("Director");
		vf.getCon().printLine(mf.getDirectorDAO().showAll());

		asignarLectores();

		vf.getVp().getPanelHPaciente().getImgP().setVisible(true);
		vf.getVp().mostrarPanelHomePaciente();

//		vf.getVp().getPanelEntrada().getImg().setVisible(true);
//		vf.getVp().mostrarPanelWelcome();

		vf.getCon().printLine(mf.getPacienteDAO().showAll());
		prop = FileHandler.loadProperties("config.properties");

		fillCombxEspecialidades();
		mostrarMenuPrincipal();

	}

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

			vf.getVp().getPanelInput().getImgD().setVisible(false);
			vf.getVp().getPanelInput().getImgE().setVisible(false);
			vf.getVp().getPanelInput().getImgP().setVisible(true);
			vf.getVp().getPanelInput().getTxtPswd().setVisible(false);
			vf.getVp().getPanelInput().getTxtConfirmPswd().setVisible(false);
			vf.getVp().mostrarPanelInput();

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
			}

			if (generarD) {

				String diagnos = vf.getVp().getPanelHEspecialista().getTxtEntrada().getText();

				int seguimiento = vf.getCon().mostrarYesOrNo("El paciente requiere seguimiento?");
				boolean esSeg = vf.getCon().validarYesOrNo(seguimiento);
				vf.getCon().mostrarMensajeEmergente(
						mf.getPacienteDAO().setData(pacienteTempId, null, false, diagnos, true, esSeg, true));
				vf.getVp().getPanelHEspecialista().getBtnEnviar().setText("ENVIAR");

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
			break;
		}

	}

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
//
//			
//		PENDIENTE			
//			
//			
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
					} else {
						vf.getCon().mostrarMensajeEmergente("Paciente actualizado con exito");
						vf.getCon().mostrarMensajeEmergente("Espere a recibir la confirmación de correo enviado");
						EmailController.sendRescheduled(correo, nombre, especialistaAsignado, especialidad);

					}
				} else {
					vf.getCon().mostrarAlerta("Intente nuevamente, verifique los datos ingresados");
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
		} catch (InputMismatchException e) {
			vf.getCon().mostrarAlerta("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}

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
			// recuperar el Especialista

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

	public void mostrarMenuPrincipal() {

		mainloop: while (true) {
			String menu = """

					Bienvenido

					1.Director
					2.Especialista
					3.Paciente
					4.Salir

					""";
			vf.getCon().printLine(menu);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();

			switch (op) {
			case 1:
				vf.getCon().printLine("Director");
				mostrarValidacionLogInDirector();
				break;
			case 2:
				vf.getCon().printLine("Especialista");
				mostrarLogInEspecialista();
				break;

			case 3:
				vf.getCon().printLine("Paciente");
				mostrarMenuPaciente();
				break;

			case 4:
				vf.getCon().printLine("Salir");
				break mainloop;

			default:
				vf.getCon().printLine("Opcion invalida, intente nuevamente");
				break;
			}
		}
	}

	public void mostrarMenuDirector() {

		directorloop: while (true) {

			String menuD = """
					Menu Director!

					1. Modificar perfil
					2. Eliminar perfil
					3. Agregar director
					4. Mostrar directores
					5. Apartado pacientes
					6. Apartado especialistas
					7. Apartado especialidades
					8. Salir

					""";
			vf.getCon().printLine(menuD);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();
			switch (op) {
			case 1:
				updateloop: while (true) {
					vf.getCon().printLine("---MODIFICANDO DIRECTOR ACTUAL---");
					vf.getCon().printLine("Esta seguro de modificar su cuenta? \n1.SI \t2.NO");
					int confirmU = vf.getCon().readInt();
					vf.getCon().burnLine();
					switch (confirmU) {
					case 1:
						vf.getCon().printLine("Ingrese sus datos: ");
						pedirDatosDirector(false, true);
						break updateloop;

					case 2:

						vf.getCon().printLine("Operacion cancelada");
						break updateloop;

					default:
						vf.getCon().printLine("Seleccione una opción válida");
						break;
					}

				}
				break;

			case 2:
				removeloop: while (true) {
					vf.getCon().printLine("---ELIMINANDO DIRECTOR ACTUAL---");
					vf.getCon().printLine("Esta seguro de eliminar su cuenta? \n1.SI \t2.NO");
					int confirm = vf.getCon().readInt();
					vf.getCon().burnLine();
					switch (confirm) {
					case 1:
						if (mf.getDirectorDAO().delete(new DirectorDTO(directorTempId, null, 0, null, null, 0))) {
							vf.getCon().printLine("Perfil eliminado con exito");
							mostrarMenuPrincipal();
							break removeloop;
						} else {
							vf.getCon().printLine("Error, intente nuevamente");
						}
						break;
					case 2:
						vf.getCon().printLine("Operacion cancelada");
						break removeloop;
					default:
						vf.getCon().printLine("Seleccione una opción válida");
						break;
					}
				}
				break;

			case 3:
				vf.getCon().printLine("---AGREGANDO DIRECTOR---");
				pedirDatosDirector(true, false);
				break;

			case 4:
				vf.getCon().printLine("---MOSTRANDO DIRECTORES REGISTRADOS---");
				vf.getCon().printLine(mf.getDirectorDAO().showAll());
				break;

			case 5:
				vf.getCon().printLine("--APARTADO PACIENTES--");
				mostrarMenuDirectorPaciente();
				break;

			case 6:
				vf.getCon().printLine("--APARTADO ESPECIALISTAS--");
				mostrarMenuDirectorEspecialista();
				break;

			case 7:
				vf.getCon().printLine("--APARTADO ESPECIALIDADES--");
				mostrarMenuDirectorEspecialidades();
				break;

			case 8:
				vf.getCon().printLine("SALIENDO DE MENU DIRECTOR");
				directorTempId = 0;
				break directorloop;

			default:
				vf.getCon().printLine("Opcion invalida, intente nuevamente");
				break;

			}
		}

	}

	public void mostrarMenuEspecialista() {

		specialistloop: while (true) {

			String menu = """

					Menu Especialista

					1. Ver turnos
					2. Solicitar examen medico
					3. Enviar resultados medicos
					4. Generar diagnostico
					5. Generar tratamiento
					6. Intercambiar turno
					7. Salir


					""";

			vf.getCon().printLine(menu);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();
			String nombreEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, true, false,
					false);
			String areaEspecialista = mf.getEspecialistaDAO().pickSpecialistData(especialistaTempId, false, true,
					false);
			switch (op) {
			case 1:
				vf.getCon().printLine("VIENDO TURNOS ESPECIALISTA");
				vf.getCon().printLine(mf.getPacienteDAO().showAppointentSpecialist(nombreEspecialista));
				break;

			case 2:
				vf.getCon().printLine("SOLICITAR EXAMENES MEDICOS");
				vf.getCon().printLine("\n" + mf.getPacienteDAO().showAppointentSpecialist(nombreEspecialista));
				vf.getCon().print("Ingrese el documento del paciente a generar tratamiento= ");
				long idPacienteExamen = vf.getCon().readLong();
				vf.getCon().burnLine();
				vf.getCon().print("Ingrese los examenes a pedir ");
				String examenes = vf.getCon().readLine();
				vf.getCon().printLine("Exámenes solicitados exitosamente");
				EmailController.sendExaminationRequested(
						mf.getPacienteDAO().pickData(idPacienteExamen, areaEspecialista, true, false),
						mf.getPacienteDAO().pickData(idPacienteExamen, areaEspecialista, false, true),
						nombreEspecialista, examenes);
				break;

			case 3:
				vf.getCon().printLine("ENVIAR RESULTADOS EXAMEN");
				vf.getCon().printLine("\n" + mf.getPacienteDAO().showAppointentSpecialist(nombreEspecialista));
				vf.getCon().print("Ingrese el documento del paciente a generar tratamiento= ");
				long idPacienteResultados = vf.getCon().readLong();
				vf.getCon().burnLine();
				vf.getCon().print(
						"Ingrese los resultados del exámen del paciente, no de enter hasta finalizar con el tratamiento= ");
				String resultados = vf.getCon().readLine();
				EmailController.sendExaminationRequestedResults(
						mf.getPacienteDAO().pickData(idPacienteResultados, areaEspecialista, true, false),
						mf.getPacienteDAO().pickData(idPacienteResultados, areaEspecialista, false, true),
						nombreEspecialista, resultados);
				break;

			case 4:
				vf.getCon().printLine("GENERAR DIAGNOSTICO");
				vf.getCon().printLine("\n" + mf.getPacienteDAO().showAppointentSpecialist(nombreEspecialista));
				vf.getCon().print("Ingrese el documento del paciente a generar tratamiento= ");
				long idPacienteDiagnostico = vf.getCon().readLong();
				vf.getCon().burnLine();
				vf.getCon().printLine(
						"Ingrese el diagnostico del paciente, no de enter hasta finalizar con el tratamiento= ");
				String diagnostico = vf.getCon().readLine();

				vf.getCon().printLine("El paciente requiere seguimiento? \n1.SI \t2.NO");
				boolean seguimiento;
				int verdad = vf.getCon().readInt();
				vf.getCon().burnLine();
				verdadloop: while (true) {

					switch (verdad) {
					case 1:
						seguimiento = true;
						break verdadloop;
					case 2:
						seguimiento = false;
						break verdadloop;

					default:
						vf.getCon().printLine("Seleccione una opción válida");
						break;
					}
				}

				vf.getCon().printLine(mf.getPacienteDAO().setData(idPacienteDiagnostico, null, false, diagnostico, true,
						seguimiento, true));

				break;
			case 5:

				vf.getCon().printLine("GENERAR TRATAMIENTO");
				vf.getCon().printLine("\n" + mf.getPacienteDAO().showAppointentSpecialist(nombreEspecialista));
				vf.getCon().print("Ingrese el documento del paciente a generar tratamiento= ");
				long idPacienteTratamiento = vf.getCon().readLong();
				vf.getCon().burnLine();
				vf.getCon()
						.print("Ingrese el tratamiento a almacenar, no de enter hasta finalizar con el tratamiento= ");
				String tratamiento = vf.getCon().readLine();
				vf.getCon().printLine(mf.getPacienteDAO().setData(idPacienteTratamiento, tratamiento, true, null, false,
						false, false));
				System.out.println(mf.getPacienteDAO().pickData(idPacienteTratamiento, areaEspecialista, true, false));
				EmailController.sendTreatment(
						mf.getPacienteDAO().pickData(idPacienteTratamiento, areaEspecialista, true, false),
						mf.getPacienteDAO().pickData(idPacienteTratamiento, areaEspecialista, false, true), tratamiento,
						nombreEspecialista);
				break;

			case 6:

				break;

			case 7:
				vf.getCon().printLine("SALIENDO MENU ESPECIALISTA");
				break specialistloop;

			default:
				break;
			}
		}

	}

	public void mostrarMenuPaciente() {

		pacienteloop: while (true) {

			String menu = """

					Menu Paciente

					1. Agendar Cita
					2. Reprogramar Cita
					3. Cancelar Cita
					4. Salir

					""";

			vf.getCon().printLine(menu);
			int op = vf.getCon().readInt();
			switch (op) {
			case 1: // Agendar Cita
				vf.getCon().printLine("AGENDAR CITA");
				pedirDatosPaciente(true, false);
				gestor.generarCitas();
				break;

			case 2: // Reprogramar Cita
				break;

			case 3: // Cancelar Cita
				vf.getCon().printLine("CANCELAR CITA");
				int opE2;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad: ");
					mostrarLista();
					opE2 = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE2 > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}

				String specialty = especialidades[opE2 - 1];

				vf.getCon().printLine("Ingrese su cedula: ");
				long id1 = vf.getCon().readLong();
				vf.getCon().burnLine();

				String tempCorreo = mf.getPacienteDAO().pickData(id1, specialty, true, false);
				String nombre = mf.getPacienteDAO().pickData(id1, specialty, false, true);

				if (mf.getPacienteDAO()
						.delete(new PacienteDTO(id1, null, 0, null, null, null, null, specialty, null, false))) {
					vf.getCon().printLine("Operacion completada");
					EmailController.sendCanceled(tempCorreo, nombre);
				} else {
					vf.getCon().printLine("Operacion incompleta");
				}

				break;
			case 4:
				vf.getCon().printLine("SALIENDO MENU PACIENTE");
				break pacienteloop;

			default:
				vf.getCon().printLine("Debe seleccionar una opcion valida!");
				break;
			}
		}
	}

	public void mostrarMenuDirectorEspecialidades() {
		mainspecialtyloop: while (true) {

			String menu = """

					APARTADO ESPECIALIDADES

					1. Ver especialidades
					2. Agregar Especialidad
					3. Modificar Especialidad
					4. Eliminar Especialidad
					5. Salir
					""";

			vf.getCon().printLine(menu);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();

			switch (op) {
			case 1:
				vf.getCon().printLine("---MOSTRANDO ESPECIALIDADES---");
				vf.getCon().printLine(mf.getEspecialidadDAO().showAll());
				break;

			case 2:
				vf.getCon().printLine("---AGREGANDO ESPECIALIDAD---");
				vf.getCon().print("NOMBRE: ");
				String especialidad = vf.getCon().readLine();
				if (mf.getEspecialidadDAO().add(new EspecialidadDTO(especialidad))) {
					vf.getCon().printLine("Operacion completada");

					especialidades = mf.getEspecialidadDAO().getAll();
				} else {
					vf.getCon().printLine("Operacion incompleta");
				}
				break;

			case 3:
				vf.getCon().printLine("---MODIFICANDO ESPECIALIDAD---");
				int opE;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad a modificar: ");
					mostrarLista();
					opE = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}
				String name = especialidades[opE - 1];
				vf.getCon().printLine("Ingrese el nombre nuevo del area");
				String nameA = vf.getCon().readLine();
				if (mf.getEspecialidadDAO().update(new EspecialidadDTO(name), new EspecialidadDTO(nameA))) {
					vf.getCon().printLine("Operacion Completada");

					especialidades = mf.getEspecialidadDAO().getAll();
				} else {
					vf.getCon().printLine("Operacion incompleta");
				}

				break;

			case 4:
				vf.getCon().printLine("---ELIMINANDO ESPECIALIDAD---");
				int opE1;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad a eliminar: ");
					mostrarLista();
					opE1 = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE1 > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}
				if (mf.getEspecialidadDAO().delete(new EspecialidadDTO(especialidades[opE1 - 1]))) {
					vf.getCon().printLine("Operacion completada");

					especialidades = mf.getEspecialidadDAO().getAll();
				} else {
					vf.getCon().printLine("Operacion incompleta");
				}
				break;

			case 5:
				vf.getCon().printLine("SALIENDO DE DIRECTOR ESPECIALIDADES");
				break mainspecialtyloop;

			default:
				break;
			}

		}
	}

	public void mostrarMenuDirectorPaciente() {

		pacienteloop: while (true) {
			String menu = """

					APARTADO PACIENTES

					1. Ver pacientes
					2. Agregar cita paciente
					3. Modificar cita paciente
					4. Eliminar cita paciente
					5. Salir

					""";
			vf.getCon().printLine(menu);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();

			switch (op) {
			case 1:
				vf.getCon().printLine("VIENDO CITAS PACIENTES");
				int opE;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad a la cual se asigno la cita: ");
					mostrarLista();
					opE = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}
				vf.getCon().printLine("PACIENTES CON CITA EN ESPECIALIDAD " + especialidades[opE - 1]);
				vf.getCon().printLine(mf.getPacienteDAO().showSpecificPatientSpecialty(especialidades[opE - 1]));
				break;

			case 2:
				vf.getCon().printLine("---AGREGANDO CITA PACIENTE---");
				pedirDatosPaciente(true, false);
				break;

			case 3:
				vf.getCon().printLine("---MODIFICANDO PACIENTE---");
				int opE1;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad: ");
					mostrarLista();
					opE1 = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE1 > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}
				String specialty1 = especialidades[opE1 - 1];
				pacienteTempECita = specialty1;
				vf.getCon().printLine(mf.getPacienteDAO().showSpecificPatientSpecialty(specialty1));
				vf.getCon().printLine("Ingrese la cedula del paciente a modificar: ");
				long id = vf.getCon().readLong();
				pacienteTempId = id;
				vf.getCon().burnLine();
				pedirDatosPaciente(false, true);
				pacienteTempECita = null;
				pacienteTempId = 0;
				break;

			case 4:
				// PENDIENTE
				vf.getCon().printLine("---ELIMINANDO PACIENTE---");
				int opE2;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad: ");
					mostrarLista();
					opE2 = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE2 > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}

				String specialty = especialidades[opE2 - 1];

				vf.getCon().printLine(mf.getPacienteDAO().showSpecificPatientSpecialty(specialty));
				vf.getCon().printLine("Ingrese la cedula del paciente a eliminar: ");
				long id1 = vf.getCon().readLong();
				vf.getCon().burnLine();

				String tempCorreo = mf.getPacienteDAO().pickData(id1, specialty, true, false);
				String nombre = mf.getPacienteDAO().pickData(id1, specialty, false, true);

				if (mf.getPacienteDAO()
						.delete(new PacienteDTO(id1, null, 0, null, null, null, null, specialty, null, false))) {
					vf.getCon().printLine("Operacion completada");
					EmailController.sendCanceled(tempCorreo, nombre);
				} else {
					vf.getCon().printLine("Operacion incompleta");
				}

				break;

			case 5:
				vf.getCon().printLine("SALIENDO MENU DIRECTOR PACIENTE");
				break pacienteloop;

			default:
				vf.getCon().printLine("Ingrese una opcion valida");
				break;
			}
		}

	}

	public void mostrarMenuDirectorEspecialista() {

		especialistaloop: while (true) {

			String menu = """

					APARTADO ESPECIALISTAS

					1. Ver especialistas
					2. Agregar especialistas
					3. Modificar especialistas
					4. Eliminar especialistas
					5. Salir
					""";

			vf.getCon().printLine(menu);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();

			switch (op) {
			case 1:
				vf.getCon().printLine("---MOSTRANDO ESPECIALISTAS---");
				vf.getCon().printLine("Elija el area del especialista");
				int opE;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad: ");
					mostrarLista();
					opE = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}
				vf.getCon().printLine("Mostrando especialistas del area " + especialidades[opE - 1]);
				vf.getCon().printLine(mf.getEspecialistaDAO().showSpecificArea(especialidades[opE - 1]));
				break;

			case 2:
				vf.getCon().printLine("---AGREGANDO ESPECIALISTA---");
				pedirDatosEspecialista(true, false);
				break;

			case 3:
				vf.getCon().printLine("---MODIFICANDO ESPECIALISTA---");
				int opE1;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad: ");
					mostrarLista();
					opE1 = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE1 > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}
				}
				String specialty = especialidades[opE1 - 1];
				vf.getCon().printLine(mf.getEspecialistaDAO().showSpecificArea(specialty));
				vf.getCon().printLine("Ingrese la cedula del especialista a modificar: ");
				long id1 = vf.getCon().readLong();
				especialistaTempId = id1;
				vf.getCon().burnLine();
				pedirDatosEspecialista(false, true);
				especialistaTempId = 0;

				break;

			case 4:
				vf.getCon().printLine("---ELIMINANDO ESPECIALISTA---");
				vf.getCon().printLine("Elija el area del especialista");
				int opE2;
				specialtyloop: while (true) {
					vf.getCon().printLine("Seleccione la especialidad: ");
					mostrarLista();
					opE2 = vf.getCon().readInt();
					vf.getCon().burnLine();
					if (opE2 > especialidades.length) {
						vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
					} else {
						break specialtyloop;
					}

				}
				String specialty1 = especialidades[opE2 - 1];
				vf.getCon().printLine(mf.getEspecialistaDAO().showSpecificArea(specialty1));
				vf.getCon().printLine("Ingrese la cedula del especialista a eliminar");
				long id = vf.getCon().readLong();
				vf.getCon().burnLine();
				if (mf.getEspecialistaDAO().delete(new EspecialistaDTO(id, null, 0, null, null, null, 0))) {
					vf.getCon().printLine("Operacion completada");
				} else {
					vf.getCon().printLine("Operacion incompleta");
				}
				break;

			case 5:
				vf.getCon().printLine("SALIENDO MENU DIRECTOR ESPECIALISTA");
				break especialistaloop;

			default:
				break;
			}

		}
	}

	public void pedirDatosEspecialista(boolean create, boolean update) {
		try {
			vf.getCon().print("ID: ");
			long id = vf.getCon().readLong();
			ExceptionChecker.notValidIdException(id);
			vf.getCon().burnLine();
			vf.getCon().print("NOMBRE: ");
			String nombre = vf.getCon().readLine();
			ExceptionChecker.notValidStringInputException(nombre);
			vf.getCon().print("EDAD: ");
			int edad = vf.getCon().readInt();
			ExceptionChecker.negativeIntNumberException(edad);
			vf.getCon().burnLine();

			String genero = null;
			genreloop: while (true) {
				vf.getCon().printLine("GENERO: \n1. Masculino \t2.Femenino");
				int opG = vf.getCon().readInt();
				vf.getCon().burnLine();
				if (opG == 1) {
					genero = "Masculino";
					break genreloop;
				} else if (opG == 2) {
					genero = "Femenino";
					break genreloop;
				} else {
					vf.getCon().printLine("Seleccione una opción");
					break;
				}
			}
			int opE;
			specialtyloop: while (true) {
				vf.getCon().printLine("ESPECIALIDAD: ");
				vf.getCon().printLine("Seleccione la especialidad: ");
				mostrarLista();
				opE = vf.getCon().readInt();
				vf.getCon().burnLine();
				if (opE > especialidades.length) {
					vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
				} else {
					break specialtyloop;
				}
			}

			String especialidad = especialidades[opE - 1];

			vf.getCon().print("CORREO: ");
			String correo = vf.getCon().readLine();
			ExceptionChecker.checkEmail(correo);

			long password;
			long confirmP;
			passloop: while (true) {

				vf.getCon().print("CONTRASENA: ");
				password = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(password);

				vf.getCon().print("CONFIRMAR CONTRASENA: ");
				confirmP = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(confirmP);

				if (password != confirmP) {
					vf.getCon().printLine("No coinciden las contraseñas, vuelva a ingresarlas");
				}

				if (password == confirmP) {
					break passloop;
				}
			}

			if (mf.getEspecialistaDAO().setLimitSpecialist(
					Integer.parseInt(prop.getProperty("bosquehealth.especialidad.limite")),
					especialidades[opE - 1]) == true) {
				vf.getCon().printLine("No es posible agregar mas especialistas al area, llego a su limite");
			} else {

				if (create == true && update == false) {
					if (mf.getEspecialistaDAO().add(
							new EspecialistaDTO(id, nombre, edad, genero, correo, especialidad, password)) == true) {
						vf.getCon().printLine("Especialista creado con exito");
					} else {
						vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
					}

				} else if (update == true && create == false) {
					if (mf.getEspecialistaDAO().update(
							new EspecialistaDTO(especialistaTempId, null, 0, null, null, null, 0),
							new EspecialistaDTO(id, nombre, edad, genero, correo, especialidad, password)) == true) {
						vf.getCon().printLine("Especialista actualizado con exito");
					} else {
						vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
					}
				} else {
					vf.getCon().printLine("ERROR EN METODO PEDIR DATOS DIRECTOR");
				}

			}
		} catch (NotValidIdException e) {
			vf.getCon().printLine("Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().printLine("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().printLine("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().printLine("Formato de correo no valido");
		} catch (NotValidPasswordException e) {
			vf.getCon().printLine("Formato de contraseña no valido, minimo 5 digitos y maximo 10 digitos");
		} catch (InputMismatchException e) {
			vf.getCon().printLine("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}

	public void pedirDatosPaciente(boolean create, boolean update) {
		try {
			vf.getCon().print("ID: ");
			long id = vf.getCon().readLong();
			ExceptionChecker.notValidIdException(id);
			vf.getCon().burnLine();
			vf.getCon().print("NOMBRE: ");
			String nombre = vf.getCon().readLine();
			ExceptionChecker.notValidStringInputException(nombre);
			vf.getCon().print("EDAD: ");
			int edad = vf.getCon().readInt();
			ExceptionChecker.negativeIntNumberException(edad);
			vf.getCon().burnLine();

			String genero = null;
			genreloop: while (true) {
				vf.getCon().printLine("GENERO: \n1. Masculino \t2.Femenino");
				int opG = vf.getCon().readInt();
				vf.getCon().burnLine();
				if (opG == 1) {
					genero = "Masculino";
					break genreloop;
				} else if (opG == 2) {
					genero = "Femenino";
					break genreloop;
				} else {
					vf.getCon().printLine("Seleccione una opción");
					break;
				}
			}
			int opE;
			specialtyloop: while (true) {
				vf.getCon().printLine("ESPECIALIDAD CITA: ");
				vf.getCon().printLine("Seleccione la especialidad: ");
				mostrarLista();
				opE = vf.getCon().readInt();
				vf.getCon().burnLine();
				if (opE > especialidades.length) {
					vf.getCon().printLine("Opcion invalida, seleccione nuevamente");
				} else {
					break specialtyloop;
				}
			}

			String especialidadCita = especialidades[opE - 1];

			vf.getCon().print("CORREO: ");
			String correo = vf.getCon().readLine();
			ExceptionChecker.checkEmail(correo);

			vf.getCon().printLine("ESPECIALISTA: ");
			String especialistaAsignado = vf.getCon().readLine();

			if (create == true && update == false) {
				if (mf.getPacienteDAO().add(new PacienteDTO(id, nombre, edad, genero, correo, null, null,
						especialidadCita, especialistaAsignado, false)) == true) {
					vf.getCon().printLine("Paciente creado con exito");
					EmailController.sendScheduled(correo, nombre, especialistaAsignado, especialidadCita);
				} else {
					vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
				}

			} else if (update == true && create == false) {
				if (mf.getPacienteDAO()
						.update(new PacienteDTO(pacienteTempId, null, 0, null, null, null, null, pacienteTempECita,
								null, false),
								new PacienteDTO(id, nombre, edad, genero, correo, null, null, especialidadCita, null,
										false)) == true) {
					vf.getCon().printLine("Paciente actualizado con exito");
					EmailController.sendRescheduled(correo, nombre, especialistaAsignado, especialidadCita);
				} else {
					vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
				}
			} else {
				vf.getCon().printLine("ERROR EN METODO PEDIR DATOS DIRECTOR");
			}
		} catch (NotValidIdException e) {
			vf.getCon().printLine("Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().printLine("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().printLine("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().printLine("Formato de correo no valido");
		} catch (InputMismatchException e) {
			vf.getCon().printLine("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}

	public void pedirDatosDirector(boolean create, boolean update) {
		try {
			vf.getCon().print("ID: ");
			long id = vf.getCon().readLong();
			ExceptionChecker.notValidIdException(id);
			vf.getCon().burnLine();
			vf.getCon().print("NOMBRE: ");
			String nombre = vf.getCon().readLine();
			ExceptionChecker.notValidStringInputException(nombre);
			vf.getCon().print("EDAD: ");
			int edad = vf.getCon().readInt();
			ExceptionChecker.negativeIntNumberException(edad);
			vf.getCon().burnLine();

			String genero = null;
			genreloop: while (true) {
				vf.getCon().printLine("GENERO: \n1. Masculino \t2.Femenino");
				int opG = vf.getCon().readInt();
				vf.getCon().burnLine();
				if (opG == 1) {
					genero = "Masculino";
					break genreloop;
				} else if (opG == 2) {
					genero = "Femenino";
					break genreloop;
				} else {
					vf.getCon().printLine("Seleccione una opción");
					break;
				}
			}

			vf.getCon().print("CORREO: ");
			String correo = vf.getCon().readLine();
			ExceptionChecker.checkEmail(correo);

			long password;
			long confirmP;
			passloop: while (true) {

				vf.getCon().print("CONTRASENA: ");
				password = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(password);

				vf.getCon().print("CONFIRMAR CONTRASENA: ");
				confirmP = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(confirmP);

				if (password != confirmP) {
					vf.getCon().printLine("No coinciden las contraseñas, vuelva a ingresarlas");
				}

				if (password == confirmP) {
					break passloop;
				}
			}

			if (create == true && update == false) {
				if (mf.getDirectorDAO().add(new DirectorDTO(id, nombre, edad, genero, correo, password)) == true) {
					vf.getCon().printLine("Usuario creado con exito");
				} else {
					vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
				}
			} else if (update == true && create == false) {
				if (mf.getDirectorDAO().update(new DirectorDTO(directorTempId, null, 0, null, null, 0),
						new DirectorDTO(id, nombre, edad, genero, correo, password)) == true) {
					vf.getCon().printLine("Usuario actualizado con exito");
				} else {
					vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
				}
			} else {
				vf.getCon().printLine("ERROR EN METODO PEDIR DATOS DIRECTOR");
			}

		} catch (NotValidIdException e) {
			vf.getCon().printLine("Formato de id no valido, verifique que tenga minimo 8 digitos y maximo 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().printLine("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().printLine("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().printLine("Formato de correo no valido");
		} catch (NotValidPasswordException e) {
			vf.getCon().printLine("Formato de contraseña no valido, minimo 5 digitos y maximo 10 digitos");
		} catch (InputMismatchException e) {
			vf.getCon().printLine("Verifique el tipo de datos que va en los campos y lo que ingreso");
		}
	}

	public void mostrarValidacionLogInDirector() {

		directorloginloop: while (true) {

			if (mf.getDirectorDAO().checkAdmin() == false) {
				vf.getCon().printLine("Desea salir?");
				String salir = vf.getCon().readLine();
				if (salir.equalsIgnoreCase("si"))
					break directorloginloop;
				pedirDatosDirector(true, false);
				break directorloginloop;
			} else {
				vf.getCon().printLine("Desea salir?");
				String salir = vf.getCon().readLine();
				if (salir.equalsIgnoreCase("si"))
					break directorloginloop;
				mostrarLogInDirector();
				break directorloginloop;
			}

		}

	}

	public void mostrarLogInDirector() {
		passwordloop: while (true) {
			try {
				vf.getCon().printLine("Bienvenido:");
				vf.getCon().print("ID: ");
				long id = vf.getCon().readLong();
				ExceptionChecker.notValidIdException(id);
				vf.getCon().print("Contraseña: ");
				long password = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(password);
				vf.getCon().printLine(mf.getDirectorDAO().verifyPassword(id, password));
				if (mf.getDirectorDAO().checkLogIn(id, password) == true) {
					directorTempId = id;
					mostrarMenuDirector();
					break passwordloop;
				}

			} catch (NotValidIdException e) {
				vf.getCon().printLine("Formato de id no valido, verifique que tenga 10 digitos");
			} catch (NotValidPasswordException e) {
				vf.getCon().printLine("Formato de contraseña no valido, verifique su contraseña");
			}
		}
	}

	public void mostrarLogInEspecialista() {
		passwordloop: while (true) {
			try {
				vf.getCon().printLine("Bienvenido:");
				vf.getCon().print("ID: ");
				long id = vf.getCon().readLong();
				ExceptionChecker.notValidIdException(id);
				vf.getCon().print("Contraseña: ");
				long password = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(password);
				if (mf.getEspecialistaDAO().checkLogIn(id, password) == true) {
					especialistaTempId = id;
					mostrarMenuEspecialista();
					break passwordloop;
				}

			} catch (NotValidIdException e) {
				vf.getCon().printLine("Formato de id no valido, verifique que tenga 10 digitos");
			} catch (NotValidPasswordException e) {
				vf.getCon().printLine("Formato de contraseña no valido, verifique su contraseña");
			}
		}
	}

	public void mostrarLista() {
		String[] out1 = new String[especialidades.length];
		for (int i = 0; i < out1.length; i++) {
			out1[i] = i + 1 + ". " + especialidades[i];
		}
		for (String o : out1) {
			vf.getCon().printLine(o);
		}
	}

	public void asignarEspecialista(String especialidad) {

	}

	/*
	 * public void enviarCorreosCitas() { // generar el formato de hora del
	 * properties Calendar calendario = Calendar.getInstance(); int h =
	 * calendario.get(Calendar.HOUR_OF_DAY); int m =
	 * calendario.get(Calendar.MINUTE); String hora = h + ":" + m;
	 * 
	 * EspecialistaDAO edao = new EspecialistaDAO();
	 * 
	 * }
	 */ // Esto deberia ir en EmailController

	@SuppressWarnings("deprecation")
	public void generarCitas() {

	}

	public void fillCombxEspecialidades() {
		especialidades = mf.getEspecialidadDAO().getAll();
		for (int i = 0; i < especialidades.length; i++) {
			vf.getVp().getPanelInput().getCmbxEspecialidad().addItem(especialidades[i]);
		}
	}

}
