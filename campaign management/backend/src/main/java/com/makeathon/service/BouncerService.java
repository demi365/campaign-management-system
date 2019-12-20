package com.makeathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.LinkHitsDTO;
import com.makeathon.entity.LinkHits;
import com.makeathon.entity.LinkHitsId;
import com.makeathon.repository.LinkHitsRepository;

@Service
public class BouncerService {
	
	@Autowired
	LinkHitsRepository linkHitsRepo;
	private static final String[] IP_HEADER_CANDIDATES = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" };

	@Transactional
	public void trackUserUrl(HttpServletRequest request, int campaignId, int workId, String inputId, String url) {
		LinkHitsId linkId = new LinkHitsId(workId, inputId);
		Optional<LinkHits> link = linkHitsRepo.findById(linkId);
		String ipAddr = getIpAddress(request);
		ipAddr = link.get().getIpAddresses().equals("")?ipAddr:link.get().getIpAddresses()+";"+ipAddr;
		
		link.get().setClicks(link.get().getClicks()+1);
		link.get().setIpAddresses(ipAddr);
		
		linkHitsRepo.save(link.get());
		
	}

	public void addLinkToTracking(int workId, int campaignId, String inputId) {
		
		LinkHits link = new LinkHits();
		link.setCampaignId(campaignId);
		LinkHitsId linkId = new LinkHitsId(workId, inputId);
		link.setLinkId(linkId);
		
		linkHitsRepo.save(link);
		
		
	}

	public List<LinkHitsDTO> getAllLinkHits(int workId) {
		List<LinkHits> linkHits = linkHitsRepo.findByworkId(workId);
		List<LinkHitsDTO> linkHitsDTO = new ArrayList<>();
		linkHits.stream().forEach(link -> linkHitsDTO.add(DTOFactory.getLinkDTO(link)));
		return linkHitsDTO;
	}
	
	private String getIpAddress(HttpServletRequest request) {
		for (String header : IP_HEADER_CANDIDATES) {
	        String ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	    }
		return request.getRemoteAddr();
	}

	
}
