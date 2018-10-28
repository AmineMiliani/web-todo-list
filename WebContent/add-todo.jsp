<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<div id="header">
<h2>Add Page</h2>
</div>
</div>
<div id="container">
<form action="AddTodoServlet" method = "post">
<table>
<tbody>
<tr>
<td><label>Description : </label> </td>
<td><input type="text" name = "description"/></td>
</tr>
<tr>
<td><input type="submit" value = "Save"/></td>
</tr>
</tbody>
</table>
</form>
</div>
	<form action="AccountControllerServlet" method="get">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>