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
	<form action="<%=request.getContextPath()%>/users/add" method="post">
<div class="container">
        <div>
            <h3 class="title">Enter user details</h3>
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4"> 
                <label for="fname" class="form-label">First name:</label>
                <input type="text" class="form-control" id="fname" placeholder="Enter first name" name="fname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="lname" class="form-label">Last name:</label>
                <input type="text" class="form-control" id="lname" placeholder="Enter last name" name="lname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="email" class="form-label">E-mail:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter e-mail" name="email">
                </div>
                
           </div>
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="phone" class="form-label">Phone number:</label>
                <input type="tel" class="form-control" id="phone" placeholder="Enter phone number" name="phone" pattern="^\d{9}$">
                </div>
                  <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <label for="uname" class="form-label">Username:</label>
                <input type="text" class="form-control" id="uname" placeholder="Enter username"name="uname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="pwd" class="form-label">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
                </div>
           </div>
           <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="adr" class="form-label">Address:</label>
                <input type="text" class="form-control" id="adr" placeholder="Enter address" name="adr" >
                </div>
                 <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <label for="city" class="form-label">City:</label>
                <input type="text" class="form-control" id="city" placeholder="Enter city name" name="city">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="country" class="form-label">Country:</label>
                <input type="text" class="form-control" id="country" placeholder="Enter country name" name="country">
                </div>
           </div>
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <label for="type" class="form-label">Type:</label>
                <select class="form-select" id="type" name="type">
  						<option selected>Select type...</option>
 						<option value="ADMIN">ADMIN</option>
  						<option value="WEBSHOP">WEBSHOP</option>
  						<option value="SUPPORT">SUPPORT</option>
				</select>
				</div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="status" class="form-label">Status:</label>
                <select id="status" name="status" class="form-select">
  						<option selected>Select status...</option>
 						<option value="ACTIVE">ACTIVE</option>
  						<option value="BLOCKED">BLOCKED</option>
  						<option value="PENDING">PENDING</option>
				</select>
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
  				<label for="formFile" class="form-label">Profile picture:</label>
  				<input class="form-control" type="file" id="formFile" name="file" >
  				  				
				</div>
           </div>
               <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <span></span>
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <span></span>
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-4">
                <button type="submit" class="btn btn-primary btn-block">Submit</button>
                <span></span>
                </div>
           </div>
            
        </div>
</div>
</form>
</body>
</html>