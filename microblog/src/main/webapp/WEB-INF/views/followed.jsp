<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <p>You (<c:out value="${myUsername}"/>) are now following <c:out value="${usernameToFollow}"/></p>
