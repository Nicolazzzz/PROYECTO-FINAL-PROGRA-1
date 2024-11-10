package co.edu.unbosque.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import co.edu.unbosque.model.persistence.EspecialistaDAO;

public class MainTest {

	static int testNum = 1;
	static String text = "";
	static boolean verifiedOut = false;
	static EspecialistaDAO eDAO;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeAll() {
		eDAO = new EspecialistaDAO();
		System.out.println("Starting test Especialista DAO.......\n");
	}

	@Before
	public void beforeTest() {
		System.out.println("STARTING TEST " + testName.getMethodName() + ", #" + testNum + ".......");
	}

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

	@Test
	public void verifyRandomSpecialist() {
		boolean verified = false;
		System.out.println(eDAO.getRandomSpecialist("Cirugía"));
		System.out.println("1");
		System.out.println("HOLA");

	}

	@AfterClass
	public static void despuesDeTodo() {
		System.out.println("Finalizando pruebas de la clase mainTest");
	}
}
