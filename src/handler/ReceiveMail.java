package handler;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
/*判读是否接收到邮件*/
/**
 * 有一封邮件就需要建立一个ReciveMail对象
 */
public class ReceiveMail {
	private  int old;

	/*
	 * public static void main(String[] args){ try { init("",""); } catch
	 * (Exception e) { // TODO 自动生成的 catch 块 e.printStackTrace(); } }
	 */
	public  void init(String popUser, String popPassword)
			throws Exception {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.socketFactory.port", "995");

		Session session = Session.getDefaultInstance(props, null);
		URLName urln = new URLName("pop3", "pop.qq.com", 995, null, popUser,
				popPassword);
		Store store = session.getStore(urln);
		store.connect();
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		Message message[] = folder.getMessages();
		System.out.println("Messages's length: " + message.length);
		old = message.length;
	}

	public  boolean receive(String popUser, String popPassword)
			throws Exception {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.socketFactory.port", "995");

		Session session = Session.getDefaultInstance(props, null);
		URLName urln = new URLName("pop3", "pop.qq.com", 995, null, popUser,
				popPassword);
		Store store = session.getStore(urln);
		store.connect();
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		Message message[] = folder.getMessages();
		System.out.println("Messages's length: " + message.length);
		if (message.length > old){
			old=message.length;
			return false;
		}
		return true;

	}
}