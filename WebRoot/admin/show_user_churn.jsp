<%@page import="com.digui.algorithm.realData"%>
<%@page import="com.digui.algorithm.getCustomerChurn"%>
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
    <link rel="stylesheet" href="../css/choice.css">
    <base href="<%=basePath%>">
    
    <title>查看用户流失情况</title>
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
<script src="http://cdn.bootcss.com/jquery/1.8.3/jquery.min.js"></script>
<script>
    $(function(){
        $('.onvk').click(function(){
       if($(this).text()=='▼'){
           $(this).text('▲')
      }else{
        $(this).text('▼')
      }
       $(this).parents('span').siblings('.tedg').stop().slideToggle();  /*找到#onvk的所有是p元素的祖先元素,.tedg的同辈元素，停止高度的变化*/
   })
    })
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
        <div class="title">查看用户流失情况</div>
    </div>
   <br><br><br>
 
<%
 	if(name!=null){
 	getCustomerChurn g = new getCustomerChurn();
 	realData r = new realData();
 	String[][] realData = r.getData();
 	String[] info = new String[realData.length];
 	 		String[] result = new String[info.length];
 	 		Object[] obj = new Object[info.length];
 	 		for(int i=0; i<realData.length; i++){
 	  			info[i] = realData[i][0]+","+realData[i][1]+","+realData[i][2]+","+realData[i][3]+","+realData[i][4]+","+realData[i][5];
 		obj[i] = g.getResult(info[i]);
 	  			result[i] = obj[i].toString();
 		result[i] = String.valueOf(info[i]);
 	  		}
 	for(int i=0; i<info.length; i++){
 %>
				
	        	<div class="middle_content">
<%
	out.print("<span class=\"middle_1\">用户编号:"+realData[i][7]+"&nbsp;&nbsp;&nbsp;&nbsp;");
		if(obj[i].toString().equals("是")){
			out.print("<font color=\"red\">是否有流失倾向:"+obj[i]+"</font></span>");
		}else{
			out.print("是否有流失倾向:"+obj[i]+"</span>");
		}
		
		ArrayList<User_info> users=d.getUser(Integer.parseInt(realData[i][7]));
		User_info u = (User_info)users.get(0);
%>
				<span class="middle_6"><a href="javascript:;" class="onvk">▼</a></span>
				<div class="tedg">
	                <div class="wrap">
	                 <span class="">姓名：<%=u.getName() %></span>
	                 <span class="">学校：<%=u.getSchool_name() %></span>
	                 <span class="">学号：<%=u.getStu_num() %><br></span>
	                 <span class="">身份证号：<%=u.getId_num() %><br></span>
	                 <span class="">收件地址：<%=u.getAddress() %><br></span>
	                 <span class="">联系方式：<%=u.getPhone_num() %><br></span>
	                 <span class="">注册时间：<%=u.getRegister_time() %><br></span>
	                 <span class="">上次登录时间：<%=u.getLast_login_time() %><br></span>
	                 </div>
	            </div>
	            </div>
	          
<%
	 		}
		}

		
%>
		
</body>
</html>
