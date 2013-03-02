<%@page import="com.mysql.jdbc.RowData"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="ERP.JDBC"%>
<%
	String userName=request.getParameter("userName");
	String password=request.getParameter("password");
	List list=JDBC.Query("select * from Stock");
	response.setContentType("text/xml;charset=utf-8");
	out.clear();
	if(list!=null&&list.size()>=1){
		out.write("<?xml version='1.0' encoding='utf-8'?>");
		out.write("<table>");
		for(int i=0;i<list.size();i++){
			out.print("<row>");
			Map rowData=(Map)list.get(i);
			Iterator it=rowData.keySet().iterator();
			while(it.hasNext()){
				Object key=it.next();
				out.print("<cell>"+rowData.get(key)+"</cell>");
			}
			out.print("</row>");
		}
		out.print("</table>");
	}
%>
