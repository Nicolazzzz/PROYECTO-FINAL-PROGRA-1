package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Especialista;
import co.edu.unbosque.model.EspecialistaDTO;

public class EspecialistaDAO implements CRUDOperation<EspecialistaDTO, Especialista> {

	private ArrayList<Especialista> listaEspecialistas;
	private final String FILE_NAME = "especialistas.csv";
	private final String SERIALIZED_NAME = "especialistas.bat";

	public EspecialistaDAO() {
		FileHandler.checkFolder();
		FileHandler.checkPropertiesFolder();
		readSerialized();

	}

	@Override
	public String showAll() {
		String content = "";
		int pos = 1;
		for (Especialista s : listaEspecialistas) {
			content += "Especialista " + pos + "\n";
			content += s + "\n";
			pos++;
		}
		return content;

	}

	@Override
	public boolean add(EspecialistaDTO newData) {
		if (find(DataMapper.especialistaDTOToEspecialista(newData)) == null) {
			listaEspecialistas.add(DataMapper.especialistaDTOToEspecialista(newData));
			writeFile();
			writeSerialized();
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(EspecialistaDTO toDelete) {
		Especialista found = find(DataMapper.especialistaDTOToEspecialista(toDelete));

		if (found != null) {
			listaEspecialistas.remove(found);
			writeFile();
			writeSerialized();
			return true;
		}

		return false;
	}

	@Override
	public boolean update(EspecialistaDTO previous, EspecialistaDTO newData) {
		Especialista found = find(DataMapper.especialistaDTOToEspecialista(previous));
		if (found != null) {
			listaEspecialistas.remove(found);
			listaEspecialistas.add(DataMapper.especialistaDTOToEspecialista(newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Especialista find(Especialista toFind) {
		Especialista found = null;
		if (!listaEspecialistas.isEmpty()) {
			for (Especialista e : listaEspecialistas) {
				if (e.getId() == (toFind.getId())) {
					found = e;
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
			listaEspecialistas = new ArrayList<>();
		} else {
			listaEspecialistas = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] cols = row.split(";");
				Especialista temp = new Especialista();
				temp.setId(Long.parseLong(cols[0]));
				temp.setNombre(cols[1]);
				temp.setEdad(Integer.parseInt(cols[2]));
				temp.setGenero(cols[2]);
				temp.setCorreo(cols[3]);

			}
		}
	}

	@Override
	public void writeFile() {
		String content = "";
		for (Especialista e : listaEspecialistas) {
			content += e.getId() + ";";
			content += e.getNombre() + ";";
			content += e.getEdad() + ";";
			content += e.getGenero() + ";";
			content += e.getCorreo();
			content += "\n";

		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIALIZED_NAME);
		if (content == null) {
			listaEspecialistas = new ArrayList<>();
		} else {
			listaEspecialistas = (ArrayList<Especialista>) content;
		}

	}

	@Override
	public void writeSerialized() {
		FileHandler.writeSerialized(SERIALIZED_NAME, listaEspecialistas);

	}

}
