<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.bean.MessageBean"%>
<%@page import="model.dao.MessageDao"%>
<%@page import="java.sql.SQLException" %>

    <%
    if (request.getSession().getAttribute("username") == null) {
 		request.getRequestDispatcher("login.jsp").forward(request, response);
 		return;
 	}
    Long id=0L;
    if (request.getParameter("id") != null)
    id = Long.parseLong(request.getParameter("id"));
    MessageBean message=null;
    try {
    	message= MessageDao.getById(id);
    	if(!message.getRead())
    	MessageDao.updateRead(message, true);
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
  <link href="css/message.css" rel='stylesheet' type='text/css' />
<title>Message</title>
</head>
<body>
<%@include file="navbar.jspf"%>
<form action="new-message.jsp" method="post">
<div class="row" >
                <div class="col-xs-4 col-md-4 col-sm-4"> 
                <label for="aname" class="form-label">Timestamp:</label>
                <input type="text" class="form-control" readonly  value="<%=message.getSentTime().toString().replaceAll("\\..*", "").replace('T',' ') %>">
                </div>
                  <div class="col-xs-4 col-md-4 col-sm-4">
  				<label for="formFile" class="form-label">E-mail:</label>
  				<input class="form-control" type="text" readonly  value="<%=message.getUserMail() %>" name="mail" >
				</div>
</div>	
<div class="row" >			
				   <div class="mt-2">
  				<label  class="form-label">Text:</label>
  				  <textarea class="form-control" id="textarea" rows="15" readonly ><%=message.getText() %></textarea>
				</div>                
           </div>
<div class="row" >  
                <div class="col-xs-4 col-md-4 col-sm-4 mt-4">
                <button type="submit" class="btn btn-primary btn-block" >Reply</button>
                </div>
          </div>
 </form>

</body>
</html>