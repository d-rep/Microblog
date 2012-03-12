<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="./">
        	Enter message here:<br/>
        	<textarea name="message" rows="5" cols="45"></textarea><br/>
        	<input type="submit" value="Post"/>
        </form>
        <div class="success"><c:out value="${message}"/></div>

<c:set value="/" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
