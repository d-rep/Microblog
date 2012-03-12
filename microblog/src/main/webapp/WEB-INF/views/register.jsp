<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

	<section id="register">
	        
		<form method="POST" action="createUser">
			<table>
				<tr>
					<td>
						<label for="username">Username: </label>
					</td>
					<td>
						<input type="text" name="username"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="password">Password: </label>
					</td>
					<td>
						<input type="password" name="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Register User">
					</td>
				</tr>
			</table>
		</form>
		
	</section>