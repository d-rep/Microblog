<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

        <form method="GET" action="posts">
        	Username: <input type="text" name="username"/><br/>
        	<input type="submit" value="Show Posts"/>
        </form>
        
		<c:if test="${not empty posts}">
		<div class="success">Showing Posts for user "<c:out value="${username}"/>"</div>
		<table>
			<thead>
				<th>Created Date</th>
				<th>Age</th>
				<th>Message</th>
			</thead>
			<tbody>
			<c:forEach var="post" items="${posts.content}">
				<tr>
					<td>
						<spring:eval expression="post.createdDate" />
					</td>
					<td>
						<c:out value="${post.age}"/>
					</td>
					<td>
						<c:out value="${post.message}"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
<c:set value="/posts" var="screenPath"/>
<jsp:include page="template/paging.jsp" />
		</c:if>
