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
		<form action=""  method="post" id="webAlbmAdd_frm">
		     <input type="hidden" name="albm_id" value="${webAlbm.albm_id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>专辑名称：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="albm_name" name="albm_name"  style="width: 90%;"  value="${webAlbm.albm_name}"/>
	                </td>
					<td class="tdbg01" style="width:15%">专辑发行日期：</td>
					<td class="tdbg02" style="width:35%">
					<input   class="easyui-datebox" style="width: 90%;"  type="text" name="albm_date"  value="<fmt:formatDate value="${webAlbm.albm_date}" pattern="yyyy-MM-dd"/>"/>
					</td>
				</tr>
				
				<tr>
					
					<td class="tdbg01" style="width:15%">专辑所属歌手：</td>
					<td class="tdbg02" style="width:85%;" colspan="3">
					 <select id="singer_id" name="singer_id" style="width:200px;" >
					       <option value="">==请选择歌手==</option>
			               <c:forEach items="${singers}" var="singer">
			                   <option value="${singer.singer_id}">${singer.singer_name}</option>
			               </c:forEach>
			           
			           </select>
					
					</td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%">专辑简介：</td>
	                <td class='tdbg02' style="width:85%" colspan="3">
	                	<input    type="text" name="albm_jianjie"  style="width: 90%;"  class="easyui-textbox" value="${webAlbm.albm_jianjie}"/>
	                </td>
				
				</tr>
				
				
			</table>
		</form>
	</div>
	<script type="text/javascript">
     $(function(){
    	  var singer_id='${webAlbm.singer_id}';
    	  
    	  if(singer_id!=""){
    		   $("#singer_id").val(singer_id);
    		  
    	  }
    	 
    	 
     });
      

</script>
</body>


</html>
