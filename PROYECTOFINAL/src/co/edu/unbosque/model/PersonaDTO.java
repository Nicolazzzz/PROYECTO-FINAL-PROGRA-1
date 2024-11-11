package co.edu.unbosque.model;

/**
 * La clase abstracta PersonaDTO es una representación de datos (DTO) de una persona, 
 * que contiene los atributos básicos comunes a todos los tipos de personas, como 
 * identificación, nombre, edad, género y correo electrónico. 
 * Los DTO (Data Transfer Objects) son usados para transferir estos datos entre capas 
 * de la aplicación sin incluir lógica de negocio.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public abstract class PersonaDTO {

    /**
     * Identificador único de la persona.
     */
    private long id;

    /**
     * Nombre de la persona.
     */
    private String nombre;

    /**
     * Edad de la persona.
     */
    private int edad;

    /**
     * Género de la persona.
     */
    private String genero;

    /**
     * Correo electrónico de la persona.
     */
    private String correo;

    /**
     * Constructor vacío de la clase PersonaDTO.
     */
    public PersonaDTO() {
        // Constructor vacío
    }

    /**
     * Constructor que inicializa los atributos de la PersonaDTO.
     * 
     * @param id Identificador único de la persona.
     * @param nombre Nombre de la persona.
     * @param edad Edad de la persona.
     * @param genero Género de la persona.
     * @param correo Correo electrónico de la persona.
     */
    public PersonaDTO(long id, String nombre, int edad, String genero, String correo) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.correo = correo;
    }

    /**
     * Obtiene el identificador único de la persona.
     * 
     * @return El identificador único de la persona.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la persona.
     * 
     * @param id Nuevo identificador de la persona.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     * 
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     * 
     * @param nombre Nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad de la persona.
     * 
     * @return La edad de la persona.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad de la persona.
     * 
     * @param edad Nueva edad de la persona.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el género de la persona.
     * 
     * @return El género de la persona.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género de la persona.
     * 
     * @param genero Nuevo género de la persona.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el correo electrónico de la persona.
     * 
     * @return El correo electrónico de la persona.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico de la persona.
     * 
     * @param correo Nuevo correo electrónico de la persona.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna una representación en cadena de los datos de la persona.
     * 
     * @return Una cadena que representa los datos de la persona.
     */
    @Override
    public String toString() {
        return "\nNúmero de identificación= " + id + "\nNombre= " + nombre + "\nEdad= " + edad + "\nGenero= " + genero
                + "\nCorreo electrónico" + correo;
    }
}