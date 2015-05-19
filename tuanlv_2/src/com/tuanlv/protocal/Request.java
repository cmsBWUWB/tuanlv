package com.tuanlv.protocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端发送给服务器端的对象
 * @author ITcms
 *
 */
public class Request implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String LOGIN_USERNAME = "username";
	public static final String LOGIN_PASSWORD = "password";
	public static final String REG_USERNAME = "username";
	public static final String REG_PASSWORD = "password";
	public static final String LOGOUT_USERNAME = "username";
	
	public static final int TYPE_LOGIN = 1;
	public static final int TYPE_REGISTER = 2;
	public static final int TYPE_LOGOUT = 3;
	
	public int type;
	public ArrayList<Map<String, String>> request = 
			new ArrayList<Map<String, String>>();
	
	
	public static Request login(String username, String password){
		Request rq = new Request();
		rq.type = Request.TYPE_LOGIN;
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put(Request.LOGIN_USERNAME, username);
		loginInfo.put(Request.LOGIN_PASSWORD, password);
		rq.request.add(loginInfo);
		return rq;
	}
	public static Request register(String username, String password){
		Request rq = new Request();
		rq.type = Request.TYPE_REGISTER;
		Map<String, String> registerInfo = new HashMap<String, String>();
		registerInfo.put(Request.REG_USERNAME, username);
		registerInfo.put(Request.REG_PASSWORD, password);
		rq.request.add(registerInfo);
		return rq;
	}
	public static Request logout(String username){
		Request rq = new Request();
		rq.type = Request.TYPE_LOGOUT;
		Map<String, String> logoutInfo = new HashMap<String, String>();
		logoutInfo.put(Request.LOGOUT_USERNAME, username);
		rq.request.add(logoutInfo);
		return rq;
	}
}
