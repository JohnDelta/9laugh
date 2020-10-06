package com.john_deligiannis.laugh_9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.john_deligiannis.laugh_9.repositories.UserRepository;
import com.john_deligiannis.laugh_9.security_config.JwtTokenUtil;
import com.john_deligiannis.laugh_9.storage.StorageService;
import com.john_deligiannis.laugh_9.entities.User;

@Controller
@RequestMapping(path="api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private final StorageService storageService;
	
	@Autowired
	public UserController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@PostMapping(path="/add")
	public @ResponseBody ResponseEntity<String> addUser (
			@RequestParam(required=false) MultipartFile file,
			@RequestParam String username,
			@RequestParam String password
	) {
		
		if((!username.isEmpty() && (!password.isEmpty())) 
				&& (userRepository.findByUsername(username) == null)) {
			
			User user = new User();
			
			user.setUsername(username);
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hash = encoder.encode(password);
			user.setPassword(hash);
			
			user.setMediaSource("default.png");
			if(file != null && !file.isEmpty()) {
				String[] args = file.getOriginalFilename().split("\\.");
				String extension = args[1];
				
				int rnd = (int) (Math.random() * 100000 + 10000);
				String mediaSource = username + "_user_" + rnd + "." + extension;
				user.setMediaSource(mediaSource);
				storageService.store(file, mediaSource);
			}
			
			userRepository.save(user);
			
			return ResponseEntity.ok("Account created");
		}
		
		return ResponseEntity.badRequest().body("Unable to create account");
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody ResponseEntity<String> deleteUser (
			@RequestHeader("Authorization") String tokenHeader
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			userRepository.delete(user);
			return ResponseEntity.ok("Account deleted");
		}
		
		return ResponseEntity.badRequest().body("Unable to delete account");
	}
	
	@PostMapping(path="/get")
	public @ResponseBody ResponseEntity<User> getUser (
			@RequestHeader("Authorization") String tokenHeader
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.badRequest().body(null);
	}
	
}
