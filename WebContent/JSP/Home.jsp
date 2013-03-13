<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("Login.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERP-首页</title>
<link rel="stylesheet" type="text/css" href="CSS/Content.css" />
</head>
<base href="http://localhost:8080/ERP/">
<jsp:include page="Nav.jsp"></jsp:include>
<body>
	<div id=background>
		<img src="Image/building.jpg" />
		<img src="Image/building.jpg" />
		<img src="Image/building.jpg" />
	</div>
</body>
</html>