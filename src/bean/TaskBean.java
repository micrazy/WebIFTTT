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
	private int Trigger;//��������� 0��ʱ��   1���յ��ʼ�  2:����΢�� 
	private int Action;//�������   0����΢�� 1�����ʼ�
	private String triggerTime;
	private String triggerID;
	private String triggerPassword;
	private String ActionID;
	private String ActionPassword;
	private String contents;//����
}

