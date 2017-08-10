
<%@page import="com.digui.algorithm.realData"%>
<%@page import="com.digui.algorithm.getCustomerChurn"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <%
    	getCustomerChurn g = new getCustomerChurn();
		realData r = new realData();
		String[][] realData = r.getData();
		String[] info = new String[realData.length];
 		String[] result = new String[info.length];
 		Object[] obj = new Object[info.length];
 		for(int i=0; i<realData.length; i++){
  			info[i] = realData[i][0]+","+realData[i][1]+","+realData[i][2]+","+realData[i][3]+","+realData[i][4]+","+realData[i][5];
			obj[i] = g.getResult(info[i]);
  			result[i] = obj[i].toString();
			result[i] = String.valueOf(info[i]);
  		}
		for(int i=0; i<info.length; i++){
			out.print("id:"+realData[i][7]+"   结果:"+obj[i]+"<br/>");
 		}
      %>
  </body>
</html>
