package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * La clase Especialidad representa una especialidad o área de conocimiento.
 * Implementa Serializable para permitir la serialización de los objetos de esta clase.
 * Incluye un atributo para el nombre de la especialidad, junto con métodos para establecer 
 * y obtener su valor.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public class Especialidad implements Serializable {


    /**
     * Versión de serialización de la clase.
     */
	private static final long serialVersionUID = 1L;
	 /**
     * Nombre de la especialidad.
     */
	private String nombre;
	  /**
     * Constructor sin parámetros de la clase Especialidad.
     */
	public Especialidad() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * Constructor que inicializa el atributo nombre de la especialidad.
     * 
     * @param nombre El nombre de la especialidad.
     */
	public Especialidad(String nombre) {
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
