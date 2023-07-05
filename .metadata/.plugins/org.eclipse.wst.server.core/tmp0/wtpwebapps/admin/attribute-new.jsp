<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.bean.UserBean"%>
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
 String catId=(String)request.getAttribute("selectedCat");
 %>
	<form action="<%=request.getContextPath()%>/categories/attributes/add" method="post">
<div class="column" >
                <div class="col-xs-4 col-md-4 col-sm-4"> 
                <label for="aname" class="form-label">Name:</label>
                <input type="text" class="form-control" id="aname" placeholder="Enter name" name="aname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="unit" class="form-label">Unit:</label>
                <input type="text" class="form-control" id="unit" placeholder="Enter unit" name="unit">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="value" class="form-label">Value:</label>
                <!-- <input type="text" class="form-control" id="value" placeholder="Enter value type" name="value"> -->
                <select class="form-select" aria-label="Default select example" id="value" name="value">
 					 <option selected>Select value type</option>
  					 <option value="Number">Number</option>
   					 <option value="String">String</option>
					 <option value="Select">Selection</option>
				</select>
                </div>
                 <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="pvalues" class="form-label">Possible values:</label>
                <input type="text" class="form-control" id="pvalues" placeholder="Enter possible values" name="pvalues">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-4">
                <input type="hidden" value=<%=catId %> name="id">
                <button type="submit" class="btn btn-primary btn-block" >Submit</button>
                </div>
                
           </div>
 </form>
</body>
</html>