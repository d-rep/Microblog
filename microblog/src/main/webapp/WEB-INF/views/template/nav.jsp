<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<div id="nav">
			<c:if test="${not empty username}">
			<strong><c:out value="${username}"/>'s Microblog</strong><br/>
			</c:if>
			<a href="./">Create Post</a><br/>
			<a href="findUser">Find a User</a><br/>
			<a href="showPosts">Show Posts</a><br/>
			<a href="follow">Follow a User</a><br/>
			<a href="j_spring_security_logout">Log Out</a><br/>
		</div>