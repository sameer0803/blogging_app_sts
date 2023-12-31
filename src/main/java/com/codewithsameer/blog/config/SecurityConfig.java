package com.codewithsameer.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

	
	  @Bean
	    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

	        httpSecurity.csrf()
	                .disable()
	                .authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated()
	                ).httpBasic(Customizer.withDefaults());

	        
	        return httpSecurity.build();
	    }
	  
	  
}