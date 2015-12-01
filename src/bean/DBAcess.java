package bean;
import java.sql.*;
public class DBAcess {
	private final String driver="com.mysql.jdbc.Driver";
	private final String url="jdbc:mysql://localhost:3306/IFTTT";
	private final String user="root";
	private final String pwd="lkjzxc";
	Connection conn;
	
	public DBAcess(){
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,pwd);
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}
	public Connection getConn(){
		return conn;
	}
	public void setConn(Connection conn){
		this.conn=conn;
	}
	public void closeConn(){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
