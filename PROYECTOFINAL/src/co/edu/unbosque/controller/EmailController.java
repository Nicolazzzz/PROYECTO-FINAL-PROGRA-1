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

	public static void createScheduledEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Cita Agendada";
		content = "Prueba gmail";

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
	}

	public static void sendScheduled(String correo) {
		createScheduledEmail(correo);
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

	public static void createCanceledEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Cita Cancelada";
		content = "Prueba gmail";

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
	}

	public static void sendCanceled(String correo) {
		createCanceledEmail(correo);
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

	public static void createRescheduledEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Cita Reagendada";
		content = "Prueba gmail";

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
	}

	public static void sendRescheduled(String correo) {
		createRescheduledEmail(correo);
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

	public static void createReminderEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Recordatorio Cita";
		content = "Prueba gmail";

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
	}

	public static void sendReminder(String correo) {
		createReminderEmail(correo);
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

	public static void createTreatmentEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Tratamiento Generado";
		content = "Prueba gmail";

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
	}

	public static void sendTreatment(String correo) {
		createTreatmentEmail(correo);
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

	public static void createExaminationRequestedEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Exámenes solicitados";
		content = "Prueba gmail";

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

	}

	public static void sendExaminationRequested(String correo) {
		createExaminationRequestedEmail(correo);
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

	public static void createExaminationRequestedResultsEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Resultados de exámenes recibidos";
		content = "Prueba gmail";

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
	}

	public static void sendExaminationRequestedResults(String correo) {
		createExaminationRequestedResultsEmail(correo);
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

	// ESPECIALISTAS

	public static void createNextDayAppointmentEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Citas para el día siguiente";
		content = "Prueba gmail";

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
	}

	public static void sendNextDayAppointment(String correo) {
		createNextDayAppointmentEmail(correo);
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

	public static void createMonthShiftsEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Turnos del mes";
		content = "Prueba gmail";

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
	}

	public static void sendMonthShifts(String correo) {
		createMonthShiftsEmail(correo);
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

	// DIRECTOR MEDICO
	public static void createChangeOfTurnEmail(String correo) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Cambio de turno - especialistas";
		content = "Prueba gmail";

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
	}

	public static void sendChangeOfTurn(String correo) {
		createChangeOfTurnEmail(correo);
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

}
