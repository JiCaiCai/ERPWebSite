<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://localhost:8080/ERP/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Nav.css" />
<link rel="stylesheet" type="text/css" href="CSS/LoginHtml.css" />
<script type="text/javascript" src="Javascript/Ajax.js"></script>
<script type="text/javascript">
	function Show(){
		var range=getRange();
		var elem=document.getElementById("loginBackground");
		elem.style.width=range.width+"px";
		elem.style.height=range.height+"px";
		elem.style.display="block";
		document.getElementById("login").style.display="block";
	}

	function getRange(){
		var height=document.body.clientHeight;
		var width=document.body.clientWidth;
		return {
			width:width,
			height:height
		};
	}
	
	var xmlHttp=GetXMLHttpRequest();

	function Login(){
		var div=document.getElementById("loginHint");
		if(IsEmpty()){
			div.innerHTML="请输帐号和密码";
			return;
		}
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		var content="userName="+userName+"&password="+password;
		var url="ajax.do?method=Login";
		SendRequest(xmlHttp,"POST",url,content,"text",LoginComplete);
	}

	function IsEmpty(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		if(userName==""||password=="")
			return true;
		else
			return false;
	}

	function LoginComplete(){
		if(xmlHttp.readyState==4 && xmlHttp.status==200){
			if(xmlHttp.responseText.indexOf("Error")!=-1){
				alert("A"+xmlHttp.responseText+"A");
				var div=document.getElementById("loginHint");
				div.innerHTML="帐号或密码错误";
			}else{
				var div=document.getElementById("userNav");
				div.innerHTML="<ul><li><a href=\"logout.do\">退出</a></li><li>"+xmlHttp.responseText+"</li></ul>";
				Hide();
			}
		}
	}

	function Hide(){
		document.getElementById("login").style.display="none";
		document.getElementById("loginBackground").style.display="none";
	}
</script>
</head>
<body>
	<div id=loginBackground></div>
	<div id=login>
		<div id=loginTitle>登录</div>
		<div id=loginInput>
			帐号：
			<input type="text" id="userName" />
			<br />
			密码：
			<input type="password" id="password" onkeydown="if(event.keyCode==13) {Login();}" />
		</div>
		<div id=loginHint></div>
		<div id=loginButton>
			<input type="button" id="comfirm" value="登录" onclick="Login();" />
			&nbsp;
			<input type="button" id="cancel" value="取消" onclick="Hide()" />
		</div>
	</div>
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