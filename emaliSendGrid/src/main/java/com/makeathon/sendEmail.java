package com.makeathon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

public class sendEmail {
	
	public static void main(String[] args) throws IOException {
	    String[] toEmails = new String[] {"deepakkumar1212365@gmail.com"};
		
	    Email from = new Email("testinfosys3@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Personalization personal = new Personalization();
	    for(String emailTo : toEmails) {
	    	personal.addTo(new Email(emailTo));
	    }
	    
	    StringBuilder cont = new StringBuilder("");
	    
	    Stream<String> stream = Files.lines(Paths.get("D:\\apache-tomcat-9.0.29\\webapps\\testing\\index.html"));
	    stream.forEach(line -> {cont.append(line);});
	    
	    Content content = new Content("text/html", 
	    		cont.toString());
//	    System.out.println(cont.toString());
	    
	    Mail mail = new Mail();
	    
	    mail.setFrom(from);
	    mail.setSubject(subject);
	    mail.addContent(content);
	    mail.addPersonalization(personal);
	    
	    mail.setReplyTo(new Email("no-reply@testing.com"));
	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	 }
	
}