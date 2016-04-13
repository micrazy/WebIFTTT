<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>

<html>
	<head>
		<title>IFTTT</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
        
        <link rel="Stylesheet" type="text/css" href="css/loginDialog.css" />

        
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>

		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>

        
	</head>
	<body id="top">

		<!-- Header -->
			<header id="header" class="skel-layers-fixed">
				<h1><a href="#">IFTTT</a></h1>
				<nav id="nav">
					<ul>
						<li id="regist"><a href="#" class="log button special">Sign Up</a></li>						
						<li id="login"><a href="#" class="log button alt">Sign In</a></li>
					</ul>
				</nav>
			</header>

		<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<h2>This is IFTTT</h2>
					<p>We connect your favorite apps together, so they work best for you.</p>
					<ul class="actions">
						<li id="regist1"><a href="#" class="log button big special">Sign Up</a></li>
						<li id="login1"><a href="#" class="log button big alt">Sign in</a></li>
					</ul>
				</div>
			</section>

				
			
		<!-- Footer -->
			<footer id="footer">
				<div class="container">					
					<ul class="copyright">
						<li>Design:翟微</li>
						<li>Background:卢志超</li>
					</ul>
				</div>
			</footer>

		
		<!-- Login -->
            <div id="LoginBox">
                <div class="row1">
                    账号窗口
                    <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
                </div>
                <div class="row2" >用户名:
                		
                        <input type="text" id="txtName" placeholder="账号/邮箱" />
                   
                </div>
                <div class="row2" >
                    密&nbsp;&nbsp;&nbsp;&nbsp;码:
                        <input type="password" id="txtPwd" placeholder="密码" />
                   
                </div>
                <div class="row2">
                    <a href="#" id="loginbtn">登录</a>
                </div>
            </div>
            
		<!-- Regist -->
            <div id="RegistBox">
                <div class="row1">
                   注册窗口
                    <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn2">×</a>
                </div>
                <div class="row2" >用户名:
                		
                        <input type="text" id="txtName1" placeholder="账号/邮箱" />
                   
                </div>
                <div class="row2" >
                    密&nbsp;&nbsp;&nbsp;&nbsp;码:
                        <input type="password" id="txtPwd1" placeholder="密码" />
                   
                </div>
               <div class="row2" >
                    重&nbsp;&nbsp;&nbsp;&nbsp;复:
                        <input type="password" id="txtrePwd" placeholder="重复" />
                   
                </div>                
                
                <div class="row2">
                    <a href="#" id="registbtn">注册</a>
                </div>
            </div>
            
         		<script type="text/javascript">
			$(function ($) {
				//弹出登录
				$("#login,#login1").hover(function () {
					$(this).stop().animate({
						opacity: '1'
					}, 600);
				}, function () {
					$(this).stop().animate({
						opacity: '0.6'
					}, 1000);}).on('click', function () {
						$("body").append("<div id='mask'></div>");
						$("#mask").addClass("mask").fadeIn("slow");
						$("#LoginBox").fadeIn("slow");						
				});
				$("#regist,#regist1").hover(function () {
					$(this).stop().animate({
						opacity: '1'
					}, 600);
				}, function () {
					$(this).stop().animate({
						opacity: '0.6'
					}, 1000);}).on('click', function () {
					$("body").append("<div id='mask'></div>");
					$("#mask").addClass("mask").fadeIn("slow");
					$("#RegistBox").fadeIn("slow");						
			});
				//
				//按钮的透明度
				$("#loginbtn").hover(function () {
					$(this).stop().animate({
						opacity: '1'
					}, 600);
				}, function () {
					$(this).stop().animate({
						opacity: '0.8'
					}, 1000);
				});
				//文本框不允许为空---按钮触发
				$("#loginbtn").on('click', function () {
					var txtName = $("#txtName").val();
					var txtPwd = $("#txtPwd").val();
					if (txtName == "" || txtName == undefined || txtName == null) {
						if (txtPwd == "" || txtPwd == undefined || txtPwd == null) {
							 alert("账号密码为空！");
						}
						else {
							alert("账号为空！");
						}
					}
					else {
						if (txtPwd == "" || txtPwd == undefined || txtPwd == null) {
							alert("密码为空！");
						}
						else {
							$.ajax({
								type:"post",
								async:true,//意思是当有返回值以后才会进行后面的js程序
								url:"login",
								dataType:"text",//返回信息
								data:"username="+txtName+"&password="+txtPwd,
								success:function(data){
									if(data=="false"){
										alert("密码错误");
									}
									else if(data=="true"){
									//	alert("登陆成功");
										window.location.href="task.jsp?username="+txtName;
										
									}
									else{
										if(data=="none"){
											alert("该用户尚未注册");
										}
										else
										alert("无法解析的对象");
									}
								}
							});
						}
					}
				});
				$("#registbtn").on('click', function () {
					var txtName = $("#txtName1").val();
					var txtPwd = $("#txtPwd1").val();
					var txtrePwd=$("#txtrePwd").val();
					if (txtName == "" || txtName == undefined || txtName == null) {
						if (txtPwd == "" || txtPwd == undefined || txtPwd == null) {
							 alert("账号密码为空！");
						}
						else {
							alert("账号为空！");
						}
					}
					else {
						if (txtPwd == "" || txtPwd == undefined || txtPwd == null) {
							alert("密码为空！");
						}
						else {
							if(txtPwd!=txtrePwd){
								alert("密码不相同！");
							}
							else{
								$.ajax({
									type:"post",
									async:true,//意思是当有返回值以后才会进行后面的js程序
									url:"jsp/regist",
									dataType:"text",
									data:"username="+txtName+"&password="+txtPwd,
									success:function(data){
										if(data=="false"){
											alert("用户名不可用");
										}
										else if(data=="true"){
											alert("注册成功");
											window.location.href="index.jsp";
											
										}
										else{
											alert("无法解析的对象");
										}
									}
								
									
								})
							}
						}
					}
				});
				//关闭
				$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) });
				$("#closeBtn").on('click', function () {
					$("#LoginBox").fadeOut("fast");
					$("#mask").css({ display: 'none' });
				});
				$("#closeBtn2").on('click', function () {
					$("#RegistBox").fadeOut("fast");
					$("#mask").css({ display: 'none' });
				});
			});
	</script>  
     
     
           
           
	</body>
</html>



