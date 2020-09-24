package com.john_deligiannis.laugh_9.repositories;

import org.springframework.data.repository.CrudRepository;

import com.john_deligiannis.laugh_9.entities.*;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface UserRepository extends CrudRepository<User, Integer> {

}
