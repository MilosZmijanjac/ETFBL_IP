<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="${pageContext.request.contextPath}/css/users.css" rel='stylesheet' type='text/css' />
<title>Users</title>
</head>
<body>
<%@include file="/WEB-INF/fragments/navbar.jspf"%>
	<%
	List<UserBean> users = ((List<?>) request.getAttribute("users")).stream().map(UserBean.class::cast).toList();
	int i=1;
	int currentPage=(Integer)request.getAttribute("currentPageUsers");
	int numOfPages=(Integer)request.getAttribute("numOfPagesUsers");
	%>
	
	<div class="table-responsive">
	<h3 class="title">List of all users</h3>
		<table
			class="table table-bordered table-hover  table-striped caption-top">
			<thead class="table-primary">
				<tr>
					<th>#</th>
					<th>Id</th>
					<th>Username</th>
					<th>Type</th>
					<th>Status</th>
					<th>Created</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			 <% for(UserBean user:users){%>
				<tr>
					<td><%=i++ %></td>
					<td><%=user.getId() %></td>
					<td><%=user.getUsername()%></td>
					<td><%=user.getType() %></td>
					<td><%=user.getStatus() %></td>
					<td><%=user.getCreated().toString().replaceAll("\\..*", "").replace('T',' ') %></td>
				<td><a href="/admin/users/details?id=<%=user.getId() %>">Details</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href="/admin/users/edit?id=<%=user.getId() %>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
					<%if(!session.getAttribute("username").equals(user.getUsername())&&!"ADMIN".equals(user.getType())){ %>
					<a href="/admin/users/delete?id=<%=user.getId() %>">Delete</a>
					<% }%>
				</td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
  <ul class="pagination">
  <li class="page-item"><a  class="btn btn-primary btn-block" href="/admin/users/new">Add New User</a></li>
  
  <%if(currentPage>1){ %>
    <li class="page-item"><a class="page-link" href="/admin/users?page=${currentPage - 1}">Previous</a></li>
    	<%} %>
    <%if(currentPage<numOfPages){ %>
    <li class="page-item"><a class="page-link" href="/admin/users?page=${currentPage + 1}">Next</a></li>
    <%} %>
  </ul>
</nav>
	</div>
</body>
</html>