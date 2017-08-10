<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
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
    <title>支付成功</title>
    <link rel="stylesheet" href="css/pay_success.css">
    
</head>

<%
	String name;
	if(session.getAttribute("name")!=null){
		name=session.getAttribute("name").toString();
	}else{
		name="null";
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
    
    <div class="con-top">
        <span><img src="image/success.png" alt=""></span>
        <span class="success">抢单成功</span>
    </div>


    <div class="con-middle">
        <img src="image/paysuccess.png" alt="">
    </div>
    
    <div class="con-bottom">请尽快送达快递并请客户确认订单！</div>
    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                        <a href="show_my_deliver_orders.jsp"><input type="button" value="查&nbsp;看&nbsp;我&nbsp;接&nbsp;单&nbsp;的&nbsp;订&nbsp;单"></a>
                </div>
        </div>
    </div>
    </div>
</body>
</html>