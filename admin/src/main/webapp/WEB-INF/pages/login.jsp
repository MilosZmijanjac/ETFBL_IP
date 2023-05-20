<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			err = (String) request.getAttribute("err");
		}
	%>
	<div class="login">

        <h1 class="text-center">Admin Panel</h1>
        
        <form class="needs-validation" action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group">
                <label class="form-label" for="username">Username</label>
                <input class="form-control" type="text"  name="username" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="password">Password</label>
                <input class="form-control" type="password" name="password" required>
            </div>
            <input class="btn btn-success w-100" type="submit" value="SIGN IN">
            <% if(err.length()>0){%>
            	<label style="color: red"><%=err%></label>
            <%} %>
				
        </form>

    </div>

</body>
</html>