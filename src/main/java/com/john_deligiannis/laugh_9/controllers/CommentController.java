package com.john_deligiannis.laugh_9.controllers;

import java.util.Date;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.john_deligiannis.laugh_9.bodies.CommentIdRequest;
import com.john_deligiannis.laugh_9.bodies.CommentRequest;
import com.john_deligiannis.laugh_9.bodies.PostIdRequest;
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
	public @ResponseBody ResponseEntity<String> addComment(
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
			return ResponseEntity.ok("Comment uploaded");
		}
		return ResponseEntity.badRequest().body("Unable to upload comment");
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody ResponseEntity<String> deleteComment(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody CommentIdRequest commentIdRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		Comment comment = commentRepository.findByCommentIdAndUser(commentIdRequest.getCommentId(), user);
		
		if(comment != null) {
			commentRepository.delete(comment);
			return ResponseEntity.ok("Comment deleted");
		}
		return ResponseEntity.badRequest().body("Unable to delete comment");
	}
	
	@PostMapping(path="/get/all")
	public @ResponseBody ResponseEntity<List<Comment>> getAllComment(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody PostIdRequest postIdRequest
	) {
		Post post = postRepository.findByPostId(postIdRequest.getPostId());
		List<Comment> comments = commentRepository.findByPost(post);

		if(comments != null) {
			return ResponseEntity.ok(comments);
		}
		return ResponseEntity.badRequest().body(null);
	}

}
