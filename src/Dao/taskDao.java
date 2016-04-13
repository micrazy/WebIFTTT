package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.DBAcess;
import bean.TaskBean;

public class taskDao {
	/*任务查询*/
	public TaskBean query(String sqlKey, int sqlVal){
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
        TaskBean task = new TaskBean();
        boolean flag = false;
        try {
           String sqlPrefix = "SELECT * FROM task WHERE ";
           String sql = sqlPrefix + sqlKey + " = ?";
           //System.out.println("!!!!SQL:"+sqlPrefix+sqlKey+" = "+sqlVal);
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setInt(1, sqlVal);
           ResultSet rs = ps.executeQuery();
           
           while (rs.next()) {
        	   task.setTrigger(rs.getInt("trig"));
        	   task.setAction(rs.getInt("action"));
        	   task.setActionID(rs.getString("trigger_id"));
        	   task.setActionPassword(rs.getString("trigger_pwd"));
        	   task.settriggerTime(rs.getString("trigger_time"));
        	   task.setContents(rs.getString("content"));
        	   System.out.println(rs.getString("content"));
        	   task.settriggerID(rs.getString("action_id"));
        	   task.settriggerPassword(rs.getString("action_pwd"));
        	   flag = true;
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           if (conn != null) {
              try {
                 conn.close();
              } catch (Exception e) {
              }
           }
        }
        if(flag)
      	  return task;
        return null;
  }
	
	/*任务删除*/
	public void delete(String taskID){
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
        try {
           String sql = "delete from task where task_id = " + taskID;
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.executeUpdate();
           
           String sql1 = "delete from usertask where task_id = " + taskID;
           PreparedStatement ps1 = conn.prepareStatement(sql1);
           ps1.executeUpdate();
           
        } catch (Exception e) {
	           e.printStackTrace();
	        } finally {
	           if (conn != null) {
	              try {
	                 conn.close();
	              } catch (Exception e) {
	              }
	           }
	        }
	}
	
	/*任务添加*/
	public boolean insert(TaskBean task,String username){
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
	        boolean ok = true;
	        try {
	           PreparedStatement ps1 = conn.prepareStatement("select * from task");
	           ResultSet rs = ps1.executeQuery();   
	           int rowCount = 0;  
	           while(rs.next()) {  
	        	   int i = rs.getInt("task_id");
	        	   if(i > rowCount)
	        		   rowCount = i;
	           }
	           System.out.println(rowCount);
	           
	           String sql = "insert into task values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	           PreparedStatement ps = conn.prepareStatement(sql);
	           ps.setInt(1,rowCount+1);
	           ps.setInt(2, task.getTrigger());
	           ps.setInt(3, task.getAction());
	           ps.setString(4, task.gettriggerTime());
	           ps.setString(5, task.gettriggerID());
	           ps.setString(6, task.gettriggerPassword());
	           ps.setString(7, task.getActionID());
	           ps.setString(8, task.getActionPassword());
	           ps.setString(9,task.getContents());
	           
	           
	           //statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
	           ps.executeUpdate();
	           /*任务添加花费用户金额，一次100*/
	           String sql3 = "update user set balance = balance -" + 100 + " WHERE username = '"+username+"'";
	           PreparedStatement ps4 = conn.prepareStatement(sql3);
	           ps4.executeUpdate();
	           //任务添加获得经验，一次升5级
	           String sql4 = "update user set grade = grade +" + 5 + " WHERE username = '"+username+"'";
	           PreparedStatement ps5 = conn.prepareStatement(sql4);
	           ps5.executeUpdate();	           
	  
	           String sql2 = "SELECT * FROM user WHERE username = '"+username+"'";
	           PreparedStatement ps2 = conn.prepareStatement(sql2);
	           ResultSet rs1 = ps2.executeQuery();
	           int balance = 0;
	           
	           while(rs1.next()){
	        	   balance = rs1.getInt("balance");
	           }
	           
	           /*将消费记录加入数据库*/
	           String sql1 = "insert into consume values (?, ?, ?, ?, ?)";
	           PreparedStatement ps3 = conn.prepareStatement(sql1);
	           ps3.setString(1, username);
	           ps3.setInt(2, rowCount+1);
	           ps3.setInt(3, -100);
	           ps3.setInt(4, balance);
	           Date date = new Date();
	           DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           ps3.setString(5,formatter.format(date));
	           ps3.executeUpdate();
	           
	           userTaskDao userTask = new userTaskDao();
	           userTask.insert(username, rowCount+1);
	           
	        } catch (Exception e) {
	           e.printStackTrace();
	           ok = false;
	        } finally {
	           if (conn != null) {
	              try {
	                 conn.close();
	              } catch (Exception e) {
	              }
	           }
	        }
	        return ok;
	  }

	/*任务修改*/
	public boolean alter(TaskBean task,String taskID) {
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
	     boolean ok = true;
	     try {
	         String s = "update task set trig = " + task.getTrigger() + ",action = " + task.getAction() + ", ";
	         s += "trigger_id = " + (task.gettriggerID() == null? "null" : ("'" + task.gettriggerID() + "'")) + ", ";
	         s += "trigger_pwd = " + (task.gettriggerPassword() == null? "null" : ("'" + task.gettriggerPassword() + "'")) + ", ";
	         s += "trigger_time = " + (task.gettriggerTime() == null? "null" : ("'" + task.gettriggerTime() + "'")) + ", ";
	         s += "action_id = " + (task.getActionID() == null? "null" : ("'" + task.getActionID() + "'")) + ", ";
	         s += "action_pwd = " + (task.getActionPassword() == null? "null" : ("'" + task.getActionPassword() + "'")) + ", ";
	         s += "content = " + (task.getContents() == null? "null" : ("'" + task.getContents() + "'"));
	         s += " where task_id = " + taskID;
	         /*PreparedStatement ps1 = conn.prepareStatement("update task "
	         		+ "set task_trigger = " + task.getTrigger() 
	         		+ ",task_action = " + task.getAction() 
	         		+ ",receiveID = '" + task.gettriggerID()
	         		+ "',receivePW = '" + task.gettriggerPassword()
	         		+ "',task_date = '" + task.gettriggerTime()
	         		+ "',sendID = '" + task.getActionID()
	         		+ "',sendPW = '" + task.getActionPassword()
	         		+ "',contents = '" + task.getContents()
	         		+ "' where task_id = " + taskID);*/
	         PreparedStatement ps1 = conn.prepareStatement(s);
	         ps1.executeUpdate();
	         return ok;
	           
	        } catch (Exception e) {
	           e.printStackTrace();
	           ok = false;
	        } finally {
	           if (conn != null) {
	              try {
	                 conn.close();
	              } catch (Exception e) {
	              }
	           }
	        }
	        return ok;
	}

	/*开始任务*/
	public boolean start(String username,int task_id) {
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
	     boolean ok = true;
	     try {
	         PreparedStatement ps1 = conn.prepareStatement("select * from user where username = '" + username + "'");
	         ResultSet rs = ps1.executeQuery();
	         int grade = 0;
	         while(rs.next()){
	        	 grade = rs.getInt("grade");
	         }
	         int count = 100;
	         System.out.println("grade is : " + grade);
	         
	         //计算打折后的运行费用
	         if(grade >= 0 && grade <= 10)
	        	 count = 100;
	         else if(grade >= 11 && grade <= 40)
	        	 count = 90;
	         else if(grade >= 41 && grade <= 90)
	        	 count = 80;
	         else if(grade >= 91 && grade <= 150)
	        	 count = 70;
	         else if(grade >= 151 && grade <= 250)
	        	 count = 60;
	         else if(grade >= 251)
	        	 count = 50;
	         
	         //设置用户开始消费一定金额
	         String sql3 = "update user set balance = balance -" + count + " WHERE username = '"+username+"'";
	         PreparedStatement ps4 = conn.prepareStatement(sql3);
	         ps4.executeUpdate();

	         String sql4 = "update user set grade = grade +" + 5 + " WHERE username = '"+username+"'";
	         PreparedStatement ps5 = conn.prepareStatement(sql4);
	         ps5.executeUpdate();	           
	  
	         String sql2 = "SELECT * FROM user WHERE username = '"+username+"'";
	         PreparedStatement ps2 = conn.prepareStatement(sql2);
	         ResultSet rs1 = ps2.executeQuery();
	         int balance = 0;
	           
	         while(rs1.next()){
	        	 balance = rs1.getInt("balance");
	         }
	           
	         String sql1 = "insert into consume values (?, ?, ?, ?, ?)";
	         PreparedStatement ps3 = conn.prepareStatement(sql1);
	         ps3.setString(1, username);
	         ps3.setInt(2, task_id);
	         ps3.setInt(3, count);
	         ps3.setInt(4, balance);
	         Date date = new Date();
	         DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         ps3.setString(5,formatter.format(date));
	         ps3.executeUpdate();
	           
	        } catch (Exception e) {
	           e.printStackTrace();
	           ok = false;
	        } finally {
	           if (conn != null) {
	              try {
	                 conn.close();
	              } catch (Exception e) {
	              }
	           }
	        }
	        return ok;		
	}
}
