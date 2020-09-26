package com.john_deligiannis.laugh_9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.john_deligiannis.laugh_9.repositories.UserRepository;

import com.john_deligiannis.laugh_9.bodies.AddUserRequest;
import com.john_deligiannis.laugh_9.config.JwtTokenUtil;
import com.john_deligiannis.laugh_9.entities.User;

@Controller
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(path="/add")
	public @ResponseBody String addUser (@RequestBody AddUserRequest addUserRequest) {
		
		if(userRepository.findByUsername(addUserRequest.getUsername()) == null) {
			User user = new User();
			
			user.setUsername(addUserRequest.getUsername());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hash = encoder.encode(addUserRequest.getPassword());
			user.setPassword(hash);
			
			userRepository.save(user);
			return "Account created";
		}
		
		return "Username already exists";
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody String deleteUser (
			@RequestHeader("Authorization") String tokenHeader
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		userRepository.delete(user);
		
		return "User " + username + " has been deleted";
	}
	
}
