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

package com.repaskys.microblog.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This class is used to save a user's data. I would have chose the name "User"
 * but didn't to avoid confusion because that class name is used in Spring
 * Security.
 * 
 * @author Drew Repasky
 */
@Entity
@Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class BlogUser implements Serializable {
	private static final long serialVersionUID = 7342832593953711366L;

	@Id
	@Size(min = 1, max=20, message="Username {javax.validation.constraints.Size.message}")
	@Pattern(regexp = "[a-zA-Z]*", message="Username must be all letters")
	private String username;
	
	// the max size is for the encrypted password, which is always 80 characters
	@Size(min = 1, max=80, message="Password {javax.validation.constraints.Size.message}")
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@OneToMany
	@JoinColumn(name="username")
	private List<Post> posts;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
