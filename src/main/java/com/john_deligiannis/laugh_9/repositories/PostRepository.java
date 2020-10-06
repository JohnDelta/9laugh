package com.john_deligiannis.laugh_9.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;

public interface PostRepository extends CrudRepository<Post, Long> {
	
	@Query("SELECT e FROM post e WHERE e.user = :user AND e.postId = :postId")
	Post findByUserAndPostId(User user, Long postId);
	
	@Query("SELECT e FROM post e WHERE e.popularity = :popularity AND e.category LIKE %:category%")
	List<Post> findByPopularityAndCategory(String popularity, String category);
	
	List<Post> findByUser(User user);
	
	@Query("SELECT e FROM post e WHERE e.postId = :postId")
	Post findByPostId(Long postId);
}
