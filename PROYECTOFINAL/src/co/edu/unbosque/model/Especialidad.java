package co.edu.unbosque.model;

public class Especialidad {

	private String nombre;

	public Especialidad() {
		// TODO Auto-generated constructor stub
	}

	public Especialidad(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "\nNombre del area= " + nombre;
	}

}
