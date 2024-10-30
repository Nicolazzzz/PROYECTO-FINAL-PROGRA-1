package co.edu.unbosque.model;

import java.io.Serializable;

public class Especialista extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;
	private long password;

	public Especialista() {
		// TODO Auto-generated constructor stub
	}

	public Especialista(String especialidad, long password) {
		super();
		this.especialidad = especialidad;
		this.password = password;
	}

	public Especialista(long id, String nombre, int edad, String genero, String correo, String especialidad,
			long password) {
		super(id, nombre, edad, genero, correo);
		this.especialidad = especialidad;
		this.password = password;
	}

	public Especialista(long id, String nombre, int edad, String genero, String correo) {
		super(id, nombre, edad, genero, correo);
		// TODO Auto-generated constructor stub
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public long getPassword() {
		return password;
	}

	public void setPassword(long password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return super.toString() + "\nEspecialidad= " + especialidad + "\nPassword=" + password;
	}

}
