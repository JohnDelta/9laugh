package com.john_deligiannis.laugh_9.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Comment;
import com.john_deligiannis.laugh_9.entities.Post;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment findByCommentId(Long commentId);
	
	@Query("SELECT e FROM comment e WHERE e.post = :post")
	List<Comment> findByPost(Post post);
	
}