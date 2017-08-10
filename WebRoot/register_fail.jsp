<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
    <title>注册失败</title>
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <link rel="stylesheet" href="css/register_failure.css">
    
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
  		String erroMsg;
  		if(session.getAttribute("erroMsg")!=null){
  			erroMsg = session.getAttribute("erroMsg").toString();
  		}else{
  			erroMsg = "未知错误";
  		}
  
   %>
  <center>
    <div class="start"><img src="image/di_logo.png" alt=""></div>
    
    <div class="con-top">
        <span><img src="image/failure.png" alt=""></span>
        <span class="failure">注册失败<br></span>
        <span class="failure1"><%=erroMsg %><br></span>
    </div>
    <br>
    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                    <a href="login.html">
                        <input type="button" value="已有账号登录">
                    </a>
                </div>
                <div class="index-btn2">
                    <a href="register.html">
                        <input type="button" value="重新注册">
                    </a>
                </div>
                    
        </div>
    </div>
</center>
</body>
</html>
