package com.tuanlv.protocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器端返回给客户端的响应
 * @author ITcms
 *
 */
public class Response implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	//下面定义response内容中的key字段常量
	public static final String LOGIN_RS_USERNAME = "username";
	public static final String REG_RS_USERNAME = "username";
	public static final String LOGOUT_RS_USERNAME = "username";
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAILED = "failed";
	public static final String STATUS_WAITING = "waiting";
	//下面定义响应类型常量
	public static final int TYPE_LOGIN_RS = 1;
	public static final int TYPE_REGISTER_RS = 2;
	public static final int TYPE_LOGOUT_RS = 3;
	
	public int type;
	public ArrayList<Map<String, String>> response = 
			new ArrayList<Map<String, String>>();
	
	//生成Response对象
	public static Response loginRs(String username, String status, String message){
		Response rs = new Response();
		rs.type = Response.TYPE_LOGIN_RS;
		Map<String, String> loginRsItem = new HashMap<String, String>();
		loginRsItem.put(Response.LOGIN_RS_USERNAME, username);
		loginRsItem.put(Response.STATUS, status);
		loginRsItem.put(Response.MESSAGE, message);
		rs.response.add(loginRsItem);
		
		return rs;
	}
	public static Response registerRs(String username, String status, String message){
		Response rs = new Response();
		rs.type = Response.TYPE_REGISTER_RS;
		Map<String, String> registerRsItem = new HashMap<String, String>();
		registerRsItem.put(Response.REG_RS_USERNAME, username);
		registerRsItem.put(Response.STATUS, status);
		registerRsItem.put(Response.MESSAGE, message);
		rs.response.add(registerRsItem);
		
		return rs;
	}
	public static Response logoutRs(String username, String status, String message){
		Response rs = new Response();
		rs.type = Response.TYPE_LOGOUT_RS;
		Map<String, String> logoutRsItem = new HashMap<String, String>();
		logoutRsItem.put(Response.LOGOUT_RS_USERNAME, username);
		logoutRsItem.put(Response.STATUS, status);
		logoutRsItem.put(Response.MESSAGE, message);
		rs.response.add(logoutRsItem);
		
		return rs;
	}
}
