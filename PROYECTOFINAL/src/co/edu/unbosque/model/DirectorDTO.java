package co.edu.unbosque.model;
/**
 * La clase DirectorDTO representa a un director en el contexto de un DTO (Data Transfer Object).
 * Hereda de la clase PersonaDTO y permite la transferencia de datos relacionados con un director,
 * incluyendo un atributo de contraseña y métodos para establecer y obtener su valor.
 * 
 * 
 * @author Mario Rodriguez
 * @see PersonaDTO
 */
public class DirectorDTO extends PersonaDTO {

	   /**
     * Versión de serialización de la clase.
     */
	private static final long serialVersionUID = 1L;
	 /**
     * Contraseña del director.
     */
	private long password;

    /**
     * Constructor sin parámetros de la clase DirectorDTO.
     */
	public DirectorDTO() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * Constructor que inicializa el atributo password del director.
     * 
     * @param password La contraseña del director.
     */
	public DirectorDTO(long password) {
		super();
		this.password = password;
	}
	  /**
     * Constructor que inicializa todos los atributos de la clase DirectorDTO, 
     * incluyendo los atributos heredados de la clase PersonaDTO.
     * 
     * @param id Identificador único del director.
     * @param nombre Nombre del director.
     * @param edad Edad del director.
     * @param genero Género del director.
     * @param correo Correo electrónico del director.
     * @param password Contraseña del director.
     */
	public DirectorDTO(long id, String nombre, int edad, String genero, String correo, long password) {
		super(id, nombre, edad, genero, correo);
		this.password = password;
	}

    /**
     * Constructor que inicializa los atributos de la clase PersonaDTO,
     * sin inicializar la contraseña.
     * 
     * @param id Identificador único del director.
     * @param nombre Nombre del director.
     * @param edad Edad del director.
     * @param genero Género del director.
     * @param correo Correo electrónico del director.
     */
	public DirectorDTO(long id, String nombre, int edad, String genero, String correo) {
		super(id, nombre, edad, genero, correo);
		// TODO Auto-generated constructor stub
	}
	/**
     * Obtiene la contraseña del director.
     * 
     * @return Contraseña del director.
     */
	public long getPassword() {
		return password;
	}
	   /**
     * Establece la contraseña del director.
     * 
     * @param password La nueva contraseña del director.
     */
	public void setPassword(long password) {
		this.password = password;
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
     * Retorna una representación en cadena de la contraseña del director.
     * 
     * @return Una cadena de texto que representa la contraseña del director.
     */
	@Override
	public String toString() {
		return "\nPassword= " + password;
	}

}
