var pwd = "";
var loginTrue = false;

$(function() {

	// //后台登录用户名验证
	// $("#inputEmail").blur(function() {
	// 	var account = $(this).val();
	// 	if(account == null || account.trim() == "") { return; }
	// 	$.post("../validateTeacher", "account="+account, function(data) {
	// 		if(data == "1") {
	// 			$("#inputEmail").val("");
	// 			$(".error-msg").first().css("display", "block");
	// 		} else {
	// 			pwd = data;
	// 			loginTrue = true;
	// 		}
	// 	});
	// }).focus(function() {
	// 	$(".error-msg").first().css("display", "none");
	// });
	// //后台登录密码验证
	// $("#inputPassword").blur(function() {
	// 	var adminPwd = $(this).val();
	// 	if(adminPwd == null || adminPwd.trim() == "") { return; }
	// 	if(adminPwd != pwd) {
	// 		$(this).val("");
	// 		$(".error-msg").eq(1).css("display", "block");
	// 		loginTrue = false;
	// 	} else {
	// 		loginTrue = true;
	// 	}
	// }).focus(function() {
	// 	$(".error-msg").eq(1).css("display", "none");
	// });
	//
	// //后台登录提交
	// $("#adminLogin").submit(function() {
	// 	if($("input").first().val().trim() == "" || $("input").last().val().trim() == "") {
	// 		return false;
	// 	}
	// 	return;
	// 	if(loginTrue) {
	// 		return true;
	// 	} else {
	// 		alert("登录信息有误!");
	// 		return false;
	// 	}
	// });


	//前台用户登录
	$("#userLogin").click(function() {
		zeroModal.show({
			title : '登 录',
			iframe : true,
			url : 'home/login',
			width : '30%',
			height : '40%',
			top : '25%',
			left : '35%',
			esc : true,
			overlay : true,
			onClosed : function() {
				location.reload();
			}
		});
	});
	$("#inputEmail3").blur(function() {
		var userAccount = $(this).val();
		if(userAccount.trim() == "" || userAccount == null) {
			loginTrue = false;
			$("#userAccountMsg").text("账号为空");
			return;
		}
		$.post("validateLoginUser", "name="+userAccount, function(data) {
			if(data == "n") {
				$("#inputEmail3").val("");
				$("#userAccountMsg").text("账号不存在");
				loginTrue = false;
			} else {
				pwd = data;
				loginTrue = true;
			}
		});
	}).focus(function() {
		$("#userAccountMsg").text("");
	});
	$("#inputPassword3").blur(function() {
		var userPwd = $(this).val();
		if(userPwd == null || userPwd.trim() == "") {
			loginTrue = false;
			return;
		}
		return;
		if(userPwd != pwd) {
			$(this).val("");
			$("#userPwdMsg").text("密码错误");
			loginTrue = false;
		} else {
			loginTrue = true;
		}
	}).focus(function() {
		$("#userPwdMsg").text("");
	});
	//前台用户注册
	$("#userRegister").click(function() {
		zeroModal.show({
			title : '注 册',
			iframe : true,
			url : 'home/register',
			width : '28%',
			height : '45%',
			top : '25%',
			left : '35%',
			esc : true,
			overlay : true,
			onClosed : function() {
				location.reload();
			}
		});
	});
	//注册账户验证是否已经被注册
	$("#account").blur(function() {
		var account = $(this).val();
		if (account == null || account.trim() == "") {
			return false;
		}
		$.ajax({
			type: "POST",
			data: "name="+account,
			url: "validateRegisterUser",
			success: function(data) {
				if(data.trim() == "f") {
					zeroModal.show({
						title: "提示",
						content: "该账户已被注册!",
						width : '200px',
						height : '130px',
						overlay : false,
						ok : true,
						onClosed : function() {
							$("#account").val("");
						}
					});
					return false;
				}
			}
		});
	});
	//注册提交
	$("#signSubmit").click(function() {
		//注册数据有效性验证
		var userAccount = $("#account").val();
		var userPwd = $("#pwd").val();
		var sex = $("#sex").val();
		//用户名不为空且不能超过30个字符
		if(userAccount.trim() == null || userAccount.trim() == "" || userAccount.length <= 0 || userAccount.length > 30) {
			zeroModal.show({
				title: "提示",
				content: "用户名不合法!",
				width : '200px',
				height : '130px',
				overlay : false,
				ok : true,
				onClosed : function() {
					$("#account").val("");
					$("#account").get(0).focus();
				}
			});
			return false;
		}
		//密码长度不超过16位
		if(userPwd.trim() == null || userPwd.trim() == "" || userPwd.length < 0 || userPwd.length > 16) {
			zeroModal.show({
				title: "提示",
				content: "密码不合法!",
				width : '200px',
				height : '130px',
				overlay : false,
				ok : true,
				onClosed : function() {
					$("#pwd").val("");
					$("#pwd").get(0).focus();
				}
			});
			return false;
		}
		$.ajax({
			type: "POST",
			url: "user_reg",
			data: "name="+userAccount+"&password="+userPwd+"&sex="+sex,
			success: function(data) {
				if (data.trim() == "t") {
					zeroModal.show({
						title: "提示",
						content: "注册成功!",
						width : '200px',
						height : '130px',
						overlay : false,
						ok : false,
						onClosed : function() {
							$("#validateName").val("");
							$("#account").val("");
							$("#pwd").val("");
						}
					});
					setTimeout(function(){
						window.location.href = 'login';
					},1000);
				} else {
					alert("注册失败");
				}
			}
		});
	});
});

function receptionLoginValidate() {
	var studnetAccount = $("#inputEmail3").val();
	if(studnetAccount.trim() == "" || studnetAccount == null) {
		loginTrue = false;
		$("userAccountMsg").text("账号为空");
		return false;
	}
	var studentPwd = $("#inputPassword3").val();
	if(studentPwd == null || studentPwd.trim() == "") {
		loginTrue = false;
		$("#userPwdMsg").text("密码为空");
		return false;
	}


	$.ajax({
		url:'login',
		method:'post',
		dataType:'json',
		data:$("#receptionLogin").serialize(),
		success:function(data){
			if(data.type == 'success'){
				alert(data.msg)
				window.top.location.reload();
			}else{
				alert(data.msg);
			}
		}
	})

}
