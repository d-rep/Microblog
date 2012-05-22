<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

		<c:choose>
		<c:when test="${not empty posts && not empty posts.content}">
		<input type="hidden" id="lastUpdate"/>
		<table id="posts">
			<tbody>
			<c:forEach var="post" items="${posts.content}">
				<tr>
					<td class="postCell" title="posted on <spring:eval expression="post.createdDate" />">
						<div class="postHeader">
							<span class="username"><c:out value="${post.blogUser.username}"/>:</span>
							<span class="age"><c:out value="${post.age}"/></span>
						</div>
						<div class="message"><c:out value="${post.message}"/></div>
						<div class="retrievalDate"><c:out value="${post.retrievalDate.time}"/></div>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
<jsp:include page="paging.jsp" />
		</c:when>
		<c:otherwise>
		No posts were found.
		</c:otherwise>
		</c:choose>