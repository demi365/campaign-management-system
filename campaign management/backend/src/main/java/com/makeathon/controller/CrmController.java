package com.makeathon.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.service.CrmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
@Api(value = "Manage email list from CRM", tags = { "recipients" })
public class CrmController {
	
	@Autowired
	CrmService crmService;
	
	@GetMapping(value="/getCountries", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves the list of countries, if data is present",
	notes = "The organisation id is mandatory to retrieve the data")
	public Set<String> getAllCountriesFromOrgId(@RequestParam("organisation_id") @Valid int orgId) {
		
		return crmService.fecthAllCountriesByOrgId(orgId);
		
	}
	
	@GetMapping(value="/getStates", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves the list of states, if data is present",
	notes = "The organisation id is mandatory to retrieve the data, rest data can be supplied as all")
	public List<String> getAllStatesFromOrgIdAndCountry(@RequestParam("organisation_id") @Valid int orgId,
			@RequestParam("country") @Valid String country) {
		
		return crmService.findAllStatesByOrgIdAndCountry(orgId,country);
		
	}
	
	@GetMapping(value="/getRegions", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves the list of regions, if data is present",
	notes = "The organisation id is mandatory to retrieve the data, rest data can be supplied as all")
	public List<String> getAllRegionsFromOrgIdAndCountryAndState(@RequestParam("organisation_id") @Valid int orgId,
			@RequestParam("country") @Valid String country, @RequestParam("state") @Valid String state) {
		
		return crmService.fecthAllRegionsByOrgIdAndCountryAndState(orgId,country,state);
		
	}
	
	@GetMapping(value="/getEmailList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves the list of email id, if data is present",
	notes = "The organisation id is mandatory to retrieve the data, rest data can be supplied as all")
	public List<String> getAllEmailListFromOrgId(@RequestParam("organisation_id") @Valid int orgId,
			@RequestParam("country") @Valid String country, @RequestParam("state") @Valid String state, 
			@RequestParam(value ="region", required = false) String region) {
		
		return crmService.fecthAllEmailsByOrgIdAndCountryAndStateAndRegion(orgId,country,state,region);
		
	}
	
}
