<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
		<form action=""  method="post" id="webMusicAdd_frm">
		     <input type="hidden" name="music_id" value="${webMusic.music_id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				
				
				<tr>
					
					<td class="tdbg01" style="width:15%"><font color="red">*</font>歌曲名称：</td>
					<td class="tdbg02" style="width:85%;" colspan="3">
					<input    type="text"  name="music_name"  id="music_name" class="easyui-textbox" style="width: 90%;" value="${webMusic.music_name}"/>
					</td>
				</tr>
				
				<tr>
					<td class="tdbg01" style="width:15%;">歌曲所属专辑：</td>
	                <td class='tdbg02' style="width:35%;">
	                
	                   <select id="music_albm_id" name="music_albm_id" style="width:200px;" >
					       <option value="">==请选择专辑==</option>
			               <c:forEach items="${webAlbms}" var="albm">
			                   <option value="${albm.albm_id}">${albm.albm_name}</option>
			               </c:forEach>
			           
			           </select>
	                	
	                </td>
					<td class="tdbg01" style="width:15%">歌曲时长：</td>
					<td class="tdbg02" style="width:35%">
					<input   class="easyui-textbox" style="width: 90%;"  type="text" name="music_shichang"  value="${webMusic.music_shichang}"/>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>
