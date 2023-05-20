package util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailUtil {
	private static Properties properties;
	
	static {
		ResourceBundle bundle=PropertyResourceBundle.getBundle("MailUtil");
		properties = new Properties();

	    Enumeration<String> keys = bundle.getKeys();
	    while (keys.hasMoreElements()) {
	      String key = keys.nextElement();
	      properties.put(key, bundle.getString(key));
	    }
	}
	

	public static void send(String userMail, String messageText) {	
		Session session=Session.getDefaultInstance(properties);		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress("support@webshop.ip"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMail));
			message.setSubject("Message reply");
			message.setText(messageText);
			Transport.send(message);
		}catch(MessagingException e) {
			e.printStackTrace();
			System.out.println("Mail can't be sent");
		}
		
	}
}
