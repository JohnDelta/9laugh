package com.john_deligiannis.laugh_9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.john_deligiannis.laugh_9.repositories.UserRepository;
import com.john_deligiannis.laugh_9.bodies.AddUser;
import com.john_deligiannis.laugh_9.entities.User;

@Controller
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestBody AddUser addUser) {
		
		if(userRepository.findByUsername(addUser.getUsername()) != null) {
			User user = new User();
			user.setUsername(addUser.getUsername());
			user.setPassword(addUser.getPassword());
			userRepository.save(user);
			return "Saved";
		}
		
		return "User already exists";
	}
	
	@PostMapping(path="/remove")
	public @ResponseBody String removeUser (@RequestBody AddUser addUser) {
		
		
		
		return "User removed";
	} 
	
}
