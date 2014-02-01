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

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This is necessary since we need the Follower relationship to have a composite
 * primary key of two users.
 * 
 * @author Drew Repasky
 */
@Embeddable
public class FollowerKey implements Serializable {
	
	private static final long serialVersionUID = -1039658742412245308L;

	@OneToOne
	private BlogUser target;
	
	@OneToOne
	private BlogUser follower;
	
	public BlogUser getTarget() {
		return target;
	}
	
	public void setTarget(BlogUser target) {
		this.target = target;
	}
	
	public BlogUser getFollower() {
		return follower;
	}
	
	public void setFollower(BlogUser follower) {
		this.follower = follower;
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
