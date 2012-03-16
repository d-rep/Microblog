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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.repaskys.microblog.domain.FollowAction;
import com.repaskys.microblog.services.UserService;

/**
 * These are operations involving following and unfollowing users.
 *  
 * @author Drew Repasky
 */
@Controller
public class FollowController {
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String follow(Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside FollowController follow()");
		String view = "follow";
		String myUsername = principal.getName();
		List<String> following = userService.getFollowingList(myUsername);
		model.put("following", following);
		return view;
	}
	
	private String changeFollower(final String targetUsername, Map<String, Object> model, final Principal principal, final FollowAction followAction) {
		String view = "invalid";
		String action = followAction.getMessage();
		if(StringUtils.isBlank(targetUsername)) {
			model.put("errorMessage", "Please specify a username to " + action);
		} else {
			
			if(userService.userExists(targetUsername)) {
				String myUsername = principal.getName();
				
				if(myUsername.equalsIgnoreCase(targetUsername)) {
					model.put("errorMessage", "You cannot " + action + " yourself.");
					view = "invalid";
				} else {
					String message = "You have successfully " + action + "ed user " + targetUsername + ".";
					String errorMessage = "";
					
					if(followAction == FollowAction.FOLLOW) {
						userService.addFollower(targetUsername, myUsername);
					} else if(followAction == FollowAction.UNFOLLOW) {
						userService.removeFollower(targetUsername, myUsername);
					}
					
					if(StringUtils.isBlank(errorMessage)) {
						model.put("myUsername", myUsername);
						model.put("targetUsername", targetUsername);
						model.put("message", message);
						
						view = follow(model, principal);
					} else {
						model.put("errorMessage", errorMessage);
					}
				}
			} else {
				model.put("errorMessage", "The user you are trying to " + action + " (\"" + targetUsername + "\") does not exist.");
				view = "invalid";
			}
		}
		return view;
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String doFollow(final String usernameToFollow, Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside FollowController doFollow()");
		return changeFollower(usernameToFollow, model, principal, FollowAction.FOLLOW);
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public String unfollow(final String usernameToUnfollow, Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside FollowController unfollow()");
		return changeFollower(usernameToUnfollow, model, principal, FollowAction.UNFOLLOW);
	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.GET)
	public String findUser() {
		logger.trace("executing inside FollowController findUser()");
		return "user_search";
	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public String findUser(final String username, Map<String, Object> model, final Principal principal) {
		logger.trace("executing inside FollowController findUser() with parameter");
		model.put("users", userService.searchForUsers(username));
		
		// get who the user is following, so that we can show the appropriate follow/unfollow button
		String myUsername = principal.getName();
		List<String> following = userService.getFollowingList(myUsername);
		model.put("following", following);
		return "user_search_results";
	}
}
