/**
 * La clase ModelFacade actúa como un intermediario (facade) para gestionar 
 * los distintos DAOs (Data Access Objects) del modelo, facilitando el acceso 
 * a los datos de los objetos Paciente, Especialista, Director y Especialidad.
 * 
 * Proporciona métodos para obtener y establecer instancias de cada DAO, 
 * encapsulando la lógica de acceso a datos y mejorando la modularidad y mantenibilidad del código.
 * 
 * @author Nicolas Zambrano
 * @see PacienteDAO
 * @see EspecialistaDAO
 * @see DirectorDAO
 * @see EspecialidadDAO
 * @version 1.0
 */
package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.DirectorDAO;
import co.edu.unbosque.model.persistence.EspecialidadDAO;
import co.edu.unbosque.model.persistence.EspecialistaDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;

public class ModelFacade {

    /**
     * DAO para gestionar los datos de los pacientes.
     */
    private PacienteDAO pacienteDAO;

    /**
     * DAO para gestionar los datos de los especialistas.
     */
    private EspecialistaDAO especialistaDAO;

    /**
     * DAO para gestionar los datos de los directores.
     */
    private DirectorDAO directorDAO;

    /**
     * DAO para gestionar las especialidades.
     */
    private EspecialidadDAO especialidadDAO;

    /**
     * Constructor sin parámetros que inicializa cada DAO.
     */
    public ModelFacade() {
        pacienteDAO = new PacienteDAO();
        especialistaDAO = new EspecialistaDAO();
        directorDAO = new DirectorDAO();
        especialidadDAO = new EspecialidadDAO();
    }

    /**
     * Obtiene el DAO de pacientes.
     * 
     * @return Instancia de PacienteDAO.
     */
    public PacienteDAO getPacienteDAO() {
        return pacienteDAO;
    }

    /**
     * Establece el DAO de pacientes.
     * 
     * @param pacienteDAO Nueva instancia de PacienteDAO.
     */
    public void setPacienteDAO(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    /**
     * Obtiene el DAO de especialistas.
     * 
     * @return Instancia de EspecialistaDAO.
     */
    public EspecialistaDAO getEspecialistaDAO() {
        return especialistaDAO;
    }

    /**
     * Establece el DAO de especialistas.
     * 
     * @param especialistaDAO Nueva instancia de EspecialistaDAO.
     */
    public void setEspecialistaDAO(EspecialistaDAO especialistaDAO) {
        this.especialistaDAO = especialistaDAO;
    }

    /**
     * Obtiene el DAO de directores.
     * 
     * @return Instancia de DirectorDAO.
     */
    public DirectorDAO getDirectorDAO() {
        return directorDAO;
    }

    /**
     * Establece el DAO de directores.
     * 
     * @param directorDAO Nueva instancia de DirectorDAO.
     */
    public void setDirectorDAO(DirectorDAO directorDAO) {
        this.directorDAO = directorDAO;
    }

    /**
     * Obtiene el DAO de especialidades.
     * 
     * @return Instancia de EspecialidadDAO.
     */
    public EspecialidadDAO getEspecialidadDAO() {
        return especialidadDAO;
    }

    /**
     * Establece el DAO de especialidades.
     * 
     * @param especialidadDAO Nueva instancia de EspecialidadDAO.
     */
    public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
        this.especialidadDAO = especialidadDAO;
    }

}