package com.makeathon.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.dto.LinkHitsDTO;
import com.makeathon.service.BouncerService;

@RestController
@RequestMapping("/bouncers")
public class BouncersController {
	
	@Autowired
	BouncerService bounceService;
	
	@GetMapping("/track")
	public ResponseEntity<Void> recieveAndTrackUserClick(HttpServletRequest request, @RequestParam("campaign_id") int campaignId, 
										@RequestParam("work_id") int workId, 
										@RequestParam("input_id") String inputId,
										@RequestParam("url") String url) {
		bounceService.trackUserUrl(request, campaignId, workId, inputId, url);
		return ResponseEntity.status(HttpStatus.FOUND)
		        .location(URI.create(url))
		        .build();
	}

	@GetMapping("/fetch/work")
	public List<LinkHitsDTO> fetchAllLinkHitsForWorkId(@RequestParam("work_id") int workId) {
		return bounceService.getAllLinkHits(workId);
	}
	
}
