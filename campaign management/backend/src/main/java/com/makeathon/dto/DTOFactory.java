package com.makeathon.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import com.makeathon.entity.Campaign;
import com.makeathon.entity.Feedbacks;
import com.makeathon.entity.Images;
import com.makeathon.entity.LinkHits;
import com.makeathon.entity.Subscribers;
import com.makeathon.entity.Template;
import com.makeathon.entity.UserDetails;
import com.makeathon.entity.Work;

public class DTOFactory {

	public static TemplateDTO getDTO(Template template) {
		
		TemplateDTO templateDTO = new TemplateDTO();
		templateDTO.setId(template.getId());
		templateDTO.setName(template.getName());
		templateDTO.setHtml(template.getHtml());
		templateDTO.setTags(template.getTags());
		return templateDTO;
		
	}
	
	public static Template getEntity(TemplateDTO templateDTO) {
		
		Template template = new Template();
		template.setName(templateDTO.getName());
		template.setHtml(templateDTO.getHtml());
		template.setTags(templateDTO.getTags());
		return template;
		
	}
	
	public static Template getEntity(CampaignTemplateDTO camptemplateDTO) {
		
		Template template = new Template();
		template.setId(camptemplateDTO.getTemplateId());
		template.setName(camptemplateDTO.getTemplateName());
		template.setHtml(camptemplateDTO.getHtml());
		template.setTags(String.join(";", camptemplateDTO.getTemplateTags()));
		template.setCreatedBy(camptemplateDTO.getCreatedBy());
		return template;
		
	}
	
	public static UserDTO getUserDTO(UserDetails user) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setEmailId(user.getEmailId());
//		userDTO.setPassword(user.getPassword());
		userDTO.setAuthCode(user.getAuthCode());
		userDTO.setLastName(user.getLastName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setOrgId(user.getOrgId());
		return userDTO;
		
	}
	
	public static UserDetails getUserEntity(UserDTO userDTO) {
		
		UserDetails user = new UserDetails();
		user.setEmailId(userDTO.getEmailId());
		user.setPassword(userDTO.getPassword());
		user.setLastName(userDTO.getLastName());
		user.setFirstName(userDTO.getFirstName());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user.setOrgId(userDTO.getOrgId());
		user.setAuthCode(DigestUtils.md5Hex(userDTO.getEmailId()).toUpperCase());
		
		return user;
		
	}
	
	public static Campaign getCampaignEntity(CampaignTemplateDTO campTemplDTO) {
		
		Campaign campaign = new Campaign();
		campaign.setId(campTemplDTO.getCampaignId());
		campaign.setActive(true);
		campaign.setEmail_list(String.join(";", campTemplDTO.getEmail_list()));
		campaign.setDescription(campTemplDTO.getCampaignDescription());
		campaign.setName(campTemplDTO.getCampaignName());
		campaign.setTags(String.join(";",campTemplDTO.getCampaignTags()));
		campaign.setCreatedBy(campTemplDTO.getCreatedBy());
		return campaign;
		
	}
	
	public static ImageDTO getImageDTO(Images image) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setUrl(image.getUrl());
		imageDTO.setName(image.getName());
		imageDTO.setType(image.getType());
		imageDTO.setThumb("");
		imageDTO.setType("image");
		imageDTO.setTag(image.getTag());
		return imageDTO;
	}

	public static CampaignDTO getCampaignDTO(Campaign camp) {

		CampaignDTO campaignDTO = new CampaignDTO();
		campaignDTO.setId(camp.getId());
		campaignDTO.setActive(true);
		campaignDTO.setEmail_list(String.join(";", camp.getEmail_list()));
		campaignDTO.setDescription(camp.getDescription());
		campaignDTO.setName(camp.getName());
		campaignDTO.setTags(String.join(";",camp.getTags()));
		campaignDTO.setCreatedBy(camp.getCreatedBy());
		
		Set<TemplateDTO> templateDTOs = new HashSet<TemplateDTO>();
		List<Integer> templateIds = new ArrayList<Integer>();
		camp.getTemplates().stream().forEach(template -> {
			templateDTOs.add(getDTO(template));
			templateIds.add(template.getId());
		});
		campaignDTO.setFeedbacks(camp.getFeedbacks());
		campaignDTO.setTemplateIds(templateIds);
		campaignDTO.setTemplateDTOs(templateDTOs);
		return campaignDTO;
	}

	public static SubscribersDTO getSubscriberDTO(Subscribers sub) {
		SubscribersDTO subDTO = new SubscribersDTO();
		subDTO.setId(sub.getId());
		subDTO.setActive(sub.isActive());
		subDTO.setAddress(sub.getAddress());
		subDTO.setCountry(sub.getCountry());
		subDTO.setFirstName(sub.getFirstName());
		subDTO.setGender(sub.getGender());
		subDTO.setLastName(sub.getLastName());
		subDTO.setMailBoxId(sub.getMailBoxId());
		subDTO.setSrcSysId(sub.getSrcSysId());
		subDTO.setState(sub.getState());
		subDTO.setAge(sub.getAge());		
		subDTO.setRegion(sub.getRegion());
		subDTO.setPhoneNumber(sub.getPhoneNumber());
		return subDTO;
	}

	public static Subscribers getSubscriber(SubscribersDTO subDTO) {
		Subscribers sub = new Subscribers();
//		sub.setId(subDTO.getId());
		sub.setActive(subDTO.isActive());
		sub.setAddress(subDTO.getAddress());
		sub.setCountry(subDTO.getCountry());
		sub.setFirstName(subDTO.getFirstName());
		sub.setGender(subDTO.getGender());
		sub.setLastName(subDTO.getLastName());
		sub.setMailBoxId(subDTO.getMailBoxId());
		sub.setSrcSysId(subDTO.getSrcSysId());
		sub.setState(subDTO.getState());
		sub.setAge(subDTO.getAge());		
		sub.setRegion(subDTO.getRegion());
		sub.setPhoneNumber(subDTO.getPhoneNumber());
		return sub;
	}

	public static Subscribers getSubscriber(SubscribersDTO subDTO, int orgId) {
		Subscribers sub = new Subscribers();
//		sub.setId(subDTO.getId());
		sub.setActive(subDTO.isActive());
		sub.setAddress(subDTO.getAddress());
		sub.setCountry(subDTO.getCountry());
		sub.setFirstName(subDTO.getFirstName());
		sub.setGender(subDTO.getGender());
		sub.setLastName(subDTO.getLastName());
		sub.setMailBoxId(subDTO.getMailBoxId());
		sub.setSrcSysId(subDTO.getSrcSysId());
		sub.setState(subDTO.getState());
		sub.setAge(subDTO.getAge());		
		sub.setRegion(subDTO.getRegion());
		sub.setPhoneNumber(subDTO.getPhoneNumber());
		sub.setOrgId(orgId);
		return sub;
	}

	public static LinkHitsDTO getLinkDTO(LinkHits link) {
		LinkHitsDTO linkHitsDTO = new LinkHitsDTO();
		linkHitsDTO.setClicks(link.getClicks());
		linkHitsDTO.setIpAddresses(link.getIpAddresses().split(";"));
		linkHitsDTO.setCampaignId(link.getCampaignId());
		linkHitsDTO.setLinkId(link.getLinkId());
		return linkHitsDTO;
	}

	public static WorkDTO getWorkDTO(Work work) {
		WorkDTO workDTO = new WorkDTO();
		workDTO.setEmailList(work.getEmailList());
		workDTO.setId(work.getId());
		workDTO.setStatus(work.getStatus());
		workDTO.setUploadedBy(work.getUploadedBy());
		return workDTO;
	}

	public static Feedbacks getFeedbackEntity(FeedbackDTO feedbackDTO) {
		Feedbacks feedback = new Feedbacks();
		feedback.setCampaignId(feedbackDTO.getCampaignId());
		feedback.setUserId(feedbackDTO.getUserId());
		feedback.setRating(feedbackDTO.getRating());
		feedback.setFeedbackComments(feedbackDTO.getFeedbackComments());
		return feedback;
	}

	public static FeedbackDTO getFeedbackDTO(Feedbacks feedback) {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		feedbackDTO.setCampaignId(feedback.getCampaignId());
		feedbackDTO.setUserId(feedback.getUserId());
		feedbackDTO.setRating(feedback.getRating());
		feedbackDTO.setFeedbackComments(feedback.getFeedbackComments());
		return feedbackDTO;
	}
	
}
