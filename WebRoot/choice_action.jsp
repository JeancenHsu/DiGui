<%@page import="com.digui.dao.DeliveryDao"%>
<%@page import="com.digui.pojo.User_info"%>
<%@page import="com.digui.pojo.Rec_info"%>
<%@page import="com.digui.pojo.Pack_info"%>
<%@page import="com.digui.pojo.Order_info"%>
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



<jsp:useBean id="r" class="com.digui.pojo.Rec_info"></jsp:useBean>
<jsp:useBean id="p" class="com.digui.pojo.Pack_info"></jsp:useBean>
<jsp:useBean id="o" class="com.digui.pojo.Order_info"></jsp:useBean>
<jsp:useBean id="d" class="com.digui.dao.DeliveryDao"></jsp:useBean>

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
            <a><%=name%>&nbsp;</a>
            <div class="name">童鞋，欢迎使用递归！</div>
        </div>
    </div>
    
   <div class="head">
        <div class="title">请确认订单信息</div>
    </div>
  
  <form action="operater?method=confirmDeliver" method="post">
<%
	String ss[] = request.getParameterValues("ckbx");
	int order_ids[]= new int[ss.length];
	for(int i=0;i<ss.length;i++){
		order_ids[i]=Integer.parseInt(ss[i]);
	}
	
	
	for(int i=0;i<order_ids.length;i++){
		o=(Order_info)d.getOrderByOrderId(order_ids[i]);
		p=(Pack_info)d.getPac(o.getPack_info_id(), o.getUser_id()).get(0);
		r=(Rec_info)d.getRec(o.getUser_id(), o.getRec_info_id()).get(0);
%>
	<input type="hidden" name="hidden" value="<%=order_ids[i] %>">
	
	<div class="person"> 
        <div class="infomation">
            &nbsp;&nbsp;订单<%=i+1 %>收货信息
        </div>
        <div class="person_con">收件人：<%=r.getName() %> </div>
        <div class="person_con">学号：<%=r.getStu_num() %> </div>
        <div class="person_con">收货地址：<%=r.getAddress() %> </div>
        <div class="person_con">联系方式：<%=r.getPhone_num() %> </div>
    </div>
    
    <div class="delivery">
         <div class="infomation">
            &nbsp;&nbsp;订单<%=i+1 %>快递信息
        </div>
        <div class="person_con">快递公司：<%=p.getCompany() %> </div>
        <div class="person_con">快递单号：<%=p.getPack_num() %> </div>
        <div class="person_con">取货号：<%=p.getGet_num() %> </div>
        <div class="person_con">货物类型：<%=p.getSize() %> </div>
        <div class="person_con">送达时间：<%=p.getRequire_time() %> </div>
        <div class="person_con">应付金额：<%=p.getBasic_price() %>元+<%=p.getTip() %>元（小费）=<%=p.getSum_price() %>元</div>
    </div>
    <hr>
<%
	}

%>
    
    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                		
                        <input type="submit" value="确&nbsp;认&nbsp;接&nbsp;单" >
                </div>
        </div>
    </div>
    </form>
 </body>
</html>
