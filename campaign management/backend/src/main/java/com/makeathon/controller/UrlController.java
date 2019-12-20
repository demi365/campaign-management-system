package com.makeathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.service.UrlService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/url")
@Api(value = "Urls used for telemetrics and tracking", tags = { "unsubscribe" })
public class UrlController {
	
	@Value("#{urls.unsubscribe}")
	public static String unsubscribeLink;
	
	@Autowired
	UrlService urlServices;
	
	@GetMapping("/unsubscribe")
	public String unsubsribeYes( 	@RequestParam(value="campaignId") int campaignId,
									@RequestParam(value="userId") String userId) {
		
		return urlServices.insertIntoUrl(campaignId, userId);
		
	}
	
}
