<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="follow">
        	Username to Follow: <input type="text" name="usernameToFollow"/><br/>
        	<input type="submit" value="Follow"/>
        </form>
        <div class="success"><c:out value="${message}"/></div>
        
        <h1>Currently Following</h1>
        <table id="following">
        	<thead>
        		<tr>
        			<th colspan="2">Username</th>
        		</tr>
        	</thead>
			<tbody>
				<c:forEach var="follow" items="${following}" varStatus="loopStatus">
				<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
					<td><c:out value="${follow}"/></td>
					<td>
				        <form method="POST" action="unfollow">
				        	<input type="hidden" name="usernameToUnfollow" value="<c:out value="${follow}"/>"/>
				        	<input type="submit" value="Unfollow"/>
				        </form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
