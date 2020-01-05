package com.makeathon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.makeathon.repository.UserDetailsRepository;

@Service
public class MyUserService implements UserDetailsService {
	
	@Autowired
	private UserDetailsRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.makeathon.entity.UserDetails> user = userRepository.findByEmailId(username);
		if(user.isPresent())
			return user.get();
		else return null;
	}
}