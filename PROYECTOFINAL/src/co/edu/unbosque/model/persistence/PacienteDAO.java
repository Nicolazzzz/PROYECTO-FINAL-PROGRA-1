package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Especialista;
import co.edu.unbosque.model.Paciente;
import co.edu.unbosque.model.PacienteDTO;

public class PacienteDAO implements CRUDOperation<PacienteDTO, Paciente> {

	private ArrayList<Paciente> listaPacientes;
	private final String FILE_NAME = "pacientes.csv";
	private final String SERIALIZED_NAME = "pacientes.bat";

	public PacienteDAO() {
		FileHandler.checkFolder();
		FileHandler.checkPropertiesFolder();
		readSerialized();
	}

	@Override
	public String showAll() {
		String content = "";
		int pos = 1;
		for (Paciente p : listaPacientes) {
			content += "Paciente " + pos + "\n";
			content += p + "\n";
			pos++;
		}

		return content;
	}

	@Override
	public boolean add(PacienteDTO newData) {
		if (find(DataMapper.pacienteDTOToPaciente(newData)) == null) {
			listaPacientes.add(DataMapper.pacienteDTOToPaciente(newData));
			writeFile();
			writeSerialized();
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(PacienteDTO toDelete) {

		Paciente found = find(DataMapper.pacienteDTOToPaciente(toDelete));
		if (found != null) {
			listaPacientes.remove(found);
			writeFile();
			writeSerialized();
			return true;

		}

		return false;
	}

	@Override
	public boolean update(PacienteDTO previous, PacienteDTO newData) {

		Paciente found = find(DataMapper.pacienteDTOToPaciente(previous));
		if (found != null) {
			listaPacientes.remove(found);
			listaPacientes.add(DataMapper.pacienteDTOToPaciente(newData));
			writeFile();
			writeSerialized();
			return true;
		}

		return false;

	}

	@Override
	public Paciente find(Paciente toFind) {

		Paciente found = null;
		if (!listaPacientes.isEmpty()) {
			for (Paciente p : listaPacientes) {
				if (p.getId() == toFind.getId()) {
					found = p;
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
			listaPacientes = new ArrayList<>();
		} else {
			listaPacientes = new ArrayList<>();
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
		for (Paciente p : listaPacientes) {
			content += p.getId() + ";";
			content += p.getNombre() + ";";
			content += p.getEdad() + ";";
			content += p.getGenero() + ";";
			content += p.getCorreo();
			content += "\n";

		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIALIZED_NAME);
		if (content == null) {
			listaPacientes = new ArrayList<>();
		} else {
			listaPacientes = (ArrayList<Paciente>) content;
		}
	}

	@Override
	public void writeSerialized() {
		FileHandler.writeSerialized(SERIALIZED_NAME, listaPacientes);
	}

}
