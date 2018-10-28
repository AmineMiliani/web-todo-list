<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web ToDoList</title>
<link type="text/css" rel="stylesheet" href="css/login_style.css">
</head>
<body>
<h2>Welcome <c:out value="${sessionScope.USERNAME}"/></h2>
	<table id = "tab">
		<thead>
			<tr id = "tab">
				<th id = "tab">Id</th>
				<th id = "tab">Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="todo" items="${TODO_LIST }">
				<tr id = "tab">
					<td id = "tab">${todo.getId()}</td>
					<td id = "tab">${todo.getDescription()}</td>
			</c:forEach>
		</tbody>
	</table>
		<form action="AccountControllerServlet" method="get">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>

