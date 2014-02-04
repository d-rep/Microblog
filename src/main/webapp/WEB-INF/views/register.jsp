<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

	<section id="register">
	        
		<form:form method="POST" action="createUser" commandName="blogUser" role="form">
		
			<spring:bind path="username">
			
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="username">Username: </label>
					<form:input type="text" path="username" maxlength="20" cssClass="form-control"/>
					<form:errors path="username" cssClass="text-danger"></form:errors>
				</div>
				
			</spring:bind>
			
			<spring:bind path="password">
			
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="password">Password: </label>
					<form:input type="password" path="password" maxlength="80" cssClass="form-control" />
					<form:errors path="password" cssClass="text-danger"></form:errors>
				</div>
				
			</spring:bind>
			
			
			<input type="submit" value="Register User" class="btn btn-default">
		</form:form>
		
	</section>