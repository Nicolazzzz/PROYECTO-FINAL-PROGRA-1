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
		
		mainloop: while(true) {
			vf.getCon().printLine(prop.getProperty("main.consola.menuprincipal"));			int op = vf.getCon().readInt();
			vf.getCon().burnLine();
			
			switch (op) {
			case 1:
				
				vf.getCon().printLine(prop.getProperty("paciente.consola.menu"));	
				break;
			case 2:
				vf.getCon().printLine(prop.getProperty("especialista.consola.menu"));	
				break;
			
			case 3:
				vf.getCon().printLine(prop.getProperty("main.consola.salir"));
				break mainloop;
				
			default:
				vf.getCon().printLine(prop.getProperty("main.consola.opcioninvalida"));
				break;
			}
		}
	}
}
