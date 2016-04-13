package servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DBAcess;

/**
 * Servlet implementation class RegistAccount
 */
@WebServlet(name="/RegistAccount",urlPatterns="/jsp/regist")
public class RegistAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pwd=request.getParameter("password");
		String username=request.getParameter("username");
	//	String rePWD=request.getParameter("repeatPWD");
	

		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
		String sql="select * from user where username=?";//设置预查询语句
		String sql1="insert into user value(?,?,?,?)";
		PreparedStatement prstmt=null;
		
		try{
			prstmt=conn.prepareStatement(sql);
			prstmt.setString(1, username);//在第一个问号的地方填入username
			ResultSet rs=prstmt.executeQuery();//执行 （executeUpdate）
			if(rs.next()){
				System.out.println("existing name");
				response.setContentType("text/html");
				response.getWriter().write("false");
			}
			else{
				prstmt=conn.prepareStatement(sql1);
				prstmt.setString(1, username);
				prstmt.setString(2, pwd);
				prstmt.setString(3, "1000");//1000的初始金额
				prstmt.setString(4, "0");//初始等级1
				int result=0;
				result=prstmt.executeUpdate();
				if(result==0){
					System.out.println("regist failure!");
					response.sendRedirect("");
				}
				else{
					System.out.println("regist success!");
					response.setContentType("text/html");
					response.getWriter().write("true");
				}
			}
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		

		

		db.closeConn();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
