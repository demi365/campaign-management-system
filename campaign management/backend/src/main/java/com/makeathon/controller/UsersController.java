package com.makeathon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.dto.LoginDTO;
import com.makeathon.dto.UserDTO;
import com.makeathon.service.UserDetailsService;

import io.swagger.annotations.Api;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/users")
@Api(value = "Users creation and login API", tags = { "users" })
public class UsersController {
	
	@Autowired
	UserDetailsService userService;
	
	@PostMapping("/login")
	public int getUserByEmailAndPassword(@Valid @RequestBody LoginDTO loginDTO) {
		
		return userService.findUserByEmailAndPassword(loginDTO.getEmail_id(), loginDTO.getPassword());
		
	}
	
	@PostMapping("/signup")
	public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
		
		return userService.createUser(userDTO);
		
	}
	
}
