<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Epic Fail</title>
        <link rel="stylesheet" type="text/css" href="static/styles.css"></link>
    </head>
    <body>
        <h1>Well, this is embarrassing, but something went horribly wrong...</h1>
        <p class="error">
        <c:out value="${errorMessage}"/>
        </p>
    </body>
</html>