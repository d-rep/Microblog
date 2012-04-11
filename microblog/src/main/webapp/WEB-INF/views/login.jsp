<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<section id="login">
	
		<c:if test="${not empty message}">
		<div class="success">
			<c:out value="${message}"/>
		</div>
		</c:if>
		
		<form action="j_spring_security_check" method="post">
			<table>
				<tr>
					<td>
						<label for="username">Username: </label>
					</td>
					<td>
						<input type="text" id="username" name="j_username"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="password">Password: </label>
					</td>
					<td>
						<input type="password" id="password" name="j_password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Sign In">
					</td>
				</tr>
			</table>
		</form>
		
		<c:if test="${empty message && not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
		<div class="error">
			Your login attempt was not successful, please try again.  
			<c:out value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"/>
		</div>
		</c:if>
		
		<p>
			If you don't currently have an account, you can <a href="register">register a new username</a>.
		</p>
		
	</section>
