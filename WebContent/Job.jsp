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
         var JobData="";
         
	      $.ajax({
	    	 type:"GET",
			url:"Job?opt=query&timeid="+new Date(),
			success:function(msg){
				JobData=msg;
				 window['g'] = 
			            $("#maingrid4").ligerGrid({
			                columns: [
			            { display: '职务编号', name: 'positionid', align: 'center', width: 120 },
			            { display: '职务名称', name: 'positionname', width:120, align: 'center',editor: { type: 'text' }}
			           ], data: $.extend(true,{},JobData), pageSize: 30,
			                toolbar: { items: [{ text: '高级自定义查询', click: item_query_click, icon: 'search2'},
			                	{ text: '增加', click: item_add_click, icon: 'add' },
			                    { line: true },
			                    { text: '修改', click: item_update_click, icon: 'modify' },
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
	      function item_query_click()
	        {
	            g.options.data = $.extend(true,{}, JobData);
	            g.showFilter();
	        }
	      function item_update_click()
	      {
	    	  g.endEdit();
	    	  var row = g.getSelectedRow();
	    	  var data={"positionname":row.positionname,"positionid":row.positionid};
	            if (!row) { alert('请选择行'); return; }
	            $.ajax({
	    			type:"Get",
	    			url:"Job?opt=update&timeid="+new Date(),
	    				data:{json:JSON.stringify(data)},
	    				success:function(msg){
	    					},
	    					dataType:"text",
	    					complete:function(){
	    						alert("修改数据成功");
	    						parent.window.$("#jobmg").click();
	    					}
	    		});
	      }
	      function item_add_click()
	      {
	    	  $.ligerDialog.open({
	                height:300,
	                width: 300,
	                title : '新增职务',
	                url: 'JobDialog.html', 
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
	    			url:"Job?opt=delete&timeid="+new Date(),
	    				data:{
	    					data:row.positionid
	    				},
	    				success:function(msg){
	    					},
	    					complete:function(msg){
	    						if(msg=='success'){
	    							alert("删除数据成功");
		    						g.deleteSelectedRow();
		    					}
	    						else{
	    							alert('删除失败');
	    							}
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