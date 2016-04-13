package handler;

import java.util.Date;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
/*监听微博*/
public class MonWeibo {
	//static Date date1;
	//static Weibo weibo;

	public MonWeibo(){
		 //date1 = new Date();

	}
	public  boolean monstart(String accessToken, String id, String key)//监听微博内容
			throws WeiboException {
		Date date1 = new Date();
		 Weibo weibo = new Weibo();
		 weibo.setToken(accessToken);
		//weibo.setToken("2.00o_D6zFPHBLOE99069576a60TnRpL");
		Timeline timeline = new Timeline();
		// timeline.setToken(accessToken);
		// 2.00o_D6zFPHBLOE99069576a60TnRpL
		//while (true) {
		Status status;
			status = timeline.getUserTimelineByName(id).getStatuses()
					.get(0);
			if (status.getCreatedAt().after(date1)) {
				String temp = status.getText();

				//System.out.println("新的微博");
				//System.out.println(temp);
				// System.exit(0);
				if (temp.contains(key)) {
					// System.out.println("含有指定内容");
					return true;
					// System.exit(0);
				}
				date1 = new Date();
			//}
			// System.out.println(status.getCreatedAt());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
			return false;
	}

	public boolean timestart(String accessToken, String id, long waitTime)
			throws WeiboException {//监听没有发微博的时间
		long startTime = System.currentTimeMillis(); // 获取开始时间
		//weibo.setToken("2.00o_D6zFPHBLOE99069576a60TnRpL");
		Weibo weibo = new Weibo();
		 weibo.setToken(accessToken);
		Timeline timeline = new Timeline();
		// timeline.setToken(accessToken);
		// 2.00o_D6zFPHBLOE99069576a60TnRpL
			Status status = timeline.getUserTimelineByName(id).getStatuses()
					.get(0);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			if ((endTime - startTime) >= waitTime*1000) {
				System.out.println("等待时间过长！");
				return true;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return false;
	}
	
	public static void main(String[] args) {
		MyThread1 mythread = new MyThread1();
		mythread.start();
	}
	
}
class MyThread1 extends Thread {
	public void run() {
		MonWeibo m = new MonWeibo();
		try {
			m.monstart(" ", "pigletZzz", "?");
		} catch (WeiboException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}