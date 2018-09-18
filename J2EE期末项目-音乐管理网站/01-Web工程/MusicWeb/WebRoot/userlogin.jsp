<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录界面</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyui.css"></link>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
    
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/pintuer.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_page.css"/>


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
	                <div class="text-center margin-big padding-big-top"><h1>在线音乐系统网站用户登录</h1></div>
	                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="text" class="input input-big"   id="account" placeholder="登录账号"  />
	                            <span class="icon icon-user margin-small"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="password" class="input input-big" id="password"  placeholder="登录密码" />
	                            <span class="icon icon-key margin-small"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="field">
	                            <input type="text" class="input input-big" id="code" placeholder="填写右侧的验证码" />
	                           <img src="<%=basePath%>/manage/identifyCode" id="codeimage" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;"  onclick="changeCode();">  
	                                                   
	                        </div>
	                    </div>
	                </div>
	                <div style="padding:30px;">
	                <input type="button"  onclick="submitForm();" class="button button-block bg-main text-big input-big" value="登录">
	                <input type="button"  onclick="userRegister();" class="button button-block bg-main text-big input-big" value="用户注册"></div>
	            </div>
	            </form>          
	        </div>
	    </div>
	</div>
<script type="text/javascript">
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
   		$.post('<%=basePath%>webUser/login',			//进入用户登录控制器,返回一个result(code返回码,msg信息)
   		   		{"account":account,"password":password,"code":code},
   			    function(data){
				if(data.code=="1"){
					window.location.href="<%=basePath%>webUser/home";		//进入用户主页
                }else{
                	$("#codeimage").attr("src","<%=basePath%>manage/identifyCode?time="+new Date().getTime());	//刷新验证码
                   	$.messager.alert('提醒', data.msg, 'info'); 
                }
   		},'json');
	}
	
	function userRegister(){
		//弹出框,给用户注册
	
			$("<div id='registerUser'/>").dialog({
                title: '注册用户',    
			    width: 500,    
			    height: 400,  
			    href:'<%=path%>/webUserRegister.jsp', 		//打开用户注册页面
			    closed: false,    
			    cache: false, 
			    modal: true,
			    onClose:function(){
			    	
			    	$(this).dialog('destroy');
			    },
			    buttons:[{
					text:'注册',
					handler:function(){
						
						  //第一步 进行必填项校验
						  var username=$("#username").textbox('getValue');
						  var password=$("#password").val();
						  var repassword=$("#repassword").val();
				    	  if(username ==""){ 
				    		  $.messager.alert("提示信息","账号不能为空","info");
				    		  return ;
				    	  }
				    	  if(password ==""){ 
				    		  $.messager.alert("提示信息","密码不能为空","info");
				    		  return ;
				    	  }
				    	  if(repassword ==""){ 
				    		  $.messager.alert("提示信息","确认密码不能为空","info");
				    		  return ;
				    	  }
				    	  if(repassword !=password){ 
				    		  $.messager.alert("提示信息","两次密码不一致","info");
				    		  return ;
				    	  }
		               
		                  //进行post请求
		                  $.post('<%=path%>/webUser/WebUserRegister',			//进入用户注册控制器,返回一个result(code,msg)
		                		  $("#webUserAdd_frm").serialize(),
		             			function(data){
		                	        
		          				if(data.code=="1"){
		          					 $('#resultDatas').datagrid('reload');
		          					 $("#registerUser").dialog('destroy');
		          					 $.messager.alert("提示信息","注册成功","info");
	                                  
		                          }else{
		                        	  $.messager.alert("提示信息","注册失败","info");
		                          }
		             		},'json');
				    	  
						
					}
				},{
					text:'关闭',
					handler:function(){
						$("#registerUser").dialog('destroy');
					}
				}]

            });
		
		
	}
	
	//点击修改验证码图片
	function changeCode(){
		
		$("#codeimage").attr("src","<%=basePath%>manage/identifyCode?time="+new Date().getTime());
		
	}
</script>
  </body>
</html>

