function Hide(){
	document.getElementById("login").style.display="none";
	document.getElementById("loginBackground").style.display="none";
}

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

function Validate(){
	var userName=document.getElementById("userName").value;
	var password=document.getElementById("password").value;
	var div=document.getElementById("loginHint");
	if(userName==""||password==""){
		div.innerHTML="请输帐号和密码";
		return;
	}
	var xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			if(xmlHttp.responseText.indexOf("Error")!=-1){
				var div=document.getElementById("loginHint");
				div.innerHTML="帐号或密码错误";
			}else{
				  document.loginForm.action ="login.do";   
				  document.loginForm.submit(); 
			}
		}
	};
	xmlHttp.open("POST","JSP/LoginActionAjax.jsp",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data="userName="+userName+"&password="+password;
	xmlHttp.send(data);
}

	function LoginAjax(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		var div=document.getElementById("loginHint");
		if(userName==""||password==""){
			div.innerHTML="请输帐号和密码";
			return;
		}
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4&&xmlHttp.status==200){
				if(xmlHttp.responseText.indexOf("Error")!=-1){
					var div=document.getElementById("loginHint");
					div.innerHTML="帐号或密码错误";
				}else{
					var div=document.getElementById("userNav");
					div.innerHTML="<ul><li><a href=\"logout.do\">退出</a></li><li>"+xmlHttp.responseText+"</li></ul>";
					Hide();
				}
			}
		};
		xmlHttp.open("POST","JSP/LoginActionAjax.jsp",true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var data="userName="+userName+"&password="+password;
		xmlHttp.send(data);
	}