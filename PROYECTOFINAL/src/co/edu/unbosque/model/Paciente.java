package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Paciente extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tratamiento;
	private String diagnostico;
	private String especialidadCita;
	private String especialistaAsignado;
	private boolean requiereSeguimiento;

	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public Paciente(String tratamiento, String diagnostico, String especialidadCita, String especialistaAsignado,
			boolean requiereSeguimiento) {
		super();
		this.tratamiento = tratamiento;
		this.diagnostico = diagnostico;
		this.especialidadCita = especialidadCita;
		this.especialistaAsignado = especialistaAsignado;
		this.requiereSeguimiento = requiereSeguimiento;
	}

	public Paciente(long id, String nombre, int edad, String genero, String correo, String tratamiento,
			String diagnostico, String especialidadCita, String especialistaAsignado, boolean requiereSeguimiento) {
		super(id, nombre, edad, genero, correo);
		this.tratamiento = tratamiento;
		this.diagnostico = diagnostico;
		this.especialidadCita = especialidadCita;
		this.especialistaAsignado = especialistaAsignado;
		this.requiereSeguimiento = requiereSeguimiento;
	}

	public Paciente(long id, String nombre, int edad, String genero, String correo) {
		super(id, nombre, edad, genero, correo);
		// TODO Auto-generated constructor stub
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getEspecialidadCita() {
		return especialidadCita;
	}

	public void setEspecialidadCita(String especialidadCita) {
		this.especialidadCita = especialidadCita;
	}

	public String getEspecialistaAsignado() {
		return especialistaAsignado;
	}

	public void setEspecialistaAsignado(String especialistaAsignado) {
		this.especialistaAsignado = especialistaAsignado;
	}

	public boolean isRequiereSeguimiento() {
		return requiereSeguimiento;
	}

	public void setRequiereSeguimiento(boolean requiereSeguimiento) {
		this.requiereSeguimiento = requiereSeguimiento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return super.toString() + "\nTratamiento= " + tratamiento + "\nDiagnostico= " + diagnostico + "\nEspecialidad= "
				+ especialidadCita + "\nEspecialista Asignado= " + especialistaAsignado + "\nRequiere Seguimiento= "
				+ requiereSeguimiento;
	}

}
