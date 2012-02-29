<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<form action="j_spring_security_check" method="post">
			<label for="username">Username: </label><input type="text" id="username" name="j_username"/><br/>
			<label for="password">Password: </label><input type="password" id="password" name="j_password"/><br/>
			<input type="submit" value="Sign In"><br/>
		</form>
		
		<c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
		<div class="error">
			Your login attempt was not successful, please try again.  
			<c:out value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"/>
		</div>
		</c:if>
		
		<p>
			If you don't currently have an account, you can <a href="register">register a new username</a>.
		</p>
