package com.john_deligiannis.laugh_9.repositories;

import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.Post;
import com.john_deligiannis.laugh_9.entities.User;
import com.john_deligiannis.laugh_9.entities.UserVote;

public interface UserVoteRepository extends CrudRepository<UserVote, Long> {
	
	UserVote findByUserAndPost(User user, Post post);

}
