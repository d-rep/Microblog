/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.repaskys.microblog.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.repaskys.microblog.domain.BlogUser;
import com.repaskys.microblog.domain.Post;
import com.repaskys.microblog.repositories.BlogUserRepository;
import com.repaskys.microblog.repositories.PostRepository;

/**
 * This implementation uses the UserDetailsManager from Spring Security, as well
 * as a Repository from Spring Data JPA.
 * 
 * @author Drew Repasky
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BlogUserRepository blogUserRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// These are the same for every user we create
	private static final boolean enabled = true;
	private static final boolean accountNonExpired = true;
	private static final boolean credentialsNonExpired = true;
	private static final boolean accountNonLocked = true;
	private static final List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
	
	/**
	 * Hash the plain text password and create a new UserDetails instance that can be persisted.
	 */
	private UserDetails initializeUser(String username, String plainTextPassword) {
		String password = passwordEncoder.encode(plainTextPassword);
		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public boolean userExists(String username) {
		return userDetailsManager.userExists(username);
	}
	
	/**
	 * This will try to save a new user, and returns an error message if the
	 * save failed. This does not check for duplicate usernames before saving.
	 */
	public String registerUser(String username, String plainTextPassword) {
		String errorMessage = "";
		try {
			userDetailsManager.createUser(initializeUser(username, plainTextPassword));
		} catch(DataIntegrityViolationException ex) {
			logger.error("DataIntegrityViolationException when saving user " + username + ".", ex);
			errorMessage = "Could not register user \"" + username + "\".  The user may already exist.";
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when saving user " + username + ".", ex);
			errorMessage = "Could not register user \"" + username + "\".  An unexpected problem occurred when trying to save the data.";			
		}
		return errorMessage;
	}
	
	public List<String> searchForUsers(String username) {
		List<String> usernames = new ArrayList<String>();
		
		// FIXME this is NOT doing the right thing; for now we just return all users
		Iterable<BlogUser> blogUsers = blogUserRepository.findAll();
		for(BlogUser blogUser : blogUsers) {
			usernames.add(blogUser.getUsername());
			logger.debug(blogUser.getUsername());
		}
		return usernames;
	}

	public String createPost(String username, String message) {
		
		// FIXME add error handling
		BlogUser blogUser = blogUserRepository.findByUsername(username);

		Date createdDate = new Date();
		Post post = new Post();
		post.setBlogUser(blogUser);
		post.setCreatedDate(createdDate);
		post.setMessage(message);
		
		String errorMessage = "";
		try {
			postRepository.save(post);
		} catch(DataIntegrityViolationException ex) {
			logger.error("DataIntegrityViolationException when saving post for user " + username + ".", ex);
			errorMessage = "Could not save post for user \"" + username + "\".";
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when saving post for user " + username + ".", ex);
			errorMessage = "Could not save post for user \"" + username + "\".  An unexpected problem occurred when trying to save the data.";			
		}
		return errorMessage;
	}
}
