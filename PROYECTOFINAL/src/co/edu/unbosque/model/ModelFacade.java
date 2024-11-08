package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.DirectorDAO;
import co.edu.unbosque.model.persistence.EspecialidadDAO;
import co.edu.unbosque.model.persistence.EspecialistaDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;

public class ModelFacade {

	private PacienteDAO pacienteDAO;
	private EspecialistaDAO especialistaDAO;
	private DirectorDAO directorDAO;
	private EspecialidadDAO especialidadDAO;

	public ModelFacade() {
		pacienteDAO = new PacienteDAO();
		especialistaDAO = new EspecialistaDAO();
		directorDAO = new DirectorDAO();
		especialidadDAO = new EspecialidadDAO();

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

	public DirectorDAO getDirectorDAO() {
		return directorDAO;
	}

	public void setDirectorDAO(DirectorDAO directorDAO) {
		this.directorDAO = directorDAO;
	}

	public EspecialidadDAO getEspecialidadDAO() {
		return especialidadDAO;
	}

	public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
		this.especialidadDAO = especialidadDAO;
	}

}
