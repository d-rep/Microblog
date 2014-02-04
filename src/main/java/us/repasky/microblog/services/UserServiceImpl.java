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

package us.repasky.microblog.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import us.repasky.microblog.domain.BlogUser;
import us.repasky.microblog.domain.Follower;
import us.repasky.microblog.domain.FollowerKey;
import us.repasky.microblog.domain.Post;
import us.repasky.microblog.dto.UserPostDto;
import us.repasky.microblog.repositories.BlogUserRepository;
import us.repasky.microblog.repositories.FollowerRepository;
import us.repasky.microblog.repositories.PostRepository;

/**
 * This implementation uses the UserDetailsManager from Spring Security, as well
 * as a Repository from Spring Data JPA.
 * 
 * @author Drew Repasky
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final int POSTS_PER_PAGE = 15;

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BlogUserRepository blogUserRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FollowerRepository followerRepository;
	
	// These are the same for every user we create
	private static final boolean ENABLED = true;
	private static final boolean ACCOUNT_NOT_EXPIRED = true;
	private static final boolean CREDENTIALS_NOT_EXPIRED = true;
	private static final boolean ACCOUNT_NOT_LOCKED = true;
	private static final List<SimpleGrantedAuthority> AUTHORITIES = Arrays.asList(new SimpleGrantedAuthority("user"));
	
	/**
	 * Hash the plain text password and create a new UserDetails instance that can be persisted.
	 */
	private UserDetails initializeUser(String username, String plainTextPassword) {
		String password = passwordEncoder.encode(plainTextPassword);
		return new User(username, password, ENABLED, ACCOUNT_NOT_EXPIRED, CREDENTIALS_NOT_EXPIRED, ACCOUNT_NOT_LOCKED, AUTHORITIES);
	}
	
	public boolean userExists(String username) {
		return userDetailsManager.userExists(username);
	}
	
	/**
	 * This will try to save a new user, and returns an error message if the
	 * save failed. This does not check for duplicate usernames before saving.
	 */
	public String registerUser(String username, String plainTextPassword) {
		String errorMessage = "";
		try {
			userDetailsManager.createUser(initializeUser(username, plainTextPassword));
		} catch(DataIntegrityViolationException ex) {
			logger.error("DataIntegrityViolationException when saving user " + username + ".", ex);
			errorMessage = "Could not register user \"" + username + "\".  The user may already exist.";
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when saving user " + username + ".", ex);
			errorMessage = "Could not register user \"" + username + "\".  An unexpected problem occurred when trying to save the data.";			
		}
		return errorMessage;
	}
	
	public List<String> searchForUsers(String username) {
		List<String> usernames = new ArrayList<String>();
		List<BlogUser> blogUsers = blogUserRepository.findByUsernameLike(username);
		for(BlogUser blogUser : blogUsers) {
			usernames.add(blogUser.getUsername());
		}
		return usernames;
	}
	
	private BlogUser findBlogUserByUsername(String username) {
		BlogUser blogUser = null;
		try {
			blogUser = blogUserRepository.findByUsername(username);
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when finding user " + username + ".", ex);
		}
		return blogUser;
	}

	public String createPost(String username, Post post) {
		
		BlogUser blogUser = findBlogUserByUsername(username);
		String errorMessage = "";

		if(blogUser != null) {
			post.setBlogUser(blogUser);
			post.setCreatedDate(new Date());
			
			try {
				postRepository.save(post);
			} catch(DataIntegrityViolationException ex) {
				logger.error("DataIntegrityViolationException when saving post for user " + username + ".", ex);
				errorMessage = "Could not save post for user \"" + username + "\".";
			} catch(RuntimeException ex) {
				logger.error("RuntimeException when saving post for user " + username + ".", ex);
				errorMessage = "Could not save post for user \"" + username + "\".  An unexpected problem occurred when trying to save the data.";			
			}
		} else {
			errorMessage = "Could not save post because the user \"" + username + "\" could not be found.";
		}
		
		return errorMessage;
	}
	
	public List<UserPostDto> getFollowersPostsForUser(String username, Date createdAfter) {
		List<String> following = getFollowingList(username);
		// get posts for yourself as well
		following.add(username);
		
		List<Post> posts = null;
		try {
			posts = postRepository.findByUsernameIn(following, createdAfter);
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when getting posts for these users: " + StringUtils.join(following, ", ") + ".", ex);
		}
		return UserPostDto.createUserPosts(posts);
	}
	
	public Page<Post> getAllPostsForUsers(List<String> usernames, int pageNumber) {
		Page<Post> posts = null;
		try {
			posts = postRepository.findByUsernameIn(usernames, new PageRequest(pageNumber, POSTS_PER_PAGE));
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when getting posts for these users (paged): " + StringUtils.join(usernames, ", ") + ".", ex);
		}
		return posts;
	}
	
	public Page<Post> getAllFollowersPostsForUser(String username, int pageNumber) {
		List<String> following = getFollowingList(username);
		// get posts for yourself as well
		following.add(username);
		return getAllPostsForUsers(following, pageNumber);
	}
	
	public String addFollower(String targetUsername, String followerUsername) {
		String errorMessage = "";
		BlogUser targetBlogUser = findBlogUserByUsername(targetUsername);
		BlogUser followerBlogUser = findBlogUserByUsername(followerUsername);
		Follower follower = new Follower();
		FollowerKey followerKey = new FollowerKey();
		followerKey.setTarget(targetBlogUser);
		followerKey.setFollower(followerBlogUser);
		follower.setFollowerKey(followerKey);
		
		try {
			followerRepository.save(follower);
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when the user \"" + followerUsername + "\" tried to follow \"" + targetUsername + "\".", ex);
			errorMessage = "The user \"" + followerUsername + "\" could not follow \"" + targetUsername + "\".  An unexpected problem occurred when trying to save the data.";			
		}
		return errorMessage;
	}
	
	public String removeFollower(String targetUsername, String followerUsername) {
		String errorMessage = "";
		BlogUser targetBlogUser = findBlogUserByUsername(targetUsername);
		BlogUser followerBlogUser = findBlogUserByUsername(followerUsername);
		Follower follower = new Follower();
		FollowerKey followerKey = new FollowerKey();
		followerKey.setTarget(targetBlogUser);
		followerKey.setFollower(followerBlogUser);
		follower.setFollowerKey(followerKey);
		
		try {
			followerRepository.delete(follower);
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when the user \"" + followerUsername + "\" tried to unfollow \"" + targetUsername + "\".", ex);
			errorMessage = "The user \"" + followerUsername + "\" could not unfollow \"" + targetUsername + "\".  An unexpected problem occurred when trying to delete the data.";			
		}
		return errorMessage;
	}
	
	public List<String> getFollowingList(String username) {
		List<String> followingList = null;
		
		try {
			followingList = followerRepository.findByFollowerUsername(username);
		} catch(RuntimeException ex) {
			logger.error("RuntimeException when finding list of this users followers: " + username + ".", ex);
		}
		return followingList;
	}
}
