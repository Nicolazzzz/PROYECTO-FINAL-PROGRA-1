package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Director;
import co.edu.unbosque.model.DirectorDTO;

public class DirectorDAO implements CRUDOperation<DirectorDTO, Director> {

	private ArrayList<Director> listaDirectores;
	private final String FILE_NAME = "directores.csv";
	private final String SERIALIZED_NAME = "directores.bat";

	public DirectorDAO() {
		FileHandler.checkFolder();
		FileHandler.checkPropertiesFolder();
		readSerialized();
	}

	@Override
	public String showAll() {
		String content = "";
		int pos = 1;

		if (!listaDirectores.isEmpty()) {
			for (Director direccion : listaDirectores) {
				content += "Director " + pos + "\n";
				content += direccion + "\n";
				pos++;
			}
		} else {
			content = "No hay elementos registrados";
		}
		return content;

	}

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

	@Override
	public Director find(Director toFind) {
		Director found = null;

		if (!listaDirectores.isEmpty()) {
			for (Director d : listaDirectores) {
				if (d.getId() == toFind.getId()) {
					found = d;
					return found;
				} else {
					continue;
				}

			}
		} else {
			return null;
		}

		return null;

	}

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

	@Override
	public void writeSerialized() {
		FileHandler.writeSerialized(SERIALIZED_NAME, listaDirectores);
	}

	public String verifyPassword(long id, long password) {

		for (Director d : listaDirectores) {
			if (d.getId() == id) {
				if (d.getPassword() == password) {
					return "Bienvenido " + d.getNombre() + "!";
				} else {
					return "Contraseña equivocada, Verifique su contraseña";
				}

			} else {
				return "Número de identificación equivocado, verifique los datos ingresados";
			}
		}

		return null;
	}

	public boolean checkLogIn(long id, long password) {

		for (Director d : listaDirectores) {
			if (d.getId() == id) {
				if (d.getPassword() == password) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		}

		return false;
	}

	public boolean checkAdmin() {
		if (listaDirectores.isEmpty())
			return false;
		else
			return true;
	}
}
