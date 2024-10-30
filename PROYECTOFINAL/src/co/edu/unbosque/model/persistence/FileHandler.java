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

	public static void checkFolder() {
		archivo = new File(FOLDER_NAME);
		if (archivo.exists() && archivo.isDirectory()) {
			return;
		} else {
			archivo.mkdir();
		}
	}

	public static void checkPropertiesFolder() {

		archivo = new File(CONFIG_FOLDER_NAME);
		if (archivo.exists() && archivo.isDirectory()) {
			return;
		} else {
			archivo.mkdir();
		}

	}

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
			e.printStackTrace();
			System.out.println("ERROR EN CREAR ARCHIVO (Serialized)");
		}

	}

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
			System.out.println("ERROR AL LEER LOS DATOS DEL SERIALIZED(CLASS NOT FOUND");
		}

		return null;
	}

	public static void writeFile(String url, String content) {
		try {

			archivo = new File(FOLDER_NAME + "/" + url); // la instancia solo apunta a esa posicion en el disco
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			pw = new PrintWriter(archivo);
			pw.write(content);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR EN LA ESCRITURA DEL ARCHIVO (PLAIN TEXT)");
			// e.printStackTrace();
		} catch (IOException e) {

			System.out.println("ERROR EN LA CREACION DEL ARCHIVO (PLAIN TEXT)");
			// e.printStackTrace();
		}

	}

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
			e.printStackTrace();
		}

		return null;
	}

}
