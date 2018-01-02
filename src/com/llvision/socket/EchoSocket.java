package com.llvision.socket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint("/echo/{userId}")
@ServerEndpoint("/echo")
public class EchoSocket {

	public EchoSocket() {
		System.out.println("EchoSocket.EchoSocket()");
	}

	// @OnOpen
	// public void open(Session session, @PathParam("userId") String userId) {
	// System.out.println("sessionId:" + session.getId() + "userId=" + userId);
	// }
	@OnOpen
	public void open(Session session) {
		System.out.println("sessionId:" + session.getId());
		System.out.println(session.getPathParameters());
		System.out.println(session.getQueryString());
	}

	@OnClose
	public void onclose(Session session) {
		System.out.println("sessionId:" + session.getId() + "通道关闭");

	}

	@OnMessage
	public void message(Session session, String msg, boolean last) {
		System.out.println(msg + session.getQueryString());
	}
}
