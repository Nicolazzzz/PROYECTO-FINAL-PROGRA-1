package co.edu.unbosque.model;

import java.io.Serializable;

public class Direccion extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long password;

	public Direccion() {
		// TODO Auto-generated constructor stub
	}

	public Direccion(long password) {
		super();
		this.password = password;
	}

	public Direccion(long id, String nombre, int edad, String genero, String correo, long password) {
		super(id, nombre, edad, genero, correo);
		this.password = password;
	}

	public Direccion(long id, String nombre, int edad, String genero, String correo) {
		super(id, nombre, edad, genero, correo);
		// TODO Auto-generated constructor stub
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
		return "\nPassword= " + password;
	}

}
