<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="ERP.JDBC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if(session.getAttribute("userName")==null){
		response.sendRedirect("Login.jsp");
	}
%>
<html>
<head>
<base href="http://localhost:8080/ERP/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERP-库存管理</title>
</head>
<jsp:include page="Nav.jsp"></jsp:include>
<jsp:include page="../Html/Login.html"></jsp:include>
<link rel="stylesheet" type="text/css" href="CSS/Content.css" />
<script type="text/javascript">

	function popup(){
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4&&xmlHttp.status==200){
				var table=document.getElementById("resultTable");
				var tr=document.getElementsByTagName("tr");
				for( var i=0;i<tr.length;){
					table.deleteRow(i);
				}
				var xmlDoc=xmlHttp.responseXML.documentElement;
				var rows=xmlDoc.getElementsByTagName("row");
				for(var i=0;i<rows.length;i++){
					var row=table.insertRow(-1);
					if(i%2==0)
						row.className="even";
					else
						row.className="odd";
					var cells=rows[i].getElementsByTagName("cell");
					for(var j=0;j<cells.length;j++){
						var cell=row.insertCell(-1);
						cell.innerHTML="<input class=\"cell\" type=\"text\" value="+cells[j].childNodes[0].nodeValue+">";
					}
				}
			}
		};
		xmlHttp.open("GET","JSP/StockAjax.jsp",true);
		xmlHttp.send(null);
	}
</script>
<body>
	<div id=content>
		<table id=resultTable>
		<%
				List result=JDBC.Query("select * from Stock");
				for(int i=0;i<result.size();i++){
					Map rowData=(Map)result.get(i);
					String className;
					if(i%2==0)
						className="even";
					else
						className="odd";
			%>
			<tr class=<%=className%>>
				<%
					Iterator it=rowData.keySet().iterator();
						while(it.hasNext()){
							Object key=it.next();
				%>
				<td><input class="cell" type="text" value=<%=rowData.get(key)%>></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<div id=buttons>
			<input class="button" type=button value="查询" onclick="popup()"/>
			<input class="button" type=button value="新增" />
			<input class="button" type=button value="保存" />
		</div>
	</div>
</body>
</html>