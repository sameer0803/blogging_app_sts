package com.codewithsameer.blog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.codewithsameer.blog.entities.UserEntity;
import com.codewithsameer.blog.exceptions.ResourceNotFoundException;
import com.codewithsameer.blog.repositories.UserRepo;

public class CustomDetailUserService implements UserDetailsService{
	
	
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity User = userRepo.findbyEmail(username).orElseThrow(()-> new ResourceNotFoundException("UserName", "userName"+username, 0));
		
		
		
		return User;
	}

}
