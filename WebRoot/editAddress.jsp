<%@page import="com.digui.pojo.Rec_info"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑我的收件地址</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <link rel="stylesheet" href="css/editAddress.css">
    </head>
    
    <script type="text/javascript">
		    function check(){
		    	if(editform.username.value==""){
		    		alert("收件人不能为空!");
		    		editform.username.focus();
		    		return false;
		   	    }
                
                if(editform.userphone.value==""){
		          alert("手机号不能为空!");
		          editform.userphone.focus();
		          return false;
                }else if(!(/^1[34578]\d{9}$/.test(editform.userphone.value))){
                  alert("请输入正确的手机号码"); 
                  document.editform.userphone.focus();
                  return false; 
                 }
		   
		    if(editform.userno.value==""){
			    alert("学号不能为空!");
			    editform.userno.focus();
			    return false;
			 }	
            if(editform.address.value==""){
			    alert("收货地址不能为空!");
			    editform.address.focus();
			    return false;
			 }	
			    editform.submit();
			}
    </script>

<%
	String name;
	int user_id;
	int rec_info_id;
	
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
	if(request.getParameter("rec_info_id")!=null){
		rec_info_id=Integer.parseInt(request.getParameter("rec_info_id"));
	}else{
		rec_info_id=0;
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
        <div class="title">编辑地址</div>
    </div>
    
<%
    	ArrayList<Rec_info> recs = new ArrayList<Rec_info>();
    	if(!d.getRec(user_id, rec_info_id).isEmpty()){
    		recs = d.getRec(user_id, rec_info_id);
    	}
    	r = (Rec_info)recs.get(0);
    %>
  <form action="" name="editform">  
    <div class="person"> 
        <div class="person_con">
            <span>收&nbsp;&nbsp;件&nbsp;人： </span>
            <span>&nbsp;<input type="text" name="username" value="<%=r.getName() %>"></span>
        </div>
        <div class="person_con">
            <span>性&nbsp;&nbsp;&nbsp;&nbsp;别： </span>
            <span>&nbsp;<input type="text" name="userno" value="<%=r.getSex() %>"></span>
        </div>
        <div class="person_con">
            <span>联&nbsp;系&nbsp;方&nbsp;式：  </span>
            <span><input type="text" name="userphone" value="<%=r.getPhone_num() %>"></span>
        </div>
        <div class="person_con">
            <span>学&nbsp;&nbsp;&nbsp;&nbsp;校： </span>
            <span>&nbsp;<input type="text" name="userno" value="<%=r.getSchool_name() %>"></span>
        </div>
        <div class="person_con">
            <span>学&nbsp;&nbsp;&nbsp;&nbsp;号： </span>
            <span>&nbsp;<input type="text" name="userno" value="<%=r.getStu_num() %>"></span>
        </div>
        <div class="person_con1">
            <span>收&nbsp;货&nbsp;地&nbsp;址：  </span>
            <span><input type="text" name="address" size="40"  value="<%=r.getAddress() %>"></span>
        </div>
        <br>
        <div class="person_con">
            <span class="delete"><a href="">删除地址</a></span>
        </div>
    </div>
    <br>
    </form>
    <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                       <a href="">
                        <input type="button" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存" onclick="check()">
                        </a>
                </div>
        </div>
    </div>
</body>
</html>