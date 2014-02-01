<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

        <form:form method="POST" action="./" commandName="post">
        	<span>Enter message here:</span>
        	<form:textarea id="createMessage" path="message"></form:textarea>
        	<div><form:errors path="message" cssClass="error"></form:errors></div>
        	<div id="counter"></div>
        	<input type="submit" value="Post"/>
        </form:form>
        <div class="success"><c:out value="${message}"/></div>

<c:set value="/" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
