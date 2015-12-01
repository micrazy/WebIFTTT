<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<% java.util.Date d=new Date(); %>
<%String s=request.getParameter("username"); %>
	<%=s %> :welcome<br/>
	Today's date is <%=d.toString() %>
</body>
</html>