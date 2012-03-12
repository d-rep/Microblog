<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

		<c:choose>
		<c:when test="${not empty posts && not empty posts.content}">
		<table id="posts">
			<thead>
				<th>User</th>
				<th>Created Date</th>
				<th>Age</th>
				<th>Message</th>
			</thead>
			<tbody>
			<c:forEach var="post" items="${posts.content}" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
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
<jsp:include page="paging.jsp" />
		</c:when>
		<c:otherwise>
		No posts were found.
		</c:otherwise>
		</c:choose>