<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
</head>
<body>
	<div class="nrBody" style="height:300px;overflow-y:auto;overflow-x:hidden;position:relative;">
		<form action=""  method="post" id="webUserAdd_frm">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
		
				<tr>
					<td class="tdbg01" style="width:30%">用户账号：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	<input    type="text" name="username" id="username"  style="width: 90%;"  class="easyui-textbox"/>
	                </td>
				
				</tr>
				<tr>
					<td class="tdbg01" style="width:30%">用户密码：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	<input    type="password" name="password"  id="password" style="width: 90%;"  />
	                </td>
				
				</tr>
				<tr>
					<td class="tdbg01" style="width:30%">用户确认密码：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	<input    type="password" name="repassword" id="repassword" style="width: 90%;"  />
	                </td>
				
				</tr>
				<tr>
					<td class="tdbg01" style="width:30%">用户性别：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	男：<input type="radio" name="usersex" value="n" class="radio"  style= "background-color:#333333;border-style:none;color:white;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    				女：<input type="radio" name="usersex" value="v" class="radio"  style= "background-color:#333333;border-style:none;color:white;"/>
	                </td>
				
				</tr>
				<tr>
					<td class="tdbg01" style="width:30%">用户身份证号：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	<input    type="text" name="usercard"  style="width: 90%;"  class="easyui-textbox" />
	                </td>
				
				</tr>
				<tr>
					<td class="tdbg01" style="width:30%">用户籍贯：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	<input    type="text" name="useraddress"  style="width: 90%;"  class="easyui-textbox" />
	                </td>
				
				</tr>
				<tr>
					<td class="tdbg01" style="width:30%">用户生日：</td>
	                <td class='tdbg02' style="width:70%" colspan="3">
	                	<input    type="text" name="userbirthdate"  style="width: 90%;"  class="easyui-datebox" />
	                </td>
				
				</tr>
				
				
				
			</table>
		</form>
	</div>
	
</body>


</html>
