<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

	<section id="register">
	        
		<form:form method="POST" action="createUser" commandName="blogUser">
			<table>
				<tr>
					<td>
						<label for="username">Username: </label>
					</td>
					<td>
						<form:input type="text" path="username"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<form:errors path="username" cssClass="error"></form:errors>
					</td>
				</tr>
				<tr>
					<td>
						<label for="password">Password: </label>
					</td>
					<td>
						<form:input type="password" path="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<form:errors path="password" cssClass="error"></form:errors>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Register User">
					</td>
				</tr>
			</table>
		</form:form>
		
	</section>