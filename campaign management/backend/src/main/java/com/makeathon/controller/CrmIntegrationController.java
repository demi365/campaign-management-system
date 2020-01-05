package com.makeathon.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.dto.AuthorizationDTO;
import com.makeathon.dto.LoadCrmDTO;
import com.makeathon.dto.SubscribersDTO;
import com.makeathon.service.CrmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/integration")
@CrossOrigin(origins = "*")
@Api(value = "load or fetch data", tags = { "integartion" })
public class CrmIntegrationController {
	
	@Autowired
	CrmService crmService;
	
	@GetMapping(value="/fetch", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrives all customer data if authorization code and organisation id match",
	notes = "The scource id which was retrieved will be retrieved as srcSysId")
	public List<SubscribersDTO> getAllCustomersData(@RequestParam("auth_code") @Valid String authCode) {
		AuthorizationDTO authDTO = new AuthorizationDTO();
		authDTO.setAuthorizationCode(authCode);
		return crmService.fetchAllCustomersData(authDTO);
		
	}

	@PostMapping(value="/load", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "If the authorization key and org id match loads the data provided",
	notes = "The scource id srcSysId is mandatory to load customer data")
	public ResponseEntity<Object> loadAllCustomersData(@RequestBody @Valid LoadCrmDTO crmDTO) {
		
		return crmService.loadAllCustomersData(crmDTO);
		
	}
	
}
