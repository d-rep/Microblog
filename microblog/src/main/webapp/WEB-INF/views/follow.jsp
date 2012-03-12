<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="doFollow">
        	Username to Follow: <input type="text" name="usernameToFollow"/><br/>
        	<input type="submit" value="Follow"/>
        </form>
        
        <h1>Currently Following</h1>
        <table id="following">
        	<thead>
        		<th>Username</th>
        	</thead>
			<tbody>
				<c:forEach var="follow" items="${following}" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
					<td><c:out value="${follow}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
