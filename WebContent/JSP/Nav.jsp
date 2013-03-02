<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://localhost:8080/ERP/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Nav.css" />
<script language="javascript" src="Javascript/Login.js"></script>
</head>
<body>
	<div id=userNav>
		<ul>
			<%
				if(session.getAttribute("userName")==null){
			%>
			<li><a href="home.jsp">注册</a></li>
			<li><a href="#" onclick="Show()">登录</a></li>
			<%
				}else{
			%>
			<li><a href="logout.do">退出</a></li>
			<li><%=session.getAttribute("userName")%></li>
			<%
				}
			%>
		</ul>
	</div>
	<div id=logo>
		<img src="Image/logo.jpg" />
	</div>
	<div id=nav>
		<ul id=menu>
			<li><a href="JSP/Home.jsp">首页</a></li>
			<li><a href="#">工程管理</a>
				<ul>
					<li><a href="#">物料代码</a></li>
					<li><a href="#">低层码</a></li>
					<li><a href="#">生产BOM</a></li>
					<li><a href="#">工艺路线</a></li>
					<li><a href="#">标准工序</a></li>
					<li><a href="#">工艺路线和工序</a></li>
					<li><a href="#">制造单位</a></li>
					<li><a href="#">工作中心</a></li>
				</ul></li>
			<li><a href="JSP/Stock.jsp">库存管理</a></li>
			<li><a href="#">订单管理</a></li>
			<li><a href="#">车间管理</a>
				<ul>
					<li><a href="#">制造令明细</a></li>
					<li><a href="#">制造令工序明细</a></li>
				</ul></li>
			<li><a href="#">计划管理</a>
				<ul>
					<li><a href="#">主生产计划MPS</a></li>
					<li><a href="#">MRP明细报表</a></li>
					<li><a href="#">MRP汇总报表</a></li>
					<li><a href="#">CRP明细报表</a></li>
					<li><a href="#">CRP汇总报表</a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>