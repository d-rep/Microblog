<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>

        <form method="POST" action="./">
        	Enter message here:<br/>
        	<textarea name="message" rows="5" cols="45"></textarea><br/>
        	<input type="submit" value="Post"/>
        </form>
        <c:out value="${message}"/>

		<c:if test="${not empty posts}">
		<table>
			<thead>
				<th>User</th>
				<th>Created Date</th>
				<th>Message</th>
			</thead>
			<c:forEach var="post" items="${posts}">
				<tbody>
					<td><c:out value="${post.blogUser.username}"/></td>
					<td>
						<c:set var="createdDate" value="${post.createdDate}" />
						<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${createdDate}" />
					</td>
					<td><c:out value="${post.message}"/></td>
				</tbody>
			</c:forEach>
		</table>
		</c:if>
