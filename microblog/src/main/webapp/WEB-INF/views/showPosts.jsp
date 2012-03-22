<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="GET" action="posts">
        	Username: <input type="text" id="username" name="username"/><br/>
        	<input type="submit" value="Show Posts"/>
        </form>
        
<c:set value="/posts" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
