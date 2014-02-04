<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="GET" action="posts" role="form">
        	<label for="username">Username: </label>
        	<input type="text" id="username" name="username" class="form-control"/>
        	<input type="submit" value="Show Posts" class="btn btn-default"/>
        </form>
        
<c:set value="/posts" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
