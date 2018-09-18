<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
<base href="<%=basePath%>"></base>
 <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/uploadify/swfobject.js"></script>  
<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.uploadify.v2.0.1.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/demo/demo.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_blue.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_page.css"/>
<script type="text/javascript">  
//加载uploadify控件
var count=0;
var compelete=0;
 $(function() { 
	 $("#fileupload").uploadify({ 
         /*注意前面需要书写path的代码*/ 
         
         'uploader'       : '<%=path%>/js/uploadify/uploadify.swf', 
         'script'         : '<%=path%>/webUpload/FileUpload', 
         'cancelImg'      : '<%=path%>/pic/tech/jquery/uploadify/cancel.png', 
         'queueID'        : 'fileQueue', //和存放队列的DIV的id一致 
         'fileDataName'   : 'fileupload', //和以下input的name属性一致 
         'auto'           : false, //是否自动开始 
         'multi'          : true, //是否支持多文件上传 
         'buttonText'     : '浏览', //按钮上的文字 
         //'simUploadLimit' : 3, //一次同步上传的文件数目 
         //'sizeLimit'      : 19871202, //设置单个文件大小限制 
         //'queueSizeLimit' : 2, //队列中同时存在的文件个数限制 
         'fileDesc'       : '支持格式:pdf,jpg', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
         'fileExt'        : '*.pdf;*.jpg;*.mp3',//允许的格式   
     	onSelect: function(e, queueId, fileObj) {  
		      	
	  		},
     	onComplete: function (event, queueID, fileObj, response, data) { 
         	if('dabhFileNoexists'==response){
         		alert("请先上传附件");
         		$('#fileupload').uploadifyClearQueue();
         	}else{
         		compelete++;
					$('#progressSta').html("<span class='info'>"+compelete+"/" + count + "</span>");
					var prog=(compelete*100/count).toFixed(2);
					$('#progressdiv').progressbar('setValue', prog); 
					
					if(compelete==count){
						$('#progressdiv').progressbar('setValue', 100);
						$.messager.alert('提醒',"文件上传成功！！！",'info');
						$('#resultDatasfj').datagrid('reload');
					}
				}
			}, 
			onError: function(event, queueID, fileObj) { 
     	          alert("文件:" + fileObj.name + "上传失败"); 
		 	}, 
			onCancel: function(event, queueID, fileObj){ 
				//alert("取消了" + fileObj.name); 
			} ,
			onSelectOnce  : function(event,data) {//当文件选择对话框关闭时触发
				count=data.fileCount;	
				$("#selectfiledata").text(count);
				compelete=0;	
				$('#progressdiv').progressbar('setValue', 0.0);
			},
			'method'         : 'GET',
			scriptData:{
				'file_pk_id':$("#file_pk_id ").val()
				},
			onQueueComplete :function (stats){
				
			    }
           }); 
		 $('#resultDatasfj').datagrid({
		        autoRowHeight:true,
		        rownumbers:true,
			    fitColumns:true,
			    pagination:true,
			    singleSelect:false,
			    cache:false,
			    //toolbar : "#optoolbar",
				idField:'id',
				columns:[[
			        {field:'fileid',title:"<input type='checkbox' value='on'/>",align:'center',widht:50,
			        	formatter:function(value,row,index){
							if(value==undefined||value=='') return;
							var del = '<div class="datagrid-cell-check" ><input type="checkbox" name="id" value="'+value+'"/></div>';
							return del;
			        	}
					},
					{field:'filename',title:'文件名',width:150,align:'center'},
					{field:'fileuploaddate',title:'文件上传时间',width:100,align:'center',
						formatter:function(value,row,index){
							if(row.f_name == undefined||row.f_name=='') return;
							var del = value.split(" ")[0];
							return del;

			        	}},
					{field:'filesize',title:'文件大小',width:100,align:'center'}
					
	
			    ]],
			    url:"<%=path%>/webUpload/selectAllWebFileByPage?file_pk_id="+$('#file_pk_id').val(),
			    rowStyler: function(index,row){   
	  			if(index%2==0){    
	  		    	return 'background-color:#fff;font-weight:bold;'; 
	  		    }else{
	  		    	return 'background-color:#f4f8fb;font-weight:bold;';  
	  		    }               
	  		 },
		    	onLoadSuccess:function(data){//清空勾选项,定位第一行
	  			$('#resultDatas1').datagrid('clearChecked'); 
	  			$('#resultDatas1').datagrid('scrollTo',0);
	  		},
	  		toolbar: '#tb1'
	  		
			});
	       
           });
           function uploadifyUpload(){
        	       
                  if($("#selectfiledata").text() == "0"){
                	  $.messager.alert("提示信息","请选择文件后在上传","info");
                	  return;
                  }   
                  $('#fileupload').uploadifySettings('scriptData',{'file_pk_id ':$("#file_pk_id").val()});
                  $('#fileupload').uploadifyUpload();
                  
           }
           
           
           function deleteFj(){
        	   
        	   var rows = $('#resultDatasfj').datagrid('getChecked');
    		   // 删除公文操作 选中一条记录,
    		    if(rows.length == 0 || rows.length >1){
    				$.messager.alert('提示', '<h2><center>请有且选择一条附件进行删除！</center></h2>', 'error');
    				return;
    			}
    		    // 进行post请求
    		    $.messager.confirm('确认','您确认想要该附件吗？',function(r){    
				    if (r){    
				    	  $.post('<%=path%>/webUpload/deleteFile',
			         		   		{ "file_pk_id":rows[0].fileid},
			         			function(data){
			            	        
			      				if(data.code=="1"){
			      					 $('#resultDatasfj').datagrid('reload');
			      					 $.messager.alert("提示信息","删除成功","info");
			      					
			      					 
			                          
			                      }else{
			                    	  $.messager.alert("提示信息","删除失败","info");
			                      }
			         		},'json');   
				    }    
				});  
    		  
        	   
           }
</script>
</head>
  <body>
  <input type="hidden" id="file_pk_id" value="${file_pk_id }"/>

		<div id="fileQueue" style="display: none;"></div> 
		<table style="margin-top: 23px;" >
			<tr>
				<td style="padding-left: 25px;"><input type="file" name="fileupload" id="fileupload"  width="100px"/></td>
				<td style="padding-left: 35px;">
					<a onclick="uploadifyUpload()" class="easyui-linkbutton">上传</a>  
				</td>		
			</tr>		
		</table>
		<div style="margin-top: 18px;padding-left: 25px;">
			<table >
				<tr>
					<td>文件上传进度:<div class="easyui-progressbar" id="progressdiv" style="width: 100px; height: 20px; line-height: 20px;"/></td>
					<td>选择文件数量:<span id="selectfiledata">0</span></td>
				</tr>
				
			</table>
		</div>

  <div id="resultDatasfj">
  
  
  </div>
  
   <!-- 菜单按钮 ,增加,删除,修改 -->
<div id="tb1">
<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteFj()">删除</a>
</div>
 
</body>
</html>