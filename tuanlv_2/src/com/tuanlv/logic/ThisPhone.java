package com.tuanlv.logic;

import java.util.ArrayList;

import com.tuanlv.protocal.Request;
import com.tuanlv.protocal.Response;

/**
 * 用于主要功能的实现
 * @author ITcms
 *
 */
public class ThisPhone {
	public static MyContext myContext = new MyContext();
	public static ArrayList<Response> responseList = new ArrayList<Response>();
	/**
	 * 执行登录操作
	 */
	public static String login(String username, String password){
		//生成登录请求
		Request rq = Request.login(username, password);
		//发送请求
		String result = SocketControl.sendRequest(rq);
		//如果发送成功，则修改上下文表示正在登录
		if(result.equals(MyMessage.SUCCESS)){
			myContext.loginUpdate(username, password);
		}
		return result;
	}
	/**
	 * 执行注册操作
	 * @param username
	 * @param password
	 * @return
	 */
	public static String register(String username, String password){
		Request rq = Request.register(username, password);
		
		String result = SocketControl.sendRequest(rq);
		if(result.equals(MyMessage.SUCCESS)){
			myContext.registerUpdate(username, password);
		}
		
		return result;
	}
	public static String logout(String username){
		Request rq = Request.logout(username);
		
		String result = SocketControl.sendRequest(rq);
		//如果发送成功，则修改上下文表示正在登录
		if(result.equals(MyMessage.SUCCESS)){
			myContext.logoutUpdate(username);
		}
		return result;
	}
	/**
	 * 处理响应，并更新上下文
	 * @return
	 */
	public static String dealResponse(){
		while(responseList != null && responseList.size() != 0){
			Response currentResponse = responseList.remove(0);
			//对响应进行处理
			myContext.update(currentResponse);
		}
		return MyMessage.SUCCESS;
	}
	public static boolean isConn(){
		return SocketControl.getConnStatus();
	}
	public static String conn(){
		return SocketControl.conn();
	}
	public static String desConn(){
		return SocketControl.disConn();
	}
	public static String recieveDataFromServer(){
		Response rs = SocketControl.recieveResponse();
		if(rs == null){
			return MyMessage.ERROR;
		} else{
			responseList.add(rs);
			return MyMessage.SUCCESS;
		}
	}
}
