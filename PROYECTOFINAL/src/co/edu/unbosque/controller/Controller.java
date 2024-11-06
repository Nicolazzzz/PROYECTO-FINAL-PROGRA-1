package co.edu.unbosque.controller;

import java.util.Properties;

import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.view.ViewFacade;

public class Controller {

	private ModelFacade mf;
	private ViewFacade vf;
	private Properties prop;

	public Controller() {
		mf = new ModelFacade();
		vf = new ViewFacade();
		prop = FileHandler.loadProperties("spanish.properties");
		run();
	}

	public void run() {
		mostrarMenuPrincipal();
	}

	public void mostrarMenuPrincipal() {

		mainloop: while (true) {
			String menu = """

					""";
			vf.getCon().printLine(prop.getProperty("main.consola.menuprincipal"));
			int op = vf.getCon().readInt();
			vf.getCon().burnLine();

			switch (op) {
			case 1:

				vf.getCon().printLine("Director");
				mostrarMenuDirector();
				break;
			case 2:
				vf.getCon().printLine("Especialista");
				mostrarMenuEspecialista();
				break;

			case 3:
				vf.getCon().printLine("Paciente");
				mostrarMenuPaciente();
				break mainloop;

			default:
				vf.getCon().printLine("Salir");
				break mainloop;
			}
		}
	}

	public void mostrarMenuDirector() {

		directorloop: while (true) {

			String menuD = """

					""";

			vf.getCon().printLine(menuD);
			int op = vf.getCon().readInt();

		}

	}

	public void mostrarMenuEspecialista() {
	}

	public void mostrarMenuPaciente() {
	}
}
