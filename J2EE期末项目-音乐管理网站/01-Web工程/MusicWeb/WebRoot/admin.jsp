<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理员登录页面</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyui.css"></link>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/pintuer.js"></script>
  </head>
  
  <body>
  <div class="bg"></div>
	<div class="container">
	    <div class="line bouncein">
	        <div class="xs6 xm4 xs3-move xm4-move">
	            <div style="height:150px;"></div>
	            <div class="media media-y margin-big-bottom">           
	            </div>         
	            <form  method="post">
	            <div class="panel loginbox">
	                <div class="text-center margin-big padding-big-top"><h1>在线音乐系统网站后台管理中心</h1></div>
	               	 <!-- 输入框 -->
	                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
	                    <!-- 用户名 -->
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="text" class="input input-big"   id="account" placeholder="登录账号"  />
	                            <span class="icon icon-user margin-small"></span>
	                        </div>
	                    </div>
	                   	<!--  密码 -->
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="password" class="input input-big" id="password"  placeholder="登录密码" />
	                            <span class="icon icon-key margin-small"></span>
	                        </div>
	                    </div>
	               	    <!--   验证码 -->
	                    <div class="form-group">
	                        <div class="field">
	                            <input type="text" class="input input-big" id="code" placeholder="填写右侧的验证码" />
	                           <img src="<%=basePath%>/manage/identifyCode" id="codeimage" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;"  onclick="changeCode();">                          
	                        </div>
	                    </div>
	                </div>
	                <div style="padding:30px;"><input type="button"  onclick="submitForm();" class="button button-block bg-main text-big input-big" value="登录"></div>
	            </div>
	            </form>          
	        </div>
	    </div>
	</div>
<script type="text/javascript">
   //点击登陆按钮时
   function submitForm(){
	  
   		var  account=$('#account').val();
   		var  password=$('#password').val();
   		var  code=$('#code').val();
   		if(account.replace(/(^\s*)|(\s*$)/, "")==''){
   			$.messager.alert('提醒', '账号不能为空!', 'info'); 
   			return;
   		}
   		if(password.replace(/(^\s*)|(\s*$)/, "")==''){
   			$.messager.alert('提醒', '请输入密码!', 'info'); 
   			return;
   		}
   		if(code.replace(/(^\s*)|(\s*$)/, "")==''){
   			$.messager.alert('提醒', '请输入验证码!', 'info'); 
   			return;
   		}
   		$.post('<%=basePath%>manage/login',		//进入管理员登录控制器
   		   		{"account":account,"password":password,"code":code},
   			    function(data){
				if(data.code=="1"){
					window.location.href="<%=basePath%>manage/home";	//进入管理员主页
                }else{
                	$("#codeimage").attr("src","<%=basePath%>manage/identifyCode?time="+new Date().getTime());	//刷新验证码
                   	$.messager.alert('提醒', data.msg, 'info'); 
                }
   		},'json');
	}
	
	//刷新验证码
	function changeCode(){
		
		$("#codeimage").attr("src","<%=basePath%>manage/identifyCode?time="+new Date().getTime());
		
	}
</script>
  </body>
</html>

