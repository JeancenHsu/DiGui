<%@page import="com.digui.pojo.Order_info"%>
<%@page import="com.digui.pojo.Rec_info"%>
<%@page import="com.digui.pojo.Pack_info"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请选择您要代拿的快递</title>
    <link rel="stylesheet" href="css/choice.css">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1,target-densitydpi=device-dpi ">
    <style type="text/css">
</style>
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
</head>
<jsp:useBean id="r" class="com.digui.pojo.Rec_info"></jsp:useBean>
<jsp:useBean id="p" class="com.digui.pojo.Pack_info"></jsp:useBean>
<jsp:useBean id="o" class="com.digui.pojo.Order_info"></jsp:useBean>
<jsp:useBean id="d" class="com.digui.dao.DeliveryDao"></jsp:useBean>

<%
	String name;
	int user_id0;
	if(session.getAttribute("name")!=null){
		name=session.getAttribute("name").toString();
	}else{
		name="null";
	}
	if(session.getAttribute("user_id")!=null){
		user_id0=Integer.parseInt(session.getAttribute("user_id").toString());
	}else{
		user_id0=0;
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
        <div class="title">请选择您要代拿的快递</div>
    </div>

<!-- 
    <div class="option">
       <div class="sort">
            <span class="option-sexboy">
                <a href="">选择性别男</a>
            </span>
            <span class="option-sexgirl">
                <a href="">选择性别女</a>
            </span>
            <span class="option-level-high">
                <a href="">评价由高到低</a>
            </span>
            <span class="option-level-low">
                <a href="">评价由低到高</a>
            </span>
        </div>
    </div>
-->
    
    <div class="header">
        <div class="header_content">
            <span class="option-sexboy">请选择</span>
            <span class="header_2">姓名</span>
            <span class="header_2">性别</span>
            <span class="header_2">快递大小</span>
            <span class="header_2">费用</span>
            <span class="header_2">查看详情</span>
        </div>
    </div>

    <form action="choice_action.jsp" method="post">
    <%
    	ArrayList<Order_info> or=d.getAllOrders(user_id0);
               	ArrayList<Pack_info> pa;
               	ArrayList<Rec_info> re;
               	
               	if(or.size()==0){
    %>
   			<br><br><br><br>
   			<center>
	            <a><font color="red" size="5rem">您暂时没有可以接的单哦~</font></a>
	        </center>

  <%
  	}else{
         		int intPageSize;//一页显示的记录数
      	   	int intRowCount;//数据库中的记录总数；
      	   	intRowCount = or.size();
      	   	int intPageCount;//总页数
      	   	int intPage;//待显示页码
      	   	int i;
      	   	String strPage;
      	   	intPageSize=5;//每页显示5条记录数 写死
      	   	//总页数=记录总数/每页记录数
      	   	if((intRowCount%intPageSize)!=0){
      	   		intPageCount=intRowCount/intPageSize;
      	   		intPageCount++;
      	   	}else{
      	   		intPageCount=intRowCount/intPageSize;
      	   	}
      	   	strPage=request.getParameter("page");
      	   	if(strPage==null){
      	   		intPage=1;
      	   	}else{
      	   		intPage=Integer.parseInt(strPage);
      	   		if(intPage<1){
      	   			intPage=1;
      	   		}
      	   	}
      	   	if(intPage>intPageCount)
      	 	   //调整待显示的页码
      	 	   intPage=intPageCount;
      	    if(intPageCount>0){
      	 	   //显示数据
      	 	   i=(intPage-1)*intPageSize+1-1;
      	 	   int j=0;
      	 	   int k=0;
      	 	   
      	 	   while(i<intPageSize*intPage&&i<or.size()){
      	 		   o=(Order_info)or.get(i);
      	 		   int user_id=o.getUser_id();
      	 		   int pack_info_id=o.getPack_info_id();
      	 		   int rec_info_id=o.getRec_info_id();
      	 		   pa=d.getPac(pack_info_id, user_id);
      	 		   int psize=pa.size();
      	 		   if(psize==0){break;}
      	 		   if(j<psize){
      	 		   		p=(Pack_info)pa.get(j);	
      	 		   		int user_id_2=p.getUser_id();
      	 		   		re=d.getRec(user_id_2, rec_info_id);
      	 		   		int rsize=re.size();
      	 		  		if(k<rsize){
      	 					r=(Rec_info)re.get(k);
  %>              
	    <div class="middle">
	        <div class="middle_content">
	            <span class="middle_1"><input type="checkbox" name="ckbx" value="<%=o.getOrder_id() %>"></span>
	            <span class="middle_2"><%=r.getName() %></span>
	            <span class="middle_3"><%=r.getSex() %></span>
	            <span class="middle_4"><%=p.getSize() %></span>
	            <span class="middle_5"><%=p.getSum_price() %></span>
	            <span class="middle_6"><a href="javascript:;" class="onvk">▼</a></span>
	            <div class="tedg">
	                <div class="wrap">
	                 <span class="tedg_1">收件人：<%=r.getName() %></span>
	                 <span class="tedg_6">&nbsp;&nbsp;快递公司：<%=p.getCompany() %><br></span>
	                 <span class="tedg_2">学号：<%=r.getStu_num() %></span>
	                 <span class="">&nbsp;&nbsp;快递单号：<%=p.getPack_num() %><br></span>
	                 <span class="">收件地址：<%=r.getAddress() %><br></span>
	                 <span class="tedg_3">取货号：<%=p.getGet_num() %></span>
	                 <span class="">&nbsp;&nbsp;货物类型：<%=p.getSize() %><br></span>
	                 <span class="tedg_4">联系方式：<%=r.getPhone_num() %><br></span>
	                 <span class="tedg_5">应付金额：<%=p.getBasic_price() %>+<%=p.getTip() %>(小费)=<%=p.getSum_price() %></span>
	                 </div>
	            </div>
	        </div>
	        
	 <%		
	 		i++;	
	   	    	 }
	   	     } 
	 	  }
	 	}
	 %>  
	 	<div class="page">
	            <ul>  
	    <%
	    	for(int j=1;j<=intPageCount;j++){
	    %>
	         <li><a href="choice.jsp?page=<%=j%>"><%=j %></a></li>
	    <%} %>
	    </ul>
	    </div>
	    <div class="number">根据星级，您最多可同时接3单！</div>
	     
	       <div class="index-btns">
	        <div class="index-btns-Con">
	             <div class="index-btn1">
	                     <input type="submit" value="确&nbsp;认&nbsp;抢&nbsp;单" onclick="">
	             </div>
	        </div>
	    </div> 
	    </div>
	    
	    </form>
<%   	
   	}
%>   	
      
     
</body>
</html>