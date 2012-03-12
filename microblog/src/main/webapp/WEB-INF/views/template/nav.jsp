<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
		<sec:authorize access="isAuthenticated()">
		<nav>
			<strong><sec:authentication property="principal.username" />'s Microblog</strong><br/>
			<a href="./">Create Post</a><br/>
			<a href="findUser">Find a User</a><br/>
			<a href="posts">Show Posts</a><br/>
			<a href="follow">Follow a User</a><br/>
			<a href="j_spring_security_logout">Log Out</a><br/>
		</nav>
		</sec:authorize>