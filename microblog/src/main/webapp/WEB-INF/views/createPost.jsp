<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

        <form method="POST" action="./">
        	Enter message here:<br/>
        	<textarea name="message" rows="5" cols="45"></textarea><br/>
        	<input type="submit" value="Post"/>
        </form>
        <div class="success"><c:out value="${message}"/></div>

		<c:choose>
		<c:when test="${not empty posts && not empty posts.content}">
		<table>
			<thead>
				<th>User</th>
				<th>Created Date</th>
				<th>Age</th>
				<th>Message</th>
			</thead>
			<tbody>
			<c:forEach var="post" items="${posts.content}">
				<tr>
					<td><c:out value="${post.blogUser.username}"/></td>
					<td>
						<spring:eval expression="post.createdDate" />
					</td>
					<td>
						<c:out value="${post.age}"/>
					</td>
					<td><c:out value="${post.message}"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
<c:set value="/" var="screenPath"/>
<jsp:include page="template/paging.jsp" />
		</c:when>
		<c:otherwise>
		There aren't any messages from your followers!  Why not <a href="findUser">find and add a few</a>?
		</c:otherwise>
		</c:choose>
