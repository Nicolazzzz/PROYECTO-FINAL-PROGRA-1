package co.edu.unbosque.controller;

import java.util.Calendar;
import java.util.Properties;

import co.edu.unbosque.model.DirectorDTO;
import co.edu.unbosque.model.EspecialidadDTO;
import co.edu.unbosque.model.EspecialistaDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.PacienteDTO;
import co.edu.unbosque.model.persistence.EspecialistaDAO;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.util.exception.EmailNotValidException;
import co.edu.unbosque.util.exception.ExceptionChecker;
import co.edu.unbosque.util.exception.NegativeIntNumberException;
import co.edu.unbosque.util.exception.NotValidIdException;
import co.edu.unbosque.util.exception.NotValidPasswordException;
import co.edu.unbosque.util.exception.NotValidStringInputException;
import co.edu.unbosque.view.ViewFacade;

public class Controller {

	private ModelFacade mf;
	private ViewFacade vf;
	private Properties prop;

	private String[] especialidades;

	private long directorTempId;
	private long pacienteTempId;
	private String pacienteTempECita;
	private long especialistaTempId;

	public Controller() {
		enviarCorreosCitas();

//		EmailController.sendScheduled("carolinaavilaleon@gmail.com");
//		EmailController.sendScheduled("m3ra2404@gmail.com");
//		EmailController.sendCanceled("m3ra2404@gmail.com");
//		EmailController.sendRescheduled("m3ra2404@gmail.com");
//		EmailController.sendReminder("nicolaszambranoabril12@gmail.com");
//		EmailController.sendReminder("m3ra2404@gmail.com");
//		EmailController.sendTreatment("m3ra2404@gmail.com");
//		EmailController.sendExaminationRequested("m3ra2404@gmail.com");
//		EmailController.sendExaminationRequestedResults("m3ra2404@gmail.com");
//		EmailController.sendNextDayAppointment("m3ra2404@gmail.com");
//		EmailController.sendMonthShifts("m3ra2404@gmail.com");
//		EmailController.sendChangeOfTurn("m3ra2404@gmail.com");
		mf = new ModelFacade();
		vf = new ViewFacade();
		prop = FileHandler.loadProperties("config.properties");
		mf.getPacienteDAO().delete(new PacienteDTO(1122515555, null, 0, null, null, null, null, null, null, false));

//		mf.getEspecialidadDAO().add(new EspecialidadDTO("Cirugía"));
//		mf.getEspecialidadDAO().add(new EspecialidadDTO("Oncología"));
//		mf.getEspecialidadDAO().add(new EspecialidadDTO("Dermatología"));
//		mf.getEspecialidadDAO().add(new EspecialidadDTO("Neumología"));
//		mf.getEspecialidadDAO().add(new EspecialidadDTO("Cardiología"));
//		mf.getEspecialidadDAO().add(new EspecialidadDTO("Medicina Interna"));

		especialidades = mf.getEspecialidadDAO().getAll();
		for (String string : especialidades) {
			System.out.println(string);
		}

		vf.getCon().printLine(mf.getDirectorDAO().showAll());

		mf.getEspecialistaDAO()
				.setLimitSpecialist(Integer.parseInt(prop.getProperty("bosquehealth.especialistas.limite")));
		run();

	}

	public void run() {
		mostrarMenuPrincipal();
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
				mostrarLogInDirector();
				break;
			case 2:
				vf.getCon().printLine("Especialista");
				logInEspecialista();
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

	public void mostrarLogInDirector() {

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
				logInDirector();
				break directorloginloop;
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

	}

	public void logInEspecialista() {
		passwordloop: while (true) {
			try {
				vf.getCon().printLine("Bienvenido:");
				vf.getCon().print("ID: ");
				long id = vf.getCon().readLong();
				ExceptionChecker.notValidIdException(id);
				vf.getCon().print("Contraseña: ");
				long password = vf.getCon().readLong();
				ExceptionChecker.notValidPasswordException(password);
				vf.getCon().printLine(mf.getEspecialistaDAO().verifyPassword(id, password));
				if (mf.getDirectorDAO().checkLogIn(id, password) == true) {
					directorTempId = id;
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

	public void mostrarMenuPaciente() {
	}

	public void logInDirector() {
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
				vf.getCon().printLine("PACIENTES CON CITA EN ESPECIALIDAD " + especialidades[opE]);
				vf.getCon().printLine(mf.getPacienteDAO().showSpecificPatientSpecialty(especialidades[opE]));
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
					String specialty = especialidades[opE1 - 1];
					pacienteTempECita = specialty;
					vf.getCon().printLine(mf.getPacienteDAO().showSpecificPatientSpecialty(specialty));
					vf.getCon().printLine("Ingrese la cedula del paciente a modificar: ");
					long id = vf.getCon().readLong();
					pacienteTempId = id;
					vf.getCon().burnLine();
					pedirDatosPaciente(false, true);
					pacienteTempECita = null;
					pacienteTempId = 0;
				}
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

					String specialty = especialidades[opE2 - 1];
					vf.getCon().printLine(mf.getPacienteDAO().showSpecificPatientSpecialty(specialty));
					vf.getCon().printLine("Ingrese la cedula del paciente a eliminar: ");
					long id = vf.getCon().readLong();
					vf.getCon().burnLine();
					if (mf.getPacienteDAO()
							.delete(new PacienteDTO(id, null, 0, null, null, null, null, specialty, null, false))) {
						vf.getCon().printLine("Operacion completada");
					} else {
						vf.getCon().printLine("Operacion incompleta");
					}
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
					String specialty = especialidades[opE1 - 1];
					vf.getCon().printLine(mf.getEspecialistaDAO().showSpecificArea(specialty));
					vf.getCon().printLine("Ingrese la cedula del especialista a modificar: ");
					long id = vf.getCon().readLong();
					especialistaTempId = id;
					vf.getCon().burnLine();
					pedirDatosEspecialista(false, true);
					especialistaTempId = 0;

				}
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
					vf.getCon().printLine("Ingrese la cedula del especialista a eliminar");
					long id = vf.getCon().readLong();
					vf.getCon().burnLine();
					if (mf.getEspecialistaDAO().delete(new EspecialistaDTO(id, null, 0, null, null, null, 0))) {
						vf.getCon().printLine("Operacion completada");
					} else {
						vf.getCon().printLine("Operacion incompleta");
					}
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

			if (create == true && update == false) {
				if (mf.getEspecialistaDAO()
						.add(new EspecialistaDTO(id, nombre, edad, genero, correo, especialidad, password)) == true) {
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
		} catch (NotValidIdException e) {
			vf.getCon().printLine("Formato de id no valido, verifique que tenga 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().printLine("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().printLine("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().printLine("Formato de correo no valido");
		} catch (NotValidPasswordException e) {
			vf.getCon().printLine("Formato de contraseña no valido, minimo 5 digitos y maximo 10 digitos");
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

			if (create == true && update == false) {
				if (mf.getPacienteDAO().add(new PacienteDTO(id, nombre, edad, genero, correo, null, null,
						especialidadCita, null, false)) == true) {
					vf.getCon().printLine("Paciente creado con exito");
					EmailController.sendScheduled(correo);
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
				} else {
					vf.getCon().printLine("Intente nuevamente, verifique los datos ingresados");
				}
			} else {
				vf.getCon().printLine("ERROR EN METODO PEDIR DATOS DIRECTOR");
			}
		} catch (NotValidIdException e) {
			vf.getCon().printLine("Formato de id no valido, verifique que tenga 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().printLine("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().printLine("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().printLine("Formato de correo no valido");
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
			vf.getCon().printLine("Formato de id no valido, verifique que tenga 10 digitos");
		} catch (NotValidStringInputException e) {
			vf.getCon().printLine("Formato de nombre no valido, no ingrese caracteres especiales");
		} catch (NegativeIntNumberException e) {
			vf.getCon().printLine("La edad no puede ser 0, negativa, mayor a 122 años");
		} catch (EmailNotValidException e) {
			vf.getCon().printLine("Formato de correo no valido");
		} catch (NotValidPasswordException e) {
			vf.getCon().printLine("Formato de contraseña no valido, minimo 5 digitos y maximo 10 digitos");
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

	public void enviarCorreosCitas() {
		// generar el formato de hora del properties
		Calendar calendario = Calendar.getInstance();
		int h = calendario.get(Calendar.HOUR_OF_DAY);
		int m = calendario.get(Calendar.MINUTE);
		String hora = h + ":" + m;

		EspecialistaDAO edao = new EspecialistaDAO();

	}
}
