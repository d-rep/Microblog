<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
	<tiles:insertAttribute name="header" />
	<body>
		<section id="main">
			<h1><tiles:getAsString name="title"/></h1>
			
<tiles:insertAttribute name="nav" />

			<section id="content">
<tiles:insertAttribute name="body" />
			</section>

		</section>
<tiles:insertAttribute name="footer" />
		

	</body>
</html>