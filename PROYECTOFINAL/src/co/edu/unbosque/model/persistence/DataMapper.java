package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Direccion;
import co.edu.unbosque.model.DireccionDTO;
import co.edu.unbosque.model.Especialista;
import co.edu.unbosque.model.EspecialistaDTO;
import co.edu.unbosque.model.Paciente;
import co.edu.unbosque.model.PacienteDTO;

public class DataMapper {

	// PACIENTES

	public static Paciente pacienteDTOToPaciente(PacienteDTO dto) {
		Paciente entidad = new Paciente(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
				dto.getTratamiento(), dto.getDiagnostico(), dto.isRequiereSeguimiento());
		return entidad;
	}

	public static PacienteDTO pacienteToPacienteDTO(Paciente e) {
		PacienteDTO dto = new PacienteDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
				e.getTratamiento(), e.getDiagnostico(), e.isRequiereSeguimiento());
		return dto;
	}

	public static ArrayList<Paciente> listaPacientesDTOToListaPacientes(ArrayList<PacienteDTO> listaDTO) {

		ArrayList<Paciente> listaPacientes = new ArrayList<>();

		for (PacienteDTO dto : listaDTO) {
			listaPacientes.add(new Paciente(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
					dto.getCorreo(), dto.getTratamiento(), dto.getDiagnostico(), dto.isRequiereSeguimiento()));
		}

		return listaPacientes;

	}

	public static ArrayList<PacienteDTO> listaPacientesToListaPacientesDTO(ArrayList<Paciente> listaEntity) {

		ArrayList<PacienteDTO> listaDTO = new ArrayList<>();

		for (Paciente e : listaEntity) {
			listaDTO.add(new PacienteDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
					e.getTratamiento(), e.getDiagnostico(), e.isRequiereSeguimiento()));
		}

		return listaDTO;

	}

	// ESPECIALISTAS
//	
//
	public static Especialista especialistaDTOToEspecialista(EspecialistaDTO dto) {
		Especialista entidad = new Especialista(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
				dto.getCorreo(), dto.getEspecialidad(), dto.getPassword());
		return entidad;
	}

	public static EspecialistaDTO especialistaToEspecialistaDTO(Especialista e) {
		EspecialistaDTO dto = new EspecialistaDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
				e.getEspecialidad(), e.getPassword());
		return dto;
	}

	public static ArrayList<Especialista> listaEspecialistasDTOToListaEspecialistas(
			ArrayList<EspecialistaDTO> listaDTO) {

		ArrayList<Especialista> listaEntidad = new ArrayList<>();

		for (EspecialistaDTO dto : listaDTO) {

			listaEntidad.add(new Especialista(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
					dto.getCorreo(), dto.getEspecialidad(), dto.getPassword()));

		}

		return listaEntidad;

	}

	public static ArrayList<EspecialistaDTO> listaEspecialistasToListaEspecialistasDTO(
			ArrayList<Especialista> listaEntity) {

		ArrayList<EspecialistaDTO> listaDTO = new ArrayList<>();

		for (Especialista e : listaEntity) {

			listaDTO.add(new EspecialistaDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
					e.getEspecialidad(), e.getPassword()));

		}

		return listaDTO;

	}
	
	//DIRECCION
//	
//	
//	

	public static Direccion direccionDTOToDireccion(DireccionDTO dto) {
		Direccion entidad = new Direccion(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
				dto.getPassword());
		return entidad;
	}

	public static ArrayList<Direccion> listaDireccionDTOToListaDireccion(ArrayList<DireccionDTO> listaDTO) {

		ArrayList<Direccion> listaEntidad = new ArrayList<>();

		for (DireccionDTO dto : listaDTO) {
			listaEntidad.add(new Direccion(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
					dto.getCorreo(), dto.getPassword()));
		}

		return listaEntidad;

	}

	public static DireccionDTO direccionToDireccionDTO(Direccion e) {
		DireccionDTO dto = new DireccionDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
				e.getPassword());
		return dto;
	}

	public static ArrayList<DireccionDTO> listaDireccionToListaDireccionDTO(ArrayList<Direccion> listaEntidad) {
		ArrayList<DireccionDTO> listaDto = new ArrayList<>();
		for (Direccion e : listaEntidad) {
			listaDto.add(new DireccionDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
					e.getPassword()));
		}

		return listaDto;
	}
}
