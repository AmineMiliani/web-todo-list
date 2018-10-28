<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web ToDoList</title>
</head>
<body>
	<h1>
		Welcome
		<c:out value="${sessionScope.USERNAME}" />
	</h1>
	<table>
		<form action="AddTodoServlet" method="get">
			<input type="submit" value="Add Todo" />
		</form>
		<thead>
			<tr>
				<th>Id</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="todo" items="${TODO_LIST }">
				<c:url var="EditLink" value="EditTodoServlet">
					<c:param name="todoId" value="${todo.getId()}" />
				</c:url>
				<c:url var="DeleteLink" value="DeleteTodoServlet">
					<c:param name="todoId" value="${todo.getId()}" />
				</c:url>
				<tr>
					<td>${todo.getId()}</td>
					<td>${todo.getDescription()}</td>
					<td><a href="${EditLink }"> Edit</a>|<a href="${DeleteLink }">Delete</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="AccountControllerServlet" method="get">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>

