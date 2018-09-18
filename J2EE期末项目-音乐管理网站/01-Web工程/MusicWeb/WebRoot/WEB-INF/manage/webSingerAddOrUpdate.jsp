<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_blue.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_page.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style-search.css"/>
<html>
<head>
</head>
<body>
	<div class="nrBody" style="height:200px;overflow-y:auto;overflow-x:hidden;position:relative;">
		<form action=""  method="post" id="webSingerAdd_frm">
		     <input type="hidden" name="singer_id" value="${webSinger.singer_id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>歌手姓名：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="singer_name" name="singer_name"  style="width: 90%;"  value="${webSinger.singer_name}"/>
	                </td>
					<td class="tdbg01" style="width:15%">歌手出生日期：</td>
					<td class="tdbg02" style="width:35%">
					<input   class="easyui-datebox" style="width: 90%;"  type="text" name="singer_birthdate"  value="<fmt:formatDate value="${webSinger.singer_birthdate}" pattern="yyyy-MM-dd"/>"/>
					</td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>歌手属相：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" name="singer_shuxiang"  style="width: 90%;" value="${webSinger.singer_shuxiang}"/>
	                </td>
				    	<td class="tdbg01" style="width:15%">歌手星座：</td>
	                <td class='tdbg02' style="width:35%">
	                	<input class="easyui-textbox"    type="text"  name="singer_xingzuo" style="width: 90%;" value="${webSinger.singer_xingzuo}"/>
	                </td>
				</tr>
				<tr>
					
					<td class="tdbg01" style="width:15%">歌手爱好：</td>
					<td class="tdbg02" style="width:85%;" colspan="3">
					<input    type="text"  name="singer_aihao"  class="easyui-textbox" style="width: 90%;" value="${webSinger.singer_aihao}"/>
					</td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%">歌手简介：</td>
	                <td class='tdbg02' style="width:85%" colspan="3">
	                	<input    type="text" name="singer_jianjie"  style="width: 90%;"  class="easyui-textbox" value="${webSinger.singer_jianjie}"/>
	                </td>
				
				</tr>
				
				
			</table>
		</form>
	</div>
</body>
</html>
