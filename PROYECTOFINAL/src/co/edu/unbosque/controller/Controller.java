package co.edu.unbosque.controller;

import java.util.Properties;

import co.edu.unbosque.model.DirectorDTO;
import co.edu.unbosque.model.ModelFacade;
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

	private long directorTempId;

	public Controller() {
		mf = new ModelFacade();
		vf = new ViewFacade();
		prop = FileHandler.loadProperties("spanish.properties");

//		mf.getDirectorDAO().delete(new DirectorDTO(0, null, 0, null, null));
		vf.getCon().printLine(mf.getDirectorDAO().showAll());
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
				mostrarMenuEspecialista();
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

		directorloop: while (true) {

			if (mf.getDirectorDAO().checkAdmin() == false) {
				vf.getCon().printLine("Desea salir?");
				String salir = vf.getCon().readLine();
				if (salir.equalsIgnoreCase("si"))
					break directorloop;
				pedirDatosDirector(true, false);
				break directorloop;
			} else {
				vf.getCon().printLine("Desea salir?");
				String salir = vf.getCon().readLine();
				if (salir.equalsIgnoreCase("si"))
					break directorloop;
				logInDirector();
				break directorloop;
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
					7. Salir

					""";
			vf.getCon().printLine(menuD);
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();
			switch (op) {
			case 1:
				vf.getCon().printLine("---MODIFICANDO DIRECTOR ACTUAL---");
				vf.getCon().printLine("Ingrese sus datos: ");

				pedirDatosDirector(false, true);

				break;

			case 2:
				removeloop: while (true) {
					vf.getCon().printLine("---ELIMINANDO DIRECTOR ACTUAL---");
					vf.getCon().printLine("Esta seguro de eliminar su cuenta? \n1.SI \t2.NO");
					int confirm = vf.getCon().readInt();
					switch (confirm) {
					case 1:
						if (mf.getDirectorDAO()
								.delete(new DirectorDTO(directorTempId, null, 0, null, null, 0)) == true) {
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
				break;

			case 4:
				vf.getCon().printLine("---MOSTRANDO DIRECTORES REGISTRADOS---");
				vf.getCon().printLine(mf.getDirectorDAO().showAll());
				break;

			case 5:
				break;

			case 6:
				break;

			case 7:
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

	public void mostrarMenuPaciente() {
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
}
