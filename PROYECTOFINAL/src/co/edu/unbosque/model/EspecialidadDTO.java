package co.edu.unbosque.model;
/**
 * La clase EspecialidadDTO representa un objeto de transferencia de datos para una especialidad.
 * Este DTO permite la transferencia de información relacionada con una especialidad, 
 * como el nombre del área de conocimiento.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class EspecialidadDTO {

    /**
     * Nombre de la especialidad.
     */
	private String nombre;
	  /**
     * Constructor sin parámetros de la clase EspecialidadDTO.
     */
	public EspecialidadDTO() {
		// TODO Auto-generated constructor stub
	}

    /**
     * Constructor que inicializa el atributo nombre de la especialidad.
     * 
     * @param nombre El nombre de la especialidad.
     */
	public EspecialidadDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	   /**
     * Obtiene el nombre de la especialidad.
     * 
     * @return El nombre de la especialidad.
     */
	public String getNombre() {
		return nombre;
	}
	   /**
     * Establece el nombre de la especialidad.
     * 
     * @param nombre El nuevo nombre de la especialidad.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	  /**
     * Retorna una representación en cadena del nombre de la especialidad.
     * 
     * @return Una cadena de texto que representa el nombre de la especialidad.
     */
	@Override
	public String toString() {
		return "\nNombre del area= " + nombre;
	}

}
