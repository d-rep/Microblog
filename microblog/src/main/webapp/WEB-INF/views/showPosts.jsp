<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

        <form method="POST" action="showPosts">
        	Username: <input type="username" name="username"/><br/>
        	<input type="submit" value="Show Posts"/>
        </form>
        
		<c:if test="${not empty posts}">
		<table>
			<thead>
				<th>Created Date</th>
				<th>Age</th>
				<th>Message</th>
			</thead>
			<c:forEach var="post" items="${posts}">
				<tbody>
					<td>
						<spring:eval expression="post.createdDate" />
					</td>
					<td>
						<c:out value="${post.age}"/>
					</td>
					<td>
						<c:out value="${post.message}"/>
					</td>
				</tbody>
			</c:forEach>
		</table>
		</c:if>
