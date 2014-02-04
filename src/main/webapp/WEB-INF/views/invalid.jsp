<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <strong>Please correct the following problems and retry your request...</strong>
        <p class="text-danger">
        <c:out value="${errorMessage}"/>
        </p>
