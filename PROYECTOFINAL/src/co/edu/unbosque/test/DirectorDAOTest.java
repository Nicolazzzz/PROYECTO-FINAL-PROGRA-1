package co.edu.unbosque.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import co.edu.unbosque.model.Director;
import co.edu.unbosque.model.persistence.DirectorDAO;

public class DirectorDAOTest {
	static int testNum = 1;
	static String text = "";
	static boolean verifiedOut = false;
	static DirectorDAO dDAO;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeAll() {
		dDAO = new DirectorDAO();
		System.out.println("Starting test Director DAO.......\n");
	}

	@AfterClass
	public static void afterAll() {
		System.out.println("Finishing test Director DAO.......\n");
	}

	@Test
	public void verifyCheckLogIn() {

		long password = 0;
		long id = 0;
		boolean verified = false;
		ArrayList<Director> listaD = dDAO.getListaDirectores();

		for (Director d : listaD) {
			id = d.getId();
			password = d.getPassword();
		}

		verified = dDAO.checkLogIn(id, password);

		assertTrue(verified);
		verifiedOut = verified;

	}

	@Test
	public void verifyPickData() {
		String gmail = "";
		String name = "";
		long id = 0;
		boolean verified = false;
		ArrayList<Director> listaD = dDAO.getListaDirectores();

		for (Director d : listaD) {
			id = d.getId();
			gmail = d.getCorreo();
			name = d.getNombre();
		}

		String content = dDAO.pickData(id, true, false);
		if (content.equalsIgnoreCase(name)) {
			verified = true;
		}
		String contentMail = dDAO.pickData(id, false, true);
		if (contentMail.equalsIgnoreCase(gmail)) {
			verified = true;
		}

		assertTrue(verified);
		verifiedOut = verified;

	}

}
