package com.john_deligiannis.laugh_9.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.*;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User c WHERE c.username = :username")
	boolean existsByUsername(String username);

}
