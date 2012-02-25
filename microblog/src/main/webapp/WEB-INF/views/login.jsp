<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <title>Microblog: Sign In</title>
    </head>
    <body>
        <h1>Sign In</h1>
        <form method="POST" action="verifyLogin">
        	User: <input type="text" name="username"/><br/>
        	Password: <input type="password" name="password"/><br/>
        	<input type="submit"/>
        </form>
    </body>
</html>