<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Follow</title>
        <link rel="stylesheet" type="text/css" href="static/styles.css"></link>
    </head>
    <body>
        <h1>Follow</h1>
        <form method="POST" action="doFollow">
        	Username to Follow: <input type="username" name="username"/><br/>
        	<input type="submit" value="Follow"/>
        </form>
        
        <h1>Currently Following</h1>
        <table>
			<tbody>
				<c:forEach var="follow" items="${following}">
				<tr>
					<td><c:out value="${follow}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
    </body>
</html>