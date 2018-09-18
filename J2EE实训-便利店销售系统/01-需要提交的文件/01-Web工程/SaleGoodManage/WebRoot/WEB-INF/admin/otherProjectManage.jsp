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
			<td class="search_head_td head_width15">支出项目名称：</td>
			<td class="search_content_td content_width15">
			<!-- 输入项目名称 -->
				<input type="text" id="query_projectName" name="projectName"   class="search_input"/>
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
<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addOtherProject()">增加</a>
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
			idField:'id',
			columns:[[
				//第一列：支出项目名称
				{field:'projectName',title:'支出项目名称',width:120,align:'center',
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.id+"')>"+value+"</a>";
						return del;
		        	}},
		        //第二列：支出项目时间
				{field:'projectDate',title:'支出项目时间',width:80,align:'center',
		        formatter:function(value,row,index){
					
					return value.replace(' 00:00:00', '');;
	        	}},
	        	//第三列：支出项目金额
				{field:'projectPrice',title:'支出项目金额',width:80,align:'center'},
				//第四列：支出项目备注
				{field:'projectRemark',title:'支出项目备注',width:50,align:'center'},
				
				{field:'cz',title:'操作',width:140,align:'center',formatter:function(value,row,index){
					if(row.projectName == undefined||row.projectName=='') return;
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.id+"')><a onclick=viewDetail('"+row.id+"')>详情</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=updateOtherProject('"+row.id+"'))><a onclick=updateOtherProject('"+row.id+"')>修改</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteOtherProject('"+row.id+"'))><a onclick=deleteOtherProject('"+row.id+"')>删除</a>";
					return del;

					
	        	}
					
				
				}

		    ]],
		    url:"<%=path%>/otherProject/selectAllOtherProjectByPage",
		   
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
			//?time="+new Date().getTime() 根据最新时间得到最新表单
			//到otherProject控制器的selectAllOtherProjectByPage操作中去
			url:"<%=path%>/otherProject/selectAllOtherProjectByPage?time="+new Date().getTime(),
			//datagrid将查询的信息动态生成表单
			//queryParams：在url的请求中添加额外的参数
			queryParams: {
				  
				   'projectName':$("#query_projectName").val()
				 
				
				 
				   
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
	
	
	
	function deleteOtherProject(id){
	   
	 
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    //到控制器deleteOtherProject中执行删除操作
			         $.post('<%=path%>/otherProject/deleteOtherProject',
			   		   		{"id":id},
			   		   		//此处data时从otherProject控制器中deleteOtherProject里得到的返回值
			   			function(data){
			   			//返回码为1
							if(data.code=="1"){
							//删除成功
							//重新动态加载页面数据
			                  	$('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '删除其他支出项目信息成功', 'info'); 
			                }else{
			                //否则删除失败
			                   	$.messager.alert('提醒', '删除其他支出项目信息失败', 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}

	function addOtherProject() {
		$("<div id='addOtherProject'/>").dialog({
            title: '新增其他支出项目信息',    
		    width: 700,    
		    height: 300,  
		    href:'<%=path%>/otherProject/toOtherProjectAddOrUpdate', 
		    closed: false,    
		    cache: false, 
		    modal: true,
		    onClose:function(){
		    	
		    	$(this).dialog('destroy');
		    },
		    buttons:[{
				text:'保存',
				handler:function(){
					
					// 第一步 进行必填项校验
					//获取项目名称的值
					  var projectName=$("#projectName").textbox('getValue');
					 //获取项目价格的值
					  var projectPrice=$("#projectPrice").textbox('getValue');
					  //获取下项目日期的值
					  var projectDate=$("#projectDate").datebox('getValue');
					//项目名为空
					  if(projectName ==""){ 
					  //提示不能为空
			    		  $.messager.alert("提示信息","其他支出项目名称不能为空","info");
			    		  return ;
			    	  }
			    	  //项目价格为空
					  if(projectPrice ==""){ 
					  //提示不能为空
			    		  $.messager.alert("提示信息","其他支出项目金额不能为空","info");
			    		  return ;
			    	  }
			    	  //项目日期为空
					  if(projectDate ==""){ 
					  //提示不能为空
			    		  $.messager.alert("提示信息","其他支出项目时间不能为空","info");
			    		  return ;
			    	  }

	               
	                  // 进行post请求
	                  $.post('<%=path%>/otherProject/OtherProjectAddOrUpdateSubmit',
	                		 
	                		  $("#otherProjectAdd_frm").serialize(),
	                		  //function(data)中data指从控制器中OtherProjectAddOrUpdateSubmit操作返回的返回码
	             			function(data){
	                	        //数据为1
	          				if(data.code=="1"){
	          				//保存成功
	          					 $('#resultDatas').datagrid('reload');
	          					 $("#addOtherProject").dialog('destroy');
	          					 $.messager.alert("提示信息","保存成功","info");
                                  
	                          }else{
	                          	//否则保存失败
	                        	  $.messager.alert("提示信息","保存失败","info");
	                          }
	             		},'json');
			    	  
					
				}
			},{
				text:'关闭',
				handler:function(){
					$("#addOtherProject").dialog('destroy');
				}
			}]

        });
	}
	
	function updateOtherProject(id) {
		 
				
		$("<div id='updateOtherProject'/>").dialog({
	                title: '修改商品分类信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/otherProject/toOtherProjectAddOrUpdate?id='+id,  
				    closed: false,    
				    cache: false, 
				    modal: true,
				    onClose:function(){
				    	
				    	$(this).dialog('destroy');
				    },
				    buttons:[{
						text:'修改',
						handler:function(){
							// 第一步 进行必填项校验
							// 获取项目名称
							  var projectName=$("#projectName").textbox('getValue');
							//若项目名为空
							  if(projectName ==""){ 
							  //提示不能为空
					    		  $.messager.alert("提示信息","其他支出项目名称不能为空","info");
					    		  return ;
					    	  }
			                 //修改后的提交操作
			                  $.post('<%=path%>/otherProject/OtherProjectAddOrUpdateSubmit',
			                		  $("#otherProjectAdd_frm").serialize(),
			                		  //此处data值从控制器otherProject中OtherProjectAddOrUpdateSubmit操作获取的值
			             			function(data){
			                	        //返回1
			          				if(data.code=="1"){
			          				//动态重载
			          					 $('#resultDatas').datagrid('reload');
			          					 $("#updateOtherProject").dialog('destroy');
			          					 //修改成功
			          					 $.messager.alert("提示信息","修改成功","info");
		                                  
			                          }else{
			                          //否则修改失败
			                        	  $.messager.alert("提示信息","修改失败","info");
			                          }
			             		},'json');
					    	  
							
						}
					},{
						text:'关闭',
						handler:function(){
							$("#updateOtherProject").dialog('destroy');
						}
					}]

	            });
	}
	
	
     
		
		  function viewDetail(id){
			  $("<div id='viewOtherProject'/>").dialog({
	               title: '查看支出项目信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/otherProject/viewOtherProjectDetail?id='+id, 
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

