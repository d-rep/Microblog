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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the Login page.
 *
 * @author Drew Repasky
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
			
	@RequestMapping(value = "/login")
	public String loginChallenge() {
		logger.trace("executing inside LoginController loginChallenge()");
		return "login";
	}
	
	@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
		logger.trace("executing inside LoginController verifyLogin()");
		logger.debug("username: " + username + " password: " + password);
		model.addAttribute("username", username);
		return "home";
	}
}
