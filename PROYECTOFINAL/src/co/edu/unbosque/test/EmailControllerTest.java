package co.edu.unbosque.test;

import static org.junit.Assert.assertTrue;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import co.edu.unbosque.controller.EmailController;

public class EmailControllerTest {
	private static String emailFrom = "bosquehealth.noreply@gmail.com";
	private static String passwordFrom = "etbu bjjd xvzc yenm";

	private static String emailTo;
	private static String subject;
	private static String content;

	private static Properties mProperties;
	private static Session mSession;
	private static MimeMessage mCorreo;

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
	public void verifySendEmail() {

		boolean verified = true;
		String correo = "example@gmail.com";

		mProperties = new Properties();

		emailTo = correo;
		subject = "Cita";
		content = "Prueba";

		mProperties.put("mail.smtp.host", "smtp.gmail.com");
		mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		mProperties.setProperty("mail.smtp.starttls.enable", "true");
		mProperties.setProperty("mail.smtp.port", "587");
		mProperties.setProperty("mail.smtp.user", emailFrom);
		mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		mProperties.setProperty("mail.smtp.auth", "true");

		mSession = Session.getDefaultInstance(mProperties);

		try {
			mCorreo = new MimeMessage(mSession);
			mCorreo.setFrom(new InternetAddress(emailFrom));
			mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			mCorreo.setSubject(subject);
			mCorreo.setText(content);

		} catch (AddressException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			Transport mTransport = mSession.getTransport("smtp");
			mTransport.connect(emailFrom, passwordFrom);
			mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
			mTransport.close();

			verified = true;
		} catch (NoSuchProviderException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		}
		assertTrue(verified);
		verifiedOut = verified;
	}

}
