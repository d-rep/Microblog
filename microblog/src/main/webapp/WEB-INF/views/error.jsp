<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <h1>Well, this is embarrassing, but something went horribly wrong...</h1>
        <p class="error">
        <c:out value="${errorMessage}"/>
        </p>
