package handler;

import java.util.Date;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
/*����΢��*/
public class MonWeibo {
	//static Date date1;
	//static Weibo weibo;

	public MonWeibo(){
		 //date1 = new Date();

	}
	public  boolean monstart(String accessToken, String id, String key)//����΢������
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

				//System.out.println("�µ�΢��");
				//System.out.println(temp);
				// System.exit(0);
				if (temp.contains(key)) {
					// System.out.println("����ָ������");
					return true;
					// System.exit(0);
				}
				date1 = new Date();
			//}
			// System.out.println(status.getCreatedAt());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
			return false;
	}

	public boolean timestart(String accessToken, String id, long waitTime)
			throws WeiboException {//����û�з�΢����ʱ��
		long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
		//weibo.setToken("2.00o_D6zFPHBLOE99069576a60TnRpL");
		Weibo weibo = new Weibo();
		 weibo.setToken(accessToken);
		Timeline timeline = new Timeline();
		// timeline.setToken(accessToken);
		// 2.00o_D6zFPHBLOE99069576a60TnRpL
			Status status = timeline.getUserTimelineByName(id).getStatuses()
					.get(0);
			long endTime = System.currentTimeMillis(); // ��ȡ����ʱ��
			if ((endTime - startTime) >= waitTime*1000) {
				System.out.println("�ȴ�ʱ�������");
				return true;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}