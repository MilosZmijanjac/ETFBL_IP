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
 <%
    UserBean user=null;
 	LocationBean location=null;
	Object o=request.getAttribute("user");
    if(o!=null){
    	user=(UserBean)o;
    	location=(LocationBean)request.getAttribute("location");
    }
	%>
	<form action="<%=request.getContextPath()%>/users/update" method="post">
<div class="container">
        <div>
            <h3 class="title">Edit user details</h3>
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4"> 
                <label for="fname" class="form-label">First name:</label>
                <input type="text" class="form-control" id="fname"   value=<%=user.getFirstName() %> name="fname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="lname" class="form-label">Last name:</label>
                <input type="text" class="form-control" id="lname"   value=<%=user.getLastName() %> name="lname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="email" class="form-label">E-mail:</label>
                <input type="email" class="form-control" id="email"   value=<%=user.getEmail()%> name="email">
                </div>
                
           </div>
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="phone" class="form-label">Phone number:</label>
                <input type="tel" class="form-control" id="phone"   value=<%=user.getPhone() %> name="phone" pattern="^\d{9}$">
                </div>
                  <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <label for="uname" class="form-label">Username:</label>
                <input type="text" class="form-control" id="uname"   value=<%=user.getUsername() %> name="uname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="pwd" class="form-label">Password:</label>
                <input type="password" class="form-control" id="pwd"   value="some lame pass" name="pwd">
                </div>
           </div>
           <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="adr" class="form-label">Address:</label>
                <input type="text" class="form-control" id="adr"   value=<%=location.getAddress() %> name="adr" >
                </div>
                 <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <label for="city" class="form-label">City:</label>
                <input type="text" class="form-control" id="city"   value=<%=location.getCity() %> name="city">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="country" class="form-label">Country:</label>
                <input type="text" class="form-control" id="country"   value=<%=location.getCountry() %> name="country">
                </div>
           </div>
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <label for="type" class="form-label">Type:</label>
                <input type="text" class="form-control" id="type"   value=<%=user.getType() %> name="type"> 
				</div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <label for="status" class="form-label">Status:</label>
                <input type="text" class="form-control" id="status"   value=<%=user.getStatus() %> name="status"> 
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
  				<label for="formFile" class="form-label">Profile picture:</label>
  				<input type="file" class="form-control" id="formFile"   value=<%=user.getAvatarPath() %> name="file"> 
				</div>
           </div>  
            <div class="row">
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2">
                <input type="hidden" value=<%=user.getId() %> name="id"> 
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-2"> 
                <input type="hidden" value=<%=user.getLocation() %> name="locid"> 
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-4">
                <button type="submit" class="btn btn-primary btn-block">Submit</button>
                <span></span>
                </div>          
        </div>
</div></div>
</form>
</body>
</html>