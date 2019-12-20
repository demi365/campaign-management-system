package com.makeathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.dto.EmailDTO;
import com.makeathon.dto.WorkDTO;
import com.makeathon.service.MailerService;

import io.swagger.annotations.Api;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/emails")
@Api(value = "Email sending API endpoints", tags = { "Email" })
public class EmailController {

	@Autowired
	private MailerService mailService;
	
	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
	private WorkDTO sendEmailViaSendGrid(@RequestBody EmailDTO emailDTO) throws Exception {
		
		return mailService.sendEmailViaSendGrid(emailDTO);
		
	}
	
}
