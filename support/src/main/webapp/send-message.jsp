<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.bean.MessageBean"%>
<%@page import="model.dao.MessageDao"%>
<%@page import="java.time.Instant"%>
<%@page import="util.MailUtil"%>
<%
if (request.getSession().getAttribute("username") == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
}

MessageBean message=new MessageBean();
message.setRead(true);
message.setSentTime(Instant.now());
message.setUserMail("support@webshop.ip");
message.setText(request.getParameter("text"));
message.setUserId((Long)request.getSession().getAttribute("id"));
message.setUsername((String)request.getSession().getAttribute("username"));
MessageDao.add(message);
MailUtil.send(request.getParameter("email"),message.getText());
response.sendRedirect("messages.jsp?type=3");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Sending mail...
</body>
</html>