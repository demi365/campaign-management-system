package com.makeathon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.makeathon.controller.UrlController;
import com.makeathon.dto.EmailDTO;
import com.makeathon.entity.Campaign;
import com.makeathon.entity.Template;
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
public class MailerService {
	
//	@Autowired
//	private Environment env;
	
	@Value("#{sendGrid.api.key}")
	private static String API_KEY;
	
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
		
		String unsubcampaignLink = UrlController.unsubscribeLink.replaceAll("<<campaignId>>", String.valueOf(emailDTO.getCampaignId()));
		List<String> unsubUsers = new ArrayList<String>();
		
		if(unsubs!=null)
			unsubs.stream().forEach(unsub -> unsubUsers.add(unsub.getUserId()));
		System.out.println(unsubUsers);
		
		if (campaign.isPresent()) {
			
			String[] toEmails = campaign.get().getEmail_list().split(";");
			
		    Email from = new Email("testinfosys3@gmail.com");
		    String subject = campaign.get().getName();
		    Personalization personal = new Personalization();
		    
		    for(String emailTo : toEmails) {
		    	if(unsubUsers.isEmpty() || !unsubUsers.contains(emailTo)) {
		    		
		    		System.out.println("email sent to "+emailTo);
			    	personal = new Personalization();
			    	personal.addTo(new Email(emailTo));
			    	
			    	Optional<Campaign> campaignToRun = campRepo.findById(emailDTO.getCampaignId());
			    	
			    	Set<Template> templatesToRun = campaignToRun.get().getTemplates();
			    	
			    	for(Template templateToRun : templatesToRun) {
			    		
				    	Optional<Template> template = tempRepo.findById(templateToRun.getId());
				    	if(template.isPresent()) {
				    		
				    		String htmlToSend = emailDTO.getHtml().replace("[Unsubscribe]", 
				    				unsubcampaignLink.replaceAll("<<userId>>", emailTo));
				    		
						    Content content = new Content("text/html", 
						    		htmlToSend);
						    
						    Mail mail = new Mail();
						    
						    mail.setFrom(from);
						    mail.setSubject(subject);
						    mail.addContent(content);
						    mail.addPersonalization(personal);
						    
						    mail.setReplyTo(new Email("no-reply@testing.com"));
						    SendGrid sg = new SendGrid(API_KEY);
						    
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
						      return "not ok";
						    }
				    	}
			    	}
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
	
}
