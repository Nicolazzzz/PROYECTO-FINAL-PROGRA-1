package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

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
		if (!listaPacientes.isEmpty()) {
			for (Paciente p : listaPacientes) {
				content += "\nPaciente " + pos;
				content += p + "\n";
				pos++;
			}
		} else {
			content = "No hay elementos registrados";
		}

		return content;
	}

	public ArrayList<PacienteDTO> getAll() {
		return DataMapper.listaPacientesToListaPacientesDTO(listaPacientes);
	}

	public String showSpecificPatientSpecialty(String especialidad) {

		String content = "";
		int pos = 1;
		for (Paciente p : listaPacientes) {
			if (p.getEspecialidadCita().equalsIgnoreCase(especialidad)) {
				content += "\nPaciente " + pos;
				content += p + "\n";
				pos++;

			}

		}
		if (content.equals("") || content == null) {
			content = "No hay pacientes con cita en esa especialidad";
		}
		return content;
	}

	public String showSpecificPatient(long id) {
		String content = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				content += "Paciente " + p.getNombre() + " cita en especialidad " + p.getEspecialidadCita();
				content += p + "\n";

			}

		}
		if (content.equals("") || content == null) {
			content = "No se encontro al paciente, verifique el id";
		}
		return content;
	}

	public String showAppointentSpecialist(String nombre) {
		String content = "";
		for (Paciente p : listaPacientes) {
			if (p.getEspecialistaAsignado().equalsIgnoreCase(nombre)) {
				content += "\nPaciente " + p.getNombre();
				content += p + "\n";
			}

		}
		if (content.equals("") || content == null) {
			content = "No tiene turnos";
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
				if (p.getId() == toFind.getId()
						&& p.getEspecialidadCita().equalsIgnoreCase(toFind.getEspecialidadCita())) {
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
				Paciente temp = new Paciente();
				temp.setId(Long.parseLong(cols[0]));
				temp.setNombre(cols[1]);
				temp.setEdad(Integer.parseInt(cols[2]));
				temp.setGenero(cols[2]);
				temp.setCorreo(cols[3]);
				listaPacientes.add(temp);

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

	public String setData(long id, String tratamiento, boolean esTratamiento, String diagnostico, boolean esDiagnostico,
			boolean requiereSeguimiento, boolean esSeguimiento) {

		String content = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {

				if (esTratamiento) {
					p.setTratamiento(tratamiento);
					content = "Tratamiento generado exitosamente";
				}

				if (esDiagnostico) {
					p.setDiagnostico(diagnostico);
					content = "Diagnostico generado exitosamente";
				}

				if (esSeguimiento) {
					p.setRequiereSeguimiento(requiereSeguimiento);
					content = "Seguimiento guardado exitosamente";
				}
			}
		}
		writeFile();
		writeSerialized();
		return content;
	}

	public String pickData(long id, String especialidad, boolean esCorreo, boolean esNombre) {
		String content = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id && p.getEspecialidadCita().equalsIgnoreCase(especialidad)) {
				if (esCorreo) {
					content = p.getCorreo();
				}
				if (esNombre) {
					content = p.getNombre();
				}
			}
		}
		return content;
	}

}
