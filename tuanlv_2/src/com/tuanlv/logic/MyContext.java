package com.tuanlv.logic;

import java.util.HashMap;
import java.util.Map;

import com.tuanlv.protocal.Response;

/**
 * 用于存储程序上下文
 * @author ITcms
 *
 */
public class MyContext {
	public static final String STATUS = "status";
	public static final String LOGIN_USERNAME = "username";
	public static final String LOGIN_PASSWORD = "password";
	public static final String REG_USERNAME = "username";
	public static final String REG_PASSWORD = "password";
	public static final String LOGOUT_USERNAME = "username";
	
	public String username;
	public String password;
	
	public Map<String, String> loginRequest;
	public Map<String, String> registerRequest;
	public Map<String, String> logoutRequest;
	
	
	public void loginUpdate(String username, String password){
		loginRequest = new HashMap<String, String>();
		loginRequest.put(MyContext.LOGIN_USERNAME, username);
		loginRequest.put(MyContext.LOGIN_PASSWORD, password);
	}
	public void registerUpdate(String username, String password){
		registerRequest = new HashMap<String, String>();
		registerRequest.put(MyContext.REG_USERNAME, username);
		registerRequest.put(MyContext.REG_PASSWORD, password);
	}
	public void logoutUpdate(String username){
		logoutRequest = new HashMap<String, String>();
		logoutRequest.put(MyContext.LOGOUT_USERNAME, username);
	}
	
	public void loginUpdate(Response rs){
		if(rs.type == Response.TYPE_LOGIN_RS){
			Map<String, String> loginRsItem = rs.response.get(0);
			String loginRsUsername = loginRsItem.get(Response.LOGIN_RS_USERNAME);
			if(loginRsUsername.equals(this.loginRequest.get(MyContext.LOGIN_USERNAME))){
				String status = loginRsItem.get(Response.STATUS);
				loginRequest.put(MyContext.STATUS, status);
			}
		}
	}
	public void registerUpdate(Response rs){
		if(rs.type == Response.TYPE_REGISTER_RS){
			Map<String, String> registerRsItem = rs.response.get(0);
			String loginRsUsername = registerRsItem.get(Response.REG_RS_USERNAME);
			if(loginRsUsername.equals(this.registerRequest.get(MyContext.REG_USERNAME))){
				String status = registerRsItem.get(Response.STATUS);
				registerRequest.put(MyContext.STATUS, status);
			}
		}
	}
	public void logoutUpdate(Response rs){
		if(rs.type == Response.TYPE_LOGOUT_RS){
			Map<String, String> logoutRsItem = rs.response.get(0);
			String loginRsUsername = logoutRsItem.get(Response.LOGOUT_RS_USERNAME);
			if(loginRsUsername.equals(this.logoutRequest.get(MyContext.LOGOUT_USERNAME))){
				String status = logoutRsItem.get(Response.STATUS);
				logoutRequest.put(MyContext.STATUS, status);
			}
		}
	}
	//根据响应来更新上下文
	public String update(Response rs){
		switch(rs.type){
		case Response.TYPE_LOGIN_RS:
			loginUpdate(rs);
			break;
		case Response.TYPE_REGISTER_RS:
			registerUpdate(rs);
			break;
		case Response.TYPE_LOGOUT_RS:
			logoutUpdate(rs);
			break;
		default:
			break;
		}
		return MyMessage.SUCCESS;
	}
}
