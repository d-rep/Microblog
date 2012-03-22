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

package com.repaskys.microblog.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.repaskys.microblog.domain.Post;
import com.repaskys.microblog.dto.UserPostDto;

/**
 * This is a service for interacting with user data. 
 *  
 * @author Drew Repasky
 */
public interface UserService {
	boolean userExists(String username);
	String registerUser(String username, String plainTextPassword);
	List<String> searchForUsers(String username);
	String createPost(String username, Post post);
	List<UserPostDto> getFollowersPostsForUser(String username, Date createdAfter);
	Page<Post> getAllPostsForUsers(List<String> usernames, int pageNumber);
	Page<Post> getAllFollowersPostsForUser(String username, int pageNumber);
	String addFollower(String targetUsername, String followerUsername);
	String removeFollower(String targetUsername, String followerUsername);
	List<String> getFollowingList(String username);
}
