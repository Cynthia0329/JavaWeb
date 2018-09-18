<%@page contentType="text/html; charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/demo/demo.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_blue.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_page.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style-search.css"/>
<html>
<head>
</head>
<body >
  <!--查询页面 -->
	<div class="searchinfo">
    	<form action="" id="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d3dbe0">
		  <tr>
			<td class="search_head_td head_width15">用户名：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_username" name="username"   class="search_input"/>
			</td>
			<td class="search_head_td head_width15">用户地址：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_useraddress" name="useraddress"   class="search_input"   />
			</td>	
            <td class="search_head_td head_width15">用户状态：</td>
			<td class="search_content_td content_width25">
				<input type="text" id="query_userzt" name="userzt"  class="search_input"    />
			</td>		
		  </tr>
          
		   <tr>
			<td  colspan="6" class="search_button">
				<div class="btns m1">
					<ul>
						<li><input type="button" value="查询" class="select_btn"
							onclick="submitForm()" /></li>
						<li><input type="reset" value="清空" class="reset_btn"
							onclick="clearForm()" /></li>
					</ul>
				</div>
			</td>
		</tr>
		  
		</table>
		
        
        </form>   
	</div>
  <div class="clear"></div>
  <div class="nrBox">
	<div class='m1'>
         <table id="resultDatas" width="100%" height="360px;" ></table>
    </div>
 <!-- 菜单按钮 ,增加,删除,修改 -->
<div id="tb">

</div>

 
<script type="text/javascript">
	 
	
	//初始化表格
	$(function(){
	    $('#progressdiv').progressbar('setValue', 0.0);       
	    $('#resultDatas').datagrid({
	        autoRowHeight:true,
	        rownumbers:true,
		    fitColumns:true,
		    pagination:true,
		    singleSelect:false,
		    cache:false,
		    //toolbar : "#optoolbar",
			idField:'userid',
			columns:[[
		      
				{field:'username',title:'用户名',width:120,align:'center',
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.userid+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'usersex',title:'用户性别',width:80,align:'center'},
				{field:'usercard',title:'用户身份证号码',width:80,align:'center'},
				{field:'userbirthdate',title:'用户出生日期',width:50,align:'center'},
				{field:'useraddress',title:'用户籍贯',width:70,align:'center'},
				{field:'userzt',title:'用户状态',width:50,align:'center',formatter:function(value,row,index){
					
					if(value=='1'){
						return "审核通过";
					}else if(value=='0'){
						
						return "审核待审核";
					}
					

					
	        	}},
				
				{field:'cz',title:'操作',width:140,align:'center',formatter:function(value,row,index){
					if(row.username == undefined||row.username=='') return;
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.userid+"')><a onclick=viewDetail('"+row.userid+"')>详情</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/pencil.png' onclick=ShUser('"+row.userid+"','"+row.userzt+"')><a onclick=ShUser('"+row.userid+"','"+row.userzt+"')>审核</a> &nbsp;&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteUser('"+row.userid+"'))><a onclick=deleteUser('"+row.userid+"')>删除</a>";
					//var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.userid+"')><a onclick=viewDetail('"+row.userid+"')>详情</a>
					//	    &nbsp<img src='<%=path%>/js/easyui/themes/icons/pencil.png' onclick=ShUser('"+row.userid+"','"+row.userzt+"')><a onclick=ShUser('"+row.userid+"','"+row.userzt+"')>审核</a> 
					//&nbsp;&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteUser('"+row.userid+"'))><a onclick=deleteUser('"+row.userid+"')>删除</a>";
					return del;

					
	        	}
					
				
				}

		    ]],
		    url:"<%=path%>/webUser/selectAllWebUserByPage",
		   
	    	onLoadSuccess:function(data){//清空勾选项,定位第一行
     			$('#resultDatas').datagrid('clearChecked'); 
     			$('#resultDatas').datagrid('scrollTo',0);
     		},
     		toolbar: '#tb'
     		
		});
	 });
    
	 //查询表单   
    function submitForm(){
      
       
		$('#resultDatas').datagrid({
			url:"<%=path%>/webUser/selectAllWebUserByPage?time="+new Date().getTime(),
			queryParams: {
				  
				   'username':$("#query_username").val(),
				   'userzt':$("#query_userzt").val(),
				   'useraddress':$("#query_useraddress").val()
				
				 
				   
				}
		});
	}

	//清空
	function clearForm() {
		$("#frm").form("clear");
		
	}
	$.ajaxSetup ({
		  cache: false //关闭AJAX相应的缓存
	});
	
	
	
	function deleteUser(userid){
	   
	 
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			         $.post('<%=path%>/webUser/deleteWebUser',
			   		   		{"userid":userid},
			   			function(data){
							if(data.code=="1"){
			                  	$('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '删除用户成功', 'info'); 
			                }else{
			                   	$.messager.alert('提醒', 删除用户失败, 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}

	
	
	function ShUser(userid,userzt){
		
		if(userzt=="1"){
			$.messager.alert('提醒', '用户已审核,不需要审核', 'info'); 
			return;
			
		}else{
			
			 $.messager.confirm('确认','您确认想要审核记录吗？',function(r){    
				    if (r){    
				         $.post('<%=path%>/webUser/shWebUser',
				   		   		{"userid":userid},
				   			function(data){
								if(data.code=="1"){
				                  	$('#resultDatas').datagrid('reload');
				                    $.messager.alert('提醒', '审核通过', 'info'); 
				                }else{
				                   	$.messager.alert('提醒', '审核失败', 'info'); 
				                }
				   		});    
				    }    
				}); 
			
			
			
			
		}
		
	}
        
     
		
		  function viewDetail(userid){
			  $("<div id='viewWebUser'/>").dialog({
	               title: '查看用户信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/webUser/viewWebUser?userid='+userid, 
				    closed: false,    
				    cache: false, 
				    modal: true,
				    onClose:function(){
				    	
				    	$(this).dialog('destroy');
				    }
				});
	     }
		
		
	

</script>
</body>
</html>

