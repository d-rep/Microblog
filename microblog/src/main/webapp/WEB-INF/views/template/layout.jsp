<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<tiles:insertAttribute name="header" />
	<body>
		<div id="main">
			<h1><tiles:getAsString name="title"/></h1>
			
<tiles:insertAttribute name="nav" />

			<div id="content">
<tiles:insertAttribute name="body" />
			</div>

		</div>
<tiles:insertAttribute name="footer" />
		

	</body>
</html>