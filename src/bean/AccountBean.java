package bean;


public class AccountBean {
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance(){
		return balance;
	}
	public void setBalance(int balance){
		this.balance=balance;
	}
	public int getGrade(){
		return grade;
	}
	public void setGrade(int grade){
		this.grade=grade;
	}
	String username;
	String password;
	int balance;
	int grade;
}
