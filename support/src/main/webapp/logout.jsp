<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		request.getSession().invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
		%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>
Logging out...
</body>
</html>