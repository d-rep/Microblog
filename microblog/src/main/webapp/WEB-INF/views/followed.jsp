<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Followed</title>
        <link rel="stylesheet" type="text/css" href="static/styles.css"></link>
    </head>
    <body>
    	<h1>Following</h1>
        <p>You (<c:out value="${myUsername}"/>) are now following <c:out value="${usernameToFollow}"/></p>
    </body>
</html>