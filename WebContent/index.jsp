<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core-rt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
        <title>阿里巴巴考勤系统主页</title>
        <link href="js/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
        </style>
        <script src="js/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>   
    <script src="js/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
     <script src="js/lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
        <script src="js/lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
            <script type="text/javascript">

                $(function ()
                {
                	$("#checkmg").click(function(){//考勤管理
                		$("#center").load("Check.jsp");
                	});
                	
                	$("#rootmg").click(function(){//权限管理
                		$("#center").load("root.html");
                	});
                	
                	$("#leavemg").click(function(){  //请假单管理
                		$("#center").load("LeaveMg.html");
                	});
                	
                	$("#travelmg").click(function(){   //出差单管理
                		$("#center").load("TravelMg.html");
                	});
                	
                	$("#usermg").click(function(){//用户管理
                		$("#center").load("User.jsp");
                	});

                	$("#deptmg").click(function()//点击部门管理
                			{
                		
                		     $("#center").load("Dept.jsp");
                		
                			});
                	
                	$("#jobmg").click(function(){//职务管理
                		$("#center").load("Job.jsp");
                		
                	});
                	$("#empmg").click(function(){//员工管理
                		$("#center").load("Emp.jsp");
                	});
                	   $("#accordion1").ligerAccordion(
                               {
                                   height: 300
                               });
                	   $("#layout1").ligerLayout({ leftWidth: 200,allowBottomResize: false, allowLeftResize: false  });
                });
                
         </script> 
        <style type="text/css"> 

            body{ padding:10px; margin:0;}
        #layout1{  width:100%; margin:40px;  height:400px;
                   margin:0; padding:0;} 
        #accordion1 { height:270px;}
            .l-page-top{ height:80px; background:orange; margin-bottom:3px;}
            
                </style>
    </head>
    <body style="padding:10px">  
      <div class="l-page-top" style="background-color:orange; font-size: 40px;">
      阿里巴巴考勤系统
      </div>
      <div id="layout1">
            <div position="left">
            <div id="accordion1"> 
     <c:set var="username" value="${username}" scope="session"/>       
  <c:if test="${username eq 'admin'}">
         <div title="基本设置" >
            
              <ul>
                    <li><div id="deptmg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
                    border-bottom:2px solid white" align="center">部门管理</div></li>
                    <li><div id="jobmg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
                    border-bottom:2px solid white" align="center">职位管理</div></li>
                    <li><div id="empmg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
                    border-bottom:2px solid white" align="center">员工管理</div></li>
                    <li><div id="usermg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
                    border-bottom:2px solid white" align="center">用户管理</div></li>
                    <li><div id="rootmg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
                    border-bottom:2px solid white" align="center">权限管理</div></li>
                </ul>
        </div>
  </c:if>
         <div title="考勤管理">
            
                <ul>
                    <li><div id="checkmg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
                    border-bottom:2px solid white" align="center">员工考勤</div></li>
                </ul>
            
        </div> 
        <div title="单据管理" > 
        
        <ul>
        <li><div id="leavemg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
        border-bottom:2px solid white" align="center">请假单管理</div></li>
        <li><div id="travelmg" style="cursor:pointer;height:40px;font-size:20px;background-color:orange;
        border-bottom:2px solid white" align="center">出差单管理</div></li>
        </ul>
        
        </div>
    </div>  
    
    <div style="display:none;">
    
</div>
            </div>
            <div position="center" title="标题" id="center"> 
                <br />
                <div align="center">
               <img src="img/alibaba.jpg" width="550" height="400"></img>
               </div>
               <div align="right"></div>
            <div position="top"></div>
            <div position="bottom"></div>
            </div>  
        </div> 
    </body>
    </html>