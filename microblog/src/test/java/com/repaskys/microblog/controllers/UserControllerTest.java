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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.repaskys.microblog.domain.Post;
import com.repaskys.microblog.services.UserService;

/**
 * Unit tests for the UserController.
 * 
 * @author Drew Repasky
 */
public class UserControllerTest {
	
	private static final String USERNAME = "drew";
	private static final String PASSWORD = "password";
	
	private UserController userController;
	private Map<String, Object> model;
	
	private static final Principal getMockSecurityPrincipal() {
		Principal mockPrincipal;
		mockPrincipal = mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn(USERNAME);
		return mockPrincipal;
	}
	
	private static final Page<List<Post>> getPagesOfPosts() {
		final Post post = new Post();
		post.setMessage("some post message");
		
		List<Post> posts = new ArrayList<Post>() {{
			add(post);
		}};
		
		Page<List<Post>> pagesOfPosts = new PageImpl(posts);
		
		return pagesOfPosts;
	}

	@Before
	public void setup() {
		userController = new UserController();
		model = new HashMap<String, Object>();
	}
	
	@Test
	public void readPageNumberOne() {
		assertEquals(Integer.valueOf(1), userController.readPageNumber("1"));
	}
	
	@Test
	public void readPageNumberTwentyFive() {
		assertEquals(Integer.valueOf(25), userController.readPageNumber("25"));
	}
	
	@Test
	public void readPageNumberBlank() {
		assertEquals(Integer.valueOf(0), userController.readPageNumber(""));
	}
	
	@Test
	public void readPageNumberSpaces() {
		assertEquals(Integer.valueOf(0), userController.readPageNumber(" "));
	}
	
	@Test
	public void readPageNumberLetters() {
		assertEquals(Integer.valueOf(0), userController.readPageNumber("abc"));
	}
	
	@Test
	public void readPageNumberNegative() {
		assertEquals(Integer.valueOf(0), userController.readPageNumber("-1"));
	}
	
	@Test
	public void readPageNumberNull() {
		assertEquals(Integer.valueOf(0), userController.readPageNumber(null));
	}
	
	@Test
	public void loginView() {
		assertEquals("login", userController.login());
	}
	
	@Test
	public void registerView() {
		assertEquals("register", userController.register());
	}
	
	@Test
	public void errorView() {
		assertEquals("error", userController.error());
	}
	
	@Test
	public void errorNotFoundView() {
		assertEquals("errorNotFound", userController.errorNotFound());
	}
	
	@Test
	public void blankUsernameShouldBeInvalid() {
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("errorMessage", "What username would you like to register?");
		}};
		String view = userController.createUser("", PASSWORD, model);
		assertEquals("invalid", view);
		assertEquals(expectedModel, model);
	}
	
	@Test
	public void duplicateUsernameShouldBeInvalid() {
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("errorMessage", "Could not register the username \"" + USERNAME + "\" because it has already been taken by another user.");
		}};
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.userExists(USERNAME)).thenReturn(true);
		
		userController.setUserService(mockUserService);
		String view = userController.createUser(USERNAME, PASSWORD, model);
		assertEquals("invalid", view);
		assertEquals(expectedModel, model);
		verify(mockUserService).userExists(USERNAME);
	}
	
	@Test
	public void uniqueUsernameShouldBeValid() {
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("message", "Thank you for registering, " + USERNAME + ".  You can now login using your new account.");
		}};
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.userExists(USERNAME)).thenReturn(false);
		// no error messages returned
		when(mockUserService.registerUser(USERNAME, PASSWORD)).thenReturn("");
		
		userController.setUserService(mockUserService);
		String view = userController.createUser(USERNAME, PASSWORD, model);
		assertEquals("login", view);
		assertEquals(expectedModel, model);
		
		verify(mockUserService).userExists(USERNAME);
		verify(mockUserService).registerUser(USERNAME, PASSWORD);
	}
	
	
	@Test
	public void createUserErrorIsReported() {
		final String errorMessage = "something went wrong during the registration";
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("errorMessage", errorMessage);
		}};
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.userExists(USERNAME)).thenReturn(false);
		when(mockUserService.registerUser(USERNAME, PASSWORD)).thenReturn(errorMessage);
		
		userController.setUserService(mockUserService);
		String view = userController.createUser(USERNAME, PASSWORD, model);
		assertEquals("error", view);
		assertEquals(expectedModel, model);
		
		verify(mockUserService).userExists(USERNAME);
		verify(mockUserService).registerUser(USERNAME, PASSWORD);
	}
	
	@Test
	public void showPostsFromFollowers() {
		final Page<List<Post>> pagesOfPosts = getPagesOfPosts();
		Map<String, Object> expectedModel = new HashMap<String, Object>() {{
			put("posts", pagesOfPosts);
		}};
		
		Principal mockPrincipal = getMockSecurityPrincipal();
		
		UserService mockUserService = mock(UserService.class);
		when(mockUserService.getAllFollowersPostsForUser(USERNAME, 1)).thenReturn(pagesOfPosts);
		userController.setUserService(mockUserService);
		
		String view = userController.showPostsFromFollowers("1", model, mockPrincipal);
		
		assertEquals("createPost", view);
		assertEquals(expectedModel, model);
		
		verify(mockUserService).getAllFollowersPostsForUser(USERNAME, 1);
		verify(mockPrincipal).getName();
		
	}

}
