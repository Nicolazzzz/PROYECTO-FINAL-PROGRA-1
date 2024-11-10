package co.edu.unbosque.model.persistence;

/**
 * La interfaz CRUDOperation define las operaciones básicas de manipulación de datos
 * (Crear, Leer, Actualizar, Eliminar) que pueden ser implementadas por clases
 * responsables de interactuar con los datos de una entidad genérica. La interfaz 
 * está parametrizada con dos tipos: D para los datos y E para la entidad.
 * 
 * @param <D> Tipo de los datos que se van a manipular.
 * @param <E> Tipo de la entidad que se va a gestionar.
 * 
 * @author Nicolas Zambrano
 * @version 1.0
 */
public interface CRUDOperation<D, E> {

    /**
     * Muestra todos los elementos almacenados.
     * 
     * @return Una cadena con la representación de todos los elementos.
     */
    public String showAll();

    /**
     * Agrega un nuevo dato.
     * 
     * @param newData El dato a agregar.
     * @return true si el dato fue agregado exitosamente, false si ocurrió un error.
     */
    public boolean add(D newData);

    /**
     * Elimina un dato especificado.
     * 
     * @param toDelete El dato a eliminar.
     * @return true si el dato fue eliminado exitosamente, false si ocurrió un error.
     */
    public boolean delete(D toDelete);

    /**
     * Actualiza un dato previamente almacenado con nuevos valores.
     * 
     * @param previous El dato que se desea actualizar.
     * @param newData El nuevo dato que reemplazará al anterior.
     * @return true si la actualización fue exitosa, false si ocurrió un error.
     */
    public boolean update(D previous, D newData);

    /**
     * Busca un dato específico.
     * 
     * @param toFind El dato a buscar.
     * @return El dato encontrado o null si no se encuentra.
     */
    public E find(E toFind);

    /**
     * Lee los datos desde un archivo de texto.
     */
    public void readFile();

    /**
     * Escribe los datos en un archivo de texto.
     */
    public void writeFile();

    /**
     * Lee los datos desde un archivo serializado.
     */
    public void readSerialized();

    /**
     * Escribe los datos en un archivo serializado.
     */
    public void writeSerialized();

}