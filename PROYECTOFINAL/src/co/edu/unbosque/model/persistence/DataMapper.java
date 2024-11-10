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

/**
 * La clase DataMapper contiene métodos estáticos para convertir entre objetos de tipo DTO 
 * (Data Transfer Object) y las entidades correspondientes en el sistema.
 * Cada conjunto de métodos se dedica a una entidad específica, como Paciente, Especialista, 
 * Director y Especialidad.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class DataMapper {

    // PACIENTES

    /**
     * Convierte un objeto PacienteDTO en una entidad Paciente.
     * 
     * @param dto El objeto PacienteDTO a convertir.
     * @return La entidad Paciente correspondiente.
     */
    public static Paciente pacienteDTOToPaciente(PacienteDTO dto) {
        Paciente entidad = new Paciente(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
                dto.getTratamiento(), dto.getDiagnostico(), dto.getEspecialidadCita(), dto.getEspecialistaAsignado(),
                dto.isRequiereSeguimiento());
        return entidad;
    }

    /**
     * Convierte una entidad Paciente en un objeto PacienteDTO.
     * 
     * @param e La entidad Paciente a convertir.
     * @return El objeto PacienteDTO correspondiente.
     */
    public static PacienteDTO pacienteToPacienteDTO(Paciente e) {
        PacienteDTO dto = new PacienteDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
                e.getTratamiento(), e.getDiagnostico(), e.getEspecialidadCita(), e.getEspecialistaAsignado(),
                e.isRequiereSeguimiento());
        return dto;
    }

    /**
     * Convierte una lista de objetos PacienteDTO en una lista de entidades Paciente.
     * 
     * @param listaDTO La lista de objetos PacienteDTO a convertir.
     * @return La lista de entidades Paciente correspondientes.
     */
    public static ArrayList<Paciente> listaPacientesDTOToListaPacientes(ArrayList<PacienteDTO> listaDTO) {
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        for (PacienteDTO dto : listaDTO) {
            listaPacientes.add(new Paciente(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
                    dto.getCorreo(), dto.getTratamiento(), dto.getDiagnostico(), dto.getEspecialistaAsignado(),
                    dto.getEspecialidadCita(), dto.isRequiereSeguimiento()));
        }
        return listaPacientes;
    }

    /**
     * Convierte una lista de entidades Paciente en una lista de objetos PacienteDTO.
     * 
     * @param listaEntity La lista de entidades Paciente a convertir.
     * @return La lista de objetos PacienteDTO correspondientes.
     */
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

    /**
     * Convierte un objeto EspecialistaDTO en una entidad Especialista.
     * 
     * @param dto El objeto EspecialistaDTO a convertir.
     * @return La entidad Especialista correspondiente.
     */
    public static Especialista especialistaDTOToEspecialista(EspecialistaDTO dto) {
        Especialista entidad = new Especialista(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
                dto.getCorreo(), dto.getEspecialidad(), dto.getPassword());
        return entidad;
    }

    /**
     * Convierte una entidad Especialista en un objeto EspecialistaDTO.
     * 
     * @param e La entidad Especialista a convertir.
     * @return El objeto EspecialistaDTO correspondiente.
     */
    public static EspecialistaDTO especialistaToEspecialistaDTO(Especialista e) {
        EspecialistaDTO dto = new EspecialistaDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
                e.getEspecialidad(), e.getPassword());
        return dto;
    }

    /**
     * Convierte una lista de objetos EspecialistaDTO en una lista de entidades Especialista.
     * 
     * @param listaDTO La lista de objetos EspecialistaDTO a convertir.
     * @return La lista de entidades Especialista correspondientes.
     */
    public static ArrayList<Especialista> listaEspecialistasDTOToListaEspecialistas(ArrayList<EspecialistaDTO> listaDTO) {
        ArrayList<Especialista> listaEntidad = new ArrayList<>();
        for (EspecialistaDTO dto : listaDTO) {
            listaEntidad.add(new Especialista(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(),
                    dto.getCorreo(), dto.getEspecialidad(), dto.getPassword()));
        }
        return listaEntidad;
    }

    /**
     * Convierte una lista de entidades Especialista en una lista de objetos EspecialistaDTO.
     * 
     * @param listaEntity La lista de entidades Especialista a convertir.
     * @return La lista de objetos EspecialistaDTO correspondientes.
     */
    public static ArrayList<EspecialistaDTO> listaEspecialistasToListaEspecialistasDTO(ArrayList<Especialista> listaEntity) {
        ArrayList<EspecialistaDTO> listaDTO = new ArrayList<>();
        for (Especialista e : listaEntity) {
            listaDTO.add(new EspecialistaDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
                    e.getEspecialidad(), e.getPassword()));
        }
        return listaDTO;
    }

    // DIRECTOR

    /**
     * Convierte un objeto DirectorDTO en una entidad Director.
     * 
     * @param dto El objeto DirectorDTO a convertir.
     * @return La entidad Director correspondiente.
     */
    public static Director directorDTOTodirector(DirectorDTO dto) {
        Director entidad = new Director(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
                dto.getPassword());
        return entidad;
    }

    /**
     * Convierte una lista de objetos DirectorDTO en una lista de entidades Director.
     * 
     * @param listaDTO La lista de objetos DirectorDTO a convertir.
     * @return La lista de entidades Director correspondientes.
     */
    public static ArrayList<Director> listadirectorDTOToListadirector(ArrayList<DirectorDTO> listaDTO) {
        ArrayList<Director> listaEntidad = new ArrayList<>();
        for (DirectorDTO dto : listaDTO) {
            listaEntidad.add(new Director(dto.getId(), dto.getNombre(), dto.getEdad(), dto.getGenero(), dto.getCorreo(),
                    dto.getPassword()));
        }
        return listaEntidad;
    }

    /**
     * Convierte una entidad Director en un objeto DirectorDTO.
     * 
     * @param e La entidad Director a convertir.
     * @return El objeto DirectorDTO correspondiente.
     */
    public static DirectorDTO directorTodirectorDTO(Director e) {
        DirectorDTO dto = new DirectorDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
                e.getPassword());
        return dto;
    }

    /**
     * Convierte una lista de entidades Director en una lista de objetos DirectorDTO.
     * 
     * @param listaEntidad La lista de entidades Director a convertir.
     * @return La lista de objetos DirectorDTO correspondientes.
     */
    public static ArrayList<DirectorDTO> listadirectorToListadirectorDTO(ArrayList<Director> listaEntidad) {
        ArrayList<DirectorDTO> listaDto = new ArrayList<>();
        for (Director e : listaEntidad) {
            listaDto.add(new DirectorDTO(e.getId(), e.getNombre(), e.getEdad(), e.getGenero(), e.getCorreo(),
                    e.getPassword()));
        }
        return listaDto;
    }

    // ESPECIALIDAD

    /**
     * Convierte un objeto EspecialidadDTO en una entidad Especialidad.
     * 
     * @param dto El objeto EspecialidadDTO a convertir.
     * @return La entidad Especialidad correspondiente.
     */
    public static Especialidad especialidadDTOToEspecialidad(EspecialidadDTO dto) {
        Especialidad entidad = new Especialidad(dto.getNombre());
        return entidad;
    }

    /**
     * Convierte una entidad Especialidad en un objeto EspecialidadDTO.
     * 
     * @param entity La entidad Especialidad a convertir.
     * @return El objeto EspecialidadDTO correspondiente.
     */
    public static EspecialidadDTO especialidadToEspecialidadDTO(Especialidad entity) {
        EspecialidadDTO dto = new EspecialidadDTO(entity.getNombre());
        return dto;
    }

    /**
     * Convierte una lista de objetos EspecialidadDTO en una lista de entidades Especialidad.
     * 
     * @param listaDTO La lista de objetos EspecialidadDTO a convertir.
     * @return La lista de entidades Especialidad correspondientes.
     */
    public static ArrayList<Especialidad> listaEspecialidadesDTOToListaEspecialidades(ArrayList<EspecialidadDTO> listaDTO) {
        ArrayList<Especialidad> entityList = new ArrayList<>();
        for (EspecialidadDTO dto : listaDTO) {
            entityList.add(new Especialidad(dto.getNombre()));
        }
        return entityList;
    }

    /**
     * Convierte una lista de entidades Especialidad en una lista de objetos EspecialidadDTO.
     * 
     * @param listaE La lista de entidades Especialidad a convertir.
     * @return La lista de objetos EspecialidadDTO correspondientes.
     */
    public static ArrayList<EspecialidadDTO> listaEspecialidadesToListaEspecialidadesDTO(ArrayList<Especialidad> listaE) {
        ArrayList<EspecialidadDTO> dtoList = new ArrayList<>();
        for (Especialidad e : listaE) {
            dtoList.add(new EspecialidadDTO(e.getNombre()));
        }
        return dtoList;
    }
}
