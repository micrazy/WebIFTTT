<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@page import="java.sql.*"%>
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

  body{ font-family:microsoft yahei;}

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
            <a class="navbar-brand" href="logSuccess.jsp?id=<%=request.getParameter("username")%>">IFTTT</a></div>
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="topFixedNavbar1">
            <ul class="nav navbar-nav">
              <li ><a href="task.jsp?username=<%=request.getParameter("username")%>">创建任务<span class="sr-only">(current)</span></a></li>
              <li class="active"><a href="#">任务列表</a></li>

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
    
<div class="row">
	<div class="col-lg-12 ">
				<h1 class="page-header">Task List</h1>
			</div>
		</div><!--/.row-->
	<input type="hidden" name="task" id="task">
	<input type="hidden" name="user" id="user" value=<%=request.getParameter("username") %>>
	<%
	Connection conn = null;
	boolean flag = false;
    try {
       String URL = "jdbc:mysql://localhost:3306/IFTTT";
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(URL, "root", "lkjzxc");
       String sqlPrefix = "SELECT * FROM usertask WHERE ";
       String sql = sqlPrefix + "username" + " = ?";
       System.out.println("!!!!SQL:"+sqlPrefix+"username"+" = "+request.getParameter("username"));
       PreparedStatement ps = conn.prepareStatement(sql);
       ps.setString(1, request.getParameter("username").toString());
       ResultSet rs = ps.executeQuery(); %>
       <table class="table table-hover">
       <thead><tr><td>task ID</td><td>trigger</td><td>action</td><td>operation</td></tr>
       </thead>
       <% 
       while (rs.next()) {
    	   %>
    	   <tr>
    	   <td>
    	   <%=rs.getString("taskid")%>
    	   </td>
    	    <%PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM task WHERE task_id = ?");
    	    String x = rs.getString("taskid");
    	    int i = 0;
           ps1.setInt(1, rs.getInt("taskid"));
           ResultSet rs1 = ps1.executeQuery();
           while(rs1.next()){%>
    	   <td>
    	  <%if(rs1.getInt("trig") == 1){ %>
    	  <label><img src="images/Time.png" height="50" width="50"></label>
    	  <%}else if(rs1.getInt("trig") == 2){ %>
    	  <label><img src="images/Email.png" height="50" width="50"></label>
    	  <%}else{ %>
    	  <label><img src="images/weibo.png" height="50" width="50"></label>
    	  <%} %>
    	   </td>
    	   <td>
    	   <%if(rs1.getInt("action") == 1){%>
    	   <label><img src="images/Email.png" height="50" width="50"></label>
    	   <%}else{ %>
    	   <label><img src="images/weibo.png" height="50" width="50"></label>
    	   <%} %>
    	   </td>
    	  
    	    <td>
    	   <a href="startTask?task_id=<%=rs.getString("taskid")%>&username=<%=request.getParameter("username") %>" style="margin:10px;"><span class="glyphicon glyphicon-off" style="font-size:30px"></span></a>
    	   <a href="stopTask?task_id=<%=rs.getString("taskid")%>&username=<%=request.getParameter("username") %>" style="margin:10px;"><span class="glyphicon glyphicon-stop" style="font-size:30px"></span></a>
    	   </td>


    	   <%} %>
    	   </tr>
       <% }
   		 }catch (Exception e) {
           e.printStackTrace();
        } finally {
           if (conn != null) {
              try {
                 conn.close();
              } catch (Exception e) {
              }
           }
        };
    
	%>
	</table>
	

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> -->
    <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
<script src="js/bootstrap.min.js"></script>
</body>
</html>