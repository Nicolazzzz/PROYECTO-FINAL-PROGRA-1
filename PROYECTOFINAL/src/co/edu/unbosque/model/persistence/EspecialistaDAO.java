package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.Random;

import co.edu.unbosque.model.Especialista;
import co.edu.unbosque.model.EspecialistaDTO;

/**
 * La clase EspecialistaDAO implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para los objetos EspecialistaDTO y Especialista. Esta clase maneja la persistencia de datos de los especialistas
 * utilizando archivos CSV y archivos serializados.
 * 
 * Además, proporciona funciones adicionales como la verificación de contraseñas, la asignación aleatoria de especialistas
 * y la búsqueda de especialistas por especialidad.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class EspecialistaDAO implements CRUDOperation<EspecialistaDTO, Especialista> {

    private ArrayList<Especialista> listaEspecialistas;
    private final String FILE_NAME = "especialistas.csv";
    private final String SERIALIZED_NAME = "especialistas.bat";

    /**
     * Constructor que inicializa el EspecialistaDAO, verifica las carpetas y lee los datos serializados.
     */
    public EspecialistaDAO() {
        FileHandler.checkFolder();
        FileHandler.checkPropertiesFolder();
        readSerialized();
    }

    /**
     * Muestra todos los especialistas registrados.
     * 
     * @return Un string con los detalles de todos los especialistas, o un mensaje indicando que no hay especialistas.
     */
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

    /**
     * Obtiene todos los especialistas como una lista de objetos EspecialistaDTO.
     * 
     * @return Una lista de objetos EspecialistaDTO con todos los datos de los especialistas.
     */
    public ArrayList<EspecialistaDTO> getAll() {
        return DataMapper.listaEspecialistasToListaEspecialistasDTO(listaEspecialistas);
    }

    /**
     * Muestra los especialistas de una especialidad específica.
     * 
     * @param area El nombre del área o especialidad.
     * @return Un string con los detalles de los especialistas en esa especialidad, o un mensaje si no se encuentran.
     */
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

    /**
     * Añade un nuevo especialista a la lista de especialistas.
     * 
     * @param newData El objeto EspecialistaDTO con los datos del nuevo especialista a agregar.
     * @return true si el especialista fue añadido correctamente, false si ya existe.
     */
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

    /**
     * Elimina un especialista de la lista de especialistas.
     * 
     * @param toDelete El objeto EspecialistaDTO que representa el especialista a eliminar.
     * @return true si el especialista fue eliminado correctamente, false si no se encontró.
     */
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

    /**
     * Actualiza los datos de un especialista en la lista de especialistas.
     * 
     * @param previous El objeto EspecialistaDTO con los datos anteriores del especialista.
     * @param newData El objeto EspecialistaDTO con los nuevos datos del especialista.
     * @return true si el especialista fue actualizado correctamente, false si no se encontró.
     */
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

    /**
     * Busca un especialista por su ID.
     * 
     * @param toFind El objeto Especialista con el ID que se quiere buscar.
     * @return El especialista encontrado o null si no se encuentra.
     */
    @Override
    public Especialista find(Especialista toFind) {
        Especialista found = null;
        if (!listaEspecialistas.isEmpty()) {
            for (Especialista e : listaEspecialistas) {
                if (e.getId() == (toFind.getId())) {
                    found = e;
                    return found;
                }
            }
        }
        return null;
    }

    /**
     * Lee los datos de los especialistas desde un archivo CSV y los almacena en la lista de especialistas.
     */
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
                temp.setGenero(cols[3]);
                temp.setCorreo(cols[4]);
                listaEspecialistas.add(temp);
            }
        }
    }

    /**
     * Escribe la lista de especialistas en un archivo CSV.
     */
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

    /**
     * Lee los datos de los especialistas desde un archivo serializado y los almacena en la lista de especialistas.
     */
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

    /**
     * Escribe la lista de especialistas en un archivo serializado.
     */
    @Override
    public void writeSerialized() {
        FileHandler.writeSerialized(SERIALIZED_NAME, listaEspecialistas);
    }

    /**
     * Realiza el inicio de sesión de un especialista utilizando su ID y contraseña.
     * 
     * @param id El ID del especialista.
     * @param password La contraseña del especialista.
     * @return Un mensaje indicando si el inicio de sesión fue exitoso o si hubo algún error.
     */
    public String logIn(long id, long password) {
        for (Especialista e : listaEspecialistas) {
            if (e.getId() == id) {
                if (e.getPassword() == password) {
                    return "Bienvenido!";
                } else {
                    return "Contraseña equivocada, Verifique su contraseña";
                }
            }
        }
        return "Número de identificación equivocado, verifique los datos ingresados";
    }

    /**
     * Verifica si la contraseña proporcionada es correcta para un especialista con el ID dado.
     * 
     * @param id El ID del especialista.
     * @param password La contraseña a verificar.
     * @return Un mensaje indicando si la contraseña es correcta o incorrecta.
     */
    public String verifyPassword(long id, long password) {
        for (Especialista d : listaEspecialistas) {
            if (d.getId() == id) {
                if (d.getPassword() == password) {
                    return "Bienvenido " + d.getNombre() + "!";
                } else {
                    return "Contraseña equivocada, Verifique su contraseña";
                }
            }
        }
        return "Número de identificación equivocado, verifique los datos ingresados";
    }

    /**
     * Verifica si las credenciales de inicio de sesión (ID y contraseña) son correctas.
     * 
     * @param id El ID del especialista.
     * @param password La contraseña del especialista.
     * @return true si las credenciales son correctas, false si no lo son.
     */
    public boolean checkLogIn(long id, long password) {
        for (Especialista e : listaEspecialistas) {
            if (e.getId() == id) {
                if (e.getPassword() == password) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Establece un límite en la cantidad de especialistas por especialidad.
     * 
     * @param index El número máximo de especialistas permitidos para esa especialidad.
     * @param especialidad La especialidad que se está verificando.
     * @return true si se ha alcanzado el límite, false si no.
     */
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

    /**
     * Obtiene un especialista aleatorio de una especialidad determinada.
     * 
     * @param especialidad La especialidad de los especialistas a seleccionar.
     * @return El nombre del especialista seleccionado aleatoriamente.
     */
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

    /**
     * Obtiene datos específicos de un especialista (nombre o especialidad).
     * 
     * @param id El ID del especialista.
     * @param esNombre true si se desea obtener el nombre, false si se desea obtener la especialidad.
     * @param esEspecialidad true si se desea obtener la especialidad, false si se desea obtener el nombre.
     * @return El dato solicitado del especialista, o null si no se encuentra.
     */
    public String pickSpecialistData(long id, boolean esNombre, boolean esEspecialidad) {
        for (Especialista e : listaEspecialistas) {
            if (e.getId() == id) {
                if (esNombre) {
                    return e.getNombre();
                }
                if (esEspecialidad) {
                    return e.getEspecialidad();
                }
            }
        }
        return null;
    }
}