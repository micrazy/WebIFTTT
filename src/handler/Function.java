package handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import bean.TaskBean;
import Dao.taskDao;
/*ʵ�ָ�������Ĺ���*/
public class Function {
	private  int taskID;
	private  int Trigger;
	private  int Action;
	private  Date triggerTime;
	private  String triggerID;
	private  String triggerPassword;
	private  String ActionID;
	private  String ActionPassword;
	private  String contents;

	public Function(TaskBean newtask, int taskid) {//��ʼ���ಢ���������̲߳�����
		Trigger = newtask.getTrigger();
		Action = newtask.getAction();
		if (Trigger == 1 && Action == 1)
			taskID = 0;
		else if (Trigger == 1 && Action == 2)
			taskID = 2;
		else if (Trigger == 2 && Action == 1)
			taskID = 1;
		else if (Trigger == 2 && Action == 2)
			taskID = 3;
		else if (Trigger == 3 && Action == 1)
			taskID = 4;
		else if (Trigger == 3 && Action == 2)
			taskID = 5;
		else if (Trigger == 4)
			taskID = 6;

		System.out.println("taskID" + taskID);

		if (taskID == 0 || taskID == 2) {//thisΪʱ��
			java.text.SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String s = newtask.gettriggerTime();
			try {
				triggerTime = formatter.parse(s);
				System.out.println(triggerTime+"here at 54");
			} catch (ParseException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

		triggerID = newtask.gettriggerID();
		triggerPassword = newtask.gettriggerPassword();
		ActionID = newtask.getActionID();
		ActionPassword = newtask.getActionPassword();
		contents = newtask.getContents();
		System.out.println(triggerID + " " + triggerPassword + " " + ActionID
				+ " " + ActionPassword + " " + contents);
		int i = 0;
		for (; i < Threadlist.threadnum; i++) {
			if (Threadlist.tasklist.get(i).taskid == taskid) {
				MyThread mythread = new MyThread(taskID,Trigger,Action,triggerTime,triggerID,
						triggerPassword,ActionID,ActionPassword,contents);
				Threadlist.threadlist.set(Threadlist.tasklist.get(i).threadid,
						mythread);
				mythread.start();
				break;
			}
		}
		if (i == Threadlist.threadnum) {
			MyThread mythread = new MyThread(taskID,Trigger,Action,triggerTime,triggerID,
					triggerPassword,ActionID,ActionPassword,contents);
			Tasklist m = new Tasklist(Threadlist.threadnum, taskid);
			Threadlist.tasklist.add(m);
			Threadlist.threadlist.add(mythread);
			mythread.start();
			Threadlist.threadnum++;
			System.out.println("threadid" + (Threadlist.threadnum - 1)
					+ "taskid:" + taskid);
		}
	}

	/*
	 * public static void main(String[] args) { TaskBean TaskBean = new
	 * TaskBean(); taskDao taskdao = new taskDao(); TaskBean =
	 * taskdao.query("task_id", 1); Function function=new Function(TaskBean); }
	 */

	public static void stop(int task) {//ֹͣ���е������߳�
		// TODO �Զ����ɵķ������
		int i;
		for (i = 0; i < Threadlist.threadnum; i++) {
			if (Threadlist.tasklist.get(i).taskid == task) {
				Threadlist.threadlist.get(Threadlist.tasklist.get(i).threadid)
						.stop();
				System.out.println("�߳��Ѿ�ֹͣ��");
				break;
			}
		}
		if (i == Threadlist.threadnum) {
			System.out.println("û���ҵ���Ӧ���̣߳�");
		}
	}

}

class MyThread extends Thread {//�߳��࣬�����������Ͳ�ͬ���в�ͬ�Ĺ���
	private  int taskID;
	private  int Trigger;
	private  int Action;
	private  Date triggerTime;
	private  String triggerID;
	private  String triggerPassword;
	private  String ActionID;
	private  String ActionPassword;
	private  String contents;
	public MyThread(int taskID,int Trigger,int Action,Date triggerTime,String triggerID,
			String triggerPassword,String ActionID,String ActionPassword,String contents){
		this.taskID=taskID;
		this.Trigger=Trigger;
		this.Action=Action;
		this.triggerTime=triggerTime;
		this.triggerID=triggerID;
		this.triggerPassword=triggerPassword;
		this.ActionID=ActionID;
		this.ActionPassword=ActionPassword;
		this.contents=contents;
		System.out.println(triggerID + " " + triggerPassword + " " + ActionID
				+ " " + ActionPassword + " " + contents);
	}
	public void run() {
	
		switch (taskID) {
		case 0: {

			Date date1 = new Date();
			if (triggerTime.compareTo(date1) == -1) {
				System.out.println("��ʱ�ѹ���");
			} else {
				while (true) {
					date1 = new Date();
					if (triggerTime.compareTo(date1) == -1
							|| triggerTime.compareTo(date1) == 0) {
						Sendmail send = new Sendmail();
						send.sendmail(ActionID, contents);
						break;
					}
				}
			}
			System.out.println("runs");
			break;
		}
		case 1: {
			System.out.println(triggerID + " "
					+ triggerPassword + " " + ActionID);
			ReceiveMail receivemail=new ReceiveMail();
			try {
				receivemail.init(triggerID,triggerPassword);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			while (true) {

				boolean s = true;
				try {
					s = receivemail.receive(triggerID,
							triggerPassword);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (!s) {
					Sendmail send = new Sendmail();
					send.sendmail(ActionID,contents);
				}

			}
			// break;
		}
		case 2: {
			Date date1 = new Date();
			System.out.println("hahah"+triggerTime);
			if (triggerTime.compareTo(date1) == -1) {
				System.out.println("��ʱ�ѹ���");
			} else {
				while (true) {
					date1 = new Date();
					if (triggerTime.compareTo(date1) == -1
							|| triggerTime.compareTo(date1) == 0) {
						SendWeibo send = new SendWeibo(ActionPassword,
								contents);
						System.out.println("���ͳɹ���");
						break;
					}
				}
			}
			break;
		}
		case 3: {
			ReceiveMail receivemail=new ReceiveMail();
			try {
				receivemail.init(triggerID, triggerPassword);
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			while (true) {
				boolean s = true;
				try {
					s = receivemail.receive(triggerID,
							triggerPassword);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (!s) {
					SendWeibo send = new SendWeibo(ActionPassword,
							contents);
					System.out.println("���ͳɹ���");
				}
			}
			// break;
		}
		case 4: {
			MonWeibo m = new MonWeibo();
			while (true) {

				try {
					MyThread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

				try {
					if (m.monstart(ActionPassword, triggerID,
							triggerPassword)) {
						Sendmail send = new Sendmail();
						send.sendmail(ActionID, contents);
					}
				} catch (WeiboException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			} // break;
		}

		case 5: {
			MonWeibo m = new MonWeibo();
			while (true) {

				try {
					MyThread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

				try {
					if (m.monstart(ActionPassword, triggerID,
							triggerPassword)) {
						SendWeibo send = new SendWeibo(ActionPassword,
								contents);
						System.out.println("���ͳɹ���");
					}
				} catch (WeiboException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			} // break;
		}

		case 6: {
			MonWeibo m = new MonWeibo();
			long l = Long.parseLong(triggerPassword);
			while (true) {

				try {
					MyThread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

				try {
					if (m.timestart(ActionPassword,
							triggerID, l)) {
						Sendmail send = new Sendmail();
						send.sendmail(ActionID, contents);
						break;
					}
				} catch (WeiboException e) { // TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}  
			break;
		}

		default:
			break;
		}
	}
}