<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<form action="findUser" method="post" role="form">
			<div class="form-group">
				<label for="username">Username: </label>
				<input type="text" id="username" name="username" class="form-control"/>
			</div>
			
			<div class="help-block">Note: you can specify a "%" symbol as a wildcard.  For instance, searching on "%drew" will match "andrew" or "drew".</div>
			<input type="submit" value="Find User" class="btn btn-default">
		</form>
