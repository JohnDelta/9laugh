package com.john_deligiannis.laugh_9.repositories;

import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.*;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);

}
