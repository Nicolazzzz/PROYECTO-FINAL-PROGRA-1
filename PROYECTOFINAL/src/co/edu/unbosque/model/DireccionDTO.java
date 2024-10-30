package co.edu.unbosque.model;

public class DireccionDTO extends PersonaDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long password;

	public DireccionDTO() {
		// TODO Auto-generated constructor stub
	}

	public DireccionDTO(long password) {
		super();
		this.password = password;
	}

	public DireccionDTO(long id, String nombre, int edad, String genero, String correo, long password) {
		super(id, nombre, edad, genero, correo);
		this.password = password;
	}

	public DireccionDTO(long id, String nombre, int edad, String genero, String correo) {
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
