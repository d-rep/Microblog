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

import com.repaskys.microblog.services.UserService;

@Controller
public class FollowController {
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String follow() {
		logger.trace("executing inside FollowController follow()");
		return "follow";
	}
	
	@RequestMapping(value = "/doFollow", method = RequestMethod.POST)
	public String doFollow(@RequestParam("username") String usernameToFollow, Model model, HttpServletRequest request) {
		logger.trace("executing inside FollowController doFollow()");
		String view = "invalid";
		if(StringUtils.isBlank(usernameToFollow)) {
			model.addAttribute("errorMessage", "Please specify a username to follow");
		} else {
			
			if(userService.userExists(usernameToFollow)) {
				String myUsername = request.getUserPrincipal().getName();
				
				if(myUsername.equalsIgnoreCase(usernameToFollow)) {
					model.addAttribute("errorMessage", "You cannot follow yourself.");
					view = "invalid";
				} else {
					model.addAttribute("myUsername", myUsername);
					model.addAttribute("usernameToFollow", usernameToFollow);
					view = "followed";
				}
			} else {
				model.addAttribute("errorMessage", "The user you are trying to follow (\"" + usernameToFollow + "\") does not exist.");
				view = "invalid";
			}
		}
		return view;
	}
}
