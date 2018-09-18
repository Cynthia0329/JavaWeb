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
			<td class="search_head_td head_width15">专辑名称：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_albm_name" name="albm_name"   class="search_input"/>
			</td>
			<td class="search_head_td head_width15">专辑所属歌手：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_singer_id" name="singer_name"   class="search_input"   />
			</td>	
            <td class="search_head_td head_width15">专辑简介：</td>
			<td class="search_content_td content_width25">
				<input type="text" id="query_albm_jianjie" name="albm_jianjie"  class="search_input"    />
			</td>		
		  </tr>
          
		   <tr>
			<td  colspan="6" class="search_button" align="center">
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
<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAlbm()">增加</a>
</div>

 
<script type="text/javascript">
	 
	
	//初始化表格
	$(function(){
	    $('#progressdiv').progressbar('setValue', 0.0);       
	    $('#resultDatas').datagrid({//表格形式展示数据
	        autoRowHeight:true,
	        rownumbers:true,
		    fitColumns:true,
		    pagination:true,
		    singleSelect:false,
		    cache:false,
		    //toolbar : "#optoolbar",
			idField:'albm_id',
			columns:[[
		      
				{field:'albm_name',title:'专辑名',width:50,align:'center',
				//formatter调用公共函数
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.singer_id+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'singer_name',title:'专辑所属歌手',width:50,align:'center'},
				{field:'albm_date',title:'专辑发行日期',width:50,align:'center'},
				{field:'albm_jianjie',title:'专辑简介',width:50,align:'center'},
				
				{field:'cz',title:'操作',width:80,align:'center',formatter:function(value,row,index){
					if(row.singer_name == undefined||row.singer_name=='') return;
					//var del = "<a onclick=viewDetail('"+row.xm_id+"')>详情</a>&nbsp;&nbsp<a onclick=viewFile('"+row.xm_id+"')>查看附件</a>&nbsp;&nbsp<a onclick=uploadFile('"+row.xm_id+"')>上传附件</a>&nbsp;&nbsp<a onclick=updateJtJsYdJg('"+row.xm_id+"')>修改</a>&nbsp;&nbsp<a onclick=deleteJtJsYdJg('"+row.xm_id+"')>删除</a>";
					var del = "<img src='<%=path%>/js/easyui/themes/icons/edit_add.png' onclick=uploadFile('"+row.albm_id+"')><a onclick=uploadFile('"+row.albm_id+"')>上传照片</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/pencil.png' onclick=updateAlbm('"+row.albm_id+"')><a onclick=updateAlbm('"+row.albm_id+"')>修改</a> &nbsp;&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteSinger('"+row.albm_id+"'))><a onclick=deleteSinger('"+row.albm_id+"')>删除</a>";
					return del;

					
	        	}}

		    ]],
		    //分页controller
		    url:"<%=path%>/webAlbm/selectAllWebAlbmByPage", 
		   
	    	onLoadSuccess:function(data){//清空勾选项,定位第一行
     			$('#resultDatas').datagrid('clearChecked'); //我勾选了这个按钮
     			$('#resultDatas').datagrid('scrollTo',0);
     		},
     		toolbar: '#tb'
     		
		});
	 });
    
	 //查询表单   
    function submitForm(){
     
		$('#resultDatas').datagrid({
			//分页查询（地址到controller里面控制器方法）
			url:"<%=path%>/webAlbm/selectAllWebAlbmByPage?time="+new Date().getTime(), 
			//datagrid动态查询	 
			queryParams: {
				  
				   'albm_name':$("#query_albm_name").val(),
				   'singer_name':$("#query_singer_name").val(),
				   'albm_jianjie':$("#query_albm_jianjie").val()
				
				 
				   
				}
		});
	}

	//清空
	function clearForm() {
		$("#frm").form("clear");//$('#').form('clear')会将所有框都清空
		
	}
	$.ajaxSetup ({
		  cache: false //关闭AJAX相应的缓存
	});
	
	
	//删除专辑界面
	function deleteSinger(albm_id){
	  
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){  
			    	//post请求功能  （controller里面deleteWebAlbm方法）
			         $.post('<%=path%>/webAlbm/deleteWebAlbm',
			   		   		{"albm_id":albm_id},
			   		   		//数据函数
			   			function(data){
							if(data.code=="1"){
								
			                    $('#resultDatas').datagrid('reload');//表格数据更新
			                    $.messager.alert('提醒', '删除专辑成功', 'info'); 
			                }else{
			                	$.messager.alert('提醒', '删除专辑失败', 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}
       
	    //上传专辑海报
        function uploadFile(albm_id){
            
			    var url="<%=path%>/webUpload/toUploadFile?file_pk_id="+albm_id;//跳转到文件上传窗口,使用window.showModlog窗口
			 
			    var iWidth = 600;
			    var iHeight = 500;
			    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
			    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
			    var win = window.open(url, "上传歌手照片", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
			
        }
        
      
        
     //增加专辑
		function addAlbm() {
			$("<div id='addAlbm'/>").dialog({
                title: '新增专辑',    
			    width: 700,    
			    height: 300,  
			    href:'<%=path%>/webAlbm/toWebAlbmAddOrUpdate', 
			    closed: false,    
			    cache: false, 
			    modal: true,
			    onClose:function(){
			    	
			    	$(this).dialog('destroy');//关闭后销毁
			    },
			    buttons:[{
					text:'保存',
					//按钮的事件响应
					handler:function(){
						
						// 第一步 进行必填项校验
						  var albm_name=$("#albm_name").textbox('getValue');//获取输入框内容
						 
				    	  if(albm_name ==""){ 
				    		  $.messager.alert("提示信息","专辑名称不能为空","info");
				    		  return ;
				    	  }
		               
		                  // 进行post请求
		                  $.post('<%=path%>/webAlbm/WebAlbmAddOrUpdateSubmit',
		                		  $("#webAlbmAdd_frm").serialize(),//序列化表单值
		             			function(data){
		                	        
		          				if(data.code=="1"){
		          					 $('#resultDatas').datagrid('reload');
		          					 $("#addAlbm").dialog('destroy');
		          					 $.messager.alert("提示信息","保存成功","info");
	                                  
		                          }else{
		                        	  $.messager.alert("提示信息","保存失败","info");
		                          }
		             		},'json');
				    	  
						
					}
				},{
					text:'关闭',
					handler:function(){
						$("#addAlbm").dialog('destroy');
					}
				}]

            });
		}
		//专辑修改
		function updateAlbm(albm_id) {
			 
					
			$("<div id='updateAlbm'/>").dialog({
		                title: '修改歌手信息',    
					    width: 700,    
					    height: 300,  
					    href:'<%=path%>/webAlbm/toWebAlbmAddOrUpdate?albm_id='+albm_id,  
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
								 var albm_name=$("#albm_name").textbox('getValue');
								 
						    	  if(albm_name ==""){ 
						    		  $.messager.alert("提示信息","专辑名称不能为空","info");
						    		  return ;
						    	  }
				                  // 进行post请求
				                 
				                  $.post('<%=path%>/webAlbm/WebAlbmAddOrUpdateSubmit',
				                		  $("#webAlbmAdd_frm").serialize(),
				             			function(data){
				                	        
				          				if(data.code=="1"){
				          					 $('#resultDatas').datagrid('reload');
				          					 $("#updateAlbm").dialog('destroy');
				          					 $.messager.alert("提示信息","修改成功","info");
			                                  
				                          }else{
				                        	  $.messager.alert("提示信息","修改失败","info");
				                          }
				             		},'json');
						    	  
								
							}
						},{
							text:'关闭',
							handler:function(){
								$("#updateAlbm").dialog('destroy');
							}
						}]

		            });
		}
		
		//专辑详情
		  function viewDetail(albm_id){
			  $("<div id='viewAlbm'/>").dialog({
	               title: '查看专辑信息',    
				    width: 700,    
				    height: 300,  
				    //href=   这后面是字符串，其中“？albm_id" 是指参数名为"albm_id"，这个参数名为"albm_id"要带上某个值，就用+号连接，后面的变量id就是具体要带的具体值
				    href:'<%=path%>/webAlbm/viewWebAlbm?albm_id='+albm_id,   
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

