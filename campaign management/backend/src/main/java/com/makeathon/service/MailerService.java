package com.makeathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.makeathon.dto.EmailDTO;
import com.makeathon.entity.Campaign;
import com.makeathon.entity.UnsubcribeLogs;
import com.makeathon.entity.Work;
import com.makeathon.repository.CampaignRepository;
import com.makeathon.repository.TemplateRepository;
import com.makeathon.repository.UnsubscribeRepository;
import com.makeathon.repository.WorkRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "send-grid")
public class MailerService {
	
	public String unsubscribeLink;
	private String from;
	private String replyTo;
	private String endpoint;
	private String apiKey;
	
	@Autowired
	CampaignRepository campRepo;
	
	@Autowired
	TemplateRepository tempRepo;
	
	@Autowired
	WorkRepository workRepo;
	
	@Autowired
	UnsubscribeRepository unsubRepo;
	
	public String sendEmailViaSendGrid(EmailDTO emailDTO) throws Exception {
		
		Optional<Campaign> campaign = campRepo.findById(emailDTO.getCampaignId());
		List<UnsubcribeLogs> unsubs = unsubRepo.findByCampaignId(emailDTO.getCampaignId());
		
		String unsubcampaignLink = unsubscribeLink.replaceAll("<<campaignId>>", String.valueOf(emailDTO.getCampaignId()));
		List<String> unsubUsers = new ArrayList<String>();
		
		if(unsubs!=null)
			unsubs.stream().forEach(unsub -> unsubUsers.add(unsub.getUserId()));
		
		if (campaign.isPresent()) {
			
			String[] toEmails = campaign.get().getEmail_list().split(";");
			
		    Email fromEmail = new Email(from);
		    String subject = campaign.get().getName();
		    Personalization personal = new Personalization();
		    
		    for(String emailTo : toEmails) {
		    	if(unsubUsers.isEmpty() || !unsubUsers.contains(emailTo)) {
		    		
		    		personal = new Personalization();
			    	personal.addTo(new Email(emailTo));
			    	
		    		String htmlToSend = emailDTO.getHtml().replace("[Unsubscribe]", 
			    				unsubcampaignLink.replaceAll("<<userId>>", emailTo));
			    		
				    Content content = new Content(MediaType.TEXT_HTML_VALUE, 
				    		htmlToSend);
				    
				    Mail mail = new Mail();
				    
				    mail.setFrom(fromEmail);
				    mail.setSubject(subject);
				    mail.addContent(content);
				    mail.addPersonalization(personal);
				    
				    mail.setReplyTo(new Email(replyTo));
				    SendGrid sg = new SendGrid(apiKey);
				    
				    Request request = new Request();
//				    try {
				      request.setMethod(Method.POST);
				      request.setEndpoint(endpoint);
				      request.setBody(mail.build());
				      Response response = sg.api(request);
				      System.out.print(response.getStatusCode()+" ");
				      System.out.println("Email sent to "+emailTo);
				    	
//				    } catch (IOException ex) {
//				      return "not ok";
//				    }
		    	}
		    }
		    
		    Work work = new Work();
		    work.setEmailList(campaign.get().getEmail_list());
		    work.setHtml(emailDTO.getHtml());
		    work.setStatus("SENT");
		    
		    workRepo.save(work);
		    
		    return "ok";
		}
		else {
			return "not ok";
		}
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getUnsubscribeLink() {
		return unsubscribeLink;
	}

	public void setUnsubscribeLink(String unsubscribeLink) {
		this.unsubscribeLink = unsubscribeLink;
	}
	
}
