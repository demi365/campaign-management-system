package com.makeathon.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.makeathon.entity.Campaign;
import com.makeathon.entity.Images;
import com.makeathon.entity.Template;
import com.makeathon.entity.UserDetails;

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
		userDTO.setLastName(user.getLastName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		return userDTO;
		
	}
	
	public static UserDetails getUserEntity(UserDTO userDTO) {
		
		UserDetails user = new UserDetails();
		user.setEmailId(userDTO.getEmailId());
		user.setPassword(userDTO.getPassword());
		user.setLastName(userDTO.getLastName());
		user.setFirstName(userDTO.getFirstName());
		user.setDateOfBirth(userDTO.getDateOfBirth());
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
		
		campaignDTO.setTemplateIds(templateIds);
		campaignDTO.setTemplatesDTO(templateDTOs);
		return campaignDTO;
	}
	
}
