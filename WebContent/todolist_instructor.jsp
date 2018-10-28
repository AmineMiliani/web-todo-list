<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web ToDoList</title>
<link type="text/css" rel="stylesheet" href="css/login_style.css">
</head>
<body>
	<h2>
		Welcome
		<c:out value="${sessionScope.USERNAME}" />
	</h2>
	<form action="AddTodoServlet" method="get">
		<input type="submit" value="Add Todo" />
	</form>
	<table id="tab">


		<thead>
			<tr id="tab">
				<th id="tab">Id</th>
				<th id="tab">Description</th>
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
				<tr id="tab">
					<td id="tab">${todo.getId()}</td>
					<td id="tab">${todo.getDescription()}</td>
					<td id="tab"><a href="${EditLink }"> Edit</a>|<a
						href="${DeleteLink }">Delete</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="AccountControllerServlet" method="get">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>

