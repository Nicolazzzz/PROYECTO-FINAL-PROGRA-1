package co.edu.unbosque.model;

public class EspecialistaDTO extends PersonaDTO {

	private String especialidad;

	public EspecialistaDTO() {

	}

	public EspecialistaDTO(String especialidad) {
		super();
		this.especialidad = especialidad;
	}

	public EspecialistaDTO(long id, String nombre, int edad, String genero, String correo, String especialidad) {
		super(id, nombre, edad, genero, correo);
		this.especialidad = especialidad;
	}

	public EspecialistaDTO(long id, String nombre, int edad, String genero, String correo) {
		super(id, nombre, edad, genero, correo);
		// TODO Auto-generated constructor stub
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "\nEspecialidad= " + especialidad;
	}

}
