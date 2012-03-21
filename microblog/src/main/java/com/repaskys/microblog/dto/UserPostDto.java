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

package com.repaskys.microblog.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.repaskys.microblog.domain.BlogUser;
import com.repaskys.microblog.domain.Post;

/**
 * This is a Data Transfer Object (DTO / Value Object) meant to be a
 * detached/cloned copy of the BlogUser and Post entity classes.
 * 
 * FIXME This class is necessary unless Spring's MappingJacksonJsonView can
 * serialize lazy-loaded JPA Entities.
 * 
 * @author Drew Repasky
 */
public class UserPostDto implements Serializable {

	private static final long serialVersionUID = -6979971216116537530L;
	
	private String username;
	private String message;
	private String age;
	
	/**
	 * Static factory method to create a list of UserPostDto instances from a List of Post objects.
	 */
	public static List<UserPostDto> createUserPosts(List<Post> posts) {
		List<UserPostDto> userPosts = new ArrayList<UserPostDto>();
		for(Post post : posts) {
			UserPostDto userPostDto = new UserPostDto(post);
			userPosts.add(userPostDto);
		}
		return userPosts;
	}
	
	public UserPostDto(Post post) {
		username = post.getBlogUser().getUsername();
		message = post.getMessage();
		age = post.getAge();
	}
	
	public String getUsername() {
		return username;
	}


	public String getMessage() {
		return message;
	}


	public String getAge() {
		return age;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
