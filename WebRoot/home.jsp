<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用递归！</title>
    <link rel="stylesheet" href="css/welcome.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<%
	String name,school_name;
	if(session.getAttribute("name")!=null){
		name=session.getAttribute("name").toString();
	}else{
		name="null";
	}
	
	if(session.getAttribute("school_name")!=null){
		school_name=session.getAttribute("school_name").toString();
	}else{
		school_name="null";
	}
	
%>
<body>
   <div class="choice-head">
        <div class="choice-head-con">
            <img src="image/tx_small.png">
            <a><%=name %>&nbsp;</a>
            <div class="name">童鞋，欢迎使用递归！</div>
        </div>
    </div>
    <div class="content">
        <img src="image/tx.png" alt="">
        <div class="content-name"><%=name %></div>
        <div class="content-school"><%=school_name %></div>     
    </div>
    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                        <a href="delivery.jsp"><input type="button" value="请别人帮我拿快递"></a>
                </div>
                <div class="index-btn2">
                     	<a href="choice.jsp"><input type="button" value="帮别人拿快递赚钱"></a>
                </div>
        </div>
    </div>
    
</body>
</html>
