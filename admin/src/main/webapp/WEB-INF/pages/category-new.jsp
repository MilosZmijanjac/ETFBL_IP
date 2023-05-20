<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.bean.UserBean"%>
<%@page import="model.bean.LocationBean"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/user-details.css" rel='stylesheet' type='text/css' />
<title>User</title>
</head>
 <body>
 <%@include file="/WEB-INF/fragments/navbar.jspf"%>

	<form action="<%=request.getContextPath()%>/categories/add" method="post">
<div class="column" >
                <div class="col-xs-4 col-md-4 col-sm-4"> 
                <label for="aname" class="form-label">Name:</label>
                <input type="text" class="form-control" id="cname" placeholder="Enter name" name="cname">
                </div>
                  <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
  				<label for="formFile" class="form-label">Profile picture:</label>
  				<input class="form-control" type="file" id="formFile" name="file" >
				</div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-4">
                <button type="submit" class="btn btn-primary btn-block" >Submit</button>
                </div>
                
           </div>
 </form>
</body>
</html>