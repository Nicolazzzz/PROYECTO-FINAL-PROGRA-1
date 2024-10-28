package co.edu.unbosque.model;

public class Area {

	private String nombre;

	public Area() {
		// TODO Auto-generated constructor stub
	}

	public Area(String nombre) {
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
