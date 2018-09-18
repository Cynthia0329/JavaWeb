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
			<td class="search_head_td head_width15">歌手姓名：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_singer_name" name="singer_name"   class="search_input"/>
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
				{field:'singer_aihao',title:'歌手爱好',width:100,align:'center'},
				{field:'singer_jianjie',title:'歌手简介',width:120,align:'center'},
				
				{field:'cz',title:'操作',width:80,align:'center',formatter:function(value,row,index){
					if(row.singer_name == undefined||row.singer_name=='') return;
					//var del = "<a onclick=viewDetail('"+row.xm_id+"')>详情</a>&nbsp;&nbsp<a onclick=viewFile('"+row.xm_id+"')>查看附件</a>&nbsp;&nbsp<a onclick=uploadFile('"+row.xm_id+"')>上传附件</a>&nbsp;&nbsp<a onclick=updateJtJsYdJg('"+row.xm_id+"')>修改</a>&nbsp;&nbsp<a onclick=deleteJtJsYdJg('"+row.xm_id+"')>删除</a>";
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.singer_id+"')><a onclick=viewDetail('"+row.singer_id+"')>详情</a>";
					return del;

					
	        	}}

		    ]],
		    url:"<%=path%>/webSinger/selectAllWebSingerByPage",
		   
	    	onLoadSuccess:function(data){//清空勾选项,定位第一行
     			$('#resultDatas').datagrid('clearChecked'); 
     			$('#resultDatas').datagrid('scrollTo',0);
     		}
     		
     		
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
	

		
		  function viewDetail(singer_id){
			  var url="<%=path%>/webSinger/toViewSinger?singer_id="+singer_id;//调整到音乐播放窗口
				 
			    var iWidth = 1000;
			    var iHeight = 700;
			    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
			    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
			    var win = window.open(url, "查看歌手信息", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	     }
		
		
	

</script>
</body>
</html>

