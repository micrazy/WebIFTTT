<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ifttt</title>

  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
     font-family: microsoft yahei;
   }
 </style>
 <script type="text/javascript">

</script>
</head>

<body style="padding-top: 70px">
<%String s=request.getParameter("username"); %>
  <div class="container-fluid">
  
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container"> 
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#topFixedNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
          <a class="navbar-brand" href="#">IFTTT</a></div>
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="topFixedNavbar1">
            <ul class="nav navbar-nav">
              <li class="active"><a href="#">创建任务<span class="sr-only">(current)</span></a></li>
              <li><a href="tasklist.jsp?username=<%=request.getParameter("username")%>">任务列表</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="#">欢迎,<%=s %></a></li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">个人空间<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">个人设置</a></li>
                  <li><a href="#">任务列表</a></li>
                  <li><a href="#">用户商城</a></li>
                  <li class="divider"></li>
                  <li><a href="index.jsp">帐号退出</a></li>
                </ul>
              </li>
            
           </ul>
          </div>
          <!-- /.navbar-collapse --> 
        </div>
        <!-- /.container-fluid --> 
      </nav>
    </div>

    <div class="container well">
      <h1>This 事件</h1>
      <form action="newTask?username=<%=s %>" method="post">

        <div class="form-group">
          <label for="ThisType">This 事件类型</label>
          <select class="form-control" name="trigger" id="ThisType" onChange="showChange1()">
            <option value="1">时间触发</option>
            <option value="2">接收邮件</option>
            <option value="3">监听微博</option>
          </select>
        </div>

        <div class="form-group">
          <label for="I1">Trigger time</label>
          <input type="text" class="form-control" name="trigger_time" id="I1" placeholder="Trigger time">
        </div>
        
        <div class="form-group">
          <label for="I3">Trigger address</label>
          <input type="text" class="form-control" name="trigger_id" id="I3" placeholder="Trigger address">
        </div>
        <div class="form-group">
          <label for="I4">Password</label>
          <input type="password" class="form-control" name="trigger_pwd" id="I4" placeholder="Password">
        </div>




        <hr>


        <h1>That 事件</h1>
        <div class="form-group">
          <label for="ThatType">That 事件类型</label>
          <select class="form-control" name="action" id="ThatType">
            <option value="1">发送邮件</option>
            <option value="2">发微博</option>
          </select>
        </div>

        <div class="form-group">
        <label for="O1">Action address</label>
          <input type="text" class="form-control" name="action_id" id="O1" placeholder="Action address">
        </div>
        <div class="form-group">
          <label for="O2">Password</label>
          <input type="password" class="form-control" name="action_pwd" id="O2" placeholder="Password">
        </div>
        <div class="form-group">
          <label for="O3">Content</label>
          <br>
          <textarea class="form-control" name="content" id="O3" rows="5" placeholder="Content"></textarea>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
      </form>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
    <!-- <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> --> 
    <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script> 
    <!-- Include all compiled plugins (below), or include individual files as needed --> 
    <script src="js/bootstrap.min.js"></script>
  </body>
  </html>