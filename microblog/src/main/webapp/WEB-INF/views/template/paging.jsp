<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty posts && posts.totalPages > 1}">
<section id="pagingPanel">
<table>
	<tr>
		<%-- Build the "Previous" link --%>
		<c:if test="${posts.number > 0}">
			<td>
				<c:url value="${screenPath}" var="pageUrlPrevious">
					<c:param name="page" value="${posts.number - 1}"/>
					
					<c:if test="${not empty param.username}">
						<c:param name="username" value="${param.username}"/>
					</c:if>
				</c:url>
				<a href='<c:out value="${pageUrlPrevious}"/>'>&lt; Previous</a>
			</td>
		</c:if>
		
		<%-- Build a link for each page of posts --%>
		<c:forEach var="pageNumber" begin="0" end="${posts.totalPages - 1}">
			
			<td>
				<c:choose>
				
					<c:when test="${posts.number == pageNumber}">
						<div class="currentPage"><c:out value="${pageNumber}"/></div>
					</c:when>
					
					<c:otherwise>
						<c:url value="${screenPath}" var="pageUrl">
							<c:param name="page" value="${pageNumber}"/>
							
							<c:if test="${not empty param.username}">
								<c:param name="username" value="${param.username}"/>
							</c:if>
						</c:url>
						<a href='<c:out value="${pageUrl}"/>'><c:out value="${pageNumber}"/></a>
					</c:otherwise>
				
				</c:choose>
				
			</td>
		
		</c:forEach>
		
		<%-- Build the "Next" link --%>
		<c:if test="${posts.number < posts.totalPages - 1}">
			<td>
				<c:url value="${screenPath}" var="pageUrlNext">
					<c:param name="page" value="${posts.number + 1}"/>
					
					<c:if test="${not empty param.username}">
						<c:param name="username" value="${param.username}"/>
					</c:if>
				</c:url>
				<a href='<c:out value="${pageUrlNext}"/>'>Next &gt;</a>
			</td>
		</c:if>
	</tr>
</table>
</section>
</c:if>