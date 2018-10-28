<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web ToDoList</title>
</head>
<body>
	<table class="blueTable">
		<thead>
			<tr>
				<th>Id</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${TODO_LIST }">
				<c:url var="EditLink" value="EditTodo">
				</c:url>
				<c:url var="DeleteLink" value="DeleteTodo">
				</c:url>
				<tr>
					<td>${list.getId()}</td>
					<td>${list.getDescription()}</td>
					<td> ${tempStudent.email}</td>
					<td> <a href="${EditLink }"> Edit</a>|<a href="${DeleteLink }">Delete</a></td>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

