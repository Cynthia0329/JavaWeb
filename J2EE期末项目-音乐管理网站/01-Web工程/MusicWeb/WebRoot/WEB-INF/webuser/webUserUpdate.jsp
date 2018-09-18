<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="<%=basePath%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/admin.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/demo/demo.css"/>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
  
  </head>
  
  <body>
		<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改个人信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" id="webUserUpdateFrm" action="">
      <!-- 隐藏用户id -->
      <input type="hidden" name="userid" value="${webUser.userid}"> 
      <input type="hidden" name="username" value="${webUser.username}"> 
      <div class="form-group">
        <div class="label">
          <label for="sitename">用户帐号：</label>
        </div>
        <div class="field">
          <label style="line-height:33px;">
              ${webUser.username}
          </label>
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">用户密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50"  name="password"  size="50" placeholder="密码不填写,默认不修改密码"  />       
        </div>
      </div>      
      
      <div class="form-group">
        <div class="label">
          <label for="sitename">用户性别：</label>
        </div>
        <div class="field">
         	 男：<input type="radio" name="usersex" value="n" class="radio"  style= "background-color:#333333;border-style:none;color:white;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	女：<input type="radio" name="usersex" value="v" class="radio"  style= "background-color:#333333;border-style:none;color:white;"/>        
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">用户身份证号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="usercard" size="50" placeholder="用户身份证号" value=" ${webUser.usercard}" />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">用户出生日期：</label>
        </div>
        <div class="field">
        
          <input type="text"  class="easyui-datebox"  class="input w50" name="userbirthdate" value="<fmt:formatDate value='${webUser.userbirthdate}' pattern='yyyy-MM-dd'/>  size="50"    />       
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">用户籍贯：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="useraddress" size="50" placeholder="用户籍贯" value=" ${webUser.useraddress}" />          
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="updateWebUser();"> 提交</button>   
        </div>
      </div>      
    </form>
  </div>
</div>
<script type="text/javascript">
      
       function updateWebUser(){
    	   
      		$.post('<%=basePath%>webUser/updateWebUserSubmit',
      				$("#webUserUpdateFrm").serialize(),
       			    function(data){
    				if(data.code=="1"){
    					$.messager.alert('提醒', data.msg, 'info');
                    }else{
                    	
                       	$.messager.alert('提醒', data.msg, 'info'); 
                    }
       		},'json');
    	   
    	   
       }


</script>
</body>
</html>
