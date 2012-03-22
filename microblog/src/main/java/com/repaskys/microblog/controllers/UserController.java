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
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repaskys.microblog.domain.BlogUser;
import com.repaskys.microblog.domain.Post;
import com.repaskys.microblog.dto.UserPostDto;
import com.repaskys.microblog.services.UserService;

/**
 * These are operations involving users, such as logging in, registering, and
 * showing/creating posts.
 * 
 * @author Drew Repasky
 */
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Parse an integer from a String. Returns zero when it's not an integer and
	 * when it's less than zero.
	 */
	protected Integer readPageNumber(final String page) {
		Integer pageNumber = 0;
		try {
			pageNumber = Integer.valueOf(page);
		} catch(NumberFormatException ex) {
			// it's safe to eat this exception, since we correct for non-integers
			logger.info("Page number input was invalid: " + page);
		}
		return (pageNumber >= 0) ? pageNumber : 0;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.trace("executing inside UserController login()");
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Map<String, Object> model) {
		logger.trace("executing inside UserController register()");
		if(! model.containsKey("blogUser")) {
			model.put("blogUser", new BlogUser());
		}
		return "register";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		logger.trace("executing inside UserController error()");
		return "error";
	}
	
	@RequestMapping(value = "/errorNotFound", method = RequestMethod.GET)
	public String errorNotFound() {
		logger.trace("executing inside UserController errorNotFound()");
		return "errorNotFound";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@Valid BlogUser blogUser, BindingResult result, Map<String, Object> model) {
		logger.trace("executing inside UserController createUser()");
		
		String view = "";
		
		if(! result.hasErrors()) {
			String username = blogUser.getUsername();
			String password = blogUser.getPassword();
			if(userService.userExists(username)) {
				view = "invalid";
				model.put("errorMessage", "Could not register the username \"" + username + "\" because it has already been taken by another user.");
			} else {
				String errorMessage = userService.registerUser(username, password);
				if(StringUtils.isBlank(errorMessage)) {
					view = "login";
					model.put("message", "Thank you for registering, " + username + ".  You can now login using your new account.");
				} else {
					view = "error";
					model.put("errorMessage", errorMessage);
				}
			}
		} else {
			logger.debug("Registering new BlogUser failed validation.");
			model.put("blogUser", blogUser);
			view = register(model);
		}
		return view;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showPostsFromFollowers(@RequestParam(defaultValue = "0") final String page, Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside UserController showPostsFromFollowers()");
		String myUsername = principal.getName();
		Page<Post> posts = userService.getAllFollowersPostsForUser(myUsername, readPageNumber(page));
		model.put("posts", posts);
		
		if(! model.containsKey("post")) {
			model.put("post", new Post());
		}
		return "createPost";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String doMessagePost(@Valid Post post, BindingResult result, @RequestParam(defaultValue = "0") final String page, Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside UserController doMessagePost()");
		
		String view = "";
		String myUsername = principal.getName();
		
		if (! result.hasErrors()) {
			String errorMessage = userService.createPost(myUsername, post);
			
			if(StringUtils.isBlank(errorMessage)) {
				model.put("message", "Post created successfully");
				view = "createPost";
			} else {
				model.put("errorMessage", errorMessage);
				view = "error";
			}
		} else {
			logger.debug("Posted message failed validation.");
			model.put("post", post);
			view = "createPost";
		}

		showPostsFromFollowers(page, model, principal);
		return view;
	}
	
	/**
	 * Return all the follower's posts for the logged-in user, in JSON format.
	 * 
	 * FIXME: This view is not currently used anywhere, and will be called via AJAX.
	 */
	@RequestMapping(value = "/livePosts", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<UserPostDto> getPosts(final Date createdAfter, final Principal principal) {
		logger.trace("executing inside UserController getPosts()");
		return userService.getFollowersPostsForUser(principal.getName(), createdAfter);
	}
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String showPostsForOneUser(final String username, @RequestParam(defaultValue = "0") final String page, Map<String, Object> model) {
		logger.trace("executing inside UserController showPostsForOneUser()");
		
		String view = "posts";
		if(! StringUtils.isBlank(username)) {
			if(userService.userExists(username)) {
				Page<Post> posts = userService.getAllPostsForUsers(Arrays.asList(username), readPageNumber(page));
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
