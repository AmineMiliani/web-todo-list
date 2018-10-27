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
				<tr>
					<td>${list.getId()}</td>
					<td>${list.getDescription()}</td>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

