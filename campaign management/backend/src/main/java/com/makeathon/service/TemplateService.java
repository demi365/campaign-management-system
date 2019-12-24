package com.makeathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makeathon.dto.CampTempMapDTO;
import com.makeathon.dto.CampaignAndTemplatesDTO;
import com.makeathon.dto.CampaignDTO;
import com.makeathon.dto.CampaignTemplateDTO;
import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.TemplateDTO;
import com.makeathon.entity.Campaign;
import com.makeathon.entity.Template;
import com.makeathon.repository.CampaignRepository;
import com.makeathon.repository.TemplateRepository;

@Service
public class TemplateService {

	@Autowired
	TemplateRepository tempRepo;

	@Autowired
	CampaignRepository campRepo;
	
	@Transactional
	public TemplateDTO createTemplate(TemplateDTO templateDTO) {
		
		Template template = DTOFactory.getEntity(templateDTO);
		return DTOFactory.getDTO(tempRepo.save(template));
        
    }

	public List<Template> getAllTemplates() {
		
		return tempRepo.findAll();
		
	}

	public TemplateDTO getTemplateFromId(int id) {
		Optional<Template> templateById = tempRepo.findById(id);
		if (templateById.isPresent())
			return DTOFactory.getDTO(templateById.get());
		else
			return null;
	}

	@Transactional
	public CampTempMapDTO createCampaignsAndTemplates(CampaignTemplateDTO campTemplDTO) {
		
		Optional<Campaign> campaign = campRepo.findById(campTemplDTO.getCampaignId());
		
		Template temp = DTOFactory.getEntity(campTemplDTO);
		
		if(campaign.isPresent()) {
			campaign.get().getTemplates().add(temp);
		} else {
			Campaign camp = DTOFactory.getCampaignEntity(campTemplDTO);
			campaign = Optional.ofNullable(camp);
			temp.setId(0);
			campaign.get().getTemplates().add(temp);
		}
		tempRepo.save(temp);
		Campaign savedCampaign = campRepo.save(campaign.get());
		
		CampTempMapDTO campTempMapDTO = new CampTempMapDTO();
		campTempMapDTO.setCampaignId(savedCampaign.getId());
		campTempMapDTO.setTemplateId(temp.getId());
		return campTempMapDTO;
		
	}

	public CampaignAndTemplatesDTO fetchAllCampsAndTemps(@Valid String user_id) {
		CampaignAndTemplatesDTO campsAndTemps = new CampaignAndTemplatesDTO();
		campsAndTemps.setCampaigns(campRepo.findByCreatedBy(user_id));
		campsAndTemps.setTemplates(tempRepo.findByCreatedBy(user_id));
		return campsAndTemps;
	}
	
	public List<CampaignDTO> fetchCampaigns() {
		List<Campaign> campaigns = campRepo.findAll();
		List<CampaignDTO> campaignDTOs = new ArrayList<CampaignDTO>();
		
		campaigns.stream().forEach(camp -> campaignDTOs.add(DTOFactory.getCampaignDTO(camp)));
		
		return campaignDTOs;
		
	}

	public CampaignDTO createCampaign(@Valid CampaignTemplateDTO campTemplDTO) {
		
		Campaign camp = DTOFactory.getCampaignEntity(campTemplDTO);
		campRepo.save(camp);
		return DTOFactory.getCampaignDTO(camp);
	}
	
}
