package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.Random;

import co.edu.unbosque.model.Director;
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
		if (!listaEspecialistas.isEmpty()) {
			for (Especialista e : listaEspecialistas) {
				content += "\nEspecialista " + pos;
				content += e + "\n";
				pos++;
			}
		} else {
			content = "No hay elementos registrados";
		}
		return content;
	}

	public ArrayList<EspecialistaDTO> getAll() {
		return DataMapper.listaEspecialistasToListaEspecialistasDTO(listaEspecialistas);
	}

	public String showSpecificArea(String area) {
		String content = "";
		int pos = 1;
		for (Especialista e : listaEspecialistas) {
			if (e.getEspecialidad().equalsIgnoreCase(area)) {
				content += "\nEspecialista " + pos;
				content += e + "\n";
				pos++;
			}

		}
		if (content.equals("") || content == null) {
			content = "No hay especialistas en esa especialidad";
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
				listaEspecialistas.add(temp);
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

	public String verifyPassword(long id, long password) {

		String content = "";
		boolean cc = false;
		boolean pswrd = false;

		for (Especialista e : listaEspecialistas) {
			if (e.getPassword() == password)
				pswrd = true;
			if (e.getId() == id) {
				cc = true;
				if (e.getPassword() == password) {
					pswrd = true;
					content = "Bienvenido " + e.getNombre() + "!";
				}
			}
		}

		if (cc == false) {
			content = "Número de identificación equivocado, verifique los datos ingresados";
		} else if (pswrd == false) {
			content = "Contraseña equivocada, Verifique su contraseña";
		}
		if (cc == false && pswrd == false) {
			content = "Contraseña y usuario incorrectos, intente nuevamente";
		}

		return content;
	}

	public boolean checkLogIn(long id, long password) {

		boolean confirmed = false;
		for (Especialista e : listaEspecialistas) {
			if (e.getId() == id) {
				if (e.getPassword() == password) {
					confirmed = true;
				}

			}
		}

		return confirmed;
	}

	public boolean setLimitSpecialist(int index, String especialidad) {

		int conta = 0;
		boolean limite = false;

		for (Especialista e : listaEspecialistas) {
			if (e.getEspecialidad().equalsIgnoreCase(especialidad)) {
				conta++;
				if (conta >= index) {
					limite = true;
					break;
				}
			}
		}
		return limite;
	}

	public String getRandomSpecialist(String especialidad) {

		int contaEspecialista = 0;

		for (Especialista e : listaEspecialistas) {
			if (e.getEspecialidad().equalsIgnoreCase(especialidad)) {
				contaEspecialista++;
			}
		}

		String[] especialistaA = new String[contaEspecialista];
		int index = 0;
		for (Especialista e : listaEspecialistas) {
			if (e.getEspecialidad().equalsIgnoreCase(especialidad)) {
				especialistaA[index] = e.getNombre();
				index++;
			}
		}
		if (especialistaA.length == 0) {
			return "No se encontraron especialistas en la especialidad solicitada.";
		}

		Random random = new Random();
		int index1 = random.nextInt(especialistaA.length);
		return especialistaA[index1];
	}

	public String pickSpecialistData(long id, boolean esNombre, boolean esEspecialidad, boolean esCorreo) {

		for (Especialista e : listaEspecialistas) {
			if (e.getId() == id) {
				if (esNombre) {
					return e.getNombre();
				}

				if (esEspecialidad) {
					return e.getEspecialidad();
				}

				if (esCorreo) {
					return e.getCorreo();
				}

			}
		}
		return null;

	}

}
