package handler;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*发送邮件*/
public class Sendmail {
	public  void sendmail(String string1, String string2) {
		String smtp = "smtp.qq.com";// smtp服务器
		String from = "497183006@qq.com";// 邮件显示名称
		String to = string1;// 收件人的邮件地址，必须是真实地址
		String copyto = "";// 抄送人邮件地址
		String subject = "测试邮件";// 邮件标题
		String content = string2;// 邮件内容
		String username = "JAVAIFTTT";// 发件人真实的账户名
		String password = "lkjzxc.1224";// 发件人密码
		Mail.sendAndCc(smtp, from, to, copyto, subject, content, username,
				password);
	}
}
