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

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.repaskys.microblog.services.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserController.
 * 
 * @author Drew Repasky
 */
public class UserControllerTest {
	private UserController userController;
	private Map<String, Object> model;
	private final String password = "password";

	@Before
	public void setup() {
		userController = new UserController();
		model = new HashMap<String, Object>();
	}
	
	@Test
	public void blankUsernameShouldBeInvalid() {
		final String username = "";
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("errorMessage", "What username would you like to register?");
		}};
		String view = userController.createUser(username, password, model);
		assertEquals("invalid", view);
		assertEquals(expectedModel, model);
	}
	
	@Test
	public void duplicateUsernameShouldBeInvalid() {
		final String username = "duplicate";
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("errorMessage", "Could not register the username \"" + username + "\" because it has already been taken by another user.");
		}};
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.userExists(username)).thenReturn(true);
		
		userController.setUserService(mockUserService);
		String view = userController.createUser(username, password, model);
		assertEquals("invalid", view);
		assertEquals(expectedModel, model);
		verify(mockUserService).userExists(username);
	}
	
	@Test
	public void uniqueUsernameShouldBeValid() {
		final String username = "unique";
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("username", username);
		}};
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.userExists(username)).thenReturn(false);
		// no error messages returned
		when(mockUserService.registerUser(username, password)).thenReturn("");
		
		userController.setUserService(mockUserService);
		String view = userController.createUser(username, password, model);
		assertEquals("user_created", view);
		assertEquals(expectedModel, model);
		
		verify(mockUserService).userExists(username);
		verify(mockUserService).registerUser(username, password);
	}
	
	
	@Test
	public void createUserErrorIsReported() {
		final String username = "unique";
		final String errorMessage = "something went wrong during the registration";
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("errorMessage", errorMessage);
		}};
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.userExists(username)).thenReturn(false);
		when(mockUserService.registerUser(username, password)).thenReturn(errorMessage);
		
		userController.setUserService(mockUserService);
		String view = userController.createUser(username, password, model);
		assertEquals("error", view);
		assertEquals(expectedModel, model);
		
		verify(mockUserService).userExists(username);
		verify(mockUserService).registerUser(username, password);
	}
}
