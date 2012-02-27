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


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.repaskys.microblog.domain.Post;
import com.repaskys.microblog.services.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		logger.trace("executing inside UserController register()");
		return "register";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestParam("username") String username, @RequestParam("password") String plainTextPassword, Model model) {
		logger.trace("executing inside UserController createUser()");
		
		String view = "error";
		
		if(userService.userExists(username)) {
			view = "invalid";
			model.addAttribute("errorMessage", "Could not register the username \"" + username + "\" because it has already been taken by another user.");
		} else {
			String errorMessage = userService.registerUser(username, plainTextPassword);
			if(StringUtils.isBlank(errorMessage)) {
				view = "user_created";
				model.addAttribute("username", username);
			} else {
				view = "error";
				model.addAttribute("errorMessage", errorMessage);
			}
		}
		
		return view;
	}
	
	@RequestMapping(value = "/createPost", method = RequestMethod.GET)
	public String createMessagePost() {
		logger.trace("executing inside UserController createMessagePost()");
		return "createPost";
	}
	
	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public String doMessagePost(@RequestParam("message") String message, Model model, HttpServletRequest request) {
		logger.trace("executing inside UserController doMessagePost()");
		
		String view = "";
		String myUsername = request.getUserPrincipal().getName(); 
		String errorMessage = userService.createPost(myUsername, message);
		
		if(StringUtils.isBlank(errorMessage)) {
			model.addAttribute("message", "Post created successfully");
			view = "createPost";
		} else {
			model.addAttribute("errorMessage", errorMessage);
			view = "error";
		}

		return view;
	}
	
	@RequestMapping(value = "/showPosts", method = RequestMethod.GET)
	public String showPost() {
		logger.trace("executing inside UserController showPost()");
		return "showPosts";
	}
	
	@RequestMapping(value = "/showPosts", method = RequestMethod.POST)
	public String showPostsForUser(@RequestParam("username") String username, Model model) {
		logger.trace("executing inside UserController showPostsForUser()");
		
		String view = "";
		if(! StringUtils.isBlank(username)) {
			if(userService.userExists(username)) {
				List<Post> posts = userService.getAllPostsForUser(username);
				model.addAttribute("posts", posts);
				view = "showPosts";
			} else {
				model.addAttribute("errorMessage", "The username \"" + username + "\" does not exist.");
				view = "invalid";
			}
		} else {
			model.addAttribute("errorMessage", "Which user would you like to get message posts for?");
			view = "invalid";
		}

		return view;
	}
}
