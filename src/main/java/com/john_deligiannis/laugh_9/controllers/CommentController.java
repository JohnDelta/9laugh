package com.john_deligiannis.laugh_9.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.john_deligiannis.laugh_9.bodies.CommentIdRequest;
import com.john_deligiannis.laugh_9.bodies.CommentRequest;
import com.john_deligiannis.laugh_9.entities.Comment;
import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;
import com.john_deligiannis.laugh_9.repositories.CommentRepository;
import com.john_deligiannis.laugh_9.repositories.PostRepository;
import com.john_deligiannis.laugh_9.repositories.UserRepository;
import com.john_deligiannis.laugh_9.security_config.JwtTokenUtil;

@Controller
@RequestMapping(path="api/comment")
public class CommentController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(path="/add")
	public @ResponseBody String addComment(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody CommentRequest commentRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		Post post = postRepository.findByPostId(commentRequest.getPostId());
		
		if(user != null && post != null) {
			Comment comment = new Comment();
			comment.setComment(commentRequest.getComment());
			comment.setPost(post);
			comment.setUser(user);
			comment.setDate(new Date().getTime());
			
			commentRepository.save(comment);
			return "Comment has been uploaded";
		}
		return "Unable to upload comment";
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody String addComment(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody CommentIdRequest commentIdRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		Comment comment = commentRepository.findByCommentId(commentIdRequest.getCommentId());
		
		if(user != null && comment != null) {
			commentRepository.delete(comment);
			return "Comment has been deleted";
		}
		return "Unable to delete comment";
	}

}
