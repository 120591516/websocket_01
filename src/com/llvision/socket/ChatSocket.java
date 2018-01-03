package com.llvision.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.llvision.vo.ContentVo;
import com.llvision.vo.Message;


@ServerEndpoint("/chatSocket")
public class ChatSocket {

	
	private  static  Set<ChatSocket>  sockets=new HashSet<ChatSocket>();
	private  static  Map<String, Session>  map=new HashMap<String, Session>();
	private  static  List<String>   names=new ArrayList<String>();
	private  Session  session;
	private String username;
	private static Gson  gson=new Gson();
	
	@OnOpen
	public  void open(Session  session){
			this.session=session;
			sockets.add(this);
			session.getQueryString();
			
			Map<String, List<String>>  queryString = session.getRequestParameterMap();
			String userName = queryString.get("username").get(0);
			System.out.println(queryString);
			this.username = userName;
			names.add(this.username);
			this.map.put(this.username, session);
			
			Message   message=new Message();
			message.setAlert("<font color=red>"+this.username+"</font>进入聊天室！！");
			message.setNames(names);
			
			broadcast(sockets, gson.toJson(message) );
			
	}
	@OnMessage
	public  void receive(Session  session,String msg ){
		ContentVo contentVo = gson.fromJson(msg, ContentVo.class);
		if(contentVo.getType()==1){
			//广播
			Map<String, List<String>> map = session.getRequestParameterMap();
			List<String> list = map.get("username");
			System.out.println(list.get(0));
			System.out.println();
			Message  message=new Message();
			message.setSendMsg(contentVo.getMsg());
			message.setFrom(this.username);
			message.setDate(new Date().toLocaleString());
			
			broadcast(sockets, gson.toJson(message));
		}else{
			//私聊
			String username = contentVo.getTo();
			Session to_session = this.map.get(username);
			Message  message=new Message();
			message.setSendMsg("<font color='red'> 私聊："+contentVo.getMsg()+"</font>");
			message.setFrom(this.username);
			message.setDate(new Date().toLocaleString());
			try {
				to_session.getBasicRemote().sendText(gson.toJson(message));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Map<String, List<String>> map = session.getRequestParameterMap();
//		List<String> list = map.get("username");
//		System.out.println(list.get(0));
//		System.out.println();
//		Message  message=new Message();
//		message.setSendMsg(msg);
//		message.setFrom(this.username);
//		message.setDate(new Date().toLocaleString());
//		
//		broadcast(sockets, gson.toJson(message));
	}
	
	@OnClose
	public  void close(Session session){
		sockets.remove(this);
		names.remove(this.username);
		
		Message   message=new Message();
		message.setAlert(this.username+"退出聊天室！！");
		message.setNames(names);
		
		broadcast(sockets, gson.toJson(message));
	}
	
	public void broadcast(Set<ChatSocket>  ss ,String msg ){
		
		for (Iterator iterator = ss.iterator(); iterator.hasNext();) {
			ChatSocket chatSocket = (ChatSocket) iterator.next();
			try {
				chatSocket.session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
