package BaseTest;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
//@SuppressWarnings("restriction")
public class SendMailSSLWithAttachment {
 
	public void sendEmail(){
 
		// Create object of Property file
		Properties props = new Properties();
 
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "587");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		props.put("mail.smtp.port", "587");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 
					return new PasswordAuthentication("AKIAUNLWS22U4QQJO3PL","BA1lCaTIXjHAzh8r+qKyS5HAHttS/TwWxtxdOYd/DvOz");
 
					}
 
				});
 
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("AutomationReport-donotreply@healthinteractive.com"));
 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("yasmittal@deloitte.com"));
            
                        // Add the subject link
			message.setSubject("Smoke Test Report");
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			messageBodyPart1.setText("Hi Everyone,"+ "\n" + "Please find the test results for smoke test of UAT environment."+ "\n" + "Please check attached report for more details. "+ "\n" + ""+ "\n" + "Thanks," + "\n" + "Selenium Automator.");
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			String filename = (System.getProperty("user.dir")+"\\ExtentReportResults.html");
 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(filename);
 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
 
			// send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
 
	}
 
}