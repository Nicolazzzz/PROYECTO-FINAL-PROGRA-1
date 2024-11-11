package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Especialidad;
import co.edu.unbosque.model.EspecialidadDTO;

/**
 * La clase EspecialidadDAO implementa las operaciones CRUD (Crear, Leer,
 * Actualizar, Eliminar) para los objetos EspecialidadDTO y Especialidad. Esta
 * clase maneja la persistencia de datos de las especialidades utilizando
 * archivos CSV y archivos serializados.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class EspecialidadDAO implements CRUDOperation<EspecialidadDTO, Especialidad> {
	private ArrayList<Especialidad> listaEspecialidades;
	private static final String FILE_NAME = "especialidades.csv";
	private static final String SERIALIZED_NAME = "especialidades.bat";

	/**
	 * Constructor que inicializa el EspecialidadDAO, verifica las carpetas y lee
	 * los datos serializados.
	 */
	public EspecialidadDAO() {
		FileHandler.checkFolder();
		FileHandler.checkPropertiesFolder();
		readSerialized();
	}

	/**
	 * Muestra todas las especialidades registradas.
	 * 
	 * @return Un string con los detalles de todas las especialidades, o un mensaje
	 *         indicando que no hay especialidades.
	 */
	@Override
	public String showAll() {
		String content = "";
		int pos = 1;
		if (!listaEspecialidades.isEmpty()) {
			for (Especialidad e : listaEspecialidades) {
				content += "\nEspecialidad " + pos;
				content += e + "\n";
				pos++;
			}
		} else {
			content = "No hay elementos registrados";
		}
		return content;
	}

	/**
	 * Obtiene todas las especialidades como una lista de objetos EspecialidadDTO.
	 * 
	 * @return Una lista de objetos EspecialidadDTO con todos los datos de las
	 *         especialidades.
	 */
	public ArrayList<EspecialidadDTO> getAllDto() {
		return DataMapper.listaEspecialidadesToListaEspecialidadesDTO(listaEspecialidades);
	}

	/**
	 * Añade una nueva especialidad a la lista de especialidades.
	 * 
	 * @param newData El objeto EspecialidadDTO con los datos de la nueva
	 *                especialidad a agregar.
	 * @return true si la especialidad fue añadida correctamente, false si ya
	 *         existe.
	 */
	@Override
	public boolean add(EspecialidadDTO newData) {
		if (find(DataMapper.especialidadDTOToEspecialidad(newData)) == null) {
			listaEspecialidades.add(DataMapper.especialidadDTOToEspecialidad(newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina una especialidad de la lista de especialidades.
	 * 
	 * @param toDelete El objeto EspecialidadDTO que representa la especialidad a
	 *                 eliminar.
	 * @return true si la especialidad fue eliminada correctamente, false si no se
	 *         encontró.
	 */
	@Override
	public boolean delete(EspecialidadDTO toDelete) {
		Especialidad found = find(DataMapper.especialidadDTOToEspecialidad(toDelete));
		if (found != null) {
			listaEspecialidades.remove(found);
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Actualiza los datos de una especialidad en la lista de especialidades.
	 * 
	 * @param previous El objeto EspecialidadDTO con los datos anteriores de la
	 *                 especialidad.
	 * @param newData  El objeto EspecialidadDTO con los nuevos datos de la
	 *                 especialidad.
	 * @return true si la especialidad fue actualizada correctamente, false si no se
	 *         encontró.
	 */
	@Override
	public boolean update(EspecialidadDTO previous, EspecialidadDTO newData) {
		Especialidad found = find(DataMapper.especialidadDTOToEspecialidad(previous));
		if (found != null) {
			listaEspecialidades.add(DataMapper.especialidadDTOToEspecialidad(newData));
			listaEspecialidades.remove(found);
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Busca una especialidad por su nombre.
	 * 
	 * @param toFind El objeto Especialidad con el nombre que se quiere buscar.
	 * @return La especialidad encontrada o null si no se encuentra.
	 */
	@Override
	public Especialidad find(Especialidad toFind) {
		Especialidad found = null;
		if (!listaEspecialidades.isEmpty()) {
			for (Especialidad e : listaEspecialidades) {
				if (e.getNombre().equalsIgnoreCase(toFind.getNombre())) {
					found = e;
					return found;
				}
			}
		}
		return null;
	}

	/**
	 * Lee los datos de las especialidades desde un archivo CSV y los almacena en la
	 * lista de especialidades.
	 */
	@Override
	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);

		if (content.equals("") || content == null) {
			listaEspecialidades = new ArrayList<>();
		} else {
			listaEspecialidades = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				Especialidad temp = new Especialidad();
				temp.setNombre(row);
				listaEspecialidades.add(temp);
			}
		}
	}

	/**
	 * Escribe la lista de especialidades en un archivo CSV.
	 */
	@Override
	public void writeFile() {
		String content = "";
		for (Especialidad e : listaEspecialidades) {
			content += e.getNombre();
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	/**
	 * Lee los datos de las especialidades desde un archivo serializado y los
	 * almacena en la lista de especialidades.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIALIZED_NAME);
		if (content == null) {
			listaEspecialidades = new ArrayList<>();
		} else {
			listaEspecialidades = (ArrayList<Especialidad>) content;
		}
	}

	/**
	 * Escribe la lista de especialidades en un archivo serializado.
	 */
	@Override
	public void writeSerialized() {
		FileHandler.writeSerialized(SERIALIZED_NAME, listaEspecialidades);
	}

	/**
	 * Obtiene todos los nombres de las especialidades como un arreglo de cadenas.
	 * 
	 * @return Un arreglo de cadenas con los nombres de las especialidades, o un
	 *         mensaje si no hay especialidades.
	 */
	public String[] getAll() {
		String[] especialidad;
		if (listaEspecialidades.isEmpty()) {
			especialidad = new String[1];
			especialidad[0] = "No hay especialidades registradas";
			return especialidad;
		} else {
			especialidad = new String[listaEspecialidades.size()];
			for (int i = 0; i < especialidad.length; i++) {
				especialidad[i] = listaEspecialidades.get(i).getNombre();
			}
		}
		return especialidad;
	}

	public ArrayList<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(ArrayList<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

}