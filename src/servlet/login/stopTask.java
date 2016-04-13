package servlet.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.taskDao;
import bean.TaskBean;
import handler.Function;

/**
 * Servlet implementation class stopTask
 */
@WebServlet("/stopTask")
public class stopTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stopTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task_id=Integer.parseInt(request.getParameter("task_id"));
		String username=(String)request.getParameter("username");
		System.out.println("task"+task_id+"is running");
		TaskBean taskBean=new TaskBean();
		taskDao taskDao=new taskDao();
		taskBean=taskDao.query("task_id",task_id);
		Function.stop(task_id);
		response.sendRedirect("tasklist.jsp?username="+username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
