package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import bean.DBAcess;

public class userTaskDao {
	/*添加用户与任务的对应关系*/
	public boolean insert(String userID,int taskID){
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
        boolean ok = true;
        try {
           String sql = "insert into usertask values (?, ?)";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, userID);
           ps.setInt(2, taskID);
           
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
}
