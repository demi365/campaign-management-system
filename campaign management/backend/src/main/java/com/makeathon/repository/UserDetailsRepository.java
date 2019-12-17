package com.makeathon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{
	
	public Optional<UserDetails> findByEmailIdAndPassword(String email, String password);
	
}
