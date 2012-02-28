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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.repaskys.microblog.domain.Follower;
import com.repaskys.microblog.services.UserService;

@Controller
public class FollowController {
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String follow(Map<String, Object> model, HttpServletRequest request) {
		logger.trace("executing inside FollowController follow()");
		String view = "follow";
		String myUsername = request.getUserPrincipal().getName();
		List<String> following = userService.getFollowingList(myUsername);
		model.put("following", following);
		return view;
	}
	
	@RequestMapping(value = "/doFollow", method = RequestMethod.POST)
	public String doFollow(@RequestParam("username") String usernameToFollow, Map<String, Object> model, HttpServletRequest request) {
		logger.trace("executing inside FollowController doFollow()");
		String view = "invalid";
		if(StringUtils.isBlank(usernameToFollow)) {
			model.put("errorMessage", "Please specify a username to follow");
		} else {
			
			if(userService.userExists(usernameToFollow)) {
				String myUsername = request.getUserPrincipal().getName();
				
				if(myUsername.equalsIgnoreCase(usernameToFollow)) {
					model.put("errorMessage", "You cannot follow yourself.");
					view = "invalid";
				} else {
					String errorMessage = userService.addFollower(usernameToFollow, myUsername);
					
					if(StringUtils.isBlank(errorMessage)) {
						model.put("myUsername", myUsername);
						model.put("usernameToFollow", usernameToFollow);
						view = "followed";
					} else {
						model.put("errorMessage", errorMessage);
					}
				}
			} else {
				model.put("errorMessage", "The user you are trying to follow (\"" + usernameToFollow + "\") does not exist.");
				view = "invalid";
			}
		}
		return view;
	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.GET)
	public String findUser() {
		logger.trace("executing inside FollowController findUser()");
		return "user_search";
	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public String findUser(@RequestParam("username") String username, Map<String, Object> model) {
		logger.trace("executing inside FollowController findUser() with parameter");
		model.put("users", userService.searchForUsers(username));
		return "user_search_results";
	}
}
