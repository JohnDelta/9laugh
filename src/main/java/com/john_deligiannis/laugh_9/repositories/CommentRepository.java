package com.john_deligiannis.laugh_9.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query("SELECT e FROM comment e WHERE e.commmentId = :commentId")
	Comment findByCommentId(Long commentId);
	
}
