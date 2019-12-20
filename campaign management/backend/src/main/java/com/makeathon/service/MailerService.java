package com.makeathon.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.EmailDTO;
import com.makeathon.dto.WorkDTO;
import com.makeathon.entity.Campaign;
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

import lombok.Getter;
import lombok.Setter;

@Service
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "send-grid")
public class MailerService {
	
	@Getter
	@Setter
	public String unsubscribeLink;
	@Getter
	@Setter
	private String from;
	@Getter
	@Setter
	private String replyTo;
	@Getter
	@Setter
	private String endpoint;
	@Getter
	@Setter
	private String apiKey;
	@Getter
	@Setter
	private String bounceUrl;
	
	@Autowired
	CampaignRepository campRepo;
	
	@Autowired
	TemplateRepository tempRepo;
	
	@Autowired
	WorkRepository workRepo;
	
	@Autowired
	UnsubscribeRepository unsubRepo;
	
	@Autowired
	BouncerService bounceService;
	
	@Transactional
	public WorkDTO sendEmailViaSendGrid(EmailDTO emailDTO) throws Exception {
		
		Optional<Campaign> campaign = campRepo.findById(emailDTO.getCampaignId());
		
		List<String> unsubscribedUsers = unsubRepo.findEmailIdsByCampaignId(emailDTO.getCampaignId());
		
	    Work work = new Work();
	    
		if (campaign.isPresent()) {
			
			List<String> toEmails = new LinkedList<String>(
					Arrays.asList(campaign.get().getEmail_list().split(";")));
			
			toEmails.removeAll(unsubscribedUsers);
			
			work.setEmailList(toEmails.toString());
		    work.setHtml(emailDTO.getHtml());
		    work.setStatus("IN PROGRESS");
		    workRepo.save(work);
		    
		    Email fromEmail = new Email(from);
		    String subject = campaign.get().getName();
		    
		    for(String emailTo : toEmails) {
		    		
		    	Email toEmail = new Email(emailTo);
			    
	    		String htmlToSend = getHtmlWithEmbeddedLinks(emailDTO, work.getId(), emailTo);
	    		
			    Content content = new Content(MediaType.TEXT_HTML_VALUE, 
			    		htmlToSend);
			    
			    Mail mail = new Mail(fromEmail, subject, toEmail, content);
			    
			    mail.setReplyTo(new Email(replyTo));
			    SendGrid sg = new SendGrid(apiKey);
			    
			    Request request = new Request();
			    try {
				    request.setMethod(Method.POST);
				    request.setEndpoint(endpoint);
				    request.setBody(mail.build());
				    Response response = sg.api(request);
				    System.out.print(response.getStatusCode()+" ");
				    System.out.println("Email sent to "+emailTo);
				    
			    } catch (IOException ex) {
			    	ex.printStackTrace();
			    	return DTOFactory.getWorkDTO(work);
			    }
		    }
		    work.setStatus("SENT");
		    workRepo.save(work);
		    
		    return DTOFactory.getWorkDTO(work);
		}
		else {
			return DTOFactory.getWorkDTO(work);
		}
	}
	
	private String getHtmlWithEmbeddedLinks(EmailDTO emailDTO, int workId, String emailTo) {
		
		Document html= Jsoup.parse(emailDTO.getHtml());
		Elements aLinks = html.select("a[href]");
		int campaignId = emailDTO.getCampaignId();
		String bounceBackUrl = bounceUrl.replace("{campaign_id}", String.valueOf(campaignId));
		bounceBackUrl = bounceBackUrl.replace("{work_id}", String.valueOf(workId));
		
		for(Element aLink : aLinks) { 
			
			aLink.attr("href",bounceBackUrl
								.replace("{inputId}",aLink.attr("id"))
								.replace("{url}", aLink.attr("href")));
			bounceService.addLinkToTracking(workId, campaignId, aLink.attr("id"));
			
		}
		return html.toString().replace("[Unsubscribe]", 
				unsubscribeLink.replaceAll("<<campaignId>>", String.valueOf(emailDTO.getCampaignId())).replaceAll("<<userId>>", emailTo));
	}
	
}
