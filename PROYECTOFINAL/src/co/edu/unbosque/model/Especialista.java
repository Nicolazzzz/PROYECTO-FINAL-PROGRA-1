package co.edu.unbosque.model;

import java.io.Serializable;
/**
 * La clase Especialista representa a un especialista que hereda de la clase Persona.
 * Implementa Serializable para permitir la serialización de los objetos de esta clase.
 * Incluye atributos para la especialidad y la contraseña, además de métodos 
 * para establecer y obtener estos valores.
 * 
 * @author Nicolas Zambrano
 * @see Persona
 * @version 1.0
 */
public class Especialista extends Persona implements Serializable {

	 /**
     * Versión de serialización de la clase.
     */
	private static final long serialVersionUID = 1L;
	  /**
     * Especialidad del especialista.
     */
	private String especialidad;
	/**
     * Contraseña del especialista.
     */
	private long password;
	/**
     * Constructor sin parámetros de la clase Especialista.
     */
	public Especialista() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * Constructor que inicializa los atributos especialidad y password del especialista.
     * 
     * @param especialidad La especialidad del especialista.
     * @param password La contraseña del especialista.
     */
	public Especialista(String especialidad, long password) {
		super();
		this.especialidad = especialidad;
		this.password = password;
	}
	  /**
     * Constructor que inicializa todos los atributos de la clase Especialista,
     * incluyendo los atributos heredados de la clase Persona.
     * 
     * @param id Identificador único del especialista.
     * @param nombre Nombre del especialista.
     * @param edad Edad del especialista.
     * @param genero Género del especialista.
     * @param correo Correo electrónico del especialista.
     * @param especialidad La especialidad del especialista.
     * @param password La contraseña del especialista.
     */
	public Especialista(long id, String nombre, int edad, String genero, String correo, String especialidad,
			long password) {
		super(id, nombre, edad, genero, correo);
		this.especialidad = especialidad;
		this.password = password;
	}
	/**
     * Constructor que inicializa los atributos de la clase Persona,
     * sin inicializar los atributos especialidad ni contraseña.
     * 
     * @param id Identificador único del especialista.
     * @param nombre Nombre del especialista.
     * @param edad Edad del especialista.
     * @param genero Género del especialista.
     * @param correo Correo electrónico del especialista.
     */
	public Especialista(long id, String nombre, int edad, String genero, String correo) {
		super(id, nombre, edad, genero, correo);
		// TODO Auto-generated constructor stub
	}
	/**
     * Obtiene la especialidad del especialista.
     * 
     * @return La especialidad del especialista.
     */
	public String getEspecialidad() {
		return especialidad;
	}
	/**
     * Establece la especialidad del especialista.
     * 
     * @param especialidad La nueva especialidad del especialista.
     */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/**
     * Obtiene la contraseña del especialista.
     * 
     * @return La contraseña del especialista.
     */
	public long getPassword() {
		return password;
	}
	   /**
     * Establece la contraseña del especialista.
     * 
     * @param password La nueva contraseña del especialista.
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
     * Retorna una representación en cadena de los datos del especialista,
     * incluyendo los atributos de la clase Persona y los atributos especialidad y password.
     * 
     * @return Una cadena de texto que representa los datos del especialista.
     */
	@Override
	public String toString() {
		return super.toString() + "\nEspecialidad= " + especialidad + "\nPassword=" + password;
	}

}
