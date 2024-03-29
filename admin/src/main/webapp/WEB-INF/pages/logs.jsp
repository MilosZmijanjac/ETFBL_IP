<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.LogBean"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="css/logs.css" rel='stylesheet' type='text/css' />
<title>Logs</title>
</head>
<body>
	<%@include file="/WEB-INF/fragments/navbar.jspf"%>
	<%
	List<LogBean> logs = ((List<?>) request.getAttribute("logs")).stream().map(LogBean.class::cast).toList();
	int i=1;
	int currentPage=(Integer)request.getAttribute("currentPageLogs");
	int numOfPages=(Integer)request.getAttribute("numOfPagesLogs");
	%>
	
	<div class="table-responsive">
	<h3 class="title">Logs from the webshop backend app</h3>
		<table
			class="table table-bordered table-hover  table-striped caption-top">
			<thead class="table-primary">
				<tr>
					<th>#</th>
					<th>Id</th>
					<th>Timestamp</th>
					<th>Type</th>
					<th>Path</th>
					<th>Message</th>
				</tr>
			</thead>
			<tbody>
			 <% for(LogBean log:logs){%>
				<tr>
					<td><%=i++ %></td>
					<td><%=log.getId() %></td>
					<td><%=log.getTimestamp()%></td>
					<td><%=log.getType() %></td>
					<td><%=log.getPath() %></td>
					<td><%=log.getMessage() %></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<nav aria-label="Page navigation example">
  <ul class="pagination">
  <%if(currentPage>1){ 
   
    %>
    <li class="page-item"><a class="page-link" href="/admin/logs?page=<%= currentPage-1%>">Previous</a></li>
    	<%} %>
    <%if(currentPage<numOfPages){ 
    	
    	%>
    <li class="page-item"><a class="page-link" href="/admin/logs?page=<%= currentPage+1%>">Next</a></li>
    <%} %>
  </ul>
</nav>
	</div>
</body>
</html>