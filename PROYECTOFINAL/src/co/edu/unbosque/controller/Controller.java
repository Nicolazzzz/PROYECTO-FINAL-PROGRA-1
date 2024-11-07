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
				mostrarMenuLogInDirector();
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

	public void mostrarMenuLogInDirector() {

		directorloop: while (true) {

			if (mf.getDirectorDAO().checkAdmin() == false) {
				vf.getCon().printLine("Desea salir?");
				String salir = vf.getCon().readLine();
				if (salir.equalsIgnoreCase("si"))
					break directorloop;
				crearPrimerAdmin();
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
					Bienvenido director!

					1. Modificar perfil
					2. Eliminar perfil
					3. Mostrar perfil
					4. Apartado pacientes
					5. Apartado especialistas
					6. Salir

					""";
			vf.getCon().printLine(menuD);
			int op = vf.getCon().readInt();
			switch (op) {
			case 1:
				break;

			case 2:
				break;

			case 3:
				break;

			case 4:
				break;

			case 5:
				break;

			case 6:
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

	public void crearPrimerAdmin() {
		try {
			vf.getCon().print("ID: ");
			long id = vf.getCon().readLong();
			System.out.println(id);
			ExceptionChecker.notValidIdException(id);
			vf.getCon().burnLine();
			vf.getCon().print("NOMBRE: ");
			String nombre = vf.getCon().readLine();
			System.out.println(nombre);
			ExceptionChecker.notValidStringInputException(nombre);
			vf.getCon().print("EDAD: ");
			int edad = vf.getCon().readInt();
			System.out.println(edad);
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
			System.out.println(genero);

			vf.getCon().print("CORREO: ");
			String correo = vf.getCon().readLine();
			ExceptionChecker.checkEmail(correo);
			System.out.println(correo);

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
				} else {
					break passloop;
				}
			}
			System.out.println(password);
			System.out.println(confirmP);

			if (mf.getDirectorDAO().add(new DirectorDTO(id, nombre, edad, genero, correo, password)) == true) {
				vf.getCon().printLine(mf.getDirectorDAO().showAll());
				vf.getCon().printLine("Usuario creado con exito");
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
				vf.getCon().printLine("Bienvenido \nID:");
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
