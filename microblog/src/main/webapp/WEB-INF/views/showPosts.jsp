<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>

        <form method="POST" action="showPosts">
        	Username: <input type="username" name="username"/><br/>
        	<input type="submit" value="Show Posts"/>
        </form>
        
		<c:if test="${not empty posts}">
		<table>
			<thead>
				<th>Created Date</th>
				<th>Message</th>
			</thead>
			<c:forEach var="post" items="${posts}">
				<tbody>
					<td>
						<c:set var="createdDate" value="${post.createdDate}" />
						<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${createdDate}" />
					</td>
					<td><c:out value="${post.message}"/></td>
				</tbody>
			</c:forEach>
		</table>
		</c:if>
