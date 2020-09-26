package com.john_deligiannis.laugh_9.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.john_deligiannis.laugh_9.bodies.AddPostRequest;
import com.john_deligiannis.laugh_9.bodies.DeletePostRequest;
import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;
import com.john_deligiannis.laugh_9.repositories.PostRepository;
import com.john_deligiannis.laugh_9.repositories.UserRepository;
import com.john_deligiannis.laugh_9.security_config.JwtTokenUtil;

@Controller
@RequestMapping(path="/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(path="/add")
	public @ResponseBody String addPost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody AddPostRequest addPostRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		
		Post post = new Post();
		post.setUser(user);
		post.setTitle(addPostRequest.getTitle());
		post.setDescription(addPostRequest.getDescription());
		post.setCategory(addPostRequest.getCategory());
		post.setMediaSource(addPostRequest.getMediaSource());
		
		postRepository.save(post);
		
		return "Post has been uploaded";
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody String deletePost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody DeletePostRequest deletePostRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		
		Post post = postRepository.findByUserIdAndPostId(user, deletePostRequest.getPostId());
		
		if(post != null) {
			postRepository.delete(post);
			return "Post has been deleted";
		}
		
		return "Unable to delete this post";
	}
	
}
