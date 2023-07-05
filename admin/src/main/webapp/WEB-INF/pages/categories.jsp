<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
<%@page import="model.bean.CategoryBean"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="${pageContext.request.contextPath}/css/users.css" rel='stylesheet' type='text/css' />
<title>Categories</title>
</head>
<body>
<%@include file="/WEB-INF/fragments/navbar.jspf"%>
<%
	List<CategoryBean> categories = ((List<?>) request.getAttribute("categories")).stream().map(CategoryBean.class::cast).toList();
	int i=1;
	int currentPage=(Integer)request.getAttribute("currentPageCategories");
	int numOfPages=(Integer)request.getAttribute("numOfPagesCategories");
	%>
	
	<div class="table-responsive">
	<h3 class="title">List of categories</h3>
		<table
			class="table table-bordered table-hover  table-striped caption-top">
			<thead class="table-primary">
				<tr>
					<th>#</th>
					<th>Id</th>
					<th>Name</th>
					<th>Additional attributes</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			 <% for(CategoryBean cat:categories){%>
				<tr>
					<td><%=i++ %></td>
					<td><%=cat.getId() %></td>
					<td><%=cat.getName()%></td>
					<td><a href="/admin/categories/attributes?id=<%=cat.getId()%>">Attributes</a></td>
					<td>
						<a href="/admin/categories/edit?id=<%=cat.getId()%>">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="/admin/categories/delete?id=<%=cat.getId()%>">Delete</a>
					</td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
  <ul class="pagination">
  <li class="page-item"><a  class="btn btn-primary btn-block" href="/admin/categories/new">Add New Category</a></li>
  <%if(currentPage>1){ %>
    <li class="page-item"><a class="page-link" href="/admin/categories?page=${currentPage - 1}">Previous</a></li>
    	<%} %>
    <%if(currentPage<numOfPages){ %>
    <li class="page-item"><a class="page-link" href="/admin/categories?page=${currentPage + 1}">Next</a></li>
    <%} %>
  </ul>
</nav>
	</div>
</body>
</html>