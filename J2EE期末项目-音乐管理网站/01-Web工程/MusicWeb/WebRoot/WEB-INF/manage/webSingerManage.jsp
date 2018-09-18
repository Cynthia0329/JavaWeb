<%@page contentType="text/html; charset=UTF-8"%>

<%
	String path = request.getContextPath();
	//request.getContextPath()拿到的是你的web项目的根路径，就是webContent(MyEclipse中是webRoot)
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//request.getSchema()可以返回当前页面使用的协议，http 或是 https;
	//request.getServerName()可以返回当前页面所在的服务器的名字;
	//request.getServerPort()可以返回当前页面所在的服务器使用的端口
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
	</div>
  <div class="clear"></div>
  <div class="nrBox">
	<div class='m1'>
         <table id="resultDatas" width="100%" height="360px;" ></table>
    </div>
 <!-- 菜单按钮 ,增加,删除,修改 -->
<div id="tb">
<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addSinger()">增加</a>
<!--在easy-ui里面，data-options可以轻松定义列表的属性 -->
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
			idField:'singer_id',
			columns:[[
		      
				{field:'singer_name',title:'歌手姓名',width:50,align:'center',
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.singer_id+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'singer_birthdate',title:'歌手出生日期',width:50,align:'center'},
				{field:'singer_shuxiang',title:'歌手属相',width:50,align:'center'},
				{field:'singer_xingzuo',title:'歌手星座',width:50,align:'center'},
				{field:'singer_aihao',title:'歌手爱好',width:70,align:'center'},
				{field:'singer_jianjie',title:'歌手简介',width:80,align:'center'},
				
				{field:'cz',title:'操作',width:80,align:'center',formatter:function(value,row,index){
					if(row.singer_name == undefined||row.singer_name=='') return;
					//var del = "<a onclick=viewDetail('"+row.xm_id+"')>详情</a>&nbsp;&nbsp<a onclick=viewFile('"+row.xm_id+"')>查看附件</a>&nbsp;&nbsp<a onclick=uploadFile('"+row.xm_id+"')>上传附件</a>&nbsp;&nbsp<a onclick=updateJtJsYdJg('"+row.xm_id+"')>修改</a>&nbsp;&nbsp<a onclick=deleteJtJsYdJg('"+row.xm_id+"')>删除</a>";
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.singer_id+"')><a onclick=viewDetail('"+row.singer_id+"')>详情</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_add.png' onclick=uploadFile('"+row.singer_id+"')><a onclick=uploadFile('"+row.singer_id+"')>上传照片</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/pencil.png' onclick=updateSinger('"+row.singer_id+"')><a onclick=updateSinger('"+row.singer_id+"')>修改</a> &nbsp;&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteSinger('"+row.singer_id+"'))><a onclick=deleteSinger('"+row.singer_id+"')>删除</a>";
					return del;

					
	        	}}

		    ]],
		    url:"<%=path%>/webSinger/selectAllWebSingerByPage",
		   
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
	
	//删除歌手信息
	
	function deleteSinger(singer_id){
	  
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			         $.post('<%=path%>/webSinger/deleteWebSinger',
			   		   		{"singer_id":singer_id},	
			   			function(data){
							if(data.code=="1"){
								
			                    $('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '删除歌手信息成功', 'info'); 
			                }else{
			                	$.messager.alert('提醒', '删除歌手信息失败', 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}
       
	    //上传歌手照片
        function uploadFile(singer_id){
            
			    var url="<%=path%>/webUpload/toUploadFile?file_pk_id="+singer_id;//跳转到文件上传窗口,使用window.showModlog窗口
			 
			    var iWidth = 600;
			    var iHeight = 500;
			    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
			    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
			    var win = window.open(url, "上传歌手照片", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
			
        }
        
      
        
     
		function addSinger() {
			$("<div id='addSinger'/>").dialog({
                title: '新增歌手',    
			    width: 700,    
			    height: 300,  
			    href:'<%=path%>/webSinger/toWebSingerAddOrUpdate', 
			    closed: false,    
			    cache: false, 
			    modal: true,
			    onClose:function(){
			    	
			    	top.window.$(this).dialog('destroy');
			    },
			    buttons:[{
					text:'保存',
					handler:function(){
						
						// 第一步 进行必填项校验
						  var singer_name=$("#singer_name").textbox('getValue');
						 
				    	  if(singer_name ==""){ 
				    		  $.messager.alert("提示信息","歌手姓名不能为空","info");
				    		  return ;
				    	  }
		               
		                  // 进行post请求
		                  $.post('<%=path%>/webSinger/WebSingerAddOrUpdateSubmit',
		                		  $("#webSingerAdd_frm").serialize(),
		             			function(data){
		                	        
		          				if(data.code=="1"){
		          					 $('#resultDatas').datagrid('reload');
		          					 $("#addSinger").dialog('destroy');
		          					 $.messager.alert("提示信息","保存成功","info");
	                                  
		                          }else{
		                        	  $.messager.alert("提示信息","保存失败","info");
		                          }
		             		},'json');
				    	  
						
					}
				},{
					text:'关闭',
					handler:function(){
						$("#addSinger").dialog('destroy');
					}
				}]

            });
		}
		
		//修改歌手信息
		
		function updateSinger(singer_id) {
			 
					
			$("<div id='updateSinger'/>").dialog({
		                title: '修改歌手信息',    
					    width: 700,    
					    height: 300,  
					    href:'<%=path%>/webSinger/toWebSingerAddOrUpdate?singer_id='+singer_id,  
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
								 var singer_name=$("#singer_name").textbox('getValue');
								 
						    	  if(singer_name ==""){ 
						    		  $.messager.alert("提示信息","歌手姓名不能为空","info");
						    		  return ;
						    	  }
				                  // 进行post请求
				                 
				                  $.post('<%=path%>/webSinger/WebSingerAddOrUpdateSubmit',
				                		  $("#webSingerAdd_frm").serialize(),
				             			function(data){
				                	        
				          				if(data.code=="1"){
				          					 $('#resultDatas').datagrid('reload');
				          					 $("#updateSinger").dialog('destroy');
				          					 $.messager.alert("提示信息","修改成功","info");
			                                  
				                          }else{
				                        	  $.messager.alert("提示信息","修改失败","info");
				                          }
				             		},'json');
						    	  
								
							}
						},{
							text:'关闭',
							handler:function(){
								$("#updateSinger").dialog('destroy');
							}
						}]

		            });
		}
		
		
		  function viewDetail(singer_id){
			  $("<div id='viewSinger'/>").dialog({
	               title: '查看歌手信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/webSinger/viewWebSinger?singer_id='+singer_id,   
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

