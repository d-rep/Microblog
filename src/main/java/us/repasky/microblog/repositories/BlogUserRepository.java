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

import org.springframework.data.repository.PagingAndSortingRepository;
import us.repasky.microblog.domain.BlogUser;

/**
 * This class uses <a href="http://www.springsource.org/spring-data/jpa">Spring Data</a> to expose BlogUser entities from JPA.
 *
 * @author Drew Repasky
 */
public interface BlogUserRepository extends PagingAndSortingRepository<BlogUser, Long> {
	BlogUser findByUsername(String username);
	List<BlogUser> findByUsernameLike(String username);
}
