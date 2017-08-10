<%@page import="com.digui.pojo.Rec_info"%>
<%@page import="com.digui.pojo.Pack_info"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理我的收件地址</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <link rel="stylesheet" href="css/manageAddress.css">
</head>

<script type="text/javascript">
function ajax(){ 

	var xhr;
  	if(window.XMLHttpRequest){
  		xhr = new XMLHttpRequest();
  	}else if(window.ActiveXObject){
  		xhr = new ActiveXOject("Microsoft.XMLHTTP");
  	}else{
  		xhr = new XMLHttpRequest();
  	}
  	
  	  	var rec_info_id = event.target.value;
  	  	
  	  	if(xhr){
  		xhr.open("GET","operater?method=changeDefaultRec&rec_info_id="+rec_info_id, true);
  		xhr.send();
  		
		
  	}
 }
 </script>
  
  

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


<jsp:useBean id="r" class="com.digui.pojo.Rec_info"></jsp:useBean>
<jsp:useBean id="d" class="com.digui.dao.DeliveryDao"></jsp:useBean>

<body>
     <div class="choice-head">
        <div class="choice-head-con">
            <img src="image/tx_small.png">
            <a><%=name%>&nbsp;</a>
            <div class="name">童鞋，欢迎使用递归！</div>
        </div>
    </div>
    
   <div class="head">
        <div class="title">管理收货地址</div>
    </div>

<%
	ArrayList<Rec_info> recs = d.getRecByUserId(user_id);
	for(int i=0;i<recs.size();i++){
	r = (Rec_info)recs.get(i);
%>
		<div class="content">
	        <div class="content_1">
	            <span class="content_name"><%=r.getName() %></span>
	            <span class="content_phone"><%=r.getPhone_num() %></span>
	        </div>
	        <div class="content_2">收货地址：<%=r.getAddress() %></div>
	        <div class="content_3">
	            <span class="content_set">
<%
					if(r.getIs_defaut()==1){
%>
						<input type="radio" name="pay" value="" checked>默认地址
<%
					}else{
%>
	                	<input type="radio" name="pay" value="<%=r.getRec_info_id() %>" onclick="ajax()">默认地址
<%
					}
%>
	            </span>
	            <span class="content_edit"><a href="editAddress.jsp?user_id=<%=r.getUser_id() %>&rec_info_id=<%=r.getRec_info_id() %>">编辑</a></span>
	            <span class="img_1"><img src="image/edit.png" alt=""></span>
	            <span class="content_delete"><a href="">删除</a></span>
	            <span class="img_2"><img src="image/delete.png" alt=""></span>
	        </div>
    	</div>
<%	
	}
%>

    
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                       <a href="editAddress.html">
                        <input type="button" value="添加新地址">
                       </a>
                </div>
        </div>
    </div>
</body>
</html>
