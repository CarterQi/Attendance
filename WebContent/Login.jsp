<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" rel="external nofollow" >  
<link rel="stylesheet" type="text/css" href="easyui/themes/default/panel.css" rel="external nofollow" >  
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" rel="external nofollow" > 
<script type="text/javascript" src="js/lib/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>


</head>
<body>
<div id="win" class="easyui-window" title="Login" style="width:800px;height:400px;background-color:orange">
	<form align="center" style="padding:80px" action="/Attendance/Login">
	    <div style="font-size: 40px;">欢迎登录阿里巴巴考勤系统</div>
		<p>用户名: <input type="text" class="easyui-textbox" name="username" id="username"></p>
		<p>密码:     <input type="password" class="easyui-textbox" name="password" id="password"></p>
		<div style="padding:5px;text-align:center">
			<input type="button" class="easyui-linkbutton" style="width:80px;height:40px" id="Login" value="登录" >
		</div>
	</form>
</div>
<script type="text/javascript">

					
	      	       
	      	         $("#Login").click(function(){	
	      	        	 var username= $("#username").val();
	      	        	  var password= $("#password").val();
	      	           $.ajax({
	   	      	        url:"Login?timeid="+new Date(),	
	   	      	        		data:{"username":username,"password":password},
	   	      	        	 success:function(msg){
	   	      	        		 if(msg!='登录失败！'){
	   	      	        	window.location.href = 'index.jsp';
	   	      	        		 }else{
	   	      	        			 alert(msg);
	   	      	        		 }
	   	      	        	 }
	   	      	        		
	   	      	         }
	   	      	         );
	      	        });
	      	      


</script>
</body>
</html>