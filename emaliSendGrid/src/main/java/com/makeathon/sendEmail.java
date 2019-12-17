package com.makeathon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.Charsets;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

public class sendEmail {
	
	public static void main(String[] args) {
		sendEmailViaSmtp();
	 }
	
	public static void sendEmailViaSendGrid() {
		String[] toEmails = new String[] {"deepak1212365@gmail.com"};
		
	    Email from = new Email("testinfosys3@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Personalization personal = new Personalization();
	    for(String emailTo : toEmails) {
	    	personal.addTo(new Email(emailTo));
	    }
	    
	    StringBuilder cont = new StringBuilder("");
	    
	    Stream<String> stream;
		try {
			stream = Files.lines(Paths.get("C:\\Users\\deepa\\Documents\\Trackers\\index.html"));
		
		    stream.forEach(line -> {cont.append(line);});
		    
		    Content content = new Content("text/html", cont.toString());
	//	    System.out.println(cont.toString());
		    Mail mail = new Mail();
		    mail.setFrom(from);
		    mail.setSubject(subject);
		    mail.addContent(content);
		    mail.addPersonalization(personal);
		    
		    System.out.println("Key in the API key : ");
		    Scanner scan = new Scanner(System.in);
		    String API_KEY = scan.nextLine();
		    mail.setReplyTo(new Email("no-reply@testing.com"));
		    SendGrid sg = new SendGrid(API_KEY);
		    Request request = new Request();
		    request.setMethod(Method.POST);
		    request.setEndpoint("mail/send");
		    request.setBody(mail.build());
		    Response response = sg.api(request);
		    System.out.println(response.getStatusCode());
		    scan.close();
	    } catch (IOException ex) {
	    	System.out.println(ex.getStackTrace());
	    }
	}

	public static void sendEmailViaSmtp() {
		
	   final String user="#username";//change accordingly  
	   final String password="#password";//change accordingly  
	    
	   String to="deepak1212365@gmail.com";//change accordingly  
	  
	   //Get the session object
	   Properties props = new Properties();  
	   props.put("mail.smtp.host", "smtp.gmail.com");    
       props.put("mail.smtp.socketFactory.port", "465");    
       props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
       props.put("mail.smtp.auth", "true");    
       props.put("mail.smtp.port", "465");  
	   
	   Session session = Session.getDefaultInstance(props,  
	   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
			   return new PasswordAuthentication(user,password);  
		   }  
	   });
	   
	   session.setDebug(true);
	   
	   // Compose the message  
	   
	   try {
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress(user));  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("javatpoint");  
		   message.setText("<h1>This is simple program of sending email using JavaMail API</h1>",
		    		 		Charsets.UTF_8.toString(), "html");  
		   // send the message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully...");  
	   
	   } catch (MessagingException e) {e.printStackTrace();}
	   
	}
	
}