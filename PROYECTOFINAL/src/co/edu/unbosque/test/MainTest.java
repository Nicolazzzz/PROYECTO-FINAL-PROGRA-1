package co.edu.unbosque.test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import co.edu.unbosque.model.persistence.EspecialistaDAO;

/**
 * Clase de pruebas para verificar el funcionamiento de la clase {@link EspecialistaDAO}.
 * <p>
 * Esta clase utiliza JUnit para realizar las pruebas unitarias del método {@link EspecialistaDAO#getRandomSpecialist(String)}.
 * En particular, se prueba la funcionalidad de obtener un especialista aleatorio según la especialidad.
 * @author Mario Rodriguez
 * @version 1.0
 * </p>
 */
public class MainTest {

    /**
     * Número de la prueba que se ejecuta.
     */
    static int testNum = 1;
    
    /**
     * Texto que indica si la prueba fue aprobada o denegada.
     */
    static String text = "";
    
    /**
     * Indicador de si la salida fue verificada correctamente.
     */
    static boolean verifiedOut = false;
    
    /**
     * Instancia de la clase {@link EspecialistaDAO} para realizar las pruebas.
     */
    static EspecialistaDAO eDAO;

    /**
     * Regla de JUnit para obtener el nombre del método de prueba actual.
     */
    @Rule
    public TestName testName = new TestName();

    /**
     * Método que se ejecuta antes de todas las pruebas. Inicializa la instancia de {@link EspecialistaDAO}.
     */
    @BeforeClass
    public static void beforeAll() {
        eDAO = new EspecialistaDAO();
        System.out.println("Starting test Especialista DAO.......\n");
    }

    /**
     * Método que se ejecuta antes de cada prueba. Imprime el inicio de cada prueba.
     */
    @Before
    public void beforeTest() {
        System.out.println("STARTING TEST " + testName.getMethodName() + ", #" + testNum + "......."); 
    }

    /**
     * Método que se ejecuta después de cada prueba. Imprime el resultado de la prueba.
     */
    @After
    public void afterTest() {
        if (verifiedOut == true)
            text = "Approved";
        else
            text = "Dennied";

        System.out.println("FINISHING TEST " + testName.getMethodName() + ", #" + testNum + "......" + "\nWas " + text);
        testNum++;
        System.out.println();
    }

    /**
     * Método de prueba que verifica el comportamiento del método {@link EspecialistaDAO#getRandomSpecialist(String)}.
     * Actualmente imprime el especialista aleatorio y algunos mensajes de depuración.
     */
    @Test
    public void verifyRandomSpecialist() {
        boolean verified = false;
        System.out.println(eDAO.getRandomSpecialist("Cirugía"));
        System.out.println("1");
        System.out.println("HOLA");
    }

    /**
     * Método que se ejecuta después de todas las pruebas. Imprime el mensaje de finalización de las pruebas.
     */
    @AfterClass
    public static void despuesDeTodo() {
        System.out.println("Finalizando pruebas de la clase mainTest");
    }
}