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
<body>
 <center>
   歌曲名:${webMusic.music_name }
   <br>
   <audio id="myaudio" src="<%=path%>/webMusic/bofangMusic?music_id=${webMusic.music_id }" controls="controls"  autoplay loop="false"  >
       
   </audio>
  <br>
   <a href="<%=path%>/webMusic/downloadMusic?music_id=${webMusic.music_id }">下载歌曲</a>
   <br>
 
   
  
  </center>
</body>
</html>

