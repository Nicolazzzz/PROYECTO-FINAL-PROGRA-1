package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.DirreccionDAO;
import co.edu.unbosque.model.persistence.EspecialistaDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;

public class ModelFacade {

	private PacienteDAO pacienteDAO;
	private EspecialistaDAO especialistaDAO;
	private DirreccionDAO dirreccionDAO;

	public ModelFacade() {
		pacienteDAO = new PacienteDAO();
		especialistaDAO = new EspecialistaDAO();
		dirreccionDAO = new DirreccionDAO();

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

	public DirreccionDAO getDirreccionDAO() {
		return dirreccionDAO;
	}

	public void setDirreccionDAO(DirreccionDAO dirreccionDAO) {
		this.dirreccionDAO = dirreccionDAO;
	}

}
