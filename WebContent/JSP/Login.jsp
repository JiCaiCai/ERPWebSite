<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://localhost:8080/ERP/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Nav.css" />
<link rel="stylesheet" type="text/css" href="CSS/Login.css" />
<script type="text/javascript" src="Javascript/Ajax.js"></script>
<script type="text/javascript">
	var xmlHttp=GetXMLHttpRequest();

	function Login(){
		var div=document.getElementById("loginHint");
		if(IsEmpty()){
			div.innerHTML="请输帐号和密码";
			return;
		}
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		var content="userName="+userName+"&password="+password+"&";
		var url="login.do?";
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
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			if(xmlHttp.responseText.indexOf("Error")!=-1){
				var div=document.getElementById("loginHint");
				div.innerHTML="帐号或密码错误";
			}else{
				window.location.href="JSP/Home.jsp";
			}
		}
	}
</script>
<title>ERP-登录</title>
</head>
<body>
	<div id=userNav></div>
	<div id=logo>
		<img src="Image/logo.jpg" />
	</div>
	<div id=login>
		<form name="loginForm">
			<div id=loginInput>
				帐号：
				<input type="text" id="userName" name="userName" />
				<br />
				密码：
				<input type="password" id="password" name="password" onkeydown="if(event.keyCode==13) {Login();}" />
			</div>
			<div id=loginHint></div>
			<div id=loginButton>
				<input type="button" id="comfirm" value="登录" onclick="Login();" />
				&nbsp; <a href="Register.jsp">立即注册</a>
			</div>
		</form>
	</div>
</body>
</html>