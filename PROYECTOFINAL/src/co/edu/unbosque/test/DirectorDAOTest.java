package co.edu.unbosque.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

public class DirectorDAOTest {
	
	static int testNum = 1;
	static String text = "";
	static boolean verifiedOut = false;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeAll() {
		System.out.println("Starting test Email Controller.......\n");
	}
	@AfterClass
	public static void afterAll() {
		System.out.println("Finishing test Email Controller.......\n");
	}

}
