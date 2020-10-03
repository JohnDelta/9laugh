package com.john_deligiannis.laugh_9.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;

public interface PostRepository extends CrudRepository<Post, Long> {
	
	// I want this to know if a post belongs to a particular user before i use it
	@Query("SELECT e FROM post e WHERE e.user = :user AND e.postId = :postId")
	Post findByUserAndPostId(User user, Long postId);
	
	@Query("SELECT e FROM post e WHERE e.popularity='POPULAR'")
	List<Post> findPopular();

	@Query("SELECT e FROM post e WHERE e.popularity='NEW'")
	List<Post> findNew();
	
	@Query("SELECT e FROM post e WHERE e.category LIKE %:category%")
	List<Post> findByCategory(String category);
	
	@Query("SELECT e FROM post e WHERE e.postId = :postId")
	Post findByPostId(Long postId);
}
