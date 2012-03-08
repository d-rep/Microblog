<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

		<table style="width: 200px;">
			<thead>
				<th colspan="2">Username</th>
			</thead>
			<c:forEach var="user" items="${users}">
				<tbody>
					<td style="vertical-align: bottom;">
						<c:out value="${user}"/>
					</td>
					<td>
				        <form method="POST" action="doFollow">
				        	<input type="hidden" name="usernameToFollow" value="<c:out value="${user}"/>"/><br/>
				        	<input type="submit" value="Follow"/>
				        </form>
				    </td>
				</tbody>
			</c:forEach>
		</table>
