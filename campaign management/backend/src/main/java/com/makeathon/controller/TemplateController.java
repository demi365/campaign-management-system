package com.makeathon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.dto.CampTempMapDTO;
import com.makeathon.dto.CampaignAndTemplatesDTO;
import com.makeathon.dto.CampaignDTO;
import com.makeathon.dto.CampaignTemplateDTO;
import com.makeathon.dto.TemplateDTO;
import com.makeathon.entity.Template;
import com.makeathon.service.TemplateService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/campaign")
@Api(value = "Templates and campaign management controller", tags = { "templates", "campaigns" })
public class TemplateController {
	
	@Autowired
	TemplateService tempService;
	
	@GetMapping("/template")
    public List<Template> getAllTemplate() {
		
		return tempService.getAllTemplates();
        
    }
	
	@GetMapping("/template/{id}")
    public TemplateDTO getTemplateById(@Valid @PathVariable("id") int id) {
		
		return tempService.getTemplateFromId(id);
        
    }
	
	@PostMapping(value = "/template", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TemplateDTO createTemplate(@Valid @RequestBody TemplateDTO templateDTO) {
		
		return tempService.createTemplate(templateDTO);
        
    }
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CampTempMapDTO createCampaignTemplates(@Valid @RequestBody CampaignTemplateDTO campTemplDTO) {
		
		return tempService.createCampaignsAndTemplates(campTemplDTO);
        
    }

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CampaignDTO createCampaign(@Valid @RequestBody CampaignTemplateDTO campTemplDTO) {
		
		return tempService.createCampaign(campTemplDTO);
        
    }
	
	@GetMapping(value = "/campsandtemps/{user_id}")
	public CampaignAndTemplatesDTO fetchAllCampaignsAndTemplates(@Valid @PathVariable("user_id") String user_id) {
		
		return tempService.fetchAllCampsAndTemps(user_id);
        
    }
	
	@GetMapping(value="/getAllCampaigns")
	public List<CampaignDTO> getAllCampaigns(){
		
		return tempService.fetchCampaigns();
		
	}
	
	@DeleteMapping(value="/{template_id}")
	public void deleteTemplate(@PathVariable("template_id") @Valid int templateId){
		
		tempService.deleteTemplate(templateId);
		
	}
	
}
