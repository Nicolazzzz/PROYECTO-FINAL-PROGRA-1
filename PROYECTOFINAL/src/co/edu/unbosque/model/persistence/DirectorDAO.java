package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Director;
import co.edu.unbosque.model.DirectorDTO;

/**
 * La clase DirectorDAO implementa las operaciones CRUD (Crear, Leer,
 * Actualizar, Eliminar) para los objetos DirectorDTO y Director. Esta clase
 * maneja la persistencia de datos de los directores utilizando archivos CSV y
 * archivos serializados.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class DirectorDAO implements CRUDOperation<DirectorDTO, Director> {

	private ArrayList<Director> listaDirectores;
	private final String FILE_NAME = "directores.csv";
	private final String SERIALIZED_NAME = "directores.bat";

	/**
	 * Constructor que inicializa el DirectorDAO, verifica las carpetas y lee los
	 * datos serializados.
	 */
	public DirectorDAO() {
		FileHandler.checkFolder();
		FileHandler.checkPropertiesFolder();
		readSerialized();
	}

	/**
	 * Muestra todos los directores registrados.
	 * 
	 * @return Un string con los detalles de todos los directores, o un mensaje
	 *         indicando que no hay directores.
	 */
	@Override
	public String showAll() {
		String content = "";
		int pos = 1;

		if (!listaDirectores.isEmpty()) {
			for (Director direccion : listaDirectores) {
				content += "\nDirector " + pos;
				content += direccion + "\n";
				pos++;
			}
		} else {
			content = "No hay elementos registrados";
		}
		return content;
	}

	/**
	 * Añade un nuevo director a la lista de directores.
	 * 
	 * @param newData El objeto DirectorDTO con los datos del nuevo director a
	 *                agregar.
	 * @return true si el director fue añadido correctamente, false si ya existe.
	 */
	@Override
	public boolean add(DirectorDTO newData) {
		if (find(DataMapper.directorDTOTodirector(newData)) == null) {
			listaDirectores.add(DataMapper.directorDTOTodirector(newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina un director de la lista de directores.
	 * 
	 * @param toDelete El objeto DirectorDTO que representa el director a eliminar.
	 * @return true si el director fue eliminado correctamente, false si no se
	 *         encontró.
	 */
	@Override
	public boolean delete(DirectorDTO toDelete) {

		Director found = find(DataMapper.directorDTOTodirector(toDelete));

		if (found != null) {
			listaDirectores.remove(found);
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Actualiza los datos de un director en la lista de directores.
	 * 
	 * @param previous El objeto DirectorDTO con los datos anteriores del director.
	 * @param newData  El objeto DirectorDTO con los nuevos datos del director.
	 * @return true si el director fue actualizado correctamente, false si no se
	 *         encontró.
	 */
	@Override
	public boolean update(DirectorDTO previous, DirectorDTO newData) {
		Director found = find(DataMapper.directorDTOTodirector(newData));
		if (found != null) {
			listaDirectores.remove(found);
			listaDirectores.add(DataMapper.directorDTOTodirector(newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Busca un director por su ID.
	 * 
	 * @param toFind El objeto Director con el ID que se quiere buscar.
	 * @return El director encontrado o null si no se encuentra.
	 */
	@Override
	public Director find(Director toFind) {
		Director found = null;

		if (!listaDirectores.isEmpty()) {
			for (Director d : listaDirectores) {
				if (d.getId() == toFind.getId()) {
					found = d;
					return found;
				}
			}
		}
		return null;
	}

	/**
	 * Lee los datos de los directores desde un archivo CSV y los almacena en la
	 * lista de directores.
	 */
	@Override
	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);

		if (content.equals("") || content == null) {
			listaDirectores = new ArrayList<>();
		} else {
			listaDirectores = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] cols = row.split(";");
				Director temp = new Director();
				temp.setId(Long.parseLong(cols[0]));
				temp.setNombre(cols[1]);
				temp.setEdad(Integer.parseInt(cols[2]));
				temp.setGenero(cols[2]);
				temp.setCorreo(cols[3]);
				listaDirectores.add(temp);
			}
		}
	}

	/**
	 * Escribe la lista de directores en un archivo CSV.
	 */
	@Override
	public void writeFile() {
		String content = "";
		for (Director d : listaDirectores) {
			content += d.getId() + ";";
			content += d.getNombre() + ";";
			content += d.getEdad() + ";";
			content += d.getGenero() + ";";
			content += d.getCorreo();
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	/**
	 * Lee los datos de los directores desde un archivo serializado y los almacena
	 * en la lista de directores.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIALIZED_NAME);
		if (content == null) {
			listaDirectores = new ArrayList<>();
		} else {
			listaDirectores = (ArrayList<Director>) content;
		}
	}

	/**
	 * Escribe la lista de directores en un archivo serializado.
	 */
	@Override
	public void writeSerialized() {
		FileHandler.writeSerialized(SERIALIZED_NAME, listaDirectores);
	}

	/**
	 * Verifica las credenciales de un director (ID y contraseña).
	 * 
	 * @param id       El ID del director.
	 * @param password La contraseña del director.
	 * @return Un mensaje indicando si las credenciales son correctas o no.
	 */
	public String verifyPassword(long id, long password) {
		String content = "";
		boolean cc = false;
		boolean pswrd = false;

		for (Director d : listaDirectores) {
			if (d.getPassword() == password)
				pswrd = true;
			if (d.getId() == id) {
				cc = true;
				if (d.getPassword() == password) {
					pswrd = true;
					content = "Bienvenido " + d.getNombre() + "!";
				}
			}
		}

		if (!cc) {
			content = "Número de identificación equivocado, verifique los datos ingresados";
		} else if (!pswrd) {
			content = "Contraseña equivocada, Verifique su contraseña";
		} else if (!cc && !pswrd) {
			content = "Contraseña y usuario incorrectos, intente nuevamente";
		}

		return content;
	}

	/**
	 * Verifica si las credenciales de inicio de sesión son correctas.
	 * 
	 * @param id       El ID del director.
	 * @param password La contraseña del director.
	 * @return true si las credenciales son correctas, false de lo contrario.
	 */
	public boolean checkLogIn(long id, long password) {
		boolean confirmed = false;
		for (Director d : listaDirectores) {
			if (d.getId() == id) {
				if (d.getPassword() == password) {
					confirmed = true;
				}
			}
		}

		return confirmed;
	}

	/**
	 * Verifica si existen directores registrados en el sistema.
	 * 
	 * @return true si existen directores, false si no.
	 */
	public boolean checkAdmin() {
		return !listaDirectores.isEmpty();
	}

	/**
	 * Obtiene información del director (nombre o correo) dado su ID.
	 * 
	 * @param id       El ID del director.
	 * @param esNombre true si se desea obtener el nombre, false si se desea obtener
	 *                 el correo.
	 * @param esGmail  true si se desea obtener el correo del director.
	 * @return El nombre o el correo del director, dependiendo de los parámetros.
	 */
	public String pickData(long id, boolean esNombre, boolean esGmail) {
		String content = "";

		for (Director d : listaDirectores) {
			if (d.getId() == id) {
				if (esNombre) {
					content = d.getNombre();
				}
				if (esGmail) {
					content = d.getCorreo();
				}
			}
		}
		return content;
	}

	public ArrayList<Director> getListaDirectores() {
		return listaDirectores;
	}

	public void setListaDirectores(ArrayList<Director> listaDirectores) {
		this.listaDirectores = listaDirectores;
	}
}
