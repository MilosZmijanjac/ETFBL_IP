<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.bean.AttributeBean"%>
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
 
 AttributeBean attribute=(AttributeBean)request.getAttribute("selectedAttr");
 %>
	<form action="<%=request.getContextPath()%>/categories/attributes/update" method="post">
<div class="column" >
                <div class="col-xs-4 col-md-4 col-sm-4"> 
                <label for="aname" class="form-label">Name:</label>
                <input type="text" class="form-control" id="aname" value="<%=attribute.getName() %>" name="aname">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="unit" class="form-label">Unit:</label>
                <input type="text" class="form-control" id="unit" value="<%=attribute.getUnit() %>" name="unit">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="value" class="form-label">Value:</label>
                <input type="text" class="form-control" id="value" value="<%=attribute.getValue() %>" name="value">
                </div>
                 <div class="col-xs-4 col-md-4 col-sm-4">
                <label for="pvalues" class="form-label">Possible values:</label>
                <input type="text" class="form-control" id="pvalues" value="<%=attribute.getPossibleValues() %>" name="pvalues">
                </div>
                <div class="col-xs-4 col-md-4 col-sm-4 mt-4">
                <input type="hidden" value="<%=attribute.getCategoryId()%>" name="id">
                <input type="hidden" value="<%=(Integer)request.getAttribute("selectedId")%>" name="sel">
                <button type="submit" class="btn btn-primary btn-block" >Submit</button>
                </div>
                
           </div>
 </form>
</body>
</html>