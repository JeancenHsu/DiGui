<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请确认您的收货地址和快递信息！</title>
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <link rel="stylesheet" href="css/info.css">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<script>
    function btn_onclick(){
        location.href="pay.html";
    }
</script>
<%
	String name = session.getAttribute("rec_name").toString();
	
	String rec_name = session.getAttribute("rec_name").toString();
	String sex = session.getAttribute("sex").toString();
	String school_name = session.getAttribute("school_name").toString();
	String stu_num = session.getAttribute("stu_num").toString();
	String address = session.getAttribute("address").toString();
	String phone_num = session.getAttribute("phone_num").toString();

	String company = session.getAttribute("company").toString();
	String pack_num = session.getAttribute("pack_num").toString();
	String get_num = session.getAttribute("get_num").toString();
	String size = session.getAttribute("size").toString();
	String basic_price = session.getAttribute("basic_price").toString();
	String tip_price = session.getAttribute("tip_price").toString();
	String sum_price = session.getAttribute("sum_price").toString();
	String require_time = session.getAttribute("require_time").toString();
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
        <div class="title">请确认快递信息</div>
    </div>
    
   <div class="person"> 
        <div class="infomation">
            &nbsp;&nbsp;收货信息
        </div>
        <div class="person_con">收件人：<%=rec_name %> </div>
        <div class="person_con">学号：<%=stu_num %> </div>
        <div class="person_con">收货地址：<%=address %> </div>
        <div class="person_con">联系方式：<%=phone_num %> </div>
        <div class="modify"><a href="manageAddress.jsp">修改个人收货信息</a></div>
    </div>
    
    <div class="delivery">
         <div class="infomation">
            &nbsp;&nbsp;快递信息
        </div>
        <div class="person_con">快递公司：<%=company %> </div>
        <div class="person_con">快递单号：<%=pack_num %> </div>
        <div class="person_con">取货号：<%=get_num %> </div>
        <div class="person_con">货物类型：<%=size %> </div>
        <div class="person_con">送达时间：<%=require_time %> </div>
        <div class="person_con">应付金额：<%=basic_price %>元+<%=tip_price %>元（小费）=<%=sum_price %>元</div>
        <div class="modify"><a href="JavaScript:history.back(-1);">返回修改快递信息</a></div>
    </div>
    
    <form action="operater?method=confirmOrder" method="post">
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                        <input type="submit" value="确&nbsp;认&nbsp;下&nbsp;单" >
                </div>
        </div>
    </div>
    </form>
 </body>
</html>
