<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="POST" action="follow" role="form">
        	<div class="form-group">
        		<label for="usernameToFollow">Username to Follow: </label>
	        	<input type="text" name="usernameToFollow" id="usernameToFollow" class="form-control"/>
	        	<input type="submit" value="Follow" class="btn btn-default"/>
        	</div>
        </form>
        <div class="text-success"><c:out value="${message}"/></div>
        
        <h1>Currently Following</h1>
        <table id="following" class="table table-striped">
        	<thead>
        		<tr>
        			<th colspan="2">Username</th>
        		</tr>
        	</thead>
			<tbody>
				<c:forEach var="follow" items="${following}">
				<tr>
					<td><c:out value="${follow}"/></td>
					<td>
				        <form method="POST" action="unfollow" role="form">
				        	<input type="hidden" name="usernameToUnfollow" value="<c:out value="${follow}"/>"/>
				        	<input type="submit" value="Unfollow" class="btn btn-default"/>
				        </form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
