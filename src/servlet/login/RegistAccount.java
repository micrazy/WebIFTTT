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
		String rePWD=request.getParameter("repeatPWD");
	//检测输入是否合法
		if(username.isEmpty()){
			System.out.println("Empty username!");
			response.sendRedirect("");
		}
		if(pwd.isEmpty()){
			System.out.println("Empty password!");
			response.sendRedirect("");
		}
		if(rePWD.isEmpty()){
			System.out.println("Empty repeatPWD!");
			response.sendRedirect("");
		}
		if(!pwd.equals(rePWD)){
			System.out.println("Different PWD");
			response.sendRedirect("");
		}
		
		
		DBAcess db=new DBAcess();//实例化数据库连接类
		Connection conn=db.getConn();//获取连接
		String sql="select * from USER where user=?";//设置预查询语句
		String sql1="insert into USER value(?,?,?,?)";
		PreparedStatement prstmt=null;
		
		try{
			prstmt=conn.prepareStatement(sql);
			prstmt.setString(1, username);//在第一个问号的地方填入username
			ResultSet rs=prstmt.executeQuery();//执行 （executeUpdate）
			if(rs.next()){
				System.out.println("existing name");
				response.sendRedirect("");
			}
			else{
				prstmt=conn.prepareStatement(sql1);
				prstmt.setString(1, null);
				prstmt.setString(2, username);
				prstmt.setString(3, pwd);
				prstmt.setString(4, "");
				int result=0;
				result=prstmt.executeUpdate();
				if(result==0){
					System.out.println("regist failure!");
					response.sendRedirect("");
				}
				else{
					System.out.println("regist success!");
					response.sendRedirect(request.getContextPath()+"/index.jsp");
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
