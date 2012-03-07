<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

        <form method="POST" action="./">
        	Enter message here:<br/>
        	<textarea name="message" rows="5" cols="45"></textarea><br/>
        	<input type="submit" value="Post"/>
        </form>
        <c:out value="${message}"/>

		<c:choose>
		<c:when test="${not empty posts}">
		<table>
			<thead>
				<th>User</th>
				<th>Created Date</th>
				<th>Age</th>
				<th>Message</th>
			</thead>
			<c:forEach var="post" items="${posts}">
				<tbody>
					<td><c:out value="${post.blogUser.username}"/></td>
					<td>
						<spring:eval expression="post.createdDate" />
					</td>
					<td>
						<c:out value="${post.age}"/>
					</td>
					<td><c:out value="${post.message}"/></td>
				</tbody>
			</c:forEach>
		</table>
		</c:when>
		<c:otherwise>
		There aren't any messages from your followers!  Why not <a href="findUser">find and add a few</a>?
		</c:otherwise>
		</c:choose>
