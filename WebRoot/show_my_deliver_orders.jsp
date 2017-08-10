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
  <!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/order_info.css">
    <base href="<%=basePath%>">
    
    <title>查看我的接单订单</title>
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
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
<jsp:useBean id="d" class="com.digui.dao.DeliveryDao"></jsp:useBean> 

  <%
   	String name;
         	int user_id;
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
        <div class="title">查看我的接单订单</div>
    </div>
    
<%
    	ArrayList<Order_info> orders=d.getAllOrdersByDeliverUserId(user_id);
            	for(int i=0;i<orders.size();i++){
            		o=(Order_info)orders.get(i);
            		p=(Pack_info)d.getPac(o.getPack_info_id(), o.getUser_id()).get(0);
            		r=(Rec_info)d.getRec(o.getUser_id(), o.getRec_info_id()).get(0);
            		//out.println(o.getDeliver_user_id());
    %>
		<div class="person">
	        <div class="order_con">订单编号(系统生成)：<%=o.getOrder_id() %></div>
	        <div class="order_con">收件人姓名：<%=r.getName() %> </div>
	        <div class="order_con">收件人地址：<%=r.getAddress() %> </div>
	        <div class="order_con">收件人联系方式：<%=r.getPhone_num() %> </div>
	        <div class="details"><a href="rec_detail_info.jsp?order_id=<%=o.getOrder_id() %>">查看订单详情</a></div>
	        <div class="order_con">快递运送需时：<%=p.getRequire_time() %></div>
	        <div class="order_price">￥<%=p.getBasic_price() %>+<%=p.getTip()%>(小费)=<%=p.getSum_price() %>.00 </div>
	        <div class="person_status">状态：<%=o.getStatus() %> </div>
    	</div>
    
<%
	}
%>

<center>
	<div class="footer">
        <div class="footer-Con">
            Copyright&copy;2016<a>南京晓庄学院信息工程学院</a>
            <br />
            <a>团队：14卓工班仙女三人行</a>
            <a>Version:Alpha 1.0.0</a>
        </div>
    </div>
</center>  
</body>
</html>
