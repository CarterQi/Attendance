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
         var UserData="";
         
	      $.ajax({
	    	 type:"GET",
			url:"User?opt=query&timeid="+new Date(),
			success:function(msg){
				UserData=msg;
				 window['g'] = 
			            $("#maingrid4").ligerGrid({
			                columns: [
			            { display: '用户ID', name: 'adminID', align: 'center', width: 200 },
			            { display: '用户名', name: 'adminName', width: 200, align: 'center' ,editor:{type:'text'}}
			            
			                ], data: $.extend(true,{},UserData), pageSize: 30,
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
	            g.options.data = $.extend(true,{}, UserData);
	            g.showFilter();
	        }
	      function item_update_click()
	      {
	    	  g.endEdit();
	    	  var row = g.getSelectedRow();
	    	  var data={"adminid":row.adminID,"adminname":row.adminName};
	            if (!row) { alert('请选择行'); return; }
	            $.ajax({
	    			type:"Get",
	    			url:"User?opt=update&timeid="+new Date(),
	    				data:{json:JSON.stringify(data)},

	    				success:function(msg){
	    					},
	    					dataType:"text",
	    					complete:function(){
	    						alert("修改数据成功");
	    						parent.window.$("#usermg").click();
	    					}
	    		});
	      }
	      function item_add_click()
	      {
	    	  $.ligerDialog.open({
              height:400,
              width: 400,
              title : '新增用户',
              url: 'UserDialog.html', 
              showMax: false,
              showToggle: true,
              showMin: false,
              isResize: true,
              slide: false
          });
	    	  
	      }
	      function item_del_click(){
	    	  var row = g.getSelectedRow();
	            if (!row) { alert('请选择行'); return; };
	            $.ajax({
	    			type:"Get",
	    			url:"User?opt=delete&timeid="+new Date(),
	    				data:{
	    					data:row.adminID
	    				},
	    				success:function(msg){
	    				
	    					},
	    					complete:function(msg){
	    						
	    							alert("删除数据成功");
		    						g.deleteSelectedRow();
		    					
	    					}
	    		});
	    	  
	      }
	     
        });
  
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