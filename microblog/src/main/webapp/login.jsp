<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<title>Microblog: Sign In</title>
		<link rel="stylesheet" type="text/css" href="static/styles.css"></link>
	</head>
	<body>
		<h1>Sign In</h1>
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
	</body>
</html>