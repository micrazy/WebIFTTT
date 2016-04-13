package handler;

import java.util.Date;
import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
/*发送微博*/
public class SendWeibo {
	public SendWeibo(String id, String content) {
		// TODO Auto-generated method stub
		Weibo weibo = new Weibo();
		String access_token =id;
		weibo.setToken(access_token);
		Timeline tm = new Timeline();
		try {
			Status status = tm.UpdateStatus(content);
		} catch (WeiboException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}