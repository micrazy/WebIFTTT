package servlet.login;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Dao.taskDao;
import handler.Auth_token;
import bean.TaskBean;

/**
 * Servlet implementation class newTask
 */
@WebServlet("/newTask")
public class newTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean success=false;
		String username=(String)request.getParameter("username");
		TaskBean newTask=new TaskBean();
		taskDao taskDao=new taskDao();
		int trigger=Integer.parseInt(request.getParameter("trigger"));
		int action=Integer.parseInt(request.getParameter("action"));
		newTask.setTrigger(Integer.parseInt(request.getParameter("trigger")));
	    newTask.setAction(Integer.parseInt(request.getParameter("action")));
	    newTask.settriggerTime((String)(request.getParameter("trigger_time")));
	    newTask.settriggerID((String)request.getParameter("trigger_id"));
	    newTask.settriggerPassword((String)request.getParameter("trigger_pwd"));
	    newTask.setActionID((String)request.getParameter("action_id"));

	    newTask.setContents((String)request.getParameter("content"));
	    

	   String ActionPassword=(String)request.getParameter("action_pwd"); 
		
	
		
		if(trigger==3||trigger==4||action==2)
			ActionPassword=handler.Auth_token.getAccessToken().getAccessToken();
		
		System.out.println("haah");
	    newTask.setActionPassword(ActionPassword);
		
		
	    
		try{
			taskDao.insert(newTask, username);
		}
		catch(Exception e){
			e.printStackTrace();
			success=false;
		}
		success=true;
		if(success){
			response.sendRedirect("tasklist.jsp?username="+username);
		}
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
