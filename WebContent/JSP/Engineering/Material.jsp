<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userName")==null){
		response.sendRedirect("../Login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="http://localhost:8080/ERP/">
<title>物料管理</title>
<jsp:include page="../Nav.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="CSS/Content.css" />
<script type="text/javascript" src="Javascript/Ajax.js"></script>
<script type="text/javascript" src="Javascript/Table.js"></script>
<script>
getColumnNameURL="material.do?method=GetColumnName";
queryURL="material.do?method=Query";
insertURL="material.do?method=Insert";
deleteURL="material.do?method=Delete";
updateURL="material.do?method=Update";
GetColumnName();
</script>
</head>
<body>
<div id=blackMask></div>
	<div id=insertDialog>
		<table style="margin: auto">
			<tr>
				<td>物料代码:</td>
				<td><input class="text" name="ID" type=text /></td>
			</tr>
			<tr>
				<td>物料名称:</td>
				<td><input class="text" name="name" type=text /></td>
			</tr>
			<tr>
				<td>物料规格:</td>
				<td><input class="text" name="name" type=text /></td>
			</tr>
						<tr>
				<td>工艺路线:</td>
				<td><input class="text" name="name" type=text /></td>
			</tr>
			<tr>
				<td><input class="button" type=button value="确定" onclick="Insert()" /></td>
				<td align="right"><input class="button" type=button value="取消" onclick="Hide('insertDialog')" /></td>
			</tr>
		</table>
	</div>
	<div id=updateDialog>
	<table style="margin: auto">
			<tr>
				<td>物料代码:</td>
				<td><input disabled="disabled" class="text" name="ID" type=text /></td>
			</tr>
			<tr>
				<td>物料名称:</td>
				<td><input class="text" name="name" type=text /></td>
			</tr>
			<tr>
				<td>物料规格:</td>
				<td><input class="text" name="name" type=text /></td>
			</tr>
			<tr>
				<td>工艺路线:</td>
				<td><input class="text" name="name" type=text /></td>
			</tr>
			<tr>
				<td><input class="button" type=button value="确定" onclick="Update()" /></td>
				<td align="right"><input class="button" type=button value="取消" onclick="Hide('updateDialog')" /></td>
			</tr>
		</table>	
	</div>
	<div id=background>
		<img src="Image/manufacturingUnit.jpg" />
	</div>
	<div id=content>
		<table id=resultTable>
		</table>
		<div id=buttons>
			<input class="button" type=button value="查询" style="margin-left: 30px;" onclick="Query()" />
			<input class="button" type=button value="新增" style="margin-left: 30px;" onclick="Show('insertDialog')" />
			<input class="button" type=button value="删除" style="margin-left: 30px;" onclick="Delete()" />
			<input class="button" type=button value="修改" style="margin-left: 30px;" onclick="InitUpdate()" />
		</div>
	</div>
</body>
</html>