<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

		<table>
			<thead>
				<th>Username</th>
			</thead>
			<c:forEach var="user" items="${users}">
				<tbody>
					<td><c:out value="${user}"/></td>
				</tbody>
			</c:forEach>
		</table>
