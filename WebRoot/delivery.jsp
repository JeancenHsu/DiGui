<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请仔细填写快递信息！</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/delivery.css">
    <script type="text/javascript">
	function add() {
		c.innerText=Number(basic.innerText)+Number(b.value)+"元";
	}
	
	function check()
    {

	    //获得 单选选按钮name集合  
	    var radios = document.getElementsByName("size");  
	                                  
	    //根据 name集合长度 遍历name集合  
	    for(var i=0;i<radios.length;i++)  
	    {   
	        //判断那个单选按钮为选中状态  
	        if(radios[0].checked)  
	        {  
	            document.getElementById("basic").innerHTML = "3";
	            break;
	        }
	        if(radios[1].checked)  
	        {  
	           	document.getElementById("basic").innerHTML = "4";
	            break;
	        }   
	        if(radios[2].checked)  
	        {  
	            document.getElementById("basic").innerHTML = "5";
	            break;
	        }     
	    }   
	 }

    
    function btn_onclick(){
        location.href="info.html";
    }

    </script>

  </head>
  
  <body>
  
  <%
  	String name,school_name;
	if(session.getAttribute("name")!=null){
		name=session.getAttribute("name").toString();
	}else{
		name="null";
	}
  %>
     <div class="choice-head">
        <div class="choice-head-con">
            <img src="image/tx_small.png">
            <a><%=name %>&nbsp;</a>
            <div class="name">童鞋，欢迎使用递归！</div>
        </div>
    </div>
    
   <div class="head">
        <div class="title">请填写快递详情</div>
    </div>
    
    
    
    
    <form action="operater?method=createOrder" method="post">
    
    <div class="content_1">
        <span>快递公司&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span>
            <select name="company" id="">
                <option value="申通">申通</option>
                <option value="中通">中通</option>
                <option value="圆通">圆通</option>
                <option value="韵达">韵达</option>
                <option value="顺丰">顺丰</option>
                <option value="EMS">EMS</option>
                <option value="全峰">全峰</option>
                <option value="天天">天天</option>
            </select>
        </span>
    </div>
    <div class="content">
        <span>快递单号&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span><span><input type="text" name="pack_num"></span></span>
    </div>
    <div class="content">
        <span>取货号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span><input type="text" name="get_num"></span>
    </div>
    <div class="content">
        <span>货物类型&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span>
            <input type="radio" name="size" id="size" value="小" onclick="check()" checked>&nbsp;小&nbsp;&nbsp;
            <input type="radio" name="size" id="size" value="中" onclick="check()">&nbsp;中&nbsp;&nbsp;
            <input type="radio" name="size" id="size" value="大" onclick="check()">&nbsp;大
        </span>
    </div>
    <div class="content">
        <span>费用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span><a id="basic">3</a>+<input type="text" name="tip_price" size="1" id="b" onblur="add()">元=<a id="c">元</a></span>
    </div>
    
    <div class="content">
        <span>送达时间&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span><input type="number" min="0" max="10" name="hour">小时
        <input type="number" min="0" max="60" name="minute">分钟</span>
    </div>
    
    <div class="content">
        <img src="image/explain1.png" alt="">
        <span class="warning">总费用由基本费用和小费组成，小费越高，被接单的可能性越高。</span>
    </div>
    
    <div class="content">
        <input type="checkbox" >&nbsp;&nbsp;我已阅读并同意<a href="">《快递代拿系统服务条款》</a>
    </div>
    
     <div class="index-btns">
        <div class="index-btns-Con">
                <div class="index-btn1">
                        <input type="submit" value="下&nbsp;一&nbsp;步" onclick="btn_onclick()">
                </div>
        </div>
     </div>
    
    </form>
    
    
  </body>
</html>
