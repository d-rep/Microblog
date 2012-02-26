<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Found Users</title>
        <link rel="stylesheet" type="text/css" href="static/styles.css"></link>
    </head>
    <body>

		<h1>User List</h1>
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

</body>
</html>