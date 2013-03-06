function SendRequest(xmlHttp,method,url,content,responseType,callback) {
    if (xmlHttp==null) {
        alert("不能创建XMLHttpRequest对象实例");
    }
    if(responseType.toLowerCase()=="text") {
    	xmlHttp.onreadystatechange=callback;
    }
    else if(responseType.toLowerCase()=="xml") {
    	xmlHttp.onreadystatechange=callback;
    }
    else {
        alert("响应类别参数错误");
        return false;
    }
    if(method.toLowerCase()=="get") {
    	xmlHttp.open(method,url,true);
    }
    else if(method.toLowerCase()=="post") {
    	xmlHttp.open(method,url,true);
    	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    }
    else {
        alert("http请求类别参数错误");
        return false;
    }
    xmlHttp.send(content);
}

function GetXMLHttpRequest(){
	var xmlHttp=null;
	 if(window.XMLHttpRequest){
		 xmlHttp=new XMLHttpRequest();
	        if(xmlHttp.overrideMimeType){
	        	xmlHttp.overrideMimeType("text/xml");
	        }
	    }
	    else if(window.ActiveXObject){
	        try {
	        	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	        } catch (e) {
	            try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	            } catch (e) {
	            }
	        }
	    }
	    return xmlHttp;
}