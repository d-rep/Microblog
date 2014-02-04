<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <strong>Well, this is embarrassing, but something went horribly wrong...</strong>
        
        <p class="text-danger">
        <c:out value="${errorMessage}"/>
        <c:out value="${exception.message}"/>
        </p>
        