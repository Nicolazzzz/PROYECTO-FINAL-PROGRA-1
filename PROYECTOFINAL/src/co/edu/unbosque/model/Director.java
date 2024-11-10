package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * La clase Director representa a un director que hereda de la clase Persona.
 * Implementa Serializable para permitir la serialización de los objetos de esta clase.
 * Esta clase incluye un atributo de contraseña y métodos para establecer y obtener su valor.
 * 
 * @see Persona
 * @author Mario Rodriguez
 * @version 1.0
 */
public class Director extends Persona implements Serializable {

	   /**
     * Versión de serialización de la clase.
     */
	private static final long serialVersionUID = 1L;
	  /**
     * Contraseña del director.
     */
	private long password;
	 /**
     * Constructor sin parámetros de la clase Director.
     */
	public Director() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * Constructor que inicializa el atributo password del director.
     * 
     * @param password La contraseña del director.
     */
	public Director(long password) {
		super();
		this.password = password;
	}
	 /**
     * Constructor que inicializa todos los atributos de la clase Director, 
     * incluyendo los atributos heredados de la clase Persona.
     * 
     * @param id Identificador único del director.
     * @param nombre Nombre del director.
     * @param edad Edad del director.
     * @param genero Género del director.
     * @param correo Correo electrónico del director.
     * @param password Contraseña del director.
     */
	public Director(long id, String nombre, int edad, String genero, String correo, long password) {
		super(id, nombre, edad, genero, correo);
		this.password = password;
	}
	/**
     * Constructor que inicializa los atributos de la clase Persona,
     * sin inicializar la contraseña.
     * 
     * @param id Identificador único del director.
     * @param nombre Nombre del director.
     * @param edad Edad del director.
     * @param genero Género del director.
     * @param correo Correo electrónico del director.
     */
	public Director(long id, String nombre, int edad, String genero, String correo) {
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
     * Retorna una representación en cadena de los datos del director,
     * incluyendo los atributos de la clase Persona y el atributo password.
     * 
     * @return Una cadena de texto que representa los datos del director.
     */
	@Override
	public String toString() {
		return super.toString() + "\nPassword= " + password;
	}

}
