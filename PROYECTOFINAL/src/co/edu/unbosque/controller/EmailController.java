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

	public static void createScheduledEmail(String correo, String nombrePaciente, String nombreEspecialista,
			String especialidad) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Cita Agendada";
		content = "Estimado/a " + nombrePaciente
				+ "\n\nNos complace informarle que su cita ha sido agengada, su especialista asigando es "
				+ nombreEspecialista + " de la especialidad de " + especialidad
				+ "\n\nAgradecemos su confianza en nuestros servicios y nos mantenemos a su disposición para cualquier duda o consulta que pueda tener respecto a su tratamiento. \nCordialmente,\nEquipo de BosqueHealth";

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

	public static void sendScheduled(String correo, String nombrePaciente, String nombreEspecialista,
			String especialidad) {
		createScheduledEmail(correo, nombrePaciente, nombreEspecialista, especialidad);
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

	public static void createCanceledEmail(String correo, String nombrePaciente) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Cita Cancelada";
		content = "Estimado/a " + nombrePaciente + "\nLamentamos informarle que su cita programada ha sido cancelada. "
				+ "\n\nNos disculpamos por cualquier inconveniente que esto pueda causar y esperamos poder atenderle pronto en una nueva fecha.\n"
				+ "\nNo dude en ponerse en contacto con nosotros para reprogramar su cita o si necesita alguna información adicional.\n"
				+ "\nAgradecemos su comprensión y confianza en nuestros servicios.\n" + "\nCordialmente,\n"
				+ "Equipo de BosqueHealth";

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

	public static void sendCanceled(String correo, String nombrePaciente) {
		createCanceledEmail(correo, nombrePaciente);
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

	public static void createTreatmentEmail(String correo, String nombrePaciente, String tratamiento,
			String nombreEspecialista) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Tratamiento Generado";
		content = "Estimado/a " + nombrePaciente + "\n\nNos complace informarle que su especialista, "
				+ nombreEspecialista
				+ " ha generado un tratamiento personalizado para usted en BosqueHealth. \nDetalles del Tratamiento: "
				+ tratamiento
				+ "\n\nAgradecemos su confianza en nuestros servicios y nos mantenemos a su disposición para cualquier duda o consulta que pueda tener respecto a su tratamiento. \nCordialmente,\nEquipo de BosqueHealth";

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

	public static void sendTreatment(String correo, String nombrePaciente, String tratamiento,
			String nombreEspecialista) {
		createTreatmentEmail(correo, nombrePaciente, tratamiento, nombreEspecialista);
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

	public static void createExaminationRequestedEmail(String correo, String nombrePaciente, String nombreEspecialista,
			String examenes) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Exámenes solicitados";
		content = "Estimado " + nombrePaciente + "\n\nNos complace informarle que su especialista, "
				+ nombreEspecialista
				+ " le ha solicitado unos exámenes en nuestra clínica BosqueHealth. \nDetalles de los exámenes: "
				+ examenes
				+ "\n\nAgradecemos su confianza en nuestros servicios y nos mantenemos a su disposición para cualquier duda o consulta que pueda tener respecto a su tratamiento. \nCordialmente,\nEquipo de BosqueHealth";

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

	public static void sendExaminationRequested(String correo, String nombrePaciente, String nombreEspecialista,
			String examenes) {
		createExaminationRequestedEmail(correo, nombrePaciente, nombreEspecialista, examenes);
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

	public static void createExaminationRequestedResultsEmail(String correo, String nombrePaciente,
			String nombreEspecialista, String resultados) {
		mProperties = new Properties();

		emailTo = correo;
		subject = "Resultados de exámenes recibidos";
		content = "Estimado/a " + nombrePaciente + "\n\nNos complace informarle que su especialista, "
				+ nombreEspecialista + " ha generado los resultados de sus exámenes. \nDetalles de los resultados: "
				+ resultados
				+ "\n\nAgradecemos su confianza en nuestros servicios y nos mantenemos a su disposición para cualquier duda o consulta que pueda tener respecto a su tratamiento. \nCordialmente,\nEquipo de BosqueHealth";

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

	public static void sendExaminationRequestedResults(String correo, String nombrePaciente, String nombreEspecialista,
			String resultados) {
		createExaminationRequestedResultsEmail(correo, nombrePaciente, nombreEspecialista, resultados);
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
