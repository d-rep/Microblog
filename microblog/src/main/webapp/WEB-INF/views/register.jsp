<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="createUser">
        	Username: <input type="username" name="username"/><br/>
        	Password: <input type="password" name="password"/><br/>
        	<input type="submit" value="Register User"/>
        </form>