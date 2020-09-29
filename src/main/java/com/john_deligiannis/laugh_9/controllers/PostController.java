package com.john_deligiannis.laugh_9.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.john_deligiannis.laugh_9.bodies.PostCategoryRequest;
import com.john_deligiannis.laugh_9.bodies.PostIdRequest;
import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;
import com.john_deligiannis.laugh_9.entities.UserVote;
import com.john_deligiannis.laugh_9.entities.enums.*;
import com.john_deligiannis.laugh_9.repositories.PostRepository;
import com.john_deligiannis.laugh_9.repositories.UserRepository;
import com.john_deligiannis.laugh_9.repositories.UserVoteRepository;
import com.john_deligiannis.laugh_9.security_config.JwtTokenUtil;
import com.john_deligiannis.laugh_9.storage.StorageFileNotFoundException;
import com.john_deligiannis.laugh_9.storage.StorageService;

@Controller
@RequestMapping(path="api/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserVoteRepository userVoteRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private final StorageService storageService;
	
	@Autowired
	public PostController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@PostMapping(path="/add")
	public @ResponseBody String addPost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestParam("file") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("category") String category
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		
		int rnd = (int) (Math.random() * 100000 + 10000);
		String mediaSource = username + "_" + rnd + ".png";
		
		Post post = new Post();
		post.setUser(user);
		post.setTitle(title);
		post.setCategory(category);
		post.setMediaSource(mediaSource);
		post.setDownvotes(0L);
		post.setUpvotes(0L);
		post.setPopularity(Popularity.NEW.toString());
		post.setDate(new Date().getTime());
		postRepository.save(post);
		
		storageService.store(file, mediaSource);
		return "Post has been uploaded";
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody String deletePost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody PostIdRequest postIdRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		
		Post post = postRepository.findByUserAndPostId(user, postIdRequest.getPostId());
		
		if(post != null) {
			postRepository.delete(post);
			return "Post has been deleted";
		}
		
		return "Unable to delete this post";
	}
	
	@PostMapping(path="/get/popular")
	public @ResponseBody List<Post> getPopularPosts(
			@RequestHeader("Authorization") String tokenHeader
	) {
		List<Post> posts = postRepository.findPopular();
		return posts;
	}
	
	@PostMapping(path="/get/new")
	public @ResponseBody List<Post> getNewPosts(
			@RequestHeader("Authorization") String tokenHeader
	) {
		List<Post> posts = postRepository.findNew();
		return posts;
	}
	
	@PostMapping(path="/get/category")
	public @ResponseBody List<Post> getCategoryPosts(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody PostCategoryRequest postCategoryRequest
	) {
		List<Post> posts = postRepository.findByCategory(postCategoryRequest.getCategory());
		return posts;
	}
	
	@PostMapping("/upvote")
	public @ResponseBody String upvotePost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody PostIdRequest postIdRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		Post post = postRepository.findByUserAndPostId(user, postIdRequest.getPostId());
		
		if(post != null) {
			UserVote userVote = userVoteRepository.findByUserAndPost(user, post);
			
			if(userVote == null) {
				
				userVote = new UserVote();
				userVote.setPost(post);
				userVote.setUser(user);
				userVote.setVote(Vote.UPVOTE.toString());
				userVoteRepository.save(userVote);
				
				post.setUpvotes(post.getUpvotes() + 1);
				postRepository.save(post);
				
				return "Post upvoted";
			} else {
				if(userVote.getVote().equals(Vote.DOWNVOTE.toString())) {
					
					userVote.setVote(Vote.UPVOTE.toString());
					userVoteRepository.save(userVote);
					
					post.setUpvotes(post.getUpvotes() + 1);
					post.setDownvotes(post.getDownvotes() - 1);
					postRepository.save(post);
					
					return "Post upvoted";
				}
				return "Post already upvoted";
			}
		}
		return "Unable to find this post";
	}
	
	@PostMapping("/downvote")
	public @ResponseBody String downvotePost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody PostIdRequest postIdRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		Post post = postRepository.findByUserAndPostId(user, postIdRequest.getPostId());
		
		if(post != null) {
			UserVote userVote = userVoteRepository.findByUserAndPost(user, post);
			
			if(userVote == null) {
				
				userVote = new UserVote();
				userVote.setPost(post);
				userVote.setUser(user);
				userVote.setVote(Vote.DOWNVOTE.toString());
				userVoteRepository.save(userVote);
				
				post.setUpvotes(post.getUpvotes() + 1);
				postRepository.save(post);
				
				return "Post downvoted";
			} else {
				if(userVote.getVote().equals(Vote.UPVOTE.toString())) {
					
					userVote.setVote(Vote.DOWNVOTE.toString());
					userVoteRepository.save(userVote);
					
					post.setUpvotes(post.getUpvotes() - 1);
					post.setDownvotes(post.getDownvotes() + 1);
					postRepository.save(post);
					
					return "Post downvoted";
				}
				return "Post already downvoted";
			}
		}
		return "Unable to find this post";
	}
	
	@PostMapping("/get")
	public @ResponseBody Post getPost(
			@RequestHeader("Authorization") String tokenHeader,
			@RequestBody PostIdRequest postIdRequest
	) {
		String jwtToken = tokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		User user = userRepository.findByUsername(username);
		Post post = postRepository.findByUserAndPostId(user, postIdRequest.getPostId());
		
		return post;
	}
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
}
