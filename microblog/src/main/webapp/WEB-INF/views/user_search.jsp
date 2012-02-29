<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<form action="findUser" method="post">
			<label for="username">Username: </label><input type="text" id="username" name="username"/><br/>
			<div class="tip">Note: you can specify a "%" symbol as a wildcard.  For instance, searching on "%drew" will match "andrew" or "drew".</div>
			<input type="submit" value="Find User"><br/>
		</form>
