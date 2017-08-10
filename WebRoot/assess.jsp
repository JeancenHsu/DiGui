<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>评价订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <link rel="stylesheet" href="css/assess.css">
</head>
  <%
  
	String name;
	int user_id,order_id,deliver_user_id;
	if(session.getAttribute("name")!=null){
		name=session.getAttribute("name").toString();
	}else{
		name="null";
	}
	if(session.getAttribute("user_id")!=null){
		user_id=Integer.parseInt(session.getAttribute("user_id").toString());
	}else{
		user_id=0;
	}
	if(request.getParameter("order_id")!=null){
		order_id=Integer.parseInt(request.getParameter("order_id"));
	}else{
		order_id=0;
	}
	if(request.getParameter("deliver_user_id")!=null){
		deliver_user_id=Integer.parseInt(request.getParameter("deliver_user_id"));
	}else{
		deliver_user_id=0;
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
    
   <div class="head">
        <div class="title">评价订单</div>
   </div>

<%
	if(order_id!=0){
%>
     
    <div class="descpriction">童鞋，来为派送者的服务打个分吧！</div>
    <div class="point">(拖动滑块即可参与打分)</div>
    
    <form action="operater?method=assess" method="post">
    <div class="assess">
       <div class="assess_title">服务质量</div>
       <div class="range">
           <input type="range" min="0" max="100" value="0" name="quality_point" onchange="change_quality()" id="range_quality"> 
       </div>
       <div class="range_show">
          <input type="text" id="show_quality">
          <span>分</span>
       </div>
   </div>
   <div class="assess">
           <div class="assess_title">服务速度</div>
       <div class="range">
           <input type="range" min="0" max="100" value="0" name="speed_point" onchange="change_speed()" id="range_speed"> 
       </div>
       <div class="range_show">
          <input type="text" id="show_speed">
          <span>分</span>
       </div>
   </div>
   
   <div class="button">
        <a href="">
        	<input type="hidden" name="order_id" value="<%=order_id %>">
        	<input type="hidden" name="deliver_user_id" value="<%=deliver_user_id %>">
            <input type="submit" value="提&nbsp;交&nbsp;评&nbsp;价">
         </a>
	</div>
	
	</form>
<%
	}else{
%>	
		<br><br><br><br>
		<div class="descpriction">无法评价！进入页面方式不对吧~</div>
<%	
	}
%>
</body>
<script type="text/javascript"> 
    function change_quality(){ 
        var num=document.getElementById("range_quality"); 
        var location=document.getElementById("show_quality"); 
        location.value=num.value; 
    } 
    function change_speed(){ 
        var num=document.getElementById("range_speed"); 
        var location=document.getElementById("show_speed"); 
        location.value=num.value; 
    } 
</script>
</html>
