<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="ERP.JDBC"%>
<%
	String userName=request.getParameter("userName");
	String password=request.getParameter("password");
	List list=JDBC.Query("select * from user where username='"+userName+"'and password='"+password+"'");
	if(list!=null&&list.size()==2){
		out.write(userName);
		session.setAttribute("userName",userName);
	}else{
		out.write("Error");
	}
%>
