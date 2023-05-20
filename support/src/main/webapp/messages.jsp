<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
<%@page import="model.bean.MessageBean"%>
<%@page import="model.dao.MessageDao"%>
<%@page import="java.sql.SQLException" %>
    <%	 
    if (request.getSession().getAttribute("username") == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}
    String search="";
    if (request.getParameter("search") != null)
		search = request.getParameter("search");
    
	int i=1;
	List<MessageBean>messages=null;
	int pagging = 1;
	int recordsPerPage = 10;
	int type=0;
	if (request.getParameter("page") != null)
		pagging = Integer.parseInt(request.getParameter("page"));
	if (request.getParameter("type") != null)
		type = Integer.parseInt(request.getParameter("type"));
	int noOfRecords = MessageDao.countAll(search,type);
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	try {
		messages= MessageDao.getAll(search,(pagging - 1) * recordsPerPage, recordsPerPage,type);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="css/messages.css" rel='stylesheet' type='text/css' />
<title>Messages</title>
</head>
<body>
<%@include file="navbar.jspf"%>
<div class="table-responsive">
	<h3 class="title">Messages</h3>
	<form action="messages.jsp" method="post">
	 
	 <div class="row">
	 
	 <div class="col-xs-4 col-md-4 col-sm-4 mb-3">
	 <input class="form-control" type="text"  name="search" >
	 <input class="form-control" type="hidden"  name="type"  value="<%=type %>">
	 <input class="form-control" type="hidden"  name="page"  value="<%=pagging %>">
	 </div>
	 <div class="col-xs-4 col-md-4 col-sm-4">
  				<button type="submit" class="btn btn-primary btn-block" >Search</button>
	 
  				
				</div></div>
	</form>
	       
		<table
			class="table table-bordered table-hover  table-striped caption-top">
			<thead class="table-primary">
				<tr>
					<th>#</th>
					<th>Id</th>
					<th>Timestamp</th>
					<th>User Id</th>
					<th>Username</th>
					<th>E-mail</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			 <% for(MessageBean message:messages){%>
				<tr>
					<td><%=(pagging-1)*recordsPerPage+i++ %></td>
					<td><%=message.getId() %></td>
					<td><%=message.getSentTime().toString().replaceAll("\\..*", "").replace('T',' ')%></td>
					<td><%=message.getUserId() %></td>
					<td><%=message.getUsername() %></td>
					<td><%=message.getUserMail() %></td>
					<td><a href="message.jsp?id=<%=message.getId() %>">Read</a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<nav aria-label="Page navigation">
  <ul class="pagination">
  <%if(pagging>1){ %>
    <li class="page-item"><a class="page-link" href="messages.jsp?page=<%=pagging-1 %>&type=<%=type %>&search=<%=search %>">Previous</a></li>
    	<%} %>
    <%if(pagging<noOfPages){ %>
    <li class="page-item"><a class="page-link" href="messages.jsp?page=<%=pagging+1 %>&type=<%=type %>&search=<%=search %>">Next</a></li>
    <%} %>
  </ul>
</nav>
	</div>
</body>
</html>