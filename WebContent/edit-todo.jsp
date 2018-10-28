<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h1>Edit Page</h1>
			<h2>
				Welcome
				<c:out value="${sessionScope.USERNAME}" />
			</h2>
		</div>
	</div>
	<div id="container">
		<form action="EditTodoServlet" method="post">

			<input type="text" name="description" placeholder="Description" /> <input
				type="submit" value="Save" />
		</form>
		<form action="TodoListControllerServlet" method="get">
			<input type="submit" value="Back to List Page" />
		</form>
	</div>
	<form action="AccountControllerServlet" method="get">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>