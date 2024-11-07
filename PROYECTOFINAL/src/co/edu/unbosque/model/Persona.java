package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Persona implements Serializable {

	private long id;
	private String nombre;
	private int edad;
	private String genero;
	private String correo;

	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public Persona(long id, String nombre, int edad, String genero, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.genero = genero;
		this.correo = correo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "\nNúmero de identificación= " + id + "\nNombre= " + nombre + "\nEdad= " + edad + "\nGenero= " + genero
				+ "\nCorreo electrónico= " + correo;
	}

}
