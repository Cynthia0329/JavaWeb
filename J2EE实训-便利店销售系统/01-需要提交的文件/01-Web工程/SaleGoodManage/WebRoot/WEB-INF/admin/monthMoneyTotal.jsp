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
    <center><font color="red" size="20;">便利店月利润统计</font></center>
	<div class="searchinfo">
    	<form action="" id="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d3dbe0">
		  
		  <tr>
			<td class="search_head_td head_width15">统计开始月份：</td>
			<td class="search_content_td content_width15">
				<!-- 开始月份选择下拉框 -->
				 <select id="startMonth" style="width: 200px;">
				     <option value="">选择统计开始月份</option>
				     <option value="1">1</option>
				     <option value="2">2</option>
				     <option value="3">3</option>
				     <option value="4">4</option>
				     <option value="5">5</option>
				     <option value="6">6</option>
				     <option value="7">7</option>
				     <option value="8">8</option>
				     <option value="9">9</option>
				     <option value="10">10</option>
				     <option value="11">11</option>
				     <option value="12">12</option>
				 
				 </select>
			</td>
			<td class="search_head_td head_width15">统计结束月份：</td>
			<td class="search_content_td content_width15">
			<!-- 结束月份选择下拉框 -->
				      <select id="endMonth" style="width: 200px;">
				     <option value="">选择统计开始月份</option>
				     <option value="1">1</option>
				     <option value="2">2</option>
				     <option value="3">3</option>
				     <option value="4">4</option>
				     <option value="5">5</option>
				     <option value="6">6</option>
				     <option value="7">7</option>
				     <option value="8">8</option>
				     <option value="9">9</option>
				     <option value="10">10</option>
				     <option value="11">11</option>
				     <option value="12">12</option>
				 
				 </select>
			</td>
				
		  </tr>
          
		   <tr>
			<td  colspan="6" class="search_button">
				<div class="btns m1">
					<ul>
						<li><input type="button" value="统计" class="select_btn"
							onclick="total()" /></li>
					
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
	    <iframe id="totalIframe" width="100%" height="500px;"  src="" style="border: opx;">
	    
	    </iframe>
         <table id="resultDatas"  ></table>
    </div>

 
<script type="text/javascript">
	   function total(){
	   		//定义开始月份和结束月份
		     var startMonth=$("#startMonth").val();
		     var endMonth=$("#endMonth").val();
		     //如果开始月份和结束月份为空
		     if(startMonth==""||endMonth==""){
		     //提示不能为空
		    	 $.messager.alert('提醒', '统计开始月份和结束月份不能为空', 'info'); 
		    	 return;
		     }
		     //如果开始月份等于结束月份
		     if(startMonth==endMonth){
		     //提示月份相等，无法统计
		    	 $.messager.alert('提醒', '统计开始月份和结束月份相等,不能统计', 'info'); 
		    	 return;
		    	 
		     }
		     //开始月份大于结束月份
		     if(startMonth>endMonth){
		     //不成立，不能统计
		    	 $.messager.alert('提醒', '统计开始月份大于结束月份,不能统计', 'info'); 
		    	 return;
		    	 
		     }
		     //开始月份小于结束月份时执行下列操作
		     //从控制器total的showMonthMoneyTotal中获取月利润
		     var url="<%=path%>/total/showMonthMoneyTotal?startMonth="+startMonth+"&endMonth="+endMonth;
		     $("#totalIframe").attr("src",url);
		   
		   
	   }
	

</script>
</body>
</html>

