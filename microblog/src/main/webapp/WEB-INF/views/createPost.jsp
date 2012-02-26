<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Create Post</title>
        <link rel="stylesheet" type="text/css" href="static/styles.css"></link>
    </head>
    <body>
        <h1>Follow</h1>
        <form method="POST" action="createPost">
        	Enter message here:<br/>
        	<textarea name="message"></textarea><br/>
        	<input type="submit" value="Post"/>
        </form>
        <c:out value="${message}"/>
    </body>
</html>