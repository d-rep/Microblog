<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

		<table id="userSearchResults">
			<thead>
				<th colspan="2">Username</th>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
					<td>
						<c:out value="${user}"/>
					</td>
					<td>
				        <form method="POST" action="doFollow">
				        	<input type="hidden" name="usernameToFollow" value="<c:out value="${user}"/>"/>
				        	<input type="submit" value="Follow"/>
				        </form>
				    </td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
