package servlet.login;

import bean.DBAcess;

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

@WebServlet(name="CheckServlet",urlPatterns="/login")

public class CheckAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String pwd=request.getParameter("password");
			String username=request.getParameter("username");
			DBAcess db=new DBAcess();//ʵ�������ݿ�������
			Connection conn=db.getConn();//��ȡ����
			String sql="select password from USER where user=?";//����Ԥ��ѯ���
			PreparedStatement prstmt=null;
			String rightpwd="";
			try{
				prstmt=conn.prepareStatement(sql);
				prstmt.setString(1, username);//�ڵ�һ���ʺŵĵط�����username
				ResultSet rs=prstmt.executeQuery();//ִ�� ��executeUpdate��
				while(rs.next()){
					rightpwd=rs.getString("password");//ȡ����
							
				}
			}catch(SQLException e){
				
				e.printStackTrace();
			}
			
			if(!pwd.isEmpty()&&rightpwd.equals(pwd)){//��ֹ���ֲ���������������ò����ڵ����
				try{
					request.getRequestDispatcher("jsp/logSuccess.jsp").forward(request, response);
				}catch(ServletException e){
					e.printStackTrace();
				}
			}
			else{
				System.out.println("password is wrong!");
				response.sendRedirect("");
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
