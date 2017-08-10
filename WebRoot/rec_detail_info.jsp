<%@page import="com.digui.pojo.User_info"%>
<%@page import="com.digui.pojo.Rec_info"%>
<%@page import="com.digui.pojo.Pack_info"%>
<%@page import="com.digui.pojo.Order_info"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单详情</title>
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


<jsp:useBean id="r" class="com.digui.pojo.Rec_info"></jsp:useBean>
<jsp:useBean id="p" class="com.digui.pojo.Pack_info"></jsp:useBean>
<jsp:useBean id="o" class="com.digui.pojo.Order_info"></jsp:useBean>
<jsp:useBean id="u" class="com.digui.pojo.User_info"></jsp:useBean>
<jsp:useBean id="d" class="com.digui.dao.DeliveryDao"></jsp:useBean>

<%
	String name;
	int user_id,order_id;
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
		order_id=Integer.parseInt(request.getParameter("order_id").toString());
	}else{
		order_id=0;
	}
	
	o=(Order_info)d.getOrderByOrderId(order_id);
	p=(Pack_info)d.getPac(o.getPack_info_id(), o.getUser_id()).get(0);
	r=(Rec_info)d.getRec(o.getUser_id(), o.getRec_info_id()).get(0);
	if(o.getDeliver_user_id()!=0){
		u=(User_info)d.getUser(o.getDeliver_user_id()).get(0);
	}else{
		u=new User_info();
		u.setName("暂时还没有人接单哦~");
		u.setPhone_num("暂时还没有人接单哦~");
		u.setStu_num("暂时还没有人接单哦~");
		u.setAddress("暂时还没有人接单哦~");
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
        <div class="title">订单详情</div>
    </div>
    
   <div class="person"> 
        <div class="infomation">
            &nbsp;&nbsp;收货信息
        </div>
        <div class="person_con">收件人：<%=r.getName() %> </div>
        <div class="person_con">学号：<%=r.getStu_num() %> </div>
        <div class="person_con">收货地址：<%=r.getAddress() %> </div>
        <div class="person_con">联系方式：<%=r.getPhone_num() %> </div>
    </div>
    <div class="person"> 
        <div class="infomation">
            &nbsp;&nbsp;接单人信息
        </div>
        <div class="person_con">接单人姓名：<%=u.getName() %> </div>
        <div class="person_con">接单人学号：<%=u.getStu_num() %> </div>
        <div class="person_con">接单人联系方式：<%=u.getPhone_num() %> </div>
        <div class="person_con">接单人地址：<%=u.getAddress() %> </div>
    </div>
    
    <div class="delivery">
         <div class="infomation">
            &nbsp;&nbsp;快递信息
        </div>
        <div class="person_con">快递公司：<%=p.getCompany() %> </div>
        <div class="person_con">快递单号：<%=p.getPack_num() %> </div>
        <div class="person_con">取货号：<%=p.getGet_num() %> </div>
        <div class="person_con">货物类型：<%=p.getSize() %> </div>
        <div class="person_con">送达所需时长：<%=p.getRequire_time() %> </div>
        <div class="person_con">应付金额：<%=p.getBasic_price() %>元+<%=p.getTip() %>元（小费）=<%=p.getSum_price() %>元</div>
    </div>
    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                        <input type="submit" value="返&nbsp;&nbsp;&nbsp;回" onclick="JavaScript:history.back(-1);">
                </div>
        </div>
    </div>
 </body>
</html>
