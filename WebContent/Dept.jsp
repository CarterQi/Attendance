<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <link href="js/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <%--link href="js/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /--%>
    <link href="js/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script src="js/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
    <script src="js/lib/ligerUI/js/plugins/ligerFilter.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="js/lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    
    <script src="js/demos/filter/ligerGrid.showFilter.js" type="text/javascript"></script>
    
    <script type="text/javascript">

    //去掉  大于小于包括,并改变顺序
    $.ligerDefaults.Filter.operators['string'] =
    $.ligerDefaults.Filter.operators['text'] =
    ["like" , "equal", "notequal", "startwith", "endwith" ];

   
	
        $(function ()
        {  
         var DeptData="";
         
	      $.ajax({
	    	 type:"GET",
			url:"Dept?opt=query&timeid="+new Date(),
			success:function(msg){
				DeptData=msg;
				 window['g'] = 
			            $("#maingrid4").ligerGrid({
			                columns: [
			            { display: '部门号', name: 'departmentid', align: 'left', width: 60 },
			            { display: '部门名称', name: 'departmentname', Width: 60, align: 'center' ,editor:{type:'text'}},
			            { display: '上午上班时间', name: 'starttimeam', width: 200, align: 'center',editor:{type:'text'} },
			            { display: '上午下班时间', name: 'endtimeam', width: 200, align: 'center',editor:{type:'text'}},
			            { display: '下午上班时间', name: 'starttimepm', width: 200, align: 'center',editor:{type:'text'}},
			            { display: '下午下班时间', name: 'endtimeam', width: 200, align: 'center',editor:{type:'text'}},
			            { display: '上级部门号', name: 'parentid', width: 80, align: 'center'},
			            {display: '操作', minwidth:20 , align:'center',render: function(rowdata, rowindex, value){
			            	
			            	return "<a href='javascript:btn_managedept(" + rowindex + ")'>管理子部门</a>";
			          	  }
			            
			            }
			            
			                ], data: $.extend(true,{},DeptData), pageSize: 30,
			                toolbar: { items: [{ text: '高级自定义查询', click: item_query_click, icon: 'search2'},
			                	{ text: '增加', click: item_add_click, icon: 'add' },
			                    { line: true },
			                    { text: '确认修改', click: item_update_click, icon: 'modify' },
			                    { line: true },
			                    { text: '删除', click: item_del_click, img: 'js/lib/ligerUI/skins/icons/delete.gif' }
			                    ],
				                 onSelectRow: function (rowdata, rowindex)
		                    {
		                    	enabledEdit: true;
		                    	clickToEdit: true;
		                    	isScroll: false;
		                        $("#txtrowindex").val(rowindex);
		                    },
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
	      function getSelected()
	        { 
	            var row = g.onSelectedRow();
	            if (!row) { alert('请选择行'); return; }
	            alert(JSON.stringify(row));
	        }
	      function item_query_click()
	        {
	            g.options.data = $.extend(true,{}, DeptData);
	            g.showFilter();
	        }
	      function item_update_click()
	      {
	    	  g.endEdit();
	    	  var row = g.getSelectedRow();
	    	  var data={"departmentid":row.departmentid,"departmentname":row.departmentname,"starttimeam":row.starttimeam,
					"endtimeam":row.endtimeam,"starttimepm":row.starttimepm,"endtimepm":row.endtimepm,"parentid":row.parentid};
	            if (!row) { alert('请选择行'); return; }
	            $.ajax({
	    			type:"Get",
	    			url:"Dept?opt=update&timeid="+new Date(),
	    				data:{json:JSON.stringify(data)},

	    				success:function(msg){
	    					},
	    					dataType:"text",
	    					complete:function(){
	    						alert("修改数据成功");
	    						parent.window.$("#deptmg").click();
	    					}
	    		});
	      }
	      function item_add_click()
	      {
	    	  $.ligerDialog.open({
              height:400,
              width: 400,
              title : '新增部门',
              url: 'DeptDialog.html', 
              showMax: false,
              showToggle: true,
              showMin: false,
              isResize: true,
              slide: false
          });
	    	  
	      }
	      function item_del_click(){
	    	  alert("请确保一级部门里没有员工，否则删除失败");
	    	  var row = g.getSelectedRow();
	            if (!row) { alert('请选择行'); return; };
	            $.ajax({
	    			type:"Get",
	    			url:"Dept?opt=delete&timeid="+new Date(),
	    				data:{
	    					data:row.departmentid
	    				},
	    				success:function(msg){
	    				
	    					if(msg='success'){
	    						alert("删除数据成功");
	    					}else{
	    						alert("请确保一级部门里没有员工，删除失败");
	    					}
	    					},
	    					complete:function(msg){
	    						
	    							
		    						g.deleteSelectedRow();
		    					
	    					}
	    		});
	    	  
	      }
	     
        });
        function btn_managedept(rowid){
	    	  var row = g.getSelectedRow();
	            if (!row) { alert('请选择行'); return; };
	    	  $.ligerDialog.open({
	              height:1000,
	              width: 1000,
	              title : '管理子部门',
	              url: 'DeptMg.html', 
	              data:row.departmentid,
	              showMax: false,
	              showToggle: true,
	              showMin: false,
	              isResize: true,
	              slide: false
	            });
      }   
    </script>
</head>
<body style="padding: 4px; overflow: hidden;">
    <div class="l-loading" style="display: block" id="pageloading">
    </div>
    <div id="maingrid4" style="margin: 0; padding: 0">
    </div>
    <div style="display: none;">
    </div>
</body>
</html>