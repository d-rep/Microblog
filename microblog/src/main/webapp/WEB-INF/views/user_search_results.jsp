<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

		<c:set var="myUsername">
			<sec:authentication property="principal.username" />
		</c:set>
		
		<table id="userSearchResults">
			<thead>
				<tr>
					<th colspan="2">Username</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
					<td>
						<c:out value="${user}"/>
					</td>
					<td>
						<c:choose>
						<c:when test="${user.equals(myUsername)}">
							This is you!
						</c:when>
						<c:when test="${following.contains(user)}">
					        <form method="POST" action="unfollow">
					        	<input type="hidden" name="usernameToUnfollow" value="<c:out value="${user}"/>"/>
					        	<input type="submit" value="Unfollow"/>
					        </form>
				        </c:when>
				        <c:otherwise>
					        <form method="POST" action="follow">
					        	<input type="hidden" name="usernameToFollow" value="<c:out value="${user}"/>"/>
					        	<input type="submit" value="Follow"/>
					        </form>
				        </c:otherwise>
				        </c:choose>
				    </td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
