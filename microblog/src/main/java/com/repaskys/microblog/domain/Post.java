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

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * This represents a post from a user to their followers.
 *  
 * @author Drew Repasky
 */
@Entity
public class Post {
	
	private static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
	    .appendDays()
	    .appendSuffix("d")
	    .appendSeparator(" ")
	    .appendHours()
	    .appendSuffix("h")
	    .appendSeparator(" ")
	    .printZeroAlways()
	    .appendMinutes()
	    .appendSuffix("m")
	    .toFormatter();
	
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "username")
	private BlogUser blogUser;
	
	@Size(min=1, max = 500, message="Post {javax.validation.constraints.Size.message}")
	@Column(nullable = false, length = 500)
	private String message;
	
	@Column(nullable = false)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BlogUser getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@DateTimeFormat(style="MM")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * How long ago was this post made? This is calculated based on the post's
	 * created date, and is not saved to the database.
	 */
	@Transient
	public String getAge() {
		DateTime now = new DateTime(new Date());
		DateTime created = new DateTime(this.createdDate);
		
		Period period = new Period(created, now);
		String age = PERIOD_FORMATTER.print(period.normalizedStandard());
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
