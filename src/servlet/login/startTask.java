package servlet.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TaskBean;
import Dao.taskDao;
import handler.Function;
import bean.TaskBean;

/**
 * Servlet implementation class startTask
 */
@WebServlet("/startTask")
public class startTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public startTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=(String)request.getParameter("username");
		int task_id=Integer.parseInt(request.getParameter("task_id"));
		System.out.println("task" + task_id + " is running");
		
		/*通过查找运行任务的编号，得到任务的信息*/
		TaskBean taskbean = new TaskBean();
		taskDao taskdao = new taskDao();
		taskbean = taskdao.query("task_id", task_id);
		/*开始任务开始扣除一定金额*/
		taskdao.start(username,task_id);
		/*调用handler类开始任务编号为task_id*/

		Function function=new Function(taskbean,task_id);
		
		System.out.println(taskbean.gettriggerID());
		response.sendRedirect("tasklist.jsp?username="+username);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
