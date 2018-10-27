<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
Login success 
</body>
<div id="content">
<table>
<tr>
<th>ID</th>
<th>Description</th>
</tr>
<c:forEach var="tempTodo" items="${TODO_LIST }" >
<tr>
<td> ${tempTodo.id}</td>
<td> ${tempTodo.description}</td>
</c:forEach>
</table>
</div>
</body>
</html>