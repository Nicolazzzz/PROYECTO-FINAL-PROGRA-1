package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Especialidad;
import co.edu.unbosque.model.EspecialidadDTO;

public class EspecialidadDAO implements CRUDOperation<EspecialidadDTO, Especialidad> {
	private ArrayList<Especialidad> listaEspecialidades;
	private static final String FILE_NAME = "especialidades.csv";
	private static final String SERIALIZED_NAME = "especialidades.bat";

	public EspecialidadDAO() {
		FileHandler.checkFolder();
		FileHandler.checkPropertiesFolder();
		readSerialized();

	}

	@Override
	public String showAll() {
		String content = "";
		int pos = 1;
		if (!listaEspecialidades.isEmpty()) {
			for (Especialidad e : listaEspecialidades) {
				content += "\nEspecialidad " + pos ;
				content += e + "\n";
				pos++;
			}
		} else {
			content = "No hay elementos registrados";
		}
		return content;

	}

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

	@Override
	public Especialidad find(Especialidad toFind) {
		Especialidad found = null;
		if (!listaEspecialidades.isEmpty()) {
			for (Especialidad e : listaEspecialidades) {
				if (e.getNombre().equalsIgnoreCase(toFind.getNombre())) {
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

	@Override
	public void writeFile() {
		String content = "";
		for (Especialidad e : listaEspecialidades) {
			content += e.getNombre();
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);

	}

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

	@Override
	public void writeSerialized() {
		FileHandler.writeSerialized(SERIALIZED_NAME, listaEspecialidades);
	}

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

}
