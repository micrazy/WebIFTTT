package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.AccountBean;
import bean.DBAcess;

public class userDao {
	/*用户查询*/
	public AccountBean query(String sqlKey, String sqlVal){
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
        AccountBean user = new AccountBean();
        boolean flag = false;
        try {
           String sqlPrefix = "SELECT * FROM user WHERE ";
           String sql = sqlPrefix + sqlKey + " = ?";
           //System.out.println("!!!!SQL:"+sqlPrefix+sqlKey+" = "+sqlVal);
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, sqlVal);
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {
        	user.setUsername(rs.getString("username"));
          	user.setPassword(rs.getString("password"));
          	user.setBalance(rs.getInt("balance"));
          	user.setGrade(rs.getInt("grade"));
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
      	  return user;
        return null;
  }
	
	/*用户充值*/
	public void update(String sqlKey, String sqlVal,int money){
		DBAcess db=new DBAcess();//实例化数据库连接类	    	
    	Connection conn=db.getConn();//获取连接
	        try {
	           String sqlPrefix = "update user set balance = balance+" + money + " WHERE ";
	           String sql = sqlPrefix + sqlKey + " = '" + sqlVal +"'";
	           
	           //System.out.println("!!!!SQL:"+sqlPrefix+sqlKey+" = "+sqlVal);
	           PreparedStatement ps = conn.prepareStatement(sql);
	           ps.executeUpdate();
	           
	           String sql2 = "SELECT * FROM user WHERE username ='" + sqlVal +"'";
	           PreparedStatement ps2 = conn.prepareStatement(sql2);
	           ResultSet rs = ps2.executeQuery();
	           int balance = 0;
	           
	           while(rs.next()){
	        	   balance = rs.getInt("balance");
	           }
	           
	           String sql1 = "insert into consume values (?, ?, ?, ?, ?)";
	           PreparedStatement ps1 = conn.prepareStatement(sql1);
	           ps1.setString(1, sqlVal);
	           ps1.setInt(2, -1);
	           ps1.setInt(3, money);
	           ps1.setInt(4, balance);
	           Date date = new Date();
	           DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           ps1.setString(5,formatter.format(date));
	           
	           //statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
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

	/*添加用户*/
	public boolean insert(AccountBean user){
		DBAcess db=new DBAcess();//实例化数据库连接类	    	
    	Connection conn=db.getConn();//获取连接
        boolean ok = true;
        try {

           String sql = "insert into user values (?, ?, ?, ?)";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, user.getUsername());
           ps.setString(2, user.getPassword());
           ps.setInt(3, user.getBalance());
           ps.setInt(4, user.getGrade());
           
           //statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
           ps.executeUpdate();
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

	/*修改用户资料*/
	public boolean alter(AccountBean user){
		DBAcess db=new DBAcess();//实例化数据库连接类	    	
    	Connection conn=db.getConn();//获取连接
        boolean ok = true;
        try {
           String sql = "update user set password = '" + user.getPassword() + "'" +
        		   "' where username = '" + user.getUsername() + "'";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.executeUpdate();
           
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
