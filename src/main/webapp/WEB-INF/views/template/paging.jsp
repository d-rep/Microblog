<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty posts && posts.totalPages > 1}">
<section id="pagingPanel">
		<%-- Build the "Previous" link --%>
		<c:if test="${posts.number > 0}">
				<c:url value="${screenPath}" var="pageUrlPrevious">
					<c:param name="page" value="${posts.number - 1}"/>
					
					<c:if test="${not empty param.username}">
						<c:param name="username" value="${param.username}"/>
					</c:if>
				</c:url>
				<a href='<c:out value="${pageUrlPrevious}"/>' role="button" class="btn btn-primary">&lt; Previous</a>
		</c:if>
		
		<%-- Build a link for each page of posts --%>
		<c:forEach var="pageNumber" begin="0" end="${posts.totalPages - 1}">
			
				<c:choose>
				
					<c:when test="${posts.number == pageNumber}">
						<span class="currentPage"><button role="button" class="btn" disabled="disabled"><c:out value="${pageNumber}"/></button></span>
					</c:when>
					
					<c:otherwise>
						<c:url value="${screenPath}" var="pageUrl">
							<c:param name="page" value="${pageNumber}"/>
							
							<c:if test="${not empty param.username}">
								<c:param name="username" value="${param.username}"/>
							</c:if>
						</c:url>
						<a href='<c:out value="${pageUrl}"/>' role="button" class="btn btn-primary"><c:out value="${pageNumber}"/></a>
					</c:otherwise>
				
				</c:choose>
				
		</c:forEach>
		
		<%-- Build the "Next" link --%>
		<c:if test="${posts.number < posts.totalPages - 1}">
				<c:url value="${screenPath}" var="pageUrlNext">
					<c:param name="page" value="${posts.number + 1}"/>
					
					<c:if test="${not empty param.username}">
						<c:param name="username" value="${param.username}"/>
					</c:if>
				</c:url>
				<a href='<c:out value="${pageUrlNext}"/>' role="button" class="btn btn-primary">Next &gt;</a>
		</c:if>
</section>
</c:if>