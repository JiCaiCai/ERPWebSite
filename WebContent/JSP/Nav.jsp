<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("Login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://localhost:8080/ERP/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Nav.css" />
<link rel="stylesheet" type="text/css" href="CSS/LoginHtml.css" />
<script type="text/javascript" src="Javascript/Ajax.js"></script>
<script type="text/javascript">
	function SwitchMenu(emid){
		var obj=document.getElementById(emid);
		obj.className=(obj.className.toLowerCase()=="expanded"?"collapsed":"expanded");
	}
</script>
</head>
<body>
	<div id=userNav>
		<ul>
			<li><a href="logout.do">退出</a></li>
			<li><%=session.getAttribute("userName")%></li>
		</ul>
	</div>
	<div id=logo>
		<img src="Image/logo.jpg" />
	</div>
	<div id=nav>
		<ul id=menu>
			<li><a href="JSP/Home.jsp">首页</a></li>
			<li><a href="javascript:void(0)" onClick="SwitchMenu('subMenu1')">工程管理</a>
				<ul id="subMenu1" class="collapsed">
					<li><a href="JSP/Engineering/Material.jsp">物料代码</a></li>
					<li><a href="JSP/Engineering/Level.jsp">低层码</a></li>
					<li><a href="JSP/Engineering/BOM.jsp">生产BOM</a></li>
					<li><a href="JSP/Engineering/Route.jsp">工艺路线</a></li>
					<li><a href="JSP/Engineering/Process.jsp">标准工序</a></li>
					<li><a href="JSP/Engineering/RouteAndProcess.jsp">工艺路线和工序</a></li>
					<li><a href="JSP/Engineering/ManufacturingUnit.jsp">制造单位</a></li>
					<li><a href="JSP/Engineering/WorkCenter.jsp">工作中心</a></li>
				</ul></li>
			<li><a href="JSP/Stock.jsp">库存管理</a></li>
			<li><a href="javascript:void(0)">订单管理</a></li>
			<li><a href="javascript:void(0)" onClick="SwitchMenu('subMenu2')">车间管理</a>
				<ul id="subMenu2" class="collapsed">
					<li><a href="#">制造令明细</a></li>
					<li><a href="#">制造令工序明细</a></li>
				</ul></li>
			<li><a href="javascript:void(0)" onClick="SwitchMenu('subMenu3')">计划管理</a>
				<ul id="subMenu3" class="collapsed">
					<li><a href="javascript:void(0)">主生产计划MPS</a></li>
					<li><a href="javascript:void(0)">MRP明细报表</a></li>
					<li><a href="javascript:void(0)">MRP汇总报表</a></li>
					<li><a href="javascript:void(0)">CRP明细报表</a></li>
					<li><a href="javascript:void(0)">CRP汇总报表</a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>