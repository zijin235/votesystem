<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href="../home-resources/images/admin/manager.png" type='image/x-icon'/>
<link rel="bookmark" href="../home-resources/images/admin/manager.png"/>
<link href="../h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="../h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="../h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="../h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">

<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="../h-ui/lib/icheck/jquery.icheck.min.js"></script>

<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function(){
		//点击图片切换验证码
		$("#vcodeImg").click(function(){
			this.src="get_cpacha?w=155&h=40&t="+new Date().getTime();
		});

		//登录
		$("#submitBtn").click(function(){


			var data = $("#login-form").serialize();
			$.ajax({
				type: "post",
				url: "login",
				data: data,
				dataType: "json", //返回数据类型
				success: function(data){
					if("success" == data.type){
						window.location.href = "index";
					} else{
						$.messager.alert("消息提醒",data.msg,"warning");
						$("#vcodeImg").click();
					}
				}

			});
		});

		//设置复选框
		$(".skin-minimal input").iCheck({
			radioClass: 'iradio-blue',
			increaseArea: '25%'
		});
	})
</script>
<title>登录投票信息管理系统</title>
<meta name="keywords" content="投票信息管理系统">
</head>
<body>

<div class="header" style="padding: 0;">
	<h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">投票系统后台</h2>
</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form id="login-form" class="form form-horizontal" method="post">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe62c;</i></label>
        <div class="formControls col-8">
          <input id="username" name="username" type="text" placeholder="用户名" class="input-text size-L" data-options="required:true, missingMessage:'用户名不能为空'">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe63f;</i></label>
        <div class="formControls col-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L" data-options="required:true, missingMessage:'密码不能为空'">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码" style="width: 200px;" data-options="required:true, missingMessage:'验证码不能为空'">
          <img title="点击图片切换验证码" id="vcodeImg" src="get_cpacha?w=155&h=40"></div>
      </div>

      <div class="row">
        <div class="formControls col-8 col-offset-3" >
          <input id="submitBtn" type="button" class="btn btn-success btn-lg btn-primary btn-block radius" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer"></div>


</body>
</html>
