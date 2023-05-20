<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
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
<link href="${pageContext.request.contextPath}/css/users.css" rel='stylesheet' type='text/css' />
<title>Attributes</title>
</head>
<body>
	<%@include file="/WEB-INF/fragments/navbar.jspf"%>
	<%
	String id=(String)request.getAttribute("selectedCategoryId");
	
	List<AttributeBean> attributes = ((List<?>) request.getAttribute("attributes")).stream().map(AttributeBean.class::cast).toList();
	int i=1;
	int currentPage=(Integer)request.getAttribute("currentPageAttributes");
	int numOfPages=(Integer)request.getAttribute("numOfPagesAttributes");
	%>
	
	<div class="table-responsive">
	<h3 class="title">Additional attributes</h3>
		<table
			class="table table-bordered table-hover  table-striped caption-top">
			<thead class="table-primary">
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Value</th>
					<th>Unit</th>
					<th>Possible Values</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			 <% for(AttributeBean atr:attributes){ %>
				<tr>
					<td><%=i++ %></td>
					<td><%=atr.getName() %></td>
					<td><%=atr.getValue()%></td>
					<td><%=atr.getUnit() %></td>
					<td><%=atr.getPossibleValues() %></td>
					<td>
						<a href="/admin/categories/attributes/edit?id=<%=atr.getCategoryId()%>&sel=<%=i-2%>">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="/admin/categories/attributes/delete?id=<%=atr.getCategoryId()%>&sel=<%=i-2%>">Delete</a>
					</td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
  <ul class="pagination">
  <li class="page-item"><a  class="btn btn-primary btn-block" href="/admin/categories/attributes/new?id=<%=id%>">Add New Attribute</a></li>
  <%if(currentPage>1){ %>
    <li class="page-item"><a class="page-link" href="/admin/categories/attributes?id=${id}&page=${currentPage - 1}">Previous</a></li>
    	<%} %>
    <%if(currentPage<numOfPages){ %>
    <li class="page-item"><a class="page-link" href="/admin/categories/attributes?id=${id}&page=${currentPage + 1}">Next</a></li>
    <%} %>
  </ul>
</nav>
</div>

</body>
</html>