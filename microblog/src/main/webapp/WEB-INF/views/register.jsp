<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Microblog: Register</title>
    </head>
    <body>
        <h1>Register New User</h1>
        <form method="POST" action="createUser">
        	Username: <input type="username" name="username"/><br/>
        	Password: <input type="password" name="password"/><br/>
        	<input type="submit" value="Register User"/>
        </form>
    </body>
</html>