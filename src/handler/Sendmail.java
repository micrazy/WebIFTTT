package handler;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*�����ʼ�*/
public class Sendmail {
	public  void sendmail(String string1, String string2) {
		String smtp = "smtp.qq.com";// smtp������
		String from = "497183006@qq.com";// �ʼ���ʾ����
		String to = string1;// �ռ��˵��ʼ���ַ����������ʵ��ַ
		String copyto = "";// �������ʼ���ַ
		String subject = "�����ʼ�";// �ʼ�����
		String content = string2;// �ʼ�����
		String username = "JAVAIFTTT";// ��������ʵ���˻���
		String password = "lkjzxc.1224";// ����������
		Mail.sendAndCc(smtp, from, to, copyto, subject, content, username,
				password);
	}
}
