<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="js/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="js/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script src="js/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
    <script src="js/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
 <script src="js/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    
    <style type="text/css">
            body
            {
                padding-left:10px;
                font-size:13px;
            }
            h4
            {
                font-size:16px;
                margin-top:25px;
                margin-bottom:10px;
            }
        td {
            padding: 5px;
        }

    </style>
    
    <script type="text/javascript">
    var EmpData="";
       var halfday=[{text:"上午",id:"am"},{text:"下午",id:"pm"}];
        $("#data1").ligerDateEditor();
        
   

    $("#one").ligerComboBox({
    	url:"Dept?opt=queryonename&timeid="+new Date(),
		 valueFieldID : 'departmentid',  
         valueField: 'departmentname',//请求参数key  
         textField: 'departmentname',//搜索框输入的值
        isMultiSelect: false,
        onSelected:function(name){
       	 $("#getname").val(name);
         $("#two").ligerComboBox({ 
 	    	url:"Dept?opt=querytwoname&data="+name+"&timeid="+new Date(),
 	    	 valueFieldID : 'departmentid',  
 	         valueField: 'departmentname',//请求参数key  
 	         textField: 'departmentname',//搜索框输入的值
 	    	isMultiSelect: false,
 	    	isShowCheckBox: false ,
 	    	onSelected:function(name){
 	          	 $("#getname").val(name);
 	          	$.ajax({
   		    	 type:"GET",
   				url:"Emp?opt=queryBydept&data="+name+"&timeid="+new Date(),
   				success:function(msg){
   					EmpData=msg;
   					 window['g'] = 
   				            $("#deptList").ligerGrid({
   				                columns: [
   				            { display: '员工姓名', name: 'employeename', align: 'left', width: 60 },
   				            { display: '员工卡号', name: 'cardnumber', Width: 60, align: 'center'},
   				            { display: '部门名称', name: 'departmentname', width: 200, align: 'center'},
   				            { display: '考勤状态', name: 'typename', width: 200, align: 'center',
   				            	editor:{
   				            		type:'select',
   				            		render:function(rowdata,rowindex){
   				            			return "出差";
   				            		}
   				            		
   				            	}},
   				            { display: '关联单据', name: 'starttimepm', width: 200, align: 'center',editor:{type:'text'}},
   				            { display: '备注', name: 'noteid', width: 200, align: 'center',editor:{type:'text'}}
   				            
   				            
   				            
   				                ], data: $.extend(true,{},EmpData), pageSize: 30,
   					                 onSelectRow: function (rowdata, rowindex)
   			                    {
   			                    	enabledEdit: true;
   			                    	clickToEdit: true;
   			                    	isScroll: false;
   			                        $("#txtrowindex").val(rowindex);
   			                    },
   		                  
   				                width: '100%', height: '100%', checkbox: false,
   				                enabledEdit: true,
   		                    	clickToEdit: true
   				            });
   				            $("#pageloading").hide();
   				},
   				dataType:"json"
   		      }
   			);
 	    	}
           });

        }
    });
    $("#halfday").ligerComboBox({
    	data:halfday, isMultiSelect: false
    });

    $(function ()
            {
    var EmpData="";
    	       $("#date1").ligerDateEditor();
    	       
            });
    </script>
</head>
<body style="padding:10px">
<form>
<table>
<tr>
<td>一级部门:</td><td><input type="text" id="one"/></td><td>二级部门:</td>
<td><input type="text" id="two"/></td><td>考勤时间 :</td><td><input id="date1" type="text"/></td>
<td>考勤时段：</td><td><input type="text" id="halfday"/></td>
</tr>
</table>
</form>

<input type="hidden" id="getname"/>
<div id="deptList"></div>
</body>
</html>
