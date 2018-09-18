<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
				 ${webSinger.singer_name }
			</td>
			<td class="search_head_td head_width15">歌手出生日期：</td>
			<td class="search_content_td content_width15">
			    <fmt:formatDate value='${webSinger.singer_birthdate}' pattern='yyyy-MM-dd'/>
				
			</td>	
            <td class="search_head_td head_width15">歌手属相：</td>
			<td class="search_content_td content_width25">
				${webSinger.singer_shuxiang }
			</td>		
		  </tr>
		   <tr>
			<td class="search_head_td head_width15">歌手星座：</td>
			<td class="search_content_td content_width15">
				${webSinger.singer_xingzuo}
			</td>
			<td class="search_head_td head_width15">歌手的爱好：</td>
			<td class="search_content_td content_width15">
				${webSinger.singer_aihao}
			</td>	
            <td class="search_head_td head_width15">歌手简介：</td>
			<td class="search_content_td content_width25">
				${webSinger.singer_jianjie}
			</td>		
		  </tr>
		
		</table>
		
        
        </form>   
	</div>
	<div class='m1' >
	     歌手照片:<c:forEach items="${webFilelists}" var="webFile">
			     <img src="<%=path%>/webImg/showImg?fileid=${webFile.fileid}" height="60px;" width="100px;">
		</c:forEach>
	
            
    </div>
	<div class='m1'>
         <table id="resultDatas" width="100%" height="360px;" ></table>
    </div>
<script type="text/javascript">
	 var singer_id="${webSinger.singer_id}";
	
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
				idField:'albm_id',
				columns:[[
			      
					{field:'albm_name',title:'专辑名',width:50,align:'center',
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
						var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=openAlbm('"+row.albm_id+"')><a onclick=openAlbm('"+row.albm_id+"')>打开专辑</a>";
						return del;

						
		        	}}

			    ]],
			    url:"<%=path%>/webAlbm/selectAllWebAlbmByPage?singer_id="+singer_id,
			   
		    	onLoadSuccess:function(data){//清空勾选项,定位第一行
	     			$('#resultDatas').datagrid('clearChecked'); 
	     			$('#resultDatas').datagrid('scrollTo',0);
	     		}
	     		
	     		
			});
		 });
  

	//清空
	function clearForm() {
		$("#frm").form("clear");
		
	}
	$.ajaxSetup ({
		  cache: false //关闭AJAX相应的缓存
	});
	
	
		
		  function openAlbm(albm_id){
			  
			  
			  var url="<%=path%>/webAlbm/viewWebAlbmDetail?albm_id="+albm_id;//调整到音乐播放窗口
				 
			    var iWidth = 1000;
			    var iHeight = 700;
			    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
			    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
			    var win = window.open(url, "查看专辑信息", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
			  
	     }
		
		
	

</script>
</body>
</html>

