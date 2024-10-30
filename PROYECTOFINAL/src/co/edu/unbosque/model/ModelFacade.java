package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.EspecialistaDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;

public class ModelFacade {

	private PacienteDAO pacienteDAO;
	private EspecialistaDAO especialistaDAO;

	public ModelFacade() {
		pacienteDAO = new PacienteDAO();
		especialistaDAO = new EspecialistaDAO();
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public EspecialistaDAO getEspecialistaDAO() {
		return especialistaDAO;
	}

	public void setEspecialistaDAO(EspecialistaDAO especialistaDAO) {
		this.especialistaDAO = especialistaDAO;
	}

}
