package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Paciente;
import co.edu.unbosque.model.PacienteDTO;

/**
 * La clase PacienteDAO implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar
 * la información de los pacientes. Utiliza archivos para almacenar los datos en formato CSV y serializado.
 * Además, proporciona funcionalidades adicionales para filtrar pacientes por especialidad o especialista asignado.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class PacienteDAO implements CRUDOperation<PacienteDTO, Paciente> {

    private ArrayList<Paciente> listaPacientes;
    private final String FILE_NAME = "pacientes.csv";
    private final String SERIALIZED_NAME = "pacientes.bat";

    /**
     * Constructor que inicializa la clase verificando las carpetas necesarias y leyendo los datos serializados
     * o de archivo, si existen.
     */
    public PacienteDAO() {
        FileHandler.checkFolder();
        FileHandler.checkPropertiesFolder();
        readSerialized();
    }

    /**
     * Muestra todos los pacientes almacenados en el sistema.
     * 
     * @return Una cadena que contiene la información de todos los pacientes.
     */
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

    /**
     * Devuelve una lista de objetos PacienteDTO a partir de la lista de pacientes.
     * 
     * @return Una lista de PacienteDTO con la información de los pacientes.
     */
    public ArrayList<PacienteDTO> getAll() {
        return DataMapper.listaPacientesToListaPacientesDTO(listaPacientes);
    }

    /**
     * Muestra los pacientes que tienen citas en una especialidad específica.
     * 
     * @param especialidad La especialidad a filtrar.
     * @return Una cadena que contiene los pacientes que tienen citas en esa especialidad.
     */
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

    /**
     * Muestra un paciente específico basado en su ID.
     * 
     * @param id El ID del paciente a buscar.
     * @return Una cadena que contiene la información del paciente con el ID especificado.
     */
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

    /**
     * Muestra los pacientes que tienen un especialista asignado específico.
     * 
     * @param nombre El nombre del especialista.
     * @return Una cadena que contiene los pacientes que tienen un turno con el especialista.
     */
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

    /**
     * Agrega un nuevo paciente al sistema.
     * 
     * @param newData El objeto PacienteDTO con los datos del nuevo paciente.
     * @return true si el paciente fue agregado correctamente, false si ya existía.
     */
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

    /**
     * Elimina un paciente del sistema.
     * 
     * @param toDelete El objeto PacienteDTO con los datos del paciente a eliminar.
     * @return true si el paciente fue eliminado correctamente, false si no fue encontrado.
     */
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

    /**
     * Actualiza la información de un paciente en el sistema.
     * 
     * @param previous El objeto PacienteDTO con los datos del paciente anterior.
     * @param newData El objeto PacienteDTO con los nuevos datos del paciente.
     * @return true si el paciente fue actualizado correctamente, false si no fue encontrado.
     */
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

    /**
     * Busca un paciente específico en la lista de pacientes.
     * 
     * @param toFind El objeto Paciente con los datos a buscar.
     * @return El paciente encontrado, o null si no se encuentra.
     */
    @Override
    public Paciente find(Paciente toFind) {
        Paciente found = null;
        if (!listaPacientes.isEmpty()) {
            for (Paciente p : listaPacientes) {
                if (p.getId() == toFind.getId()
                        && p.getEspecialidadCita().equalsIgnoreCase(toFind.getEspecialidadCita())) {
                    found = p;
                    return found;
                }
            }
        }
        return null;
    }

    /**
     * Lee los datos de los pacientes desde un archivo de texto plano.
     */
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
                temp.setGenero(cols[3]);
                temp.setCorreo(cols[4]);
                listaPacientes.add(temp);
            }
        }
    }

    /**
     * Escribe los datos de los pacientes en un archivo de texto plano.
     */
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

    /**
     * Lee los datos de los pacientes desde un archivo serializado.
     */
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

    /**
     * Escribe los datos de los pacientes en un archivo serializado.
     */
    @Override
    public void writeSerialized() {
        FileHandler.writeSerialized(SERIALIZED_NAME, listaPacientes);
    }

    /**
     * Establece los datos del paciente, como tratamiento, diagnóstico o seguimiento.
     * 
     * @param id El ID del paciente a modificar.
     * @param tratamiento El tratamiento a asignar.
     * @param esTratamiento Indica si se está configurando el tratamiento.
     * @param diagnostico El diagnóstico a asignar.
     * @param esDiagnostico Indica si se está configurando el diagnóstico.
     * @param requiereSeguimiento Indica si requiere seguimiento.
     * @param esSeguimiento Indica si se está configurando el seguimiento.
     * @return Un mensaje que indica el resultado de la operación.
     */
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
                    content = "Diagnóstico generado exitosamente";
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

    /**
     * Recupera información de un paciente, como su nombre o correo.
     * 
     * @param id El ID del paciente.
     * @param especialidad La especialidad de la cita.
     * @param esCorreo Indica si se quiere obtener el correo del paciente.
     * @param esNombre Indica si se quiere obtener el nombre del paciente.
     * @return El nombre o correo del paciente.
     */
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
