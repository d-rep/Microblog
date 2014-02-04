<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="en">
	<tiles:insertAttribute name="header" />
	<body>
		<section id="main" class="container">
			<h1>
				<tiles:getAsString name="title"/>
				<sec:authorize access="isAuthenticated()">
				<small><sec:authentication property="principal.username" />'s Microblog</small>
				</sec:authorize>
			</h1>
			
<tiles:insertAttribute name="nav" />

			<section id="content">
<tiles:insertAttribute name="body" />
			</section>

<tiles:insertAttribute name="footer" />

		</section>
		

	</body>
</html>