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

package com.repaskys.microblog.controllers;


import java.util.Arrays;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		logger.trace("executing inside UserController register()");
		return "register";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestParam("username") String username, @RequestParam("password") String plainTextPassword, Model model) {
		logger.trace("executing inside UserController createUser()");
		
		String view = "error";
		try {
			userDetailsManager.createUser(initializeUser(username, plainTextPassword));
			view = "user_created";
		} catch(DataIntegrityViolationException ex) {
			logger.error("DataIntegrityViolationException when saving user " + username + ".", ex);
			model.addAttribute("errorMessage", "Could not register user \"" + username + "\".  The user may already exist.");
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when saving user " + username + ".", ex);
			model.addAttribute("errorMessage", "Could not register user \"" + username + "\".  An unexpected problem occurred when trying to save the data.");			
		}
		model.addAttribute("username", username);
		return view;
	}
}
