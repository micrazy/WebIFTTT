package bean;

public class TaskBean {

	public int getTrigger(){
		return Trigger;
	}
	public void setTrigger(int Trigger){
		this.Trigger = Trigger;
	}
	public int getAction(){
		return Action;
	}
	public void setAction(int Action){
		this.Action = Action;
	}
	public String gettriggerTime(){
		return triggerTime;
	}
	public void settriggerTime(String triggerTime){
		this.triggerTime = triggerTime;
	}
	public String gettriggerID(){
		return triggerID;
	}
	public void settriggerID(String triggerID){
		this.triggerID = triggerID;
	}
	public String gettriggerPassword(){
		return triggerPassword;
	}
	public void settriggerPassword(String triggerPassword){
		this.triggerPassword = triggerPassword;
	}
	public String getActionID(){
		return ActionID;
	}
	public void setActionID(String ActionID){
		this.ActionID = ActionID;
	}
	public String getActionPassword(){
		return ActionPassword;
	}
	public void setActionPassword(String ActionPassword){
		this.ActionPassword = ActionPassword;
	}
	public String getContents(){
		return contents;
	}
	public void setContents(String contents){
		this.contents = contents;
	}
	private int Trigger;//触发器编号 0：时间   1：收到邮件  2:监听微博 
	private int Action;//动作编号   0：发微博 1：发邮件
	private String triggerTime;
	private String triggerID;
	private String triggerPassword;
	private String ActionID;
	private String ActionPassword;
	private String contents;//内容
}

