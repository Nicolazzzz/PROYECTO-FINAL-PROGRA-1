package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Director;
import co.edu.unbosque.model.DirectorDTO;
import co.edu.unbosque.model.Especialidad;
import co.edu.unbosque.model.EspecialidadDTO;
import co.edu.unbosque.model.Especialista;
import co.edu.unbosque.model.EspecialistaDTO;
import co.edu.unbosque.model.Paciente;
import co.edu.unbosque.model.PacienteDTO;

public class DataMapper {

	// PACIENTES

	public static Paciente pacienteDTOToPaciente(PacienteDTO dto) {
		Paciente entidad = new Paciente(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
				dto.getTratamiento(), dto.getDiagnostico(), dto.getEspecialidadCita(), dto.getEspecialistaAsignado(),
				dto.isRequiereSeguimiento());
		return entidad;
	}

	public static PacienteDTO pacienteToPacienteDTO(Paciente e) {
		PacienteDTO dto = new PacienteDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
				e.getTratamiento(), e.getDiagnostico(), e.getEspecialidadCita(), e.getEspecialistaAsignado(),
				e.isRequiereSeguimiento());
		return dto;
	}

	public static ArrayList<Paciente> listaPacientesDTOToListaPacientes(ArrayList<PacienteDTO> listaDTO) {

		ArrayList<Paciente> listaPacientes = new ArrayList<>();

		for (PacienteDTO dto : listaDTO) {
			listaPacientes.add(new Paciente(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
					dto.getCorreo(), dto.getTratamiento(), dto.getDiagnostico(), dto.getEspecialistaAsignado(),
					dto.getEspecialidadCita(), dto.isRequiereSeguimiento()));
		}

		return listaPacientes;

	}

	public static ArrayList<PacienteDTO> listaPacientesToListaPacientesDTO(ArrayList<Paciente> listaEntity) {

		ArrayList<PacienteDTO> listaDTO = new ArrayList<>();

		for (Paciente e : listaEntity) {
			listaDTO.add(new PacienteDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
					e.getTratamiento(), e.getDiagnostico(), e.getEspecialidadCita(), e.getEspecialistaAsignado(),
					e.isRequiereSeguimiento()));
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

	// director
//	
//	
//	

	public static Director directorDTOTodirector(DirectorDTO dto) {
		Director entidad = new Director(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
				dto.getPassword());
		return entidad;
	}

	public static ArrayList<Director> listadirectorDTOToListadirector(ArrayList<DirectorDTO> listaDTO) {

		ArrayList<Director> listaEntidad = new ArrayList<>();

		for (DirectorDTO dto : listaDTO) {
			listaEntidad.add(new Director(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
					dto.getPassword()));
		}

		return listaEntidad;

	}

	public static DirectorDTO directorTodirectorDTO(Director e) {
		DirectorDTO dto = new DirectorDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
				e.getPassword());
		return dto;
	}

	public static ArrayList<DirectorDTO> listadirectorToListadirectorDTO(ArrayList<Director> listaEntidad) {
		ArrayList<DirectorDTO> listaDto = new ArrayList<>();
		for (Director e : listaEntidad) {
			listaDto.add(new DirectorDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
					e.getPassword()));
		}

		return listaDto;
	}

	// ESPECIALIDAD
//	
//	
//	
	public static Especialidad especialidadDTOToEspecialidad(EspecialidadDTO dto) {
		Especialidad entidad = new Especialidad(dto.getNombre());
		return entidad;
	}

	public static EspecialidadDTO especialidadToEspecialidadDTO(Especialidad entity) {
		EspecialidadDTO dto = new EspecialidadDTO(entity.getNombre());
		return dto;
	}

	public static ArrayList<Especialidad> listaEspecialidadesDTOToListaEspecialidades(
			ArrayList<EspecialidadDTO> listaDTO) {

		ArrayList<Especialidad> entityList = new ArrayList<>();
		for (EspecialidadDTO dto : listaDTO) {
			entityList.add(new Especialidad(dto.getNombre()));

		}
		return entityList;

	}

	public static ArrayList<EspecialidadDTO> listaEspecialidadesToListaEspecialidadesDTO(
			ArrayList<Especialidad> listaE) {

		ArrayList<EspecialidadDTO> dtoList = new ArrayList<>();
		for (Especialidad e : listaE) {
			dtoList.add(new EspecialidadDTO(e.getNombre()));

		}
		return dtoList;

	}

}
