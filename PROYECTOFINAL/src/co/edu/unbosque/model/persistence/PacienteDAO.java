package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Paciente;
import co.edu.unbosque.model.PacienteDTO;

public class PacienteDAO implements CRUDOperation<PacienteDTO, Paciente> {

	@Override
	public String showAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(PacienteDTO newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PacienteDTO newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PacienteDTO previous, PacienteDTO newData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Paciente find(Paciente toFind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readSerialized() {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeSerialized() {
		// TODO Auto-generated method stub

	}

}
