package co.edu.unbosque.model;

public class EspecialidadDTO {

	private String nombre;

	public EspecialidadDTO() {
		// TODO Auto-generated constructor stub
	}

	public EspecialidadDTO(String nombre) {
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
