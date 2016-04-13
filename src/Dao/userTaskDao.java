package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import bean.DBAcess;

public class userTaskDao {
	/*����û�������Ķ�Ӧ��ϵ*/
	public boolean insert(String userID,int taskID){
		DBAcess db=new DBAcess();//ʵ�������ݿ�������
		Connection conn=db.getConn();//��ȡ����
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
