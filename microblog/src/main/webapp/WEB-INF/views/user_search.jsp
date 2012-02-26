<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<title>Microblog: Find User</title>
		<link rel="stylesheet" type="text/css" href="static/styles.css"></link>
	</head>
	<body>
		<h1>Find User</h1>
		<form action="findUser" method="post">
			<label for="username">Username: </label><input type="text" id="username" name="username"/><br/>
			<input type="submit" value="Find User"><br/>
		</form>
	</body>
</html>