<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<td><c:out value="${post.createdDate}"/></td>
					<td><c:out value="${post.message}"/></td>
				</tbody>
			</c:forEach>
		</table>
    </body>
</html>