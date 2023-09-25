package com.codewithsameer.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsameer.blog.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findbyEmail(String email);	
	

}
