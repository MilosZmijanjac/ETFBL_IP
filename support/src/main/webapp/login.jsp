<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.LoginDao" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html">
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Login</title>
</head>
<body>
	<%
		String err = "";
		if (request.getAttribute("err") != null) {
			err = (String) request.getParameter("err");
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (request.getParameter("submit") != null) {
			Long id=LoginDao.loginUser(username, password);
			if (id>0) {
				err="";
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("id", id);
				response.sendRedirect("messages.jsp?page=1&type=2");
			} else {
				err="Incorrect username or password";
			}
		} else {
			request.getSession().invalidate();
		}
		
	%>
	<div class="login">

        <h1 class="text-center">Support Panel</h1>
        
        <form class="needs-validation" action="login.jsp" method="post">
            <div class="form-group">
                <label class="form-label" for="username">Username</label>
                <input class="form-control" type="text"  name="username" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="password">Password</label>
                <input class="form-control" type="password" name="password" required>
                <input type="hidden" name="err" value=<%=err %>>
            </div>
            <input class="btn btn-success w-100" type="submit" name="submit" value="SIGN IN">
            <% if(err.length()>0){%>
            	<label style="color: red"><%=err%></label>
            <%} %>
				
        </form>

    </div>

</body>
</html>