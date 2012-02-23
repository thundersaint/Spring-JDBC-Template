/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.util ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException ;
import java.util.Properties ;

import javax.mail.Message ;
import javax.mail.MessagingException ;
import javax.mail.Session ;
import javax.mail.Transport ;
import javax.mail.internet.AddressException ;
import javax.mail.internet.InternetAddress ;
import javax.mail.internet.MimeMessage ;

public class Emailer {

	public Emailer() {
	}

	public void send(String to, String subject, String content)
			throws AddressException, MessagingException, IOException {
		Properties props = System.getProperties() ;

		Session mailSession = Session.getDefaultInstance(props) ;
		mailSession.setDebug(true) ;
		Transport transport = mailSession.getTransport() ;

		MimeMessage message = new MimeMessage(mailSession) ;
		message.setSubject(subject) ;
		message.setContent(content, "text/plain") ;

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)) ;

		transport.connect(props.getProperty("mail.smtps.host"), Integer.parseInt(props.getProperty("mail.smtp.port")), props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password")) ;
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO)) ;
		transport.close() ;
	}

	public static void main(String[] args) {
    	Properties prop = System.getProperties() ;    	        	
	    try {
			prop.load(new FileInputStream("./src/config.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		Emailer email = new Emailer() ;
		try {
			email.send("thundersaint@yahoo.com", "test subject", "test content") ;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace() ;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace() ;
		}
	}
}
