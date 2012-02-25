<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Changes Required</title>
    </head>
    <body>
        <h1>Please correct the following problems and retry your request...</h1>
        <c:out value="${errorMessage}"/>
    </body>
</html>