package co.edu.unbosque.controller;

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
import javax.swing.JOptionPane;

public class EmailController {

	private static String emailFrom = "bosquehealth.noreply@gmail.com";
	private static String passwordFrom = "etbu bjjd xvzc yenm";

	private static String emailTo;
	private static String subject;
	private static String content;

	private static Properties mProperties;
	private static Session mSession;
	private static MimeMessage mCorreo;

	public static void createScheduledEmail() {
		mProperties = new Properties();

		emailTo = "m3ra2404@gmail.com";
		subject = "Cita Programada";
		content = "Prueba gmail";
		
		// Simple mail transfer protocol
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
			mCorreo.setText(content, "ISO-8859-1", "html");

		} catch (AddressException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void sendScheduled() {
		createScheduledEmail();
		try {
			Transport mTransport = mSession.getTransport("smtp");
			mTransport.connect(emailFrom, passwordFrom);
			mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
			mTransport.close();

			JOptionPane.showMessageDialog(null, "Correo enviado");
		} catch (NoSuchProviderException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void sendCanceled(String correo) {

	}

	public static void sendRescheduled(String correo) {

	}

	public static void sendReminder(String correo) {

	}

	public static void sendTreatment(String correo) {

	}

	public static void sendExaminationRequested(String correo) {

	}

	public static void sendExaminationRequestedResults(String correo) {

	}

	// ESPECIALISTAS

	public static void sendNextDayAppointment(String correo) {

	}

	public static void sendMonthShifts(String correo) {

	}

	// DIRECTOR MEDICO
	public static void sendChangeOfTurn(String correo) {

	}

}
