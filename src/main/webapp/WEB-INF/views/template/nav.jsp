<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
		<sec:authorize access="isAuthenticated()">
		<ul class="nav nav-tabs">
			<li id="navCreatePost"><a href="./">Create Post</a></li>
			<li id="navFindUser"><a href="findUser">Find a User</a></li>
			<li id="navPosts"><a href="posts">Show Posts</a></li>
			<li id="navFollow"><a href="follow">Follow a User</a></li>
			<li><a href="j_spring_security_logout">Log Out</a></li>
		</ul>
		</sec:authorize>