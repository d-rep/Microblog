<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: New User Created</title>
    </head>
    <body>
        <h1>Successfully Created User <c:out value="${username}"/></h1>
    </body>
</html>