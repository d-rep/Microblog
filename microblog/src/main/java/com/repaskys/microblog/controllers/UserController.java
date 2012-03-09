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

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.repaskys.microblog.domain.Post;
import com.repaskys.microblog.services.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.trace("executing inside UserController login()");
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		logger.trace("executing inside UserController register()");
		return "register";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(final String username, final String password, Map<String, Object> model) {
		logger.trace("executing inside UserController createUser()");
		
		String view = "";
		
		if(StringUtils.isBlank(username)) {
			view = "invalid";
			model.put("errorMessage", "What username would you like to register?");
		} else if(userService.userExists(username)) {
			view = "invalid";
			model.put("errorMessage", "Could not register the username \"" + username + "\" because it has already been taken by another user.");
		} else {
			String errorMessage = userService.registerUser(username, password);
			if(StringUtils.isBlank(errorMessage)) {
				view = "user_created";
				model.put("username", username);
			} else {
				view = "error";
				model.put("errorMessage", errorMessage);
			}
		}
		
		return view;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showPostsFromFollowers(Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside UserController showPostsFromFollowers()");
		String myUsername = principal.getName();
		model.put("username", myUsername);
		List<Post> posts = userService.getAllFollowersPostsForUser(myUsername);
		model.put("posts", posts);
		return "createPost";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String doMessagePost(final String message, Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside UserController doMessagePost()");
		
		String view = "";
		String myUsername = principal.getName();
		String errorMessage = userService.createPost(myUsername, message);
		
		if(StringUtils.isBlank(errorMessage)) {
			model.put("message", "Post created successfully");
			view = "createPost";
			
			showPostsFromFollowers(model, principal);
		} else {
			model.put("errorMessage", errorMessage);
			view = "error";
		}

		return view;
	}
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String showPostsForOneUser(final String username, Map<String, Object> model) {
		logger.trace("executing inside UserController showPostsForOneUser()");
		
		String view = "posts";
		if(! StringUtils.isBlank(username)) {
			if(userService.userExists(username)) {
				List<Post> posts = userService.getAllPostsForUsers(Arrays.asList(username));
				model.put("posts", posts);
				model.put("username", username);
			} else {
				model.put("errorMessage", "The username \"" + username + "\" does not exist.");
				view = "invalid";
			}
		}
		return view;
	}
}
