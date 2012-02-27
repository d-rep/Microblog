<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Show Posts</title>
        <link rel="stylesheet" type="text/css" href="static/styles.css"></link>
    </head>
    <body>
        <h1>Show Posts</h1>
        <form method="POST" action="showPosts">
        	Username: <input type="username" name="username"/><br/>
        	<input type="submit" value="Show Posts"/>
        </form>
        
		<h1>Posts</h1>
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
    </body>
</html>