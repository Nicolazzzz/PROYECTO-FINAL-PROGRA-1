package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

/**
 * La clase FileHandler se encarga de manejar la lectura y escritura de archivos en el sistema de archivos,
 * tanto en formato de texto plano como en formato serializado. Proporciona métodos para manejar archivos de propiedades,
 * archivos de texto y objetos serializados.
 * 
 * Además, garantiza la creación de las carpetas necesarias para almacenar los archivos de datos y configuración.
 * 
 * @author Mario Rodriguez
 * @version 1.0
 */
public class FileHandler {

    private static Scanner sc;
    private static PrintWriter pw;
    private static File archivo;
    private static final String FOLDER_NAME = "data";
    private static final String CONFIG_FOLDER_NAME = "config";

    // para serializados
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;

    private static FileInputStream fis;
    private static ObjectInputStream ois;

    /**
     * Verifica si la carpeta para almacenar datos existe, y si no, la crea.
     */
    public static void checkFolder() {
        archivo = new File(FOLDER_NAME);
        if (archivo.exists() && archivo.isDirectory()) {
            return;
        } else {
            archivo.mkdir();
        }
    }

    /**
     * Verifica si la carpeta de configuración existe, y si no, la crea.
     */
    public static void checkPropertiesFolder() {
        archivo = new File(CONFIG_FOLDER_NAME);
        if (archivo.exists() && archivo.isDirectory()) {
            return;
        } else {
            archivo.mkdir();
        }
    }

    /**
     * Carga un archivo de propiedades (.properties) desde la carpeta de configuración.
     * 
     * @param url La ruta del archivo de propiedades a cargar.
     * @return Un objeto Properties que contiene las claves y valores del archivo.
     */
    public static Properties loadProperties(String url) {
        Properties prop = null;
        prop = new Properties();

        try {
            prop.load(new FileInputStream(CONFIG_FOLDER_NAME + "/" + url));
        } catch (FileNotFoundException e) {
            System.out.println("RUTA DEL ARCHIVO DE PROPIEDADES NO ENCONTRADO (PROPERTIES)");
            System.out.println(CONFIG_FOLDER_NAME + "/" + url);
        } catch (IOException e) {
            System.out.println("ERROR AL LEER EL ARCHIVO DE PROPIEDADES");
        }

        return prop;
    }

    /**
     * Escribe un objeto en un archivo serializado.
     * 
     * @param url La ruta donde se guardará el archivo serializado.
     * @param content El objeto que se va a serializar y guardar.
     */
    public static void writeSerialized(String url, Object content) {
        try {
            archivo = new File(FOLDER_NAME + "/" + url);

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            fos = new FileOutputStream(archivo); // Write file
            oos = new ObjectOutputStream(fos); // To write file
            oos.writeObject(content); // writer
            fos.close();
            oos.close();

        } catch (IOException e) {
            System.out.println("ERROR EN CREAR ARCHIVO (Serialized)");
        }
    }

    /**
     * Lee un archivo serializado y devuelve el objeto contenido en él.
     * 
     * @param url La ruta del archivo serializado a leer.
     * @return El objeto leído desde el archivo, o null si ocurre un error.
     */
    public static Object readSerialized(String url) {
        try {
            archivo = new File(FOLDER_NAME + "/" + url);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);

            Object content = ois.readObject();

            fis.close();
            ois.close();
            return content;

        } catch (IOException e) {
            System.out.println("ERROR EN LECTURA DE ARCHIVO (SERIALIZED)");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR AL LEER LOS DATOS DEL SERIALIZED (CLASS NOT FOUND)");
        }

        return null;
    }

    /**
     * Escribe texto plano en un archivo.
     * 
     * @param url La ruta del archivo en el que se escribirá el contenido.
     * @param content El contenido de texto a escribir en el archivo.
     */
    public static void writeFile(String url, String content) {
        try {
            archivo = new File(FOLDER_NAME + "/" + url);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            pw = new PrintWriter(archivo);
            pw.write(content);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR EN LA ESCRITURA DEL ARCHIVO (PLAIN TEXT)");
        } catch (IOException e) {
            System.out.println("ERROR EN LA CREACION DEL ARCHIVO (PLAIN TEXT)");
        }
    }

    /**
     * Lee el contenido de un archivo de texto plano.
     * 
     * @param url La ruta del archivo de texto a leer.
     * @return El contenido del archivo como una cadena de texto, o null si ocurre un error.
     */
    public static String readFile(String url) {
        try {
            archivo = new File(FOLDER_NAME + "/" + url);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            sc = new Scanner(archivo);
            String content = "";
            while (sc.hasNext()) {
                content += sc.nextLine() + "\n";
            }

            return content;

        } catch (IOException e) {
            System.out.println("ERROR EN LA LECTURA DEL ARCHIVO (PLAIN TEXT)");
        }

        return null;
    }
}
