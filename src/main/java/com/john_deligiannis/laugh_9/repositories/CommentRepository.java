package com.john_deligiannis.laugh_9.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Comment;
import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query("SELECT e FROM comment e WHERE e.commentId = :commentId AND e.user = :user")
	Comment findByCommentIdAndUser(Long commentId, User user);
	
	List<Comment> findByPost(Post post);
	
}