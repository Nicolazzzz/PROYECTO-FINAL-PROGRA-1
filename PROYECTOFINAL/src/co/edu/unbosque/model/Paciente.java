package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * La clase Paciente representa a un paciente dentro del sistema, 
 * heredando de la clase Persona y añadiendo atributos y comportamientos 
 * específicos como tratamiento, diagnóstico, especialidad de la cita, 
 * especialista asignado y la necesidad de seguimiento.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 * @see Persona
 */
public class Paciente extends Persona implements Serializable {

    /**
     * Versión de serialización de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Tratamiento que recibe el paciente.
     */
    private String tratamiento;

    /**
     * Diagnóstico médico del paciente.
     */
    private String diagnostico;

    /**
     * Especialidad de la cita médica del paciente.
     */
    private String especialidadCita;

    /**
     * Especialista asignado al paciente.
     */
    private String especialistaAsignado;

    /**
     * Indica si el paciente requiere seguimiento posterior.
     */
    private boolean requiereSeguimiento;

    /**
     * Constructor vacío de la clase Paciente.
     */
    public Paciente() {
        // Constructor vacío
    }

    /**
     * Constructor que inicializa los atributos tratamiento, diagnóstico, especialidad de la cita,
     * especialista asignado y si requiere seguimiento.
     * 
     * @param tratamiento Tratamiento que recibe el paciente.
     * @param diagnostico Diagnóstico del paciente.
     * @param especialidadCita Especialidad de la cita médica.
     * @param especialistaAsignado Especialista asignado al paciente.
     * @param requiereSeguimiento Si el paciente requiere seguimiento posterior.
     */
    public Paciente(String tratamiento, String diagnostico, String especialidadCita, String especialistaAsignado,
                    boolean requiereSeguimiento) {
        super();
        this.tratamiento = tratamiento;
        this.diagnostico = diagnostico;
        this.especialidadCita = especialidadCita;
        this.especialistaAsignado = especialistaAsignado;
        this.requiereSeguimiento = requiereSeguimiento;
    }

    /**
     * Constructor que inicializa todos los atributos de la clase Paciente, 
     * incluyendo los heredados de Persona.
     * 
     * @param id Identificador único del paciente.
     * @param nombre Nombre del paciente.
     * @param edad Edad del paciente.
     * @param genero Género del paciente.
     * @param correo Correo electrónico del paciente.
     * @param tratamiento Tratamiento que recibe el paciente.
     * @param diagnostico Diagnóstico médico del paciente.
     * @param especialidadCita Especialidad de la cita médica.
     * @param especialistaAsignado Especialista asignado al paciente.
     * @param requiereSeguimiento Si el paciente requiere seguimiento.
     */
    public Paciente(long id, String nombre, int edad, String genero, String correo, String tratamiento,
                    String diagnostico, String especialidadCita, String especialistaAsignado, boolean requiereSeguimiento) {
        super(id, nombre, edad, genero, correo);
        this.tratamiento = tratamiento;
        this.diagnostico = diagnostico;
        this.especialidadCita = especialidadCita;
        this.especialistaAsignado = especialistaAsignado;
        this.requiereSeguimiento = requiereSeguimiento;
    }

    /**
     * Constructor que inicializa los atributos de la clase Persona, sin inicializar los atributos específicos de Paciente.
     * 
     * @param id Identificador único del paciente.
     * @param nombre Nombre del paciente.
     * @param edad Edad del paciente.
     * @param genero Género del paciente.
     * @param correo Correo electrónico del paciente.
     */
    public Paciente(long id, String nombre, int edad, String genero, String correo) {
        super(id, nombre, edad, genero, correo);
        // Constructor vacío
    }

    /**
     * Obtiene el tratamiento que recibe el paciente.
     * 
     * @return Tratamiento del paciente.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Establece el tratamiento que recibe el paciente.
     * 
     * @param tratamiento Nuevo tratamiento del paciente.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Obtiene el diagnóstico del paciente.
     * 
     * @return Diagnóstico médico del paciente.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Establece el diagnóstico del paciente.
     * 
     * @param diagnostico Nuevo diagnóstico del paciente.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Obtiene la especialidad de la cita médica.
     * 
     * @return Especialidad de la cita.
     */
    public String getEspecialidadCita() {
        return especialidadCita;
    }

    /**
     * Establece la especialidad de la cita médica del paciente.
     * 
     * @param especialidadCita Nueva especialidad de la cita.
     */
    public void setEspecialidadCita(String especialidadCita) {
        this.especialidadCita = especialidadCita;
    }

    /**
     * Obtiene el especialista asignado al paciente.
     * 
     * @return Especialista asignado.
     */
    public String getEspecialistaAsignado() {
        return especialistaAsignado;
    }

    /**
     * Establece el especialista asignado al paciente.
     * 
     * @param especialistaAsignado Nuevo especialista asignado.
     */
    public void setEspecialistaAsignado(String especialistaAsignado) {
        this.especialistaAsignado = especialistaAsignado;
    }

    /**
     * Indica si el paciente requiere seguimiento posterior.
     * 
     * @return true si el paciente requiere seguimiento, false en caso contrario.
     */
    public boolean isRequiereSeguimiento() {
        return requiereSeguimiento;
    }

    /**
     * Establece si el paciente requiere seguimiento posterior.
     * 
     * @param requiereSeguimiento Si el paciente requiere o no seguimiento.
     */
    public void setRequiereSeguimiento(boolean requiereSeguimiento) {
        this.requiereSeguimiento = requiereSeguimiento;
    }

    /**
     * Obtiene la versión de serialización de la clase.
     * 
     * @return La versión de serialización.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Retorna una representación en cadena de los datos del paciente, 
     * incluyendo los atributos heredados de la clase Persona y los atributos específicos de Paciente.
     * 
     * @return Una cadena que representa los datos del paciente.
     */
    @Override
    public String toString() {
        return super.toString() + "\nTratamiento= " + tratamiento + "\nDiagnostico= " + diagnostico + "\nEspecialidad= "
                + especialidadCita + "\nEspecialista Asignado= " + especialistaAsignado + "\nRequiere Seguimiento= "
                + requiereSeguimiento;
    }

}
