<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://localhost:8080/ERP/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Nav.css" />
<link rel="stylesheet" type="text/css" href="CSS/LoginJSP.css" />
<script language="javascript" src="Javascript/Login.js"></script>
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
				帐号：<input type="text" id="userName" name="userName"/> <br />
				 密码：<input type="password" id="password" name="password"
					onkeydown="if(event.keyCode==13) {Validate();}" />
			</div>
			<div id=loginHint></div>
			<div id=loginButton>
				<input type="button" id="comfirm" value="登录" onclick="Validate();" /> &nbsp; <a href="Register.jsp">立即注册</a>
			</div>
		</form>
	</div>
</body>
</html>