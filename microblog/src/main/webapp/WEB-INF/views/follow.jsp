<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="doFollow">
        	Username to Follow: <input type="username" name="username"/><br/>
        	<input type="submit" value="Follow"/>
        </form>
        
        <h1>Currently Following</h1>
        <table>
			<tbody>
				<c:forEach var="follow" items="${following}">
				<tr>
					<td><c:out value="${follow}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
