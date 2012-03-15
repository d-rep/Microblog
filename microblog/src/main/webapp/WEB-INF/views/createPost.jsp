<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="./">
        	<span>Enter message here:</span>
        	<textarea id="createMessage" name="message"></textarea>
        	<div id="counter"></div>
        	<input type="submit" value="Post"/>
        </form>
        <div class="success"><c:out value="${message}"/></div>

<c:set value="/" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
