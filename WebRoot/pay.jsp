<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请选择支付方式</title>
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <link rel="stylesheet" href="css/pay.css">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script>
    function btn_onclick(){
        location.href="pay_success.html";
    }
</script>
<%
	String name,sum_price;
	if(session.getAttribute("name")!=null){
		name=session.getAttribute("name").toString();
	}else{
		name="null";
	}
	
	if(session.getAttribute("sum_price")!=null){
		sum_price=session.getAttribute("sum_price").toString();
	}else{
		sum_price="null";
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
        <div class="title">请选择支付方式</div>
    </div>
    
    <div class="success">
        您已成功下单，请在<span class="time">&nbsp;5：00&nbsp;</span>内完成支付！
    </div>
    
    <div class="money">
        <div class="money_1">应付金额</div>
        <div class="money_2"><%=sum_price %>.00<span class="yuan">&nbsp;&nbsp;元</span></div>
    </div>
    
    <form action="operater?method=choosePayWay" method="post">
    <div class="method">
        <div class="money_1">支付方式</div>
        <div class="method_2">
            <input type="radio" name="pay_way" value="货到付款" checked>&nbsp;&nbsp;&nbsp;货到付款<br>
            <input type="radio" name="pay_way" value="支付宝">&nbsp;&nbsp;&nbsp;支付宝<br>
            <input type="radio" name="pay_way" value="微信支付">&nbsp;&nbsp;&nbsp;微信支付<br>
            <input type="radio" name="pay_way" value="QQ钱包">&nbsp;&nbsp;&nbsp;QQ钱包<br>
        </div>
    </div>
    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                        <input type="submit" value="确&nbsp;认&nbsp;付&nbsp;款">
                </div>
        </div>
    </div>
    </form>
</body>
</html>
