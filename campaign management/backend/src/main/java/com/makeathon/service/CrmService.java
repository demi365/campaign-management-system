package com.makeathon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.makeathon.dto.AuthorizationDTO;
import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.LoadCrmDTO;
import com.makeathon.dto.SubscribersDTO;
import com.makeathon.dto.UserCreationStatusDTO;
import com.makeathon.entity.Subscribers;
import com.makeathon.entity.UserDetails;
import com.makeathon.exceptions.NotAuthorizedException;
import com.makeathon.repository.SubscribersRepository;
import com.makeathon.repository.UserDetailsRepository;

@Service
public class CrmService {
	
	@Autowired
	SubscribersRepository crmRepo;
	
	@Autowired
	UserDetailsRepository userRepo;
	
	public List<SubscribersDTO> fetchAllCustomersData(AuthorizationDTO authDTO){
		
		Optional<UserDetails> user = userRepo.findByAuthCode(authDTO.getAuthorizationCode());
		if(user.isPresent()) {
			List<SubscribersDTO> subscribersInformation = new ArrayList<SubscribersDTO>();
			System.out.println(user.get().getOrgId());
			Set<Subscribers> subscribers = crmRepo.findByOrgId(user.get().getOrgId());
			subscribers.stream().forEach(sub -> subscribersInformation.add(DTOFactory.getSubscriberDTO(sub)));
			return subscribersInformation;
		}
		throw new NotAuthorizedException();
		
	}
	
	public ResponseEntity<Object> loadAllCustomersData(LoadCrmDTO crmDTO) {
		
		Optional<UserDetails> user = userRepo.findByOrgIdAndAuthCode(crmDTO.getOrgId(), crmDTO.getAuthorizationCode());
		UserCreationStatusDTO status;
		HttpStatus statusCode;
		String message;
		
		if(user.isPresent()) {

			int orgId = user.get().getOrgId();
			
			List<Subscribers> suppliedSubscribers = new ArrayList<>();
			crmDTO.getCustomers().stream().forEach(cust -> suppliedSubscribers.add(DTOFactory.getSubscriber(cust, orgId)));
			
			Set<Subscribers> existingCustomers = crmRepo.findByOrgId(crmDTO.getOrgId());
			
			int createdCount = 0, updatedCount = 0, errorCount = 0;
			
			if(existingCustomers != null) {
				
				Map<String, Integer> scrSysIdAndCurSysIdMap = new HashMap<String, Integer>();
				existingCustomers.stream()
						.forEach(customer -> 
								scrSysIdAndCurSysIdMap
										.put(customer.getSrcSysId(), customer.getId()));
				
				Set<String> scrSysIdSet = scrSysIdAndCurSysIdMap.keySet();
				
//				suppliedSubscribers.stream().forEach
				for (Subscribers customer : suppliedSubscribers) {
					
					if(scrSysIdSet.contains(customer.getSrcSysId())) {
						customer.setId(scrSysIdAndCurSysIdMap.get(customer.getSrcSysId()));
						updatedCount++;
					}
					else {
						customer.setId(0);
						createdCount++;
					}
					
				}
			} else {
				createdCount = suppliedSubscribers.size();
			}
			for (Subscribers subscriber : suppliedSubscribers) {
				try {
					crmRepo.save(subscriber);
				} catch(ConstraintViolationException ex) {
					createdCount--;
					errorCount++;
				}
			}
			if(errorCount==0) {
				statusCode = HttpStatus.ACCEPTED;
				message = "success";
			} else {
				statusCode = HttpStatus.CONFLICT;
				message = "Some data were not loaded because of constrait validation";
			}
			
			status = new UserCreationStatusDTO
					(new Date(), createdCount, updatedCount, errorCount, message);
		} else {
			throw new NotAuthorizedException();
		}
		return new ResponseEntity<Object>(status, statusCode);
	}
	
	public Set<String> fecthAllCountriesByOrgId(int orgId) {
		
		return crmRepo.findDistinctCountryValuesByOrgId(orgId);
		
	}
	
	public List<String> findAllStatesByOrgIdAndCountry(@Valid int orgId, @Valid String country) {
		
		return crmRepo.findAllStates(orgId, country);
		
	}
	
	public List<String> fecthAllRegionsByOrgIdAndCountryAndState(@Valid int orgId, @Valid String country,
			@Valid String state) {

		return crmRepo.findAllRegions(orgId, country, state);
	}
	
	public List<String> fecthAllEmailsByOrgIdAndCountryAndStateAndRegion(@Valid int orgId, @Valid String country,
			@Valid String state, String region) {
		return crmRepo.findAllEmails(orgId, country, state, region);
	}
	
}
