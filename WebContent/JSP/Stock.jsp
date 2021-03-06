<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
<title>ERP-库存管理</title>
</head>
<jsp:include page="Nav.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="CSS/Content.css" />
<script type="text/javascript" src="Javascript/Ajax.js"></script>
<script type="text/javascript">
	var xmlHttp=GetXMLHttpRequest();
	var selectedRow;
	var originColor;
	GetColumnName();

	function GetColumnName(){
		var url="stock.do?method=GetColumnName";
		SendRequest(xmlHttp,"GET",url,null,"xml",GetColumnNameComplete);
	}

	function GetColumnNameComplete(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			var str=xmlHttp.responseText.split(";");
			var table=document.getElementById("resultTable");
			var newRow=table.insertRow(-1);
			newRow.className="odd";
			for( var i=0;i<str.length;i++){
				var newCell=newRow.insertCell(-1);
				newCell.innerHTML=str[i];
			}
		}
	}

	function Query(){
		var url="stock.do?method=Query";
		SendRequest(xmlHttp,"GET",url,null,"xml",QueryComplete);
	}

	function QueryComplete(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			var table=document.getElementById("resultTable");
			DeleteAll(table);
			var xmlDoc=xmlHttp.responseXML.documentElement;
			InsertXMLData(table,xmlDoc);
		}
	}

	function DeleteAll(table){
		var tr=table.getElementsByTagName("tr");
		for( var i=1;i<tr.length;)
			table.deleteRow(i);
	}

	function InsertXMLData(table,xmlDoc){
		var rows=xmlDoc.getElementsByTagName("row");
		for( var i=0;i<rows.length;i++){
			var row=table.insertRow(-1);
			row.onclick=function(){
				SelectRow(this);
			};
			row.className=CheckEvenOrOdd(i);
			var cells=rows[i].getElementsByTagName("cell");
			for( var j=0;j<cells.length;j++){
				var cell=row.insertCell(-1);
				cell.innerHTML=cells[j].childNodes[0].nodeValue;
			}
		}
	}

	function Show(divId){
		var blackMask=document.getElementById("blackMask");
		blackMask.style.display="block";
		var dialog=document.getElementById(divId);
		dialog.style.display="block";
	}

	function Insert(){
		var dialog=document.getElementById("insertDialog");
		var data=dialog.getElementsByTagName("input");
		var url="stock.do?method=Insert";
		var content="";
		for( var i=0;i<data.length;i++){
			if(data[i].type=="text"&&data[i].value==""){
				alert("请填写完整");
				return;
			}
			content+=data[i].name+"="+data[i].value+"&";
		}
		SendRequest(xmlHttp,"POST",url,content,"text",InsertComplete);
	}

	function InsertComplete(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			if(xmlHttp.responseText=="success"){
				Hide("insertDialog");
				Query();
			}else{

			}
		}
	}

	function CheckEvenOrOdd(i){
		if(i%2==0)
			return "even";
		else
			return "odd";
	}

	function SelectRow(tr){
		if(originColor!=null)
			selectedRow.style.backgroundColor=originColor;
		selectedRow=tr;
		originColor=selectedRow.style.backgroundColor;
		selectedRow.style.backgroundColor="#4c6c8f";
	}

	function Delete(){
		var url="stock.do?method=Delete";
		var td=selectedRow.getElementsByTagName("td");
		var content="id="+td[0].innerHTML;
		SendRequest(xmlHttp,"POST",url,content,"text",DeleteComplete);
	}

	function DeleteComplete(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200)
			selectedRow.parentNode.removeChild(selectedRow);
	}

	function Hide(divId){
		var div=document.getElementById(divId);
		div.style.display="none";
		var blackMask=document.getElementById("blackMask");
		blackMask.style.display="none";
	}
	
	function InitUpdate(){
		if(selectedRow==null)
			return;
		var dialog=document.getElementById("updateDialog");
		var input=dialog.getElementsByTagName("input");
		for(var i=0;i<input.length;i++){
			if(input[i].disabled==true){
				var td=selectedRow.getElementsByTagName("td");
				input[i].value=td[0].innerHTML;
			}
		}
		Show("updateDialog");
	}
	
	function Update(){
		var url="stock.do?method=Update";
		var content="";
		var dialog=document.getElementById("updateDialog");
		var data=dialog.getElementsByTagName("input");
		for(var i=0;i<data.length;i++){
			if(data[i].type=="text"&&data[i].value==""){
				alert("请填写完整");
				return;
			}
			content+=data[i].name+"="+data[i].value+"&";
		}
		SendRequest(xmlHttp,"POST",url,content,"text",UpdateComplete);
	}

	function UpdateComplete(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			Hide("updateDialog");
			Query();
		}
	}
</script>
<body>
	<div id=blackMask></div>
	<div id=insertDialog>
		<table style="margin: auto">
			<tr>
				<td>物料代码:</td>
				<td><input class="text" name="materialID" type=text /></td>
			</tr>
			<tr>
				<td>库存数量:</td>
				<td><input class="text" name="stockNum" type=text /></td>
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
				<td><input disabled="disabled" class="text" name="materialID" type=text /></td>
			</tr>
			<tr>
				<td>库存数量:</td>
				<td><input class="text" name="stockNum" type=text /></td>
			</tr>
			<tr>
				<td><input class="button" type=button value="确定" onclick="Update()" /></td>
				<td align="right"><input class="button" type=button value="取消" onclick="Hide('updateDialog')" /></td>
			</tr>
		</table>	
	</div>
	<div id=background>
		<img src="Image/warehouse.jpg" />
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