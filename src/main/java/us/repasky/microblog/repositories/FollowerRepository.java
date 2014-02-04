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

package us.repasky.microblog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import us.repasky.microblog.domain.Follower;

/**
 * This class uses <a href="http://www.springsource.org/spring-data/jpa">Spring Data</a> to expose Follower entities from JPA.
 *
 * @author Drew Repasky
 */
public interface FollowerRepository extends PagingAndSortingRepository<Follower, Long> {
	@Query("select f.followerKey.target.username from Follower f where f.followerKey.follower.username = ?1 order by f.followerKey.target.username")
	List<String> findByFollowerUsername(String username);
}
