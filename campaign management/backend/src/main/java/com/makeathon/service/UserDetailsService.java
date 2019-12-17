package com.makeathon.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.UserDTO;
import com.makeathon.entity.UserDetails;
import com.makeathon.repository.UserDetailsRepository;

@Service
@CrossOrigin
public class UserDetailsService {
	
	@Autowired
	private UserDetailsRepository userRepo;
	
	public int findUserByEmailAndPassword(String email, String password) {
		
		Optional<UserDetails> user = userRepo.findByEmailIdAndPassword(email, password);
		if(user.isPresent())
			return user.get().getId();
		else 
			return 0;
		
	}

	@Transactional
	public UserDTO createUser(UserDTO userDTO) {
		
		UserDetails user = userRepo.save(DTOFactory.getUserEntity(userDTO));
		if(user!=null)
			return DTOFactory.getUserDTO(user);
		else
			return null;
		
	}
	
}
