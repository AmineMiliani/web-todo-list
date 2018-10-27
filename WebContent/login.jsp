<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/login_style.css">
</head>
<body>
<div id="wrapper">
<div id="header">
<h2>Login Page</h2>
</div>
</div>
<div id="container">
<form action="AccountControllerServlet" method = "post">
<table>
<tbody>
<tr>
<td><input type="text" name = "username" placeholder = "Username"/></td>
</tr>
<tr>
<td><input type="password" name = "password" placeholder = "Password"/></td>
</tr>
<tr>
<td><input type="submit" value = "login"/></td>
</tr>
</tbody>
</table>
</form>
</div>
</body>
</html>