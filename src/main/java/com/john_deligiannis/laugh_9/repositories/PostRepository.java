package com.john_deligiannis.laugh_9.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;

public interface PostRepository extends CrudRepository<Post, Integer> {
	
	@Query("SELECT e FROM post e WHERE e.user = :user AND e.postId = :postId")
	Post findByUserIdAndPostId(User user, Integer postId);

}
